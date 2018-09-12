package demo.rest;

import demo.domain.Location;
import demo.service.LocationService;
import demo.service.impl.LocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class RunningUploadRestController {

    private LocationService locationService;  //todo: why not impl
    //private LocationServiceImpl locationServiceimpl;

    @Autowired
    public RunningUploadRestController (LocationService locationService) {this.locationService = locationService;}

    //rest最重要的anotation, 定义了位置, 方法和返回值
    //一定要记住!
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    //public void upLoad (List<Location> locations) {  //将包含所有location信息的JSON文件从前端传回
    public void upLoad (@RequestBody List<Location> locations) {  //和上面的区别: 因为我们要将locations作为body/payload发送给后端,所以加annotation
        locationService.saveRunningLocation(locations);
        //ilocationServiceimpl.saveRunningLocation(locations);
    }

    @RequestMapping(value = "/purge", method = RequestMethod.POST) //DELETE 也行
    //@ResponseStatus(HttpStatus.ACCEPTED) todo: 可否加返回值?
    public  void purge () {
        locationService.deleteAll();
    }

    //下面这个annotation理解:
    // @pathvariable是对应大括号{} 里的东西, 也就是说url通过movementType传入
    // @RequestParam意思是因为我们get请求要把参数都放进去, 所以
    // 最后出来的url可能是/localhost/portNum/running/STOOPED?page=2&size=1

    @RequestMapping(value = "/running/{movementType}", method = RequestMethod.GET)
    //public Page<Location> findByMovementType(String movementType, int page, int size){  //todo: name找不到 =>typo?
    public Page<Location> findByMovementType(@PathVariable String movementType, @RequestParam(name = "page") int page, @RequestParam(name = "size") int size){
        return locationService.findByRunnerMovementType(movementType, new PageRequest(page, size)); //todo pageable如何使用例子
    }

    @RequestMapping(value = "/running/runningId/{runningId}", method = RequestMethod.GET)
    public Page<Location> findByRunningId (@PathVariable String runningId, @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        return locationService.findByRunningId(runningId, new PageRequest(page, size));
    }
}

//这里因为我们要处理前端发来的json, 并且存入数据库, 并且要将之前domain里面的model用上, 所以我们对model做一些更改


//另外JPA只能应用于与RDB的通信, 如这里的H2, mongodb不用JPA