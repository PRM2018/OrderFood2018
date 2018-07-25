package com.example.tungpham.orderfood.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tungpham.orderfood.R;

public class CreateCustomerActivity extends AppCompatActivity {
    private TextView tvTableID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_customer);
    }
}
