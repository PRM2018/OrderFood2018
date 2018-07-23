package com.example.tungpham.orderfood.entity;

/**
 * Created by Tung Pham on 7/23/2018.
 */

public class Table {
    private int tableID;
    private String tableName;
    private int tableStatus;

    public Table() {
    }

    public Table(int tableID, String tableName, int tableStatus) {
        this.tableID = tableID;
        this.tableName = tableName;
        this.tableStatus = tableStatus;
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(int tableStatus) {
        this.tableStatus = tableStatus;
    }
}
