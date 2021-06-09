package com.app.tools.web.tool;


import com.app.tools.model.Location;
import com.app.tools.model.Type;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.app.tools.model.Tool;
import com.app.tools.service.ToolService;
import com.app.tools.util.exception.NotFoundException;
import com.app.tools.web.AbstractControllerTest;
import com.app.tools.web.json.JsonUtil;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Month;

import static com.app.tools.testdata.UserTestData.*;
import static com.app.tools.util.exception.ErrorType.VALIDATION_ERROR;
import static com.app.tools.web.ExceptionInfoHandler.EXCEPTION_TOOL_DUPLICATE_DESCRIPTION_LOCATION;
import static java.time.LocalDate.of;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.app.tools.testdata.ToolTestData.getNew;
import static com.app.tools.testdata.ToolTestData.getUpdated;
import static com.app.tools.testdata.ToolTestData.*;
import static com.app.tools.TestUtil.readFromJson;
import static com.app.tools.TestUtil.userHttpBasic;
import static com.app.tools.util.ToolUtil.createTool;
import static com.app.tools.util.ToolUtil.getFilteredTool;

class ToolRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = ToolRestController.REST_URL + '/';

    @Autowired
    private ToolService toolService;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + TOOL1_ID)
                .with(userHttpBasic(user)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TOOL_MATCHER.contentJson(tool1));
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(user)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TOOL_ADD_MATCHER.contentJson(getFilteredTool(tools)));
    }

    @Test
    void getBetween() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "filter")
                .param("toolType", "100007").param("filteredLocation", "3")
                .param("startDate", "2020-12-28").param("endDate", "2021-01-05")
                .with(userHttpBasic(user)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(TOOL_ADD_MATCHER.contentJson(createTool(tool5, false)));
    }

    @Test
    void getBetweenAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "filter?toolType=0&filteredLocation=0&startDate=&endDate=")
                .with(userHttpBasic(user)))
                .andExpect(status().isOk())
                .andExpect(TOOL_ADD_MATCHER.contentJson(getFilteredTool(tools)));
    }

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + TOOL1_ID))
                .andExpect(status().isUnauthorized());
    }

    /*@Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + ADMIN_TOOL_ID)
                .with(userHttpBasic(user)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }*/

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + TOOL1_ID)
                .with(userHttpBasic(editor)))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> toolService.get(TOOL1_ID));
    }

    /*@Test
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + ADMIN_TOOL_ID)
                .with(userHttpBasic(user)))
                .andExpect(status().isUnprocessableEntity());
    }*/

    @Test
    void update() throws Exception {
        Tool updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + TOOL1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(editor)))
                .andExpect(status().isNoContent());

        TOOL_MATCHER.assertMatch(toolService.get(TOOL1_ID), updated);
    }

    @Test
    void createWithLocation() throws Exception {
        Tool newTool = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newTool))
                .with(userHttpBasic(editor)));

        Tool created = readFromJson(action, Tool.class);
        int newId = created.id();
        newTool.setId(newId);
        TOOL_MATCHER.assertMatch(created, newTool);
        TOOL_MATCHER.assertMatch(toolService.get(newId), newTool);
    }



    @Test
    void createInvalid() throws Exception {
        Tool invalid = new Tool(null, null, "EC-H4M 06-12C06CF-E57", 1,
                "Iscar", new Location(16, "БИХ 220"), 2, new Type(100009,
                "Фрезы монолитные", 100006, 3, true));
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(editor)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR));
    }

    @Test
    void updateInvalid() throws Exception {
        Tool invalid = new Tool(TOOL1_ID, null, null,
                5, "Sandvik", new Location(14, "БИХ 119"), 5,
                new Type(100015, "Пластинки для проходных резцов", 100014, 2, true));
        perform(MockMvcRequestBuilders.put(REST_URL + TOOL1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(editor)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR));
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void createDuplicate() throws Exception {
        Tool invalid = new Tool(null, of(2020, Month.NOVEMBER, 15), "CNMM 120408-PR 4415",
                5, "Sandvik", new Location(4, "БИХ 104"), 5,
                new Type(100015, "Пластинки для проходных резцов", 100014, 2, true));
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(editor)))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(errorType(VALIDATION_ERROR))
                .andExpect(detailMessage(EXCEPTION_TOOL_DUPLICATE_DESCRIPTION_LOCATION));
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void updateDuplicate() throws Exception {
        Tool invalid = new Tool(TOOL1_ID, of(2020, Month.NOVEMBER, 15), tool2.getDescription(),
                5, "Sandvik", tool2.getLocation(), 5,
                new Type(100015, "Пластинки для проходных резцов", 100014, 2, true));

        perform(MockMvcRequestBuilders.put(REST_URL + TOOL1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(editor)))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(errorType(VALIDATION_ERROR))
                .andExpect(detailMessage(EXCEPTION_TOOL_DUPLICATE_DESCRIPTION_LOCATION));
    }
}