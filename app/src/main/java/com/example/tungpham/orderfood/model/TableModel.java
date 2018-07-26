package com.example.tungpham.orderfood.model;

import com.example.tungpham.orderfood.entity.Customer;
import com.example.tungpham.orderfood.entity.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Tung Pham on 7/23/2018.
 */

public class TableModel {
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

    public ArrayList<Table> getAllTable(){
        ArrayList<Table> tableLst = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from TableTBL";
        try{
            con = DBConnection.Getconnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                 Table table = new Table();
                 table.setTableID(rs.getInt(1));
                 table.setTableName(rs.getString(2));
                 table.setTableStatus(rs.getInt(3));
                 tableLst.add(table);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeConnection(con,ps,rs);
        }
        return tableLst;
    }

    public boolean updateStatusTable(int tableID, int tableStatus){
        boolean check = false;
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "update TableTBL set tableStatus = ? where tableId = ?";
        try{
            con = DBConnection.Getconnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,tableStatus);
            ps.setInt(2,tableID);
            ps.executeUpdate();
            check = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeConnection(con,ps,null);
        }
        return check;
    }

    public Customer getCusName(int cusID, int tableID){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Customer cus = new Customer();
        String sql = "select * from CustomerTBL where customId = ? and tableId = ?";
        try{
            con = DBConnection.Getconnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,cusID);
            ps.setInt(2,tableID);
            rs=ps.executeQuery();
            while(rs.next()){
                cus.setCusID(rs.getInt(1));
                cus.setCusName(rs.getString(2));
                cus.setTableID(rs.getInt(3));
                cus.setTotalBill(rs.getFloat(4));
                cus.setCheckout(rs.getInt(5));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeConnection(con,ps,null);
        }
        return cus;
    }
}
