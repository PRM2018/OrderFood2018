package com.example.tungpham.orderfood.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.example.tungpham.orderfood.R;
import com.example.tungpham.orderfood.entity.Employee;
import com.example.tungpham.orderfood.entity.Table;
import com.example.tungpham.orderfood.model.EmployeeModel;
import com.example.tungpham.orderfood.model.TableModel;
import com.example.tungpham.orderfood.ui.adapter.EmployeeTableAdapter;
import java.util.ArrayList;

public class WaiterActivity extends AppCompatActivity {

    private int empID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter);

        Intent intent = getIntent();
        empID = intent.getIntExtra("empID", 1);
        Employee employee = new EmployeeModel().getEmployeeProfileByID(empID);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setTitle(employee.getEmpName());
        setTableAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_profile) {
            Intent intent = new Intent(this, ProfileActivity.class);
            // Push data into intent to ProfileActivity
            intent.putExtra("empID", empID);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void setTableAdapter() {
        ListView listView = (ListView) findViewById(R.id.lv_table_list);
        TableModel tableModel = new TableModel();
        ArrayList<Table> arrTable = tableModel.getAllTable();
        EmployeeTableAdapter tableAdapter =
                new EmployeeTableAdapter(this, R.layout.item_table, arrTable);
        listView.setAdapter(tableAdapter);
    }
}
