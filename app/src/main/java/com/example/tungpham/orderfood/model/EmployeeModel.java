package com.example.tungpham.orderfood.model;

import com.example.tungpham.orderfood.entity.Employee;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Tung Pham on 7/23/2018.
 */

public class EmployeeModel {
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

    public ArrayList<Employee> getAllEmp(){
        ArrayList<Employee> empLst = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from EmployeeTBL";
        try{
            con = DBConnection.Getconnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Employee emp = new Employee();
                emp.setEmpID(rs.getInt("eId"));
                emp.setEmpName(rs.getString("eName"));
                emp.setEmpAddress(rs.getString("address"));
                emp.setEmpPhone(rs.getString("phone"));
                emp.setEmpType(rs.getInt("roleId"));
                empLst.add(emp);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeConnection(con,ps,rs);
        }
        return empLst;
    }


    public ArrayList<Employee> getAllEmpExceptAdmin(){
        ArrayList<Employee> empLst = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from EmployeeTBL where roleId!=1";
        try{
            con = DBConnection.Getconnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Employee emp = new Employee();
                emp.setEmpID(rs.getInt("eId"));
                emp.setEmpName(rs.getString("eName"));
                emp.setEmpAddress(rs.getString("address"));
                emp.setEmpPhone(rs.getString("phone"));
                emp.setEmpType(rs.getInt("roleId"));
                empLst.add(emp);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeConnection(con,ps,rs);
        }
        return empLst;
    }
}
