package com.app.tools.testdata;

import com.app.tools.TestMatcher;
import com.app.tools.model.Role;
import com.app.tools.model.User;
import com.app.tools.web.json.JsonUtil;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static com.app.tools.testdata.ToolTestData.*;
import static com.app.tools.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final TestMatcher<User> USER_MATCHER = TestMatcher.usingIgnoringFieldsComparator(User.class, "registered", "tools", "password");
    public static TestMatcher<User> USER_WITH_TOOLS_MATCHER =
            TestMatcher.usingAssertions(User.class,
                    (a, e) -> assertThat(a).usingRecursiveComparison()
                            .ignoringFields("registered", "tools.user", "password").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int EDITOR_ID = START_SEQ + 2;
    public static final int NOT_FOUND = 10;

    public static final User user = new User(USER_ID, "user", "password", Role.USER);
    public static final User admin = new User(ADMIN_ID, "admin", "admin", Role.ADMIN, Role.USER, Role.EDITOR);
    public static final User editor = new User(EDITOR_ID, "editor", "editor", Role.EDITOR, Role.USER);

    static {
        editor.setTools(tools);
        admin.setTools(List.of(adminTool2, adminTool1));
    }

    public static User getNew() {
        return new User(null, "newuser", "newpass", false, new Date(), Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(user);
        updated.setName("updatedname");
        updated.setPassword("newpass");
        updated.setEnabled(false);
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
