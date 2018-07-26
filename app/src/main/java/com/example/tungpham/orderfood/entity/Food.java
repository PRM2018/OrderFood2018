package com.example.tungpham.orderfood.entity;

/**
 * Created by Tung Pham on 7/23/2018.
 */

public class Food {
    private int foodID;
    private String foodName;
    private int foodQuantity;
    private float foodPrice;
    private String foodIMG;
    private String foodDescription;

    public Food() {
    }

    public Food(String foodName, int foodQuantity, float foodPrice, String foodIMG, String foodDescription) {
        this.foodName = foodName;
        this.foodQuantity = foodQuantity;
        this.foodPrice = foodPrice;
        this.foodIMG = foodIMG;
        this.foodDescription = foodDescription;
    }

    public Food(int foodID, String foodName, int foodQuantity, float foodPrice, String foodIMG, String foodDescription) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.foodQuantity = foodQuantity;
        this.foodPrice = foodPrice;
        this.foodIMG = foodIMG;
        this.foodDescription = foodDescription;
    }

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(int foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public float getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(float foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodIMG() {
        return foodIMG;
    }

    public void setFoodIMG(String foodIMG) {
        this.foodIMG = foodIMG;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }
}
