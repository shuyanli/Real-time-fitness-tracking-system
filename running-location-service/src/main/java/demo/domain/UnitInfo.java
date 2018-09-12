package demo.domain;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Embeddable;

//ORM object relational mapping
// Json <-> Class Mapping. 将json转换成class, 并且在返回前端的时候, 将class转换会json
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Embeddable  //与Locaiton中embedded对应, 表示可以被embed
public class UnitInfo {

    private String runningId;  //手环id
    private String bandMake; //牌子
    private String customerName;
    private String unitNumber; //型号

    public UnitInfo() {
        this.runningId = "";
    }

    public UnitInfo (String rid) {
        this.runningId = rid;
    }

    public UnitInfo (String rid, String bm, String cn, String un) {
        this.runningId = rid;
        this.bandMake = bm;
        this.customerName = cn;
        this.unitNumber = un;
    }

}
