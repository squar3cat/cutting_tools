package com.app.tools.web.tool.location;

import com.app.tools.model.Location;
import com.app.tools.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.app.tools.util.ValidationUtil.assureIdConsistent;
import static com.app.tools.util.ValidationUtil.checkNew;

public abstract class AbstractLocationController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private LocationService service;

    public Location get(int id) {
        log.info("get location {}", id);
        return service.get(id);
    }

    public List<Location> getAll() {
        log.info("getAll locations");
        return service.getAll();
    }

    public Location create(Location location) {
        checkNew(location);
        log.info("create {}", location);
        return service.create(location);
    }

    public void update(Location location, int id) {
        assureIdConsistent(location, id);
        log.info("update {}", location);
        service.update(location);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

}