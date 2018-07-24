package com.example.tungpham.orderfood.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Tung Pham on 7/23/2018.
 */

public class EmployeeTypeModel {
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

    public String getEmpTypeName(int empTypeId){
        String roleName="";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select roleName from RoleTBL where roleId="+empTypeId;
        try{
            con = DBConnection.Getconnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                roleName=rs.getString("roleName");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeConnection(con,ps,rs);
        }
        return roleName;
    }
}
