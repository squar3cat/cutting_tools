package com.app.tools.web.tool.type;

import com.app.tools.View;
import com.app.tools.model.Type;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/profile/tools/types", produces = MediaType.APPLICATION_JSON_VALUE)
public class TypeUIController extends AbstractTypeController {

    @Override
    @GetMapping( "/{id}")
    @JsonView(View.JsonUI.class)
    public Type get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @GetMapping
    @JsonView(View.JsonUI.class)
    public List<Type> getAll() {
        return super.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createOrUpdate(@Valid Type type) {
        if (type.isNew()) {
            super.create(type);
        } else {
            super.update(type, type.getId());
        }
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

}