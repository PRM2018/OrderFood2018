package com.example.tungpham.orderfood.model;

import com.example.tungpham.orderfood.entity.Food;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Tung Pham on 7/23/2018.
 */

public class FoodModel {
    public void closeConnection(Connection con, PreparedStatement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) { /* ignored */}
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) { /* ignored */}
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) { /* ignored */}
        }
    }


    public ArrayList<Food> getAllFood() {
        ArrayList<Food> foodLst = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select ROW_NUMBER() OVER(ORDER BY foodId asc) AS Row,* from FoodTBL";
        try {
            con = DBConnection.Getconnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Food food = new Food();
                food.setRn(rs.getInt("Row"));
                food.setFoodID(rs.getInt("foodId"));
                food.setFoodName(rs.getString("foodName"));
                food.setFoodQuantity(rs.getInt("foodQuantity"));
                food.setFoodPrice(rs.getFloat("foodPrice"));
                food.setFoodIMG(rs.getString("foodImg"));
                foodLst.add(food);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(con, ps, rs);
        }
        return foodLst;
    }

    public Food getFoodById(int foodId) {
        Food food = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from FoodTBL where foodId=" + foodId;
        try {
            con = DBConnection.Getconnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String foodName = rs.getString("foodName");
                int foodQuantity = rs.getInt("foodQuantity");
                float foodPrice = rs.getFloat("foodPrice");
                String foodIMG = rs.getString("foodImg");
                String foodDes = rs.getString("foodDes");
                food = new Food(foodId, foodName, foodQuantity, foodPrice, foodIMG, foodDes);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(con, ps, rs);
        }
        return food;
    }

    //Add new food
    public void addFood(Food f) throws SQLException {
        DBConnection db = null;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            db = new DBConnection();
            con = db.Getconnection();

            String fName = f.getFoodName();
            float fPrice = f.getFoodPrice();
            int fQuantity = f.getFoodQuantity();
            String fImg = f.getFoodIMG();
            String fDes = f.getFoodDescription();
            String sql = "insert into FoodTBL values('" + fName + "'," + fQuantity + "," + fPrice + ",'" + fImg + "','" + fDes + "')";
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(con, ps, null);
        }
    }


    //Edit Quantity Food
    public void editFoodQuantity(int foodId, float newQuantity) throws SQLException {
        DBConnection db = null;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            db = new DBConnection();
            con = db.Getconnection();
            String sql = "update FoodTBL set foodQuantity=" + newQuantity + " where foodId=" + foodId;
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(con, ps, null);
        }
    }

    //Edit Food
    public void editFood(int foodId, float fPrice,int fQuantity,String fDes) throws SQLException {
        DBConnection db = null;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            db = new DBConnection();
            con = db.Getconnection();
            String sql = "update FoodTBL \n" +
                    "set foodPrice=" +fPrice+",\n"+
                    "\tfoodQuantity=" +fQuantity+",\n"+
                    "\tfoodDes='" +fDes+"'\n"+
                    "where foodId="+foodId+"";
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(con, ps, null);
        }
    }


}
