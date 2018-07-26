package com.example.tungpham.orderfood.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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

public class CookerActivity extends AppCompatActivity {

    private int empID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooker);

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
            intent.putExtra("empID", empID);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setTableAdapter() {
        ListView listView = findViewById(R.id.lv_table_list);
        TableModel tableModel = new TableModel();
        ArrayList<Table> arrTable = tableModel.getAllTable();
        EmployeeTableAdapter tableAdapter =
                new EmployeeTableAdapter(this, R.layout.item_table, arrTable);
        listView.setAdapter(tableAdapter);
        tableAdapter.setRoleID(3);
    }
}
