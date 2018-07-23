package com.example.tungpham.orderfood.entity;

import java.util.Date;

/**
 * Created by Tung Pham on 7/23/2018.
 */

public class Order {
    private int orderID;
    private int customerID;
    private Date timeOrder;
    private int foodID;
    private int orderQuantity;
    private int empID;

    public Order() {
    }

    public Order(int orderID, int customerID, Date timeOrder, int foodID, int orderQuantity, int empID) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.timeOrder = timeOrder;
        this.foodID = foodID;
        this.orderQuantity = orderQuantity;
        this.empID = empID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Date getTimeOrder() {
        return timeOrder;
    }

    public void setTimeOrder(Date timeOrder) {
        this.timeOrder = timeOrder;
    }

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }
}
