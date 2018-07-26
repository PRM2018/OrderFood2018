package com.example.tungpham.orderfood.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tungpham.orderfood.R;
import com.example.tungpham.orderfood.entity.Food;
import com.example.tungpham.orderfood.model.FoodModel;

import java.sql.SQLException;

public class EditFoodActivity extends AppCompatActivity {
    TextView tvName;
    TextView tvQuantity;
    EditText etAddQuantity;
    TextView tvPrice;
    TextView tvDes;
    Button btnCancel;
    Button btnEditQuantity;
    Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food);
        Intent intent = getIntent();
        final int fId = intent.getIntExtra("fId", 0);
        final FoodModel fm = new FoodModel();
        final Food foodEdit = fm.getFoodById(fId);

        // Set text when load page
        tvName = findViewById(R.id.tv_edit_food_name);
        tvQuantity = findViewById(R.id.tv_edit_food_quantity);
        tvPrice = findViewById(R.id.et_add_food_price);
        tvDes = findViewById(R.id.et_add_food_description);

        tvName.setText(foodEdit.getFoodName());
        tvPrice.setText("" + foodEdit.getFoodPrice());
        tvDes.setText("" + foodEdit.getFoodDescription());
        tvQuantity.setText("" + foodEdit.getFoodQuantity());


        //Edit
        btnEdit = findViewById(R.id.btn_edit_food);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fDes = tvDes.getText().toString();
                Float fPrice = Float.parseFloat(tvPrice.getText().toString());
                int newQuantity = Integer.parseInt(tvQuantity.getText().toString());
                try {
                    fm.editFood(fId, fPrice, newQuantity, fDes);
                    Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                    startActivity(intent);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });

        //Edit quantity
        btnEditQuantity = findViewById(R.id.btn_edit_quantity_food);
        btnEditQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // New quantity
                etAddQuantity = findViewById(R.id.et_add_food_quantity);
                int addQuantity = Integer.parseInt(etAddQuantity.getText().toString());
                int newQuantity = addQuantity + foodEdit.getFoodQuantity();
                tvQuantity.setText(String.valueOf(newQuantity));
                etAddQuantity.setText("0");
            }
        });

        //Cancel
        btnCancel = findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
