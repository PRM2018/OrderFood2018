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

    public Employee getEmployeeProfileByID(int empID){
        Employee emp = new Employee();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select emp.*,role.roleName from EmployeeTBL emp left join RoleTBL role on emp.roleId = role.roleId where emp.eId = ?";
        try{
            con = DBConnection.Getconnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,empID);
            rs = ps.executeQuery();
            while (rs.next()){
                emp.setEmpID(rs.getInt(1));
                emp.setEmpName(rs.getString(2));
                emp.setEmpAddress(rs.getString(3));
                emp.setEmpPhone(rs.getString(4));
                emp.setEmpType(rs.getInt(5));
                emp.setRoleName(rs.getString(6));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            closeConnection(con,ps,rs);
        }
        return emp;
    }

    public boolean updateEmpInfo(String address, String empPhone, int empRoleID, int empID){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "update EmployeeTBL set address = ?, phone = ?, roleId = ? where eId = ?";
        boolean check = false;
        try{
            con = DBConnection.Getconnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,address);
            ps.setString(2,empPhone);
            ps.setInt(3,empRoleID);
            ps.setInt(4,empID);
            ps.executeUpdate();

            check = true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConnection(con,ps,null);
        }
        return check;
    }
}
