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
import com.example.tungpham.orderfood.entity.Food;
import com.example.tungpham.orderfood.ui.activity.EditEmployeeActivity;
import com.example.tungpham.orderfood.ui.activity.EditFoodActivity;

import java.util.List;

public class ListFoodAdapter extends ArrayAdapter<Food> {
    private Context context;
    private int resource;
    private List<Food> arrFood;

    public ListFoodAdapter(@NonNull Context context, int resource, @NonNull List<Food> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrFood = arrFood;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_admin,parent,false);
            viewHolder.foodNo = (TextView)convertView.findViewById(R.id.tv_item_no);
            viewHolder.foodName = (TextView)convertView.findViewById(R.id.tv_item_name);
            viewHolder.foodQuantity = (TextView) convertView.findViewById(R.id.iv_item_number);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Food food = (Food)getItem(position);
        viewHolder.foodNo.setText(String.valueOf(food.getFoodID()));
        viewHolder.foodName.setText(food.getFoodName().toString());
        viewHolder.foodQuantity.setText(""+food.getFoodQuantity());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditFoodActivity.class);
                intent.putExtra("fId", food.getFoodID());
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    public class ViewHolder{
        TextView foodNo, foodName,foodQuantity;
    }
}
