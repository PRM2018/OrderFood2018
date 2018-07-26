package com.example.tungpham.orderfood.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import com.example.tungpham.orderfood.R;
import com.example.tungpham.orderfood.entity.Food;
import com.example.tungpham.orderfood.model.FoodModel;
import com.example.tungpham.orderfood.ui.adapter.AddFoodOrderAdapter;
import java.util.ArrayList;

public class AddOrderFoodActivity extends AppCompatActivity {

    private ListView listView;
    private AddFoodOrderAdapter adapter;

    private Button btnAdd;
    private ArrayList<Integer> quantityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order_food);
        setAdapter();
        btnAdd = findViewById(R.id.btn_add_order_food);
        btnAdd.setOnClickListener(v -> {

            Intent returnIntent = new Intent();
            returnIntent.putExtra("TEST", quantityList);
            setResult(RESULT_OK, returnIntent);
            finish();
        });
    }

    private void setAdapter() {
        listView = findViewById(R.id.lv_order_food);
        FoodModel fm = new FoodModel();
        ArrayList<Food> lstFood = fm.getAllFood();
        adapter = new AddFoodOrderAdapter(this, R.layout.item_add_order_food, lstFood);
        listView.setAdapter(adapter);

        quantityList = adapter.getQuantityList();
    }
}
