package com.example.tungpham.orderfood.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.tungpham.orderfood.R;

public class AddEmployeeActivity extends AppCompatActivity {

    private EditText etName;
    private Spinner spinnerRole;
    private Button btnAddNew;

    private String[] roles = { "Waiter", "Cooker" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        etName = findViewById(R.id.et_add_employee_name);
        spinnerRole = findViewById(R.id.spinner_role);
        spinnerRole.setAdapter(new ArrayAdapter<>(this, R.layout.item_spinner, roles));

        btnAddNew = findViewById(R.id.btn_add_new_employee);
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String converted = convertToUsername(etName.getText().toString());
                Log.d(AddEmployeeActivity.class.getSimpleName(), converted);
                Toast.makeText(AddEmployeeActivity.this, converted, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String convertToUsername(String name) {
        StringBuilder sb = new StringBuilder();
        String[] temp = name.split("\\s+");
        sb.append(temp[temp.length - 1].toLowerCase());
        for(int i = temp.length - 1; i >= 0; i--) {
            sb.append((char) (temp[i].charAt(0) + 32));
        }

        return sb.substring(0,sb.length() - 1);
    }
}
