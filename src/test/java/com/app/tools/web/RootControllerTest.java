package com.app.tools.web;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static com.app.tools.TestUtil.userAuth;
import static com.app.tools.testdata.UserTestData.admin;
import static com.app.tools.testdata.UserTestData.user;

class RootControllerTest extends AbstractControllerTest {

    @Test
    void unAuth() throws Exception {
        perform(get("/users"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    void getTools() throws Exception {
        perform(get("/tools")
                .with(userAuth(user)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("tools"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/tools.jsp"));
    }

    @Test
    void getUsers() throws Exception {
        perform(get("/users")
                .with(userAuth(admin)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"));
    }

    @Test
    void getLocations() throws Exception {
        perform(get("/tools/locations")
                .with(userAuth(admin)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("locations"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/locations.jsp"));
    }

    @Test
    void getTypes() throws Exception {
        perform(get("/tools/types")
                .with(userAuth(admin)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("types"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/types.jsp"));
    }
}