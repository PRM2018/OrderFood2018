package com.example.tungpham.orderfood.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.tungpham.orderfood.R;
import com.example.tungpham.orderfood.entity.Employee;
import com.example.tungpham.orderfood.model.EmployeeModel;
import com.example.tungpham.orderfood.ui.activity.AddEmployeeActivity;
import com.example.tungpham.orderfood.ui.activity.EditEmployeeActivity;
import com.example.tungpham.orderfood.ui.activity.EmployeeActivity;
import com.example.tungpham.orderfood.ui.adapter.ListEmployeeAdapter;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeFragment extends Fragment {
    ListView lv_employee;
    FloatingActionButton fab_add_employee;

    public EmployeeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_employee, container, false);

        // List view Employee
        lv_employee = view.findViewById(R.id.lv_employee_list);
        ArrayList<Employee> listEmployee = new ArrayList<>();
        EmployeeModel em = new EmployeeModel();
        listEmployee = em.getAllEmpExceptAdmin();
        ListEmployeeAdapter empAdapter = new ListEmployeeAdapter(view.getContext(), R.layout.item_admin, listEmployee);
        lv_employee.setAdapter(empAdapter);
        fab_add_employee=view.findViewById(R.id.fab_add_employee);
        fab_add_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), AddEmployeeActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}