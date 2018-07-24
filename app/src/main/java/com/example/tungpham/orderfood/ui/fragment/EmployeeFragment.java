package com.example.tungpham.orderfood.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
    Button btn_go_addEmployeePage;
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
        View view=inflater.inflate(R.layout.fragment_employee, container, false);

        // List view Employee
        lv_employee=view.findViewById(R.id.lv_employee);
        ArrayList<Employee> listEmployee = new ArrayList<>();
        EmployeeModel em=new EmployeeModel();
        listEmployee=em.getAllEmpExceptAdmin();
        ListEmployeeAdapter empAdapter = new ListEmployeeAdapter(view.getContext(), R.layout.item_list_employee, listEmployee);
        lv_employee.setAdapter(empAdapter);


//        ArrayList<Employee> listEmployee=em.getAllEmpExceptAdmin();
//        ArrayAdapter<Employee> arrayAdapter=new ArrayAdapter(view.getContext(),android.R.layout.simple_list_item_1,listEmployee);
//        lv_employee.setAdapter(arrayAdapter);
//        final ArrayList<Employee> finalListEmployee = listEmployee;
//        lv_employee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { // Set when click each Employee
//                Intent intent=new Intent(view.getContext(),EditEmployeeActivity.class);
//                int eId = finalListEmployee.get(position).getEmpID();
//                intent.putExtra("eId", eId);
//                startActivity(intent);
//            }
//        });
//
//




        // Go to Add Employee Page
        btn_go_addEmployeePage=view.findViewById(R.id.btn_go_addEmployeePage);
        btn_go_addEmployeePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddEmployeeActivity.class);
                startActivity(intent);
            }
        });

        return view;

    }
}
