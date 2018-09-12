package demo.rest;

import demo.domain.SupplyLocation;
import demo.domain.SupplyLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SupplyLocationRestController {
    //因为这个service比较小, 就直接写业务逻辑了, 不再像前面单独写service然后在这里实现
    private SupplyLocationRepository repository;

    @Autowired
    private SupplyLocationRestController (SupplyLocationRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "bulk/supplyLocations", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload (@RequestBody List<SupplyLocation> locationList) {
        repository.save(locationList);
    }

    @RequestMapping(value = "/purge", method = RequestMethod.DELETE)
    public void delete () {
        repository.deleteAll();
    }
}
