package com.example.tungpham.orderfood.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tungpham.orderfood.R;
import com.example.tungpham.orderfood.entity.Table;

import java.util.List;

/**
 * Created by Tung Pham on 7/24/2018.
 */

public class EmployeeTableAdapter extends ArrayAdapter<Table> {
    private Context context;
    private int resource;
    private List<Table> arrTable;

    public EmployeeTableAdapter(@NonNull Context context, int resource, @NonNull List<Table> objects) {
        super(context, resource, objects);
        this.context= context;
        this.resource = resource;
        this.arrTable = arrTable;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_table,parent,false);
            viewHolder.tableNo = (TextView) convertView.findViewById(R.id.tv_table_no);
            viewHolder.tableName = (TextView) convertView.findViewById(R.id.tv_table_name);
            viewHolder.tableStatus = (ImageView) convertView.findViewById(R.id.iv_table_check);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Table table = (Table) getItem(position);
        viewHolder.tableNo.setText(String.valueOf(table.getTableID()));
        viewHolder.tableName.setText(table.getTableName());
        if (table.getTableStatus() == 1) {
            Log.d("Adapter", "Get status");
            viewHolder.tableStatus.setImageResource(R.drawable.checked_checkbox);
            Log.d("Adapter", "Get status 1");
        } else {
            viewHolder.tableStatus.setImageResource(R.drawable.unchecked_checkbox);
            Log.d("Adapter", "Get status 2");
        }

//        convertView.setOnClickListener(v -> {
//            Intent intent = new Intent(context, TableDetailActivity.class);
//            intent.putExtra("TableID", table.getTableID());
//            intent.putExtra("TableName", table.getTableName());
//            context.startActivity(intent);
//        });

        return convertView;
    }

    public class ViewHolder {
        TextView tableNo, tableName;
        ImageView tableStatus;
    }
}
