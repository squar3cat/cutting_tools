package com.app.tools.web.tool.location;

import com.app.tools.model.Location;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = LocationRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class LocationRestController extends AbstractLocationController {
    static final String REST_URL = "/rest/profile/tools/locations";

    @Override
    @GetMapping("/{id}")
    public Location get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @GetMapping
    public List<Location> getAll() {
        return super.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Location> createWithLocation(@Valid @RequestBody Location location) {
        Location created = super.create(location);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Location location, @PathVariable int id) {
        super.update(location, id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }







}