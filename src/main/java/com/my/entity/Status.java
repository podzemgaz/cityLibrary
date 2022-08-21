package com.my.entity;

public enum Status {
    BLOCK , UNBLOCK;

    public static Status getStatus(User user) {
        int id = user.getStatusId();

        return values()[id];
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
