package com.app.tools.service;

import com.app.tools.model.Type;
import com.app.tools.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.ConstraintViolationException;

import static com.app.tools.testdata.TypeTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("datajpa")
public class TypeServiceTest extends AbstractServiceTest {

    @Autowired
    protected TypeService service;

    @Test
    void get() {
        Type actual = service.get(TYPE1_ID);
        TYPE_MATCHER.assertMatch(actual, type1);
    }

    @Test
    void getAll() {
        TYPE_MATCHER.assertMatch(service.getAll(), types);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    void delete() {
        service.delete(TYPE1_ID);
        assertThrows(NotFoundException.class, () -> service.get(TYPE1_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    void create() {
        Type created = service.create(getNew());
        int newId = created.id();
        Type newType = getNew();
        newType.setId(newId);
        TYPE_MATCHER.assertMatch(created, newType);
        TYPE_MATCHER.assertMatch(service.get(newId), newType);
    }

    @Test
    void update() {
        Type updated = getUpdated();
        service.update(updated);
        TYPE_MATCHER.assertMatch(service.get(TYPE1_ID), getUpdated());
    }

    @Test
    void createWithException(){
        validateRootCause(() -> service.create(new Type(null, null, 100006, 3, true)), ConstraintViolationException.class);

        validateRootCause(() -> service.create(new Type(null, "Фрезы монолитные", null, 3, true)), ConstraintViolationException.class);

        validateRootCause(() -> service.create(new Type(null, "Фрезы монолитные", 100006, null, true)), ConstraintViolationException.class);
    }

}
