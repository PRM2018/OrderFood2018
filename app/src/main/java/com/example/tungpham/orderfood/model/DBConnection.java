package com.example.tungpham.orderfood.model;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Tung Pham on 7/15/2018.
 */

public class DBConnection {
    public static String User_name = "sa";
    public static String User_pass = "sa";
    public static String Db_name = "AndroidProject";
    static Context mcontext;
    public static Connection Conn = null;
    public static String Server_ip = "192.168.107.81:1433;";

    public static Connection Getconnection() {

        Log.i("Android", " MySQL Connect.");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String connString = null;
        try {
            Log.i("SQL Looking", "Start Looking for server");
            String driver = "net.sourceforge.jtds.jdbc.Driver";
            Class.forName(driver).newInstance();
            connString = "jdbc:jtds:sqlserver://" + Server_ip
                    + ";databaseName=" + Db_name + ";user=" + User_name
                    + ";password=" + User_pass + ";";

            Conn = DriverManager.getConnection(connString);
            Log.i("Connection", "open DB Class");
        } catch (Exception e) {

            Log.w("Error connection", e.getMessage());

        }
        return Conn;
    }

    public static void close_DB() {

        try {
            Conn.close();
            Log.i("Connection", "Close DB Class");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
