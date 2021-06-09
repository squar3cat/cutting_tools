package com.app.tools.web.tool.location;

import com.app.tools.View;
import com.app.tools.model.Location;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/profile/tools/locations", produces = MediaType.APPLICATION_JSON_VALUE)
public class LocationUIController extends AbstractLocationController {

    @Override
    @GetMapping( "/{id}")
    @JsonView(View.JsonUI.class)
    public Location get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @GetMapping
    @JsonView(View.JsonUI.class)
    public List<Location> getAll() {
        return super.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createOrUpdate(@Valid Location location) {
        if (location.isNew()) {
            super.create(location);
        } else {
            super.update(location, location.getId());
        }
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

}