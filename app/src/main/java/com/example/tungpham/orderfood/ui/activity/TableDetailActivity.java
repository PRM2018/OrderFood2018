package com.example.tungpham.orderfood.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
    private int tableID;
    private ArrayList<CustomerOrder> arrOrder = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_detail);
        CustomerModel cm = new CustomerModel();
        Intent intent = getIntent();
        tableID = intent.getIntExtra("tableID", 1);
        cusID = intent.getIntExtra("cusID", 1);
        String arrCheck = cm.checkListOrderByCustomer(cusID, tableID);
        tm = new TableModel();
        Customer customer = new Customer();
        customer = tm.getCusName(cusID, tableID);
        textView = (TextView) findViewById(R.id.tv_customer);
        textView.setText(customer.getCusName());
        if(arrCheck != null){
            setAdapter(cusID, tableID);
        }

        btnAddFood = findViewById(R.id.btn_add_food);
        btnCheckout = findViewById(R.id.btn_checkout);
        int roleID = intent.getIntExtra("roleID", 2);
        if (roleID == 3) {
            btnAddFood.setVisibility(View.GONE);
            btnCheckout.setVisibility(View.GONE);
        }
    }

    private void setAdapter(int cusID, int tableID) {
        ListView listView = findViewById(R.id.lv_food_by_table);
        CustomerModel cm = new CustomerModel();
        arrOrder = cm.getListOrderByCustomer(cusID, tableID);
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
        CustomerModel cm = new CustomerModel();
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                ArrayList<Integer> listQuantity = data.getIntegerArrayListExtra("listOrder");
                ArrayList<Integer> listIdFood = data.getIntegerArrayListExtra("listOrderFoodID");

                for (int i = 0; i < listQuantity.size(); i++) {
                    if (listQuantity.get(i) != 0) {
                        int idFood = listIdFood.get(i);
                        int quantity = listQuantity.get(i);

                        int check = cm.checkExistOrder(cusID, idFood);
                        if (check == 0) {
                            boolean insert = cm.insertNewOrder(cusID, idFood, quantity);
                        } else if (check > 0) {
                            int oldQuantity = cm.getQuantityOrder(cusID, idFood);
                            boolean update = cm.updateOrderCustomer(cusID, idFood, oldQuantity + quantity);
                        }

                    }
                }
            }
        }
        setAdapter(cusID, tableID);

    }

    public void checkOutBtn(View v) {
        Intent intent = new Intent(this, CheckoutActivity.class);
        intent.putExtra("cusID", cusID);
        intent.putExtra("tableID", tableID);
        startActivity(intent);
    }

    public void backOrder(View view){
        Intent intent = new Intent(this,WaiterActivity.class);
        startActivity(intent);
    }
}
