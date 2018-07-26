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
import com.example.tungpham.orderfood.entity.CustomerOrder;
import com.example.tungpham.orderfood.entity.Food;
import com.example.tungpham.orderfood.entity.Order;
import com.travijuu.numberpicker.library.Enums.ActionEnum;
import com.travijuu.numberpicker.library.Interface.ValueChangedListener;
import com.travijuu.numberpicker.library.NumberPicker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tung Pham on 7/25/2018.
 */

public class AddFoodOrderAdapter extends ArrayAdapter<Food> {
    private Context context;
    private int resource;
    private List<Food> arrOrder;
    private ArrayList<Integer> quantityList = new ArrayList<>();

    public ArrayList<Integer> getQuantityList() {
        return quantityList;
    }

    public AddFoodOrderAdapter(@NonNull Context context, int resource, @NonNull List<Food> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrOrder = objects;

        for(int i = 0; i < arrOrder.size(); i++) {
            quantityList.add(0);
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_add_order_food, parent, false);
            viewHolder.no = convertView.findViewById(R.id.tv_order_food_no);
            viewHolder.foodName = convertView.findViewById(R.id.tv_order_food_name);
            viewHolder.foodPrice = convertView.findViewById(R.id.tv_order_food_price);
            viewHolder.addQuantity = convertView.findViewById(R.id.np_order_food_quantity);
            viewHolder.totalPrice = convertView.findViewById(R.id.tv_order_food_price_total);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Food orderFood = getItem(position);
        viewHolder.no.setText(String.valueOf(orderFood.getRn()));
        viewHolder.foodName.setText(orderFood.getFoodName());
        viewHolder.foodPrice.setText(String.valueOf(orderFood.getFoodPrice()));
        valueChange(position, viewHolder.addQuantity, viewHolder.foodPrice, viewHolder.totalPrice);

        return convertView;
    }

    // Example for item_order_food_number_picker
    private void valueChange(int position, NumberPicker numberPicker, TextView tvPrice, TextView tvTotal) {
        numberPicker.setValue(0);
        numberPicker.setValueChangedListener(new ValueChangedListener() {
            @Override
            public void valueChanged(int value, ActionEnum action) {
                quantityList.set(position, value);
                double price = Double.parseDouble(tvPrice.getText().toString());
                tvTotal.setText(String.valueOf(price * value));
            }
        });
    }

    private class ViewHolder {
        TextView no, foodName, foodPrice, totalPrice;
        NumberPicker addQuantity;
    }

}
