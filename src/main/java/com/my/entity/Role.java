package com.my.entity;

public enum Role {
    ADMIN, MODER, CLIENT;

    public static Role getRole(User user) {
        int roleId = user.getRoleId();
        return Role.values()[roleId];
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
