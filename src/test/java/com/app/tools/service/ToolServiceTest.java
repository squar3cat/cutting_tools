package com.app.tools.service;

import com.app.tools.model.Location;
import com.app.tools.model.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import com.app.tools.testdata.UserTestData;
import com.app.tools.model.Tool;

import com.app.tools.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.Month;

import static com.app.tools.testdata.ToolTestData.*;
import static com.app.tools.testdata.UserTestData.ADMIN_ID;
import static com.app.tools.testdata.UserTestData.USER_ID;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("datajpa")
public class ToolServiceTest extends AbstractServiceTest {

    @Autowired
    protected ToolService service;

    @Test
    void get() {
        Tool actual = service.get(ADMIN_TOOL_ID);
        TOOL_MATCHER.assertMatch(actual, adminTool1);
    }

    @Test
    void getAll() {
        TOOL_MATCHER.assertMatch(service.getAll(), tools);
    }

    @Test
    void getBetween() {
        TOOL_MATCHER.assertMatch(service.getBetween(100011, 3,
                LocalDate.of(2020, Month.DECEMBER, 28),
                LocalDate.of(2021, Month.JANUARY, 5)),
                tool3);
    }

    //toolType is null
    @Test
    void getBetweenWithNullType() {
        TOOL_MATCHER.assertMatch(service.getBetween(0, 3,
                LocalDate.of(2020, Month.DECEMBER, 28),
                LocalDate.of(2021, Month.JANUARY, 5)),
                tool5, tool3);
    }

    //all fields of filter are null
    @Test
    void getBetweenWithNullFilters() {
        TOOL_MATCHER.assertMatch(service.getBetween(0,0, null, null), tools);
    }

    //startDate and endDate are null
    @Test
    void getBetweenWithNullDates() {
        TOOL_MATCHER.assertMatch(service.getBetween(100015,14, null,null), tool1);
    }

    //location is null
    @Test
    void getBetweenWithNullLocation() {
        TOOL_MATCHER.assertMatch(service.getBetween(100009, 0,
                LocalDate.of(2020, Month.DECEMBER, 28),
                LocalDate.of(2020, Month.DECEMBER, 30)), tool4);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    void delete() {
        service.delete(TOOL1_ID);
        assertThrows(NotFoundException.class, () -> service.get(TOOL1_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    void create() {
        Tool created = service.create(getNew(), USER_ID);
        int newId = created.id();
        Tool newTool = getNew();
        newTool.setId(newId);
        TOOL_MATCHER.assertMatch(created, newTool);
        TOOL_MATCHER.assertMatch(service.get(newId), newTool);
    }

    @Test
    void update() {
        Tool updated = getUpdated();
        service.update(updated, USER_ID);
        TOOL_MATCHER.assertMatch(service.get(TOOL1_ID), getUpdated());
    }

    @Test
    void createWithException(){

        //null registrationDate
        validateRootCause(() -> service.create(new Tool(null, null, "Description", 1, "Sandvik", new Location(3, "БИХ 103"), 1, new Type(100011, "Корпуса фрез", 100010, 3, true)), USER_ID), ConstraintViolationException.class);

        //empty description
        validateRootCause(() -> service.create(new Tool(null, LocalDate.of(2021, Month.JUNE, 1), "  ", 1, "Sandvik", new Location(3, "БИХ 103"), 1, new Type(100011, "Корпуса фрез", 100010, 3, true)), USER_ID), ConstraintViolationException.class);

        //toolsCount out of bounds
        validateRootCause(() -> service.create(new Tool(null, LocalDate.of(2021, Month.JUNE, 1), "Description", 100001, "Sandvik", new Location(3, "БИХ 103"), 1, new Type(100011, "Корпуса фрез", 100010, 3, true)), USER_ID), ConstraintViolationException.class);

        //empty manufacturer
        validateRootCause(() -> service.create(new Tool(null, LocalDate.of(2021, Month.JUNE, 1), "Description", 1, "  ", new Location(3, "БИХ 103"), 1, new Type(100011, "Корпуса фрез", 100010, 3, true)), USER_ID), ConstraintViolationException.class);

        //null location
        validateRootCause(() -> service.create(new Tool(null, LocalDate.of(2021, Month.JUNE, 1), "Description", 1, "Sandvik", null, 1, new Type(100011, "Корпуса фрез", 100010, 3, true)), USER_ID), ConstraintViolationException.class);

        //null deficiency
        validateRootCause(() -> service.create(new Tool(null, LocalDate.of(2021, Month.JUNE, 1), "Description", 1, "Sandvik", new Location(3, "БИХ 103"), 0, new Type(100011, "Корпуса фрез", 100010, 3, true)), USER_ID), ConstraintViolationException.class);

        //deficiency out of bounds
        validateRootCause(() -> service.create(new Tool(null, LocalDate.of(2021, Month.JUNE, 1), "Description", 1, "Sandvik", new Location(3, "БИХ 103"), 10001, new Type(100011, "Корпуса фрез", 100010, 3, true)), USER_ID), ConstraintViolationException.class);

        //null toolType
        validateRootCause(() -> service.create(new Tool(null, LocalDate.of(2021, Month.JUNE, 1), "Description", 1, "Sandvik", new Location(3, "БИХ 103"), 10001, null), USER_ID), ConstraintViolationException.class);
    }

    @Test
    void getWithUser() {
        Tool adminTool = service.getWithUser(ADMIN_TOOL_ID, ADMIN_ID);
        TOOL_MATCHER.assertMatch(adminTool, adminTool1);
        UserTestData.USER_MATCHER.assertMatch(adminTool.getUser(), UserTestData.admin);
    }

    @Test
    void getWithUserNotFound() {
        Assertions.assertThrows(NotFoundException.class,
                () -> service.getWithUser(1, ADMIN_ID));
    }
}
