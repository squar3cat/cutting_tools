package com.app.tools.service;

import com.app.tools.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;
import com.app.tools.testdata.UserTestData;
import com.app.tools.model.User;
import com.app.tools.util.exception.NotFoundException;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static com.app.tools.testdata.UserTestData.*;
import static com.app.tools.testdata.UserTestData.USER_ID;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("datajpa")
public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserService service;

    @Test
    void get() {
        User user = service.get(USER_ID);
        USER_MATCHER.assertMatch(user, UserTestData.user);
    }

    @Test
    void getAll() {
        List<User> all = service.getAll();
        USER_MATCHER.assertMatch(all, admin, editor, user);
    }

    @Test
    void getByName() {
        User user = service.getByName("admin");
        USER_MATCHER.assertMatch(user, admin);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    void create() {
        User created = service.create(getNew());
        int newId = created.id();
        User newUser = getNew();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(service.get(newId), newUser);
    }

    @Test
    void duplicateNameCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new User(null, "user", "newPass", Role.USER)));
    }

    @Test
    void delete() {
        service.delete(USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(USER_ID));
    }

    @Test
    void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    void update() {
        User updated = getUpdated();
        service.update(updated);
        USER_MATCHER.assertMatch(service.get(USER_ID), getUpdated());
    }

    @Test
    void enable() {
        service.enable(USER_ID, false);
        assertFalse(service.get(USER_ID).isEnabled());
        service.enable(USER_ID, true);
        assertTrue(service.get(USER_ID).isEnabled());
    }

    @Test
    void createWithException() {
        validateRootCause(() -> service.create(new User(null, "  ", "password", Role.USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "user", "  ", Role.USER)), ConstraintViolationException.class);
    }

    @Transactional
    @Test
    void getWithTools() {
        User admin = service.getWithTools(ADMIN_ID);
        USER_WITH_TOOLS_MATCHER.assertMatch(admin, UserTestData.admin);
    }

    @Test
    void getWithToolsNotFound() {
        Assertions.assertThrows(NotFoundException.class,
                () -> service.getWithTools(1));
    }
}