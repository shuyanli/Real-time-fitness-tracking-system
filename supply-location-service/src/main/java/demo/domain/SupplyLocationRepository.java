package demo.domain;

import org.springframework.data.geo.Point;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//对于service的开发, 通常使用interface来做
//然后再用一个class去具体实现

@RepositoryRestResource(path = "supplyLocations")
//上面这个annotation的意思: 对于很小的微服务, 我们可以把他直接提供给外部使用, 这样的话外部不需要通过我的controller, call一大堆东西
//这个annotation就是干这个的
//使用方法: 启动服务后, 直接localhost+port+这里定义的path, 相当于一个api
//问题: 为什么这里什么都没写调用方法, 但是postman能返回那么多数据? 答: 它集成的父类里面有一个findAll
//如果按照第一个service那样后面加上page and size, 则能返回单独的值
public interface SupplyLocationRepository extends PagingAndSortingRepository<SupplyLocation, String> { //用法和JPA类似
                    //这里的find...near是mongo里专门针对这种geolocation搜索的方法,是一种固定写法
    SupplyLocation findFirstByLocationNear(@Param("location")Point location); //todo 老师point用了jwt=>我的是对的
}
//todo: note: 之所以不需要我么你手动注入bean, 是因为
//我们extend的父类里面, 已经注入了bean containery