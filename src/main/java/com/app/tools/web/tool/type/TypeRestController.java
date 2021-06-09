package com.app.tools.web.tool.type;

import com.app.tools.model.Type;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = TypeRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class TypeRestController extends AbstractTypeController {
    static final String REST_URL = "/rest/profile/tools/types";

    @Override
    @GetMapping("/{id}")
    public Type get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @GetMapping
    public List<Type> getAll() {
        return super.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Type> createWithLocation(@Valid @RequestBody Type type) {
        Type created = super.create(type);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Type type, @PathVariable int id) {
        super.update(type, id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }







}