package com.example.tungpham.orderfood.entity;

/**
 * Created by Tung Pham on 7/25/2018.
 */

public class CustomerOrder {
    private int rn;
    private String foodName;
    private int quantity;
    private float price;
    private String cusName;

    public CustomerOrder() {
    }

    public CustomerOrder(int rn, String foodName, int quantity, float price) {
        this.rn = rn;
        this.foodName = foodName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getRn() {
        return rn;
    }

    public void setRn(int rn) {
        this.rn = rn;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }
}
