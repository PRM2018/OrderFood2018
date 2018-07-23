package com.example.tungpham.orderfood.entity;

/**
 * Created by Tung Pham on 7/23/2018.
 */

public class EmployeeType {
    private int id;
    private String type;

    public EmployeeType() {
    }

    public EmployeeType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
