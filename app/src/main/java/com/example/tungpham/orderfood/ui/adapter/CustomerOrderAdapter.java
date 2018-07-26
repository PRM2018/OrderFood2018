package com.example.tungpham.orderfood.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tungpham.orderfood.R;
import com.example.tungpham.orderfood.entity.Customer;
import com.example.tungpham.orderfood.entity.CustomerOrder;

import java.util.List;

/**
 * Created by Tung Pham on 7/25/2018.
 */

public class CustomerOrderAdapter extends ArrayAdapter<CustomerOrder> {
    private Context context;
    private int resource;
    private List<CustomerOrder> arrOrder;

    public CustomerOrderAdapter(@NonNull Context context, int resource, @NonNull List<CustomerOrder> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrOrder = arrOrder;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_food_order, parent, false);
            viewHolder.rn = (TextView) convertView.findViewById(R.id.tv_food_id);
            viewHolder.foodName = (TextView) convertView.findViewById(R.id.tv_food_name);
            viewHolder.quantity = (TextView) convertView.findViewById(R.id.iv_food_quantity);
            viewHolder.price = (TextView) convertView.findViewById(R.id.iv_food_price);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CustomerOrder cusOrder = (CustomerOrder) getItem(position);
        viewHolder.rn.setText(String.valueOf(cusOrder.getRn()));
        viewHolder.foodName.setText(cusOrder.getFoodName());
        viewHolder.quantity.setText(String.valueOf(cusOrder.getQuantity()));
        viewHolder.price.setText(String.valueOf(cusOrder.getPrice()*cusOrder.getQuantity()));

        return convertView;
    }

    public class ViewHolder {
        TextView rn, foodName, quantity, price;
    }
}
