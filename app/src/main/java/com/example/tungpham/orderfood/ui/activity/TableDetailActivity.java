package com.example.tungpham.orderfood.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tungpham.orderfood.R;
import com.example.tungpham.orderfood.entity.Customer;
import com.example.tungpham.orderfood.entity.CustomerOrder;
import com.example.tungpham.orderfood.model.CustomerModel;
import com.example.tungpham.orderfood.model.TableModel;
import com.example.tungpham.orderfood.ui.adapter.CustomerOrderAdapter;

import java.util.ArrayList;

public class TableDetailActivity extends AppCompatActivity {
    private TableModel tm;
    private TextView textView;
    private int cusID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_detail);

        Intent intent = getIntent();
        int tableID = intent.getIntExtra("tableID",1);
        cusID = intent.getIntExtra("cusID",1);
        tm = new TableModel();
        Customer customer = new Customer();
        customer = tm.getCusName(cusID,tableID);
        textView = (TextView)findViewById(R.id.tv_customer);
        textView.setText(customer.getCusName());
        setAdapter(cusID,tableID);
    }

    private void setAdapter(int cusID, int tableID){
        ListView listView = findViewById(R.id.lv_food_by_table);
        CustomerModel cm = new CustomerModel();
        ArrayList<CustomerOrder> arrOrder = cm.getListOrderByCustomer(cusID,tableID);
        CustomerOrderAdapter orderAdapter = new CustomerOrderAdapter(this,R.layout.item_food_order,arrOrder);
        listView.setAdapter(orderAdapter);
    }

    public void addNewFood(View v){
            Intent intent = new Intent(this,AddOrderFoodActivity.class);
            intent.putExtra("cusID",cusID);
            startActivity(intent);
    }
}
