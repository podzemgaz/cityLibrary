package com.my.entity;

import java.io.Serial;
import java.io.Serializable;


public abstract class Entity implements Serializable {
    @Serial
    private static final long serialVersionUID = -7241055284023863052L;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
