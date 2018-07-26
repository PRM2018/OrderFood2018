package com.example.tungpham.orderfood.ui.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.tungpham.orderfood.R;
import com.example.tungpham.orderfood.entity.Table;
import com.example.tungpham.orderfood.model.CustomerModel;
import com.example.tungpham.orderfood.model.TableModel;
import com.example.tungpham.orderfood.ui.activity.TableDetailActivity;
import java.util.List;

/**
 * Created by Tung Pham on 7/24/2018.
 */

public class EmployeeTableAdapter extends ArrayAdapter<Table> {
    private Context context;
    private int resource;
    private List<Table> arrTable;
    private int tableID;
    private int roleID;

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public EmployeeTableAdapter(@NonNull Context context, int resource,
            @NonNull List<Table> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrTable = arrTable;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_table, parent, false);
            viewHolder.tableNo = (TextView) convertView.findViewById(R.id.tv_table_no);
            viewHolder.tableName = (TextView) convertView.findViewById(R.id.tv_table_name);
            viewHolder.tableStatus = (ImageView) convertView.findViewById(R.id.iv_table_check);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Table table = (Table) getItem(position);
        tableID = table.getTableID();
        viewHolder.tableNo.setText(String.valueOf(table.getTableID()));
        viewHolder.tableName.setText(table.getTableName());
        if (table.getTableStatus() == 1) {
            viewHolder.tableStatus.setImageResource(R.drawable.checked_checkbox);
        } else {
            viewHolder.tableStatus.setImageResource(R.drawable.unchecked_checkbox);
        }

        convertView.setOnClickListener(v -> {
            if (table.getTableStatus() == 1) {
                CustomerModel cm = new CustomerModel();
                int cusID = cm.getCusID(table.getTableID(), 2);
                Intent intent = new Intent(context, TableDetailActivity.class);
                intent.putExtra("roleID", roleID);
                intent.putExtra("tableID", table.getTableID());
                intent.putExtra("TableName", table.getTableName());
                intent.putExtra("cusID", cusID);
                context.startActivity(intent);
            } else {
                showDialog(position);
            }
        });

        return convertView;
    }

    public class ViewHolder {
        TextView tableNo, tableName;
        ImageView tableStatus;
    }

    private void showDialog(int position) {
        Table table = (Table) getItem(position);
        tableID = table.getTableID();
        CustomerModel cm = new CustomerModel();
        TableModel tm = new TableModel();
        Dialog dialog = new Dialog(getContext());
        dialog.setTitle("");
        dialog.setContentView(R.layout.activity_create_customer);
        TextView tableTv = (TextView) dialog.findViewById(R.id.tv_table_id);
        tableTv.setText(String.valueOf(tableID));
        Button acceptBtn = (Button) dialog.findViewById(R.id.btn_accept);
        Button cancelBtn = (Button) dialog.findViewById(R.id.btn_cancel);
        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tableID = Integer.parseInt(
                        ((TextView) dialog.findViewById(R.id.tv_table_id)).getText().toString());
                String cusName =
                        ((TextView) dialog.findViewById(R.id.tv_cus_name)).getText().toString();
                if (cusName.isEmpty() || cusName.length() == 0) {
                    ((TextView) dialog.findViewById(R.id.tv_cus_name)).setError(
                            "Name customer is required!");
                } else {
                    boolean check = cm.insertNewCustomer(cusName, tableID);
                    if (check) {
                        boolean updateTableStatus = tm.updateStatusTable(tableID, 1);
                        boolean updateCusStatus = cm.updateCusStatus(2, 0, tableID);
                        int cusID = cm.getCusID(tableID, 2);
                        Intent intent = new Intent(getContext(), TableDetailActivity.class);
                        intent.putExtra("cusID", cusID);
                        intent.putExtra("tableID", tableID);
                        context.startActivity(intent);
                    }
                }
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
