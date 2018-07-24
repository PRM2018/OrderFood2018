package com.example.tungpham.orderfood.model;

import com.example.tungpham.orderfood.entity.Account;

import java.security.BasicPermission;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Tung Pham on 7/23/2018.
 */

public class AccountModel {

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

    // Kiểm tra ở trang login
    public boolean checkAccountAndPass(String account, String pass) throws SQLException {
        Boolean check = false;
        DBConnection db;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            db = new DBConnection();
            con = db.Getconnection();
            String sql = "select COUNT(*) from AccountTBL where account like '" + account + "' and pass like '" + pass + "'";
            int count = 0;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);// Đọc dữ liệu từ ResultSet
            }
            if (count == 1) {
                check = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(con, ps, rs);
        }
        return check;
    }

    public int checkRole(String acc) throws SQLException {
        DBConnection db;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int roleId = 0;
        try {
            db = new DBConnection();
            con = db.Getconnection();
            String sql = "select r.roleId \n" +
                    "from AccountTBL a,EmployeeTBL e,RoleTBL r\n" +
                    "where a.eId=e.eId and e.roleId=r.roleId and account like '"+acc+"'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                roleId = rs.getInt(1);// Đọc dữ liệu từ ResultSet
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(con, ps, rs);
        }
        return roleId;
    }

    public int getAccountInfo(String acc, String pass){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idEmp = 0;
        try{
            String sql = "select * from AccountTBL where account like ? and pass like ?";
            con = DBConnection.Getconnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,acc);
            ps.setString(2,pass);
            rs = ps.executeQuery();
            while(rs.next()){
                idEmp = rs.getInt("eId");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeConnection(con,ps,rs);
        }
        return idEmp;
    }
}
