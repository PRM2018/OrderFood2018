package com.example.tungpham.orderfood.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

    private Button btnAddFood;
    private Button btnCheckout;
    private TextView textView;

    private TableModel tm;
    private int cusID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_detail);

        Intent intent = getIntent();
        int tableID = intent.getIntExtra("tableID", 1);
        cusID = intent.getIntExtra("cusID", 1);
        tm = new TableModel();
        Customer customer = new Customer();
        customer = tm.getCusName(cusID, tableID);
        textView = (TextView) findViewById(R.id.tv_customer);
        textView.setText(customer.getCusName());
        setAdapter(cusID, tableID);

        btnAddFood = findViewById(R.id.btn_add_food);
        btnCheckout = findViewById(R.id.btn_checkout);
        int roleID = intent.getIntExtra("roleID", 2);
        if(roleID == 3) {
            btnAddFood.setVisibility(View.GONE);
            btnCheckout.setVisibility(View.GONE);
        }
    }

    private void setAdapter(int cusID, int tableID) {
        ListView listView = findViewById(R.id.lv_food_by_table);
        CustomerModel cm = new CustomerModel();
        ArrayList<CustomerOrder> arrOrder = cm.getListOrderByCustomer(cusID, tableID);
        CustomerOrderAdapter orderAdapter =
                new CustomerOrderAdapter(this, R.layout.item_food_order, arrOrder);
        listView.setAdapter(orderAdapter);
    }

    public void addNewFood(View v) {
        Intent intent = new Intent(this, AddOrderFoodActivity.class);
        intent.putExtra("cusID", cusID);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                ArrayList<Integer> test = data.getIntegerArrayListExtra("TEST");
                for (int i : test) {
                    Log.d(TableDetailActivity.class.getSimpleName(), String.valueOf(i));
                }
            }
        }
    }
}
