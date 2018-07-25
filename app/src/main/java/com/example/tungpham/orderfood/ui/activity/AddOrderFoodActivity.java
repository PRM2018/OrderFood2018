package com.example.tungpham.orderfood.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.tungpham.orderfood.R;

public class AddOrderFoodActivity extends AppCompatActivity {

    //    private NumberPicker npQuantity;
    //    private TextView tvPrice;
    //    private TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order_food);
    }

    // Example for item_order_food_number_picker
    //    private void valueChange() {
    //        npQuantity = findViewById(R.id.np_order_food_quantity);
    //        npQuantity.setValue(0);
    //
    //        tvPrice = findViewById(R.id.tv_order_food_price);
    //        tvTotal = findViewById(R.id.tv_order_food_price_total);
    //
    //        npQuantity.setValueChangedListener(new ValueChangedListener() {
    //            @Override
    //            public void valueChanged(int value, ActionEnum action) {
    //                int price = Integer.parseInt(tvPrice.getText().toString());
    //                tvTotal.setText(String.valueOf(price * value));
    //            }
    //        });
    //    }
}
