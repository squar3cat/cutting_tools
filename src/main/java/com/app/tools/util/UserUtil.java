package com.app.tools.util;

import com.app.tools.model.Role;
import com.app.tools.model.User;
import com.app.tools.dto.UserTo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

public class UserUtil {

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName().toLowerCase(), userTo.getPassword(), /*userTo.getRoles()*/Role.USER);
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getPassword()/*, user.getRoles()*/);
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        String password = user.getPassword();
        user.setPassword(StringUtils.hasText(password) ? passwordEncoder.encode(password) : password);
        user.setName(user.getName().toLowerCase());
        return user;
    }
}