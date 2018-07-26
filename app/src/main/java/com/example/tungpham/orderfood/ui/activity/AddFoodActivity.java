package com.example.tungpham.orderfood.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tungpham.orderfood.R;
import com.example.tungpham.orderfood.entity.Food;
import com.example.tungpham.orderfood.model.FoodModel;

import java.sql.SQLException;

public class AddFoodActivity extends AppCompatActivity {
    EditText et_name;
    EditText et_quantity;
    EditText et_price;
    EditText et_des;
    Button btnAddFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        Intent intent = getIntent();

        et_name = findViewById(R.id.et_add_food_name);
        et_price = findViewById(R.id.et_add_food_price);
        et_quantity = findViewById(R.id.et_add_food_quantity);
        et_des = findViewById(R.id.et_add_food_description);

        btnAddFood=findViewById(R.id.btn_add_new_food);
        btnAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fName = et_name.getText().toString();
                Float fPrice = Float.parseFloat(et_price.getText().toString());
                int fQuantity = Integer.parseInt(et_quantity.getText().toString());
                String fDes = et_des.getText().toString();

                Food foodAdd = new Food(fName, fQuantity, fPrice, "a", fDes);

                FoodModel fm = new FoodModel();
                try {
                    fm.addFood(foodAdd);
                    Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                    startActivity(intent);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Toast.makeText(AddFoodActivity.this, "Add Food successfull", Toast.LENGTH_LONG).show();
            }
        });
    }
}
