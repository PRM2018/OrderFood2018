package com.example.tungpham.orderfood.entity;

/**
 * Created by Tung Pham on 7/23/2018.
 */

public class Account {
    private int empID;
    private String userName;
    private String password;

    public Account() {
    }

    public Account(int accID, String userName, String password) {
        this.empID = accID;
        this.userName = userName;
        this.password = password;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
