package com.example.tungpham.orderfood.model;

import com.example.tungpham.orderfood.entity.CustomerOrder;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Tung Pham on 7/23/2018.
 */

public class CustomerModel {
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

    public boolean insertNewCustomer(String cusName, int tableID) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean check = false;
        String sql = "insert into CustomerTBL (customeName,tableId,total,checkOut) values (?,?,?,?)";
        try {
            con = DBConnection.Getconnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cusName);
            ps.setInt(2, tableID);
            ps.setDouble(3, 0);
            ps.setInt(4, 0);
            ps.executeUpdate();
            check = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(con, ps, null);
        }
        return check;
    }

    public boolean updateCusStatus(int newStatus, int oldSStatus, int tableID) {
        boolean check = false;
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "update CustomerTBL set checkOut = ? where checkOut = ? and tableId = ?";
        try {
            con = DBConnection.Getconnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, newStatus);
            ps.setInt(2, oldSStatus);
            ps.setInt(3, tableID);
            ps.executeUpdate();
            check = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(con, ps, null);
        }
        return check;
    }

    public int getCusID(int tableId, int checkOut) {
        int cusID = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select customId from CustomerTBL where tableId = ? and checkOut = ?";
        try {
            con = DBConnection.Getconnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, tableId);
            ps.setInt(2, checkOut);
            rs = ps.executeQuery();
            while (rs.next()) {
                cusID = rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(con, ps, rs);
        }
        return cusID;
    }

    public ArrayList<CustomerOrder> getListOrderByCustomer(int cusID, int tableID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<CustomerOrder> lstOrder = new ArrayList<>();
        String sql = "select ROW_NUMBER() OVER(ORDER BY ord.orderDate asc) AS Row, cus.customId,cus.customeName,cus.tableId,ord.orderDate,ord.foodId,ord.orderQuantity,food.foodName,food.foodPrice,food.foodImg,food.foodDes from CustomerTBL cus \n" +
                "left join OrderTBL ord on cus.customId = ord.customerId \n" +
                "left join FoodTBL food on ord.foodId = food.foodId\n" +
                "left join TableTBL tbl on tbl.tableId = cus.tableId\n" +
                "where cus.customId = ? and cus.tableId = ? and cus.checkOut = 2 and tbl.tableStatus = 1 order by ord.orderDate asc\n";
        try {
            con = DBConnection.Getconnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cusID);
            ps.setInt(2, tableID);
            rs = ps.executeQuery();
            while (rs.next()) {
                CustomerOrder cusOrder = new CustomerOrder();
                cusOrder.setRn(rs.getInt("Row"));
                cusOrder.setFoodName(rs.getString("foodName"));
                cusOrder.setQuantity(rs.getInt("orderQuantity"));
                cusOrder.setPrice(rs.getFloat("foodPrice"));
                cusOrder.setCusName(rs.getString("customeName"));
                lstOrder.add(cusOrder);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(con, ps, rs);
        }
        return lstOrder;
    }

    public String checkListOrderByCustomer(int cusID, int tableID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String check = "";
        String sql = "select food.foodName from CustomerTBL cus \n" +
                "left join OrderTBL ord on cus.customId = ord.customerId \n" +
                "left join FoodTBL food on ord.foodId = food.foodId\n" +
                "left join TableTBL tbl on tbl.tableId = cus.tableId\n" +
                "where cus.customId = ? and cus.tableId = ? and cus.checkOut = 2 and tbl.tableStatus = 1 order by ord.orderDate asc\n";
        try {
            con = DBConnection.Getconnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cusID);
            ps.setInt(2, tableID);
            rs = ps.executeQuery();
            while (rs.next()) {
                check = rs.getString(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(con, ps, rs);
        }
        return check;
    }

    public boolean updateOrderCustomer(int cusID, int foodID, int quantity) {
        boolean check = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "UPDATE OrderTBL SET orderQuantity = ? where foodId = ? and customerId = ?";
            con = DBConnection.Getconnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,quantity);
            ps.setInt(2,foodID);
            ps.setInt(3,cusID);
            ps.executeUpdate();
            check = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeConnection(con,ps,null);
        }
        return check;
    }

    public boolean insertNewOrder(int cusID, int foodID, int quantity){
        boolean check = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "INSERT INTO OrderTBL (customerId,foodId,orderQuantity,orderDate) values (?,?,?,SYSDATETIME ( ))";
            con = DBConnection.Getconnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,cusID);
            ps.setInt(2,foodID);
            ps.setInt(3,quantity);
            ps.executeUpdate();
            check = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeConnection(con,ps,null);
        }
        return check;
    }

    public int checkExistOrder(int cusID, int foodID) {
        int count = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT count(*) as count FROM OrderTBL WHERE customerId = ? AND foodId = ?";
        try {
            con = DBConnection.Getconnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cusID);
            ps.setInt(2, foodID);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(con, ps, null);
        }
        return count;
    }

    public int getQuantityOrder(int cusID, int foodID){
        int quantity = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "select orderQuantity from OrderTBL where customerId = ? and foodId = ?";
            con = DBConnection.Getconnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,cusID);
            ps.setInt(2,foodID);
            rs = ps.executeQuery();
            while (rs.next()){
                quantity = rs.getInt(1);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeConnection(con,ps,rs);
        }
        return quantity;
    }

    public boolean checkOutCus(int cusID, int tableID, double total){
        Connection con = null;
        PreparedStatement ps = null;
        boolean check = false;
        try{
            String sql = "update CustomerTBL set total = ?, checkOut = 0 where customId = ? and tableId = ?";
            con = DBConnection.Getconnection();
            ps = con.prepareStatement(sql);
            ps.setDouble(1,total);
            ps.setInt(2,cusID);
            ps.setInt(3,tableID);
            ps.executeUpdate();
            check = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeConnection(con,ps,null);
        }
        return  check;
    }


    public boolean checkOutTable(int tableID){
        Connection con = null;
        PreparedStatement ps = null;
        boolean check = false;
        try{
            String sql = "update TableTBL set tableStatus = 0 where tableId = ?";
            con = DBConnection.Getconnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,tableID);
            ps.executeUpdate();
            check = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeConnection(con,ps,null);
        }
        return  check;
    }
}
