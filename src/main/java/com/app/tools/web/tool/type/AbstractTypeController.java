package com.app.tools.web.tool.type;

import com.app.tools.model.Type;
import com.app.tools.service.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.app.tools.util.TypeUtil.renderTypeHierarchy;
import static com.app.tools.util.ValidationUtil.assureIdConsistent;
import static com.app.tools.util.ValidationUtil.checkNew;

public abstract class AbstractTypeController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private TypeService service;

    public Type get(int id) {
        log.info("get type {}", id);
        return service.get(id);
    }

    public List<Type> getAll() {
        log.info("getAll types");
        return renderTypeHierarchy(service.getAll());
    }

    public Type create(Type type) {
        checkNew(type);
        log.info("create {}", type);
        return service.create(type);
    }

    public void update(Type type, int id) {
        assureIdConsistent(type, id);
        log.info("update {}", type);
        service.update(type);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

}