package com.my.entity;

import java.io.Serial;

public class User extends Entity{
    @Serial
    private static final long serialVersionUID = 4985387515740550576L;

    private String login;
    private int roleId;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + getId() + '\'' +
                "login='" + login + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}
