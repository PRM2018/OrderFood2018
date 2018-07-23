package com.example.tungpham.orderfood.entity;

/**
 * Created by Tung Pham on 7/23/2018.
 */

public class Customer {
    private int cusID;
    private String cusName;
    private int tableID;
    private float totalBill;
    private int checkout;

    public Customer() {
    }

    public Customer(int cusID, String cusName, int tableID, float totalBill, int checkout) {
        this.cusID = cusID;
        this.cusName = cusName;
        this.tableID = tableID;
        this.totalBill = totalBill;
        this.checkout = checkout;
    }

    public int getCusID() {
        return cusID;
    }

    public void setCusID(int cusID) {
        this.cusID = cusID;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public float getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(float totalBill) {
        this.totalBill = totalBill;
    }

    public int getCheckout() {
        return checkout;
    }

    public void setCheckout(int checkout) {
        this.checkout = checkout;
    }
}
