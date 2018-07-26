package com.example.tungpham.orderfood.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class CheckoutActivity extends AppCompatActivity {
    private int cusID;
    private int tableID;
    private TableModel tm;
    private TextView textView;
    private ArrayList<CustomerOrder> arrOrder = new ArrayList<>();
    private double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        CustomerModel cm = new CustomerModel();
        ArrayList<CustomerOrder> arrCheck = new ArrayList<>();
        arrCheck = cm.getListOrderByCustomer(cusID, tableID);

        Intent intent = getIntent();
        tableID = intent.getIntExtra("tableID", 1);
        cusID = intent.getIntExtra("cusID", 1);
        tm = new TableModel();
        Customer customer = new Customer();
        customer = tm.getCusName(cusID, tableID);
        textView = (TextView) findViewById(R.id.tv_customer);
        textView.setText(customer.getCusName());
        if (arrCheck.size() != 0) {
            setAdapter(cusID, tableID);
        }
        total = 0;
        for (int i = 0; i < arrOrder.size(); i++) {
            double amout = arrOrder.get(i).getPrice() * arrOrder.get(i).getQuantity();
            total += amout;
        }
        TextView textViewTotal = findViewById(R.id.tv_total);
        textViewTotal.setText(String.valueOf(total));

        findViewById(R.id.btn_checkout).setOnClickListener(v -> {
            boolean updateTable = cm.checkOutTable(tableID);
            boolean updateCus = cm.checkOutCus(cusID, tableID, total);
            Intent finishIntent = new Intent(this, WaiterActivity.class);
            startActivity(finishIntent);
        });

        findViewById(R.id.btn_cancel).setOnClickListener(v -> finish());
    }

    private void setAdapter(int cusID, int tableID) {
        ListView listView = findViewById(R.id.lv_food_by_table);
        CustomerModel cm = new CustomerModel();
        arrOrder = cm.getListOrderByCustomer(cusID, tableID);
        CustomerOrderAdapter orderAdapter =
                new CustomerOrderAdapter(this, R.layout.item_food_order, arrOrder);
        listView.setAdapter(orderAdapter);
    }

}
