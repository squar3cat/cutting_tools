package com.app.tools.web.json;

import com.app.tools.testdata.UserTestData;
import com.app.tools.model.User;
import org.junit.jupiter.api.Test;
import com.app.tools.testdata.ToolTestData;
import com.app.tools.model.Tool;

import java.util.List;

import static com.app.tools.testdata.ToolTestData.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonUtilTest {

    @Test
    void readWriteValue() {
        String json = JsonUtil.writeValue(adminTool1);
        System.out.println(json);
        Tool tool = JsonUtil.readValue(json, Tool.class);
        TOOL_MATCHER.assertMatch(tool, adminTool1);
    }

    @Test
    void readWriteValues() {
        String json = JsonUtil.writeValue(tools);
        System.out.println(json);
        List<Tool> tools = JsonUtil.readValues(json, Tool.class);
        TOOL_MATCHER.assertMatch(tools, ToolTestData.tools);
    }

    @Test
    void writeOnlyAccess() {
        String json = JsonUtil.writeValue(UserTestData.user);
        System.out.println(json);
        assertThat(json, not(containsString("password")));
        String jsonWithPass = UserTestData.jsonWithPassword(UserTestData.user, "newPass");
        System.out.println(jsonWithPass);
        User user = JsonUtil.readValue(jsonWithPass, User.class);
        assertEquals(user.getPassword(), "newPass");
    }
}