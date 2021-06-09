package com.app.tools.dto;

import com.app.tools.HasIdAndName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class UserTo extends BaseTo implements HasIdAndName, Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank
    @Size(min = 5, max = 32)
    private String password;

    public UserTo() {
    }

    public UserTo(Integer id, String name, String password) {
        super(id);
        this.name = name;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
