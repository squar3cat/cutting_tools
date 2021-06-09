package com.app.tools.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.app.tools.model.User;
import com.app.tools.service.UserService;
import com.app.tools.dto.UserTo;
import com.app.tools.util.UserUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindException;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import com.app.tools.HasId;

import java.util.List;

import static com.app.tools.util.ValidationUtil.assureIdConsistent;
import static com.app.tools.util.ValidationUtil.checkNew;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected UserService service;

    @Autowired
    private UniqueNameValidator nameValidator;

    @Autowired
    @Qualifier("defaultValidator")
    private Validator validator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(nameValidator);
    }

    public User get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public User getByName(String name) {
        log.info("getByName {}", name);
        return service.getByName(name);
    }

    public User getWithTools(int id) {
        log.info("getWithTools {}", id);
        return service.getWithTools(id);
    }

    public User create(UserTo userTo) {
        log.info("create from to {}", userTo);
        return create(UserUtil.createNewFromTo(userTo));
    }

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        return service.create(user);
    }

    public void update(UserTo userTo, int id) {
        log.info("update {} with id={}", userTo, id);
        service.update(userTo);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void enable(int id, boolean enabled) {
        log.info(enabled ? "enable {}" : "disable {}", id);
        service.enable(id, enabled);
    }

    protected void validateBeforeUpdate(HasId user, int id) throws BindException {
        assureIdConsistent(user, id);
        DataBinder binder = new DataBinder(user);
        binder.addValidators(nameValidator, validator);
        binder.validate();
        if (binder.getBindingResult().hasErrors()) {
            throw new BindException(binder.getBindingResult());
        }
    }
}