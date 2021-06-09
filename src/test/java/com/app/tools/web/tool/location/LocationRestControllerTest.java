package com.app.tools.web.tool.location;

import com.app.tools.model.Location;
import com.app.tools.service.LocationService;
import com.app.tools.util.exception.NotFoundException;
import com.app.tools.web.AbstractControllerTest;
import com.app.tools.web.json.JsonUtil;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.app.tools.TestUtil.readFromJson;
import static com.app.tools.TestUtil.userHttpBasic;
import static com.app.tools.testdata.LocationTestData.*;
import static com.app.tools.testdata.LocationTestData.NOT_FOUND;
import static com.app.tools.testdata.LocationTestData.getNew;
import static com.app.tools.testdata.LocationTestData.getUpdated;
import static com.app.tools.testdata.UserTestData.*;
import static com.app.tools.util.exception.ErrorType.VALIDATION_ERROR;
import static com.app.tools.web.ExceptionInfoHandler.EXCEPTION_LOCATION_DUPLICATE_NAME;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LocationRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = LocationRestController.REST_URL + '/';

    @Autowired
    private LocationService locationService;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + LOCATION1_ID)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(LOCATION_MATCHER.contentJson(location1));
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(LOCATION_MATCHER.contentJson(locations));
    }

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + LOCATION1_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + NOT_FOUND)
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + NOT_FOUND)
                .with(userHttpBasic(admin)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + LOCATION1_ID)
                .with(userHttpBasic(admin)))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> locationService.get(LOCATION1_ID));
    }

    @Test
    void update() throws Exception {
        Location updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + LOCATION1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(admin)))
                .andExpect(status().isNoContent());

        LOCATION_MATCHER.assertMatch(locationService.get(LOCATION1_ID), updated);
    }

    @Test
    void createWithLocation() throws Exception {
        Location newLocation = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newLocation))
                .with(userHttpBasic(admin)));

        Location created = readFromJson(action, Location.class);
        int newId = created.id();
        newLocation.setId(newId);
        LOCATION_MATCHER.assertMatch(created, newLocation);
        LOCATION_MATCHER.assertMatch(locationService.get(newId), newLocation);
    }



    @Test
    void createInvalid() throws Exception {
        Location invalid = new Location(null, null);
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR));
    }

    @Test
    void updateInvalid() throws Exception {
        Location invalid = new Location(LOCATION1_ID, null);
        perform(MockMvcRequestBuilders.put(REST_URL + LOCATION1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR));
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void createDuplicate() throws Exception {
        Location invalid = new Location(null, "БИХ 101");
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(errorType(VALIDATION_ERROR))
                .andExpect(detailMessage(EXCEPTION_LOCATION_DUPLICATE_NAME));
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void updateDuplicate() throws Exception {
        Location invalid = new Location(LOCATION1_ID, "БИХ 102");

        perform(MockMvcRequestBuilders.put(REST_URL + LOCATION1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(errorType(VALIDATION_ERROR))
                .andExpect(detailMessage(EXCEPTION_LOCATION_DUPLICATE_NAME));
    }
}