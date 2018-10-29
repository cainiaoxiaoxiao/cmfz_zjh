package com.baizhi.entity;

import java.util.List;

/**
 * Created by no on 2018/10/29.
 */
public class UserLocationDTO {

    private String name;

    private int value;

    public UserLocationDTO() {
    }

    public UserLocationDTO(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "UserLocationDTO{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
