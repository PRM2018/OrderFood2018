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


    public ArrayList<Food> getAllFood(){
        ArrayList<Food> empLst = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from FoodTBL";
        try{
            con = DBConnection.Getconnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Food food = new Food();
                food.setFoodID(rs.getInt("foodId"));
                food.setFoodName(rs.getString("foodName"));
                food.setFoodQuantity(rs.getInt("foodQuantity"));
                food.setFoodPrice(rs.getFloat("foodPrice"));
                food.setFoodIMG(rs.getString("foodImg"));
                empLst.add(food);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeConnection(con,ps,rs);
        }
        return empLst;
    }
}
