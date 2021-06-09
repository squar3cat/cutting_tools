package com.app.tools.service;

import com.app.tools.model.Location;
import com.app.tools.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.ConstraintViolationException;

import static com.app.tools.testdata.LocationTestData.LOCATION1_ID;
import static com.app.tools.testdata.LocationTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("datajpa")
public class LocationServiceTest extends AbstractServiceTest {

    @Autowired
    protected LocationService service;

    @Test
    void get() {
        Location actual = service.get(LOCATION1_ID);
        LOCATION_MATCHER.assertMatch(actual, location1);
    }

    @Test
    void getAll() {
        LOCATION_MATCHER.assertMatch(service.getAll(), locations);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    void delete() {
        service.delete(LOCATION1_ID);
        assertThrows(NotFoundException.class, () -> service.get(LOCATION1_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    void create() {
        Location created = service.create(getNew());
        int newId = created.id();
        Location newLocation = getNew();
        newLocation.setId(newId);
        LOCATION_MATCHER.assertMatch(created, newLocation);
        LOCATION_MATCHER.assertMatch(service.get(newId), newLocation);
    }

    @Test
    void update() {
        Location updated = getUpdated();
        service.update(updated);
        LOCATION_MATCHER.assertMatch(service.get(LOCATION1_ID), getUpdated());
    }

    @Test
    void createWithException(){
        validateRootCause(() -> service.create(new Location(null, null)), ConstraintViolationException.class);
    }

}
