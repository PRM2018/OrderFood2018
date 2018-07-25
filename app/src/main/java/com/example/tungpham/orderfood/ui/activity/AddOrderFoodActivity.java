package com.example.tungpham.orderfood.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tungpham.orderfood.R;
import com.example.tungpham.orderfood.entity.Food;
import com.example.tungpham.orderfood.model.FoodModel;
import com.example.tungpham.orderfood.ui.adapter.AddFoodOrderAdapter;
import com.travijuu.numberpicker.library.Enums.ActionEnum;
import com.travijuu.numberpicker.library.Interface.ValueChangedListener;
import com.travijuu.numberpicker.library.NumberPicker;

import java.util.ArrayList;

public class AddOrderFoodActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order_food);
        setAdapter();

    }


    private void setAdapter(){
        ListView listView = findViewById(R.id.lv_order_food);
        FoodModel fm = new FoodModel();
        ArrayList<Food> lstFood = fm.getAllFood();
        AddFoodOrderAdapter adapter = new AddFoodOrderAdapter(this,R.layout.item_add_order_food,lstFood);
        listView.setAdapter(adapter);
    }


}
