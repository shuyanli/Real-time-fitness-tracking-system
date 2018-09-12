package demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

//对于service的开发, 通常使用interface来做
//然后再用一个class去具体实现

//注意: all methods in interface need implmenetation, if you dont really want this method ,
// throw some variety of NotImplementedException

@RepositoryRestResource(path = "Locations")  //直接暴露给外部
public interface LocationRepository extends JpaRepository<Location, Long> {
    //第一个参数是与数据库talk的java entity的类型, 即增删改查都用Location这个对象和数据库交互
    //第二个long 是在Location里id的数据类型(数据库中每个row都有id, primary key)

    @RestResource(path = "runners") //浏览器中localhost+port+ /Locations/runners, 前面引入 <artifactId>spring-data-rest-hal-browser</artifactId> 这个dependency支持浏览器主页浏览
    Page<Location> findByRunnerMovementType(Location.RunnerMovementType movenType, Pageable pageable );
    //写成query: select * from Location WHERE RunnerMovenType = movenType

    Page<Location> findByUnitInfoRunningId(String runningId, Pageable pageable);
}
