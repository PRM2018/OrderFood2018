package com.example.tungpham.orderfood.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tungpham.orderfood.R;
import com.example.tungpham.orderfood.entity.Employee;
import com.example.tungpham.orderfood.model.EmployeeModel;

public class ProfileActivity extends AppCompatActivity {


    private static final String TAG = ProfileActivity.class.getSimpleName();

    private EditText etAddress;
    private EditText etPhone;
    private TextView etUserID;
    private TextView etEmpName;
    private Spinner spRole;

    private EmployeeModel empDAO;
    private int empRole;
    private Employee emp;

    private final String[] roles = { "Admin", "Waiter", "Cooker" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        init();

        setup();
    }

    private void init(){
        empDAO = new EmployeeModel();
        emp = new Employee();
        Intent intent = getIntent();
        int empID = intent.getIntExtra("empID",1);
        emp = empDAO.getEmployeeProfileByID(empID);

        etAddress = findViewById(R.id.et_user_address);
        etPhone = findViewById(R.id.et_user_phone);
        spRole = findViewById(R.id.sp_user_role);
        etUserID = findViewById(R.id.tv_user_id);
        etEmpName = findViewById(R.id.tv_user_name);


        etAddress.setText(emp.getEmpAddress());
        etPhone.setText(emp.getEmpPhone());
        etUserID.setText(emp.getRoleName());
        etEmpName.setText(emp.getEmpName());
        empRole = emp.getEmpType();
    }

    private void setup(){
        // Data for spinner
        SparseArray<String> spinnerArray = new SparseArray<>();
        for (int i = 0; i < 3; i++) {
            spinnerArray.put(i + 1, roles[i]);
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roles);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spRole.setAdapter(adapter);

        // Setup view
        //int empID = getIntent().getIntExtra("empID", 1);
        spRole.setSelection(empRole - 1);
        switch (empRole) {
            case 1:
                disableView(etAddress, etPhone);
                enableView(spRole);
                break;
            default:
                enableView(etAddress, etPhone);
                disableView(spRole);
        }

        //        findViewById(R.id.btn_profile_save).setOnClickListener(v -> finish());
        findViewById(R.id.btn_profile_cancel).setOnClickListener(v -> finish());
    }


    private void enableView(View... views) {
        for (View view : views) {
            view.setEnabled(true);
            if (!(view instanceof Spinner)) {
                view.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            }
        }
    }

    private void disableView(View... views) {
        for (View view : views) {
            view.setEnabled(false);
            if (!(view instanceof Spinner)) {
                view.setBackgroundColor(getResources().getColor(R.color.colorGray));
            }
        }
    }

    public void saveInfo(View v) {
        empDAO = new EmployeeModel();

        etAddress = findViewById(R.id.et_user_address);
        etPhone = findViewById(R.id.et_user_phone);
        spRole = findViewById(R.id.sp_user_role);
        etUserID = findViewById(R.id.tv_user_id);

        String address = etAddress.getText().toString();
        String mobile = etPhone.getText().toString();
        int empID = emp.getEmpID();
        int roleID = emp.getEmpType();

        boolean check = empDAO.updateEmpInfo(address, mobile, roleID, empID);
        if (check) {
            Toast.makeText(ProfileActivity.this, "Update profile successfully", Toast.LENGTH_LONG)
                    .show();
        } else {
            Toast.makeText(ProfileActivity.this, "Update profile unsuccessfully", Toast.LENGTH_LONG)
                    .show();
        }
        finish();
    }
}
