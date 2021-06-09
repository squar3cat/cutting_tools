package com.app.tools;

import com.app.tools.dto.ToolTo;
import com.app.tools.web.tool.ToolRestController;
import com.app.tools.web.user.AdminRestController;
import org.springframework.context.support.GenericXmlApplicationContext;
import com.app.tools.model.Role;
import com.app.tools.model.User;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static com.app.tools.TestUtil.mockAuthorize;
import static com.app.tools.testdata.UserTestData.user;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management (ARM)
        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            appCtx.getEnvironment().setActiveProfiles(Profiles.getActiveDbProfile(), "datajpa");
            appCtx.load("spring/inmemory.xml");
            appCtx.refresh();

            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "password", Role.ADMIN));
            System.out.println();

            mockAuthorize(user);

            ToolRestController toolController = appCtx.getBean(ToolRestController.class);
            List<ToolTo> filteredTools = toolController.getBetween(null, null, LocalDate.of(2020, Month.JANUARY, 30),
                    LocalDate.of(2020, Month.JANUARY, 31));
            filteredTools.forEach(System.out::println);
            System.out.println();
            System.out.println(toolController.getBetween(null,null, null, null));
        }
    }
}
