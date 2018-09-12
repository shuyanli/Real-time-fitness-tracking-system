package demo.service;


//对于java 的service, 都需要先定义一个抽象的interface, 然后写一个class去具体实现他或者他的某个方法
//这么做的好处: 方便扩展

import demo.domain.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface LocationService {
    List<Location> saveRunningLocation(List<Location> runningLocation);

    void deleteAll();

    Page<Location> findByRunnerMovementType(String movementType, Pageable pageable);

    Page<Location> findByRunningId(String runningId, Pageable pageable);




}
