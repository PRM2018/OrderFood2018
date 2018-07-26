package com.example.tungpham.orderfood.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tungpham.orderfood.R;
import com.example.tungpham.orderfood.entity.Account;
import com.example.tungpham.orderfood.entity.Employee;
import com.example.tungpham.orderfood.model.EmployeeModel;

import java.sql.SQLException;

public class AddEmployeeActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etAddress;
    private EditText etPhone;
    private Spinner spinnerRole;
    private Button btnAddNew;


    private String[] roles = {"Waiter", "Cooker"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        Intent intent = getIntent();
        final EmployeeModel em = new EmployeeModel();
        etName = findViewById(R.id.et_add_employee_name);
        etAddress = findViewById(R.id.et_add_employee_address);
        etPhone = findViewById(R.id.et_add_employee_phone);


        spinnerRole = findViewById(R.id.spinner_role);
        spinnerRole.setAdapter(new ArrayAdapter<>(this, R.layout.item_spinner, roles));

        btnAddNew = findViewById(R.id.btn_add_new_employee);
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // New Emp
                    String empName = etName.getText().toString();
                    String empAddress = etAddress.getText().toString();
                    String empPhone = etPhone.getText().toString();
                    int roleId = spinnerRole.getSelectedItemPosition() + 2;
                    Employee employeeAdd = new Employee(roleId, empName, empAddress, empPhone, roleId);
                    em.addEmployee(employeeAdd); // Add Employee


                    // New Acc
                    String userName = convertToUsername(etName.getText().toString());
                    String pass = "123";
                    int empId = em.getLastEmpId();
                    Account account = new Account(empId, userName, pass);
                    em.addEmployeeAccount(account); // Add Account
                    Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                    startActivity(intent);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private String convertToUsername(String name) {
        StringBuilder sb = new StringBuilder();
        String[] temp = name.split("\\s+");
        for (int i = temp.length - 2; i >= 0; i--) {
            sb.append(temp[i].toLowerCase().charAt(0));
        }
        return temp[temp.length - 1].toLowerCase() + sb.reverse().toString();
    }
}
