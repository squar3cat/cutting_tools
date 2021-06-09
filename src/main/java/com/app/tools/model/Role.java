package com.app.tools.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    EDITOR,
    ADMIN;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}