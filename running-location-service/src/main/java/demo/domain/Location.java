package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data; //自动建立并隐藏private field的constructor
//import org.springframework.data.annotation.Id;  //todo: place goes wrong
//不知道为何他自动产生了这个, 导致@Id引自这里, 这是错的

import javax.persistence.*;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)  //作用: 将json文件中和model中名字一样的值map进model. 括号:只有非空field才会被map过来
@Entity //与数据库的交互, 必须把class变成entity, JPA才能将他转化成query
@Table(name = "LOCATIONS")  //建好的class object变成entity以后, 存入数据库中LOCATIONS这个table(下面的class名字无所谓)
@Data //lombok使用
public class Location {
    public enum GpsStatus{
        EXCELLENT, OK, UNRELIABLE, BAD, NOFIX, UNKNOWN;
    }

    public enum RunnerMovementType{
        STOPPED, IN_MOTION;
    }

    @Id  //数据库中primary key
    @GeneratedValue //随机生成这个id
    private long id;


    @Embedded //这里, Location嵌套了unitinfo 和medicalinfo,我们要让json知道有一个class in class的关系, 需要notation embedded
    @AttributeOverride( name = "bandMake", column = @Column(name = "unit_band_make"))
    private UnitInfo unitInfo;



    @Embedded
    //与前面@Table 的 table对应tablename类似, 这里将这两个private变量对应到table的column中的对应名字地方
    @AttributeOverrides({ //注意overide和overides
            @AttributeOverride(name = "fmi", column = @Column(name = "medical_fmi")),
            @AttributeOverride(name = "bfr", column = @Column(name = "medical_bfr"))
    })
    private MedicalInfo medicalInfo;


    private double latitude;
    private double longtitude;
    private String heading; //等到当前位置, 因为google api返回值是string而不是数字
    private double gpsSpeed;
    private GpsStatus gpsStatus;
    private double odometer;
    private double totalRunningTime;
    private double totalIdelTime;
    private double totalCalorieBurn;

    private String address;
    private Date timeStemp = new Date();
    private String gearProvider;
    private RunnerMovementType runnerMovementType;
    private String serviceType;

    //constructor
    //public Location () {} //这个不需要是因为系统已经自动定义并隐藏了这个空的constructor
    public Location () {this.unitInfo = null;}
    public Location (UnitInfo unitInfo) {this.unitInfo = unitInfo;}

    //传过来的json如果有个property叫"runningId"的话,就用传进来的这个rid新建一个unitInfo的instance
    //这个Jsonproperty是一个命名的转化, rid可以命名为任何, 但是引号内的会被传进去
    @JsonCreator  // when json pass into service, use this constructor
    public Location (@JsonProperty("runningId") String rid ) {this.unitInfo = new UnitInfo(rid);}

    public String getRunningId() {return this.unitInfo == null? null:this.unitInfo.getRunningId();} //这里没有implment ,但是因为使用了lombok, 自动穿件getter, setter

}
//接RunningUploadRestController:
// 这个data model 需要干两件事情:
// 第一, 前端发来的json文件可以translate成data model
// 第二, 此data model再向数据库存的时候, data model 能transfer成数据库所需要的data entity
// 所以需要要有支持者两部转化的对应function