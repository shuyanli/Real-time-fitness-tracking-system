package demo.service.impl;

import demo.domain.Location;
import demo.domain.LocationRepository;
import demo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

//一个service想要被bean factory发现的话(因为要实现), 需要加anotation
//这个service主要实现location和后端的交互
@Service
public class LocationServiceImpl implements LocationService {
    //在业务逻辑中, 我们需要:
    //1, 拿到前端数据,放入service
    //2, 和数据库交互

    //将bin导入(inject), 需要1. 使用autowire的anotaion, 或者, 2, 构造方法的inject, 我们使用一下构造函数

    private LocationRepository locationRepository;  //db交互

    @Autowired
    public LocationServiceImpl (LocationRepository locationRepository) {this.locationRepository = locationRepository;}
    //注意上面这句, 我们直接把一个interface扔进了构造函数, 为什么这样是允许的?
    //因为JPA当我们调用这个interface的时候帮我们内部实现了它(如果我们查看locationrepo继承的父亲的父亲, 里面就有一个泛型实现(Page<T> findAll(Pageable p) )

    @Override
    public List<Location> saveRunningLocation (List<Location> runningLocation) { //实现接口
        return locationRepository.save(runningLocation); //父亲中有实现
    }


    @Override
    public void deleteAll() {
        locationRepository.deleteAll();
    }

    @Override
    public Page<Location> findByRunnerMovementType(String movementType, Pageable pageable) {
        return locationRepository.findByRunnerMovementType(Location.RunnerMovementType.valueOf(movementType), pageable);
    }  //小疑问:这个是怎么实现的, 这里并没有去实现啊, 还是call了interface?

    @Override
    public Page<Location> findByRunningId(String runningId, Pageable pageable) {
        return locationRepository.findByUnitInfoRunningId(runningId, pageable);
    }
}

//注意: all methods in interface need implmenetation, if you dont really want this method ,
// throw some variety of NotImplementedException