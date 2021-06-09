package com.app.tools.web.tool.type;

import com.app.tools.model.Type;
import com.app.tools.service.TypeService;
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
import static com.app.tools.testdata.TypeTestData.*;
import static com.app.tools.testdata.UserTestData.admin;
import static com.app.tools.util.exception.ErrorType.VALIDATION_ERROR;
import static com.app.tools.web.ExceptionInfoHandler.EXCEPTION_TYPE_DUPLICATE_NAME_PARENT_ID;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TypeRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = TypeRestController.REST_URL + '/';

    @Autowired
    private TypeService typeService;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + TYPE1_ID)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TYPE_MATCHER.contentJson(type1));
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TYPE_MATCHER.contentJson(renderedTypes));
    }

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + TYPE1_ID))
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
        perform(MockMvcRequestBuilders.delete(REST_URL + TYPE1_ID)
                .with(userHttpBasic(admin)))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> typeService.get(TYPE1_ID));
    }

    @Test
    void update() throws Exception {
        Type updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + TYPE1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(admin)))
                .andExpect(status().isNoContent());

        TYPE_MATCHER.assertMatch(typeService.get(TYPE1_ID), updated);
    }

    @Test
    void createWithLocation() throws Exception {
        Type newType = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newType))
                .with(userHttpBasic(admin)));

        Type created = readFromJson(action, Type.class);
        int newId = created.id();
        newType.setId(newId);
        TYPE_MATCHER.assertMatch(created, newType);
        TYPE_MATCHER.assertMatch(typeService.get(newId), newType);
    }



    @Test
    void createInvalid() throws Exception {
        Type invalid = new Type(null, null, 100006, 3, true);
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
        Type invalid = new Type(TYPE1_ID, null, 100006, 3, true);
        perform(MockMvcRequestBuilders.put(REST_URL + TYPE1_ID)
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
        Type invalid = new Type(null, "Режущий инструмент", 0, 0, false);
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(errorType(VALIDATION_ERROR))
                .andExpect(detailMessage(EXCEPTION_TYPE_DUPLICATE_NAME_PARENT_ID));
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void updateDuplicate() throws Exception {
        Type invalid = new Type(TYPE1_ID, "Режущий инструмент", 0, 0, false);
        perform(MockMvcRequestBuilders.put(REST_URL + TYPE1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(errorType(VALIDATION_ERROR))
                .andExpect(detailMessage(EXCEPTION_TYPE_DUPLICATE_NAME_PARENT_ID));
    }
}