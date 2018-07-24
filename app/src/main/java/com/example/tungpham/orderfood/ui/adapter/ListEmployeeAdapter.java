package com.example.tungpham.orderfood.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tungpham.orderfood.R;
import com.example.tungpham.orderfood.entity.Employee;
import com.example.tungpham.orderfood.model.EmployeeTypeModel;
import com.example.tungpham.orderfood.ui.activity.EditEmployeeActivity;

import java.util.List;

public class ListEmployeeAdapter  extends ArrayAdapter<Employee> {
    private Context context;
    private int resource;
    private List<Employee> arrEmp;

    public ListEmployeeAdapter(@NonNull Context context, int resource, @NonNull List<Employee> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrEmp = arrEmp;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_employee,parent,false);
            viewHolder.empNo = (TextView)convertView.findViewById(R.id.tv_emp_no);
            viewHolder.empName = (TextView)convertView.findViewById(R.id.tv_emp_name);
            viewHolder.empRoleName = (TextView) convertView.findViewById(R.id.tv_emp_role);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Employee emp = (Employee)getItem(position);

        // Colum ID,Name
        viewHolder.empNo.setText(String.valueOf(emp.getEmpID()));
        viewHolder.empName.setText(emp.getEmpName().toString());

        // Column role
        EmployeeTypeModel etm=new EmployeeTypeModel();
        viewHolder.empRoleName.setText(etm.getEmpTypeName(emp.getEmpType()));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditEmployeeActivity.class);
                intent.putExtra("eId", emp.getEmpID());
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    public class ViewHolder{
        TextView empNo, empName,empRoleName;
    }
}
