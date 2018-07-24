package com.example.tungpham.orderfood.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.tungpham.orderfood.R;
import com.example.tungpham.orderfood.entity.Food;
import com.example.tungpham.orderfood.model.EmployeeModel;
import com.example.tungpham.orderfood.model.FoodModel;
import com.example.tungpham.orderfood.ui.adapter.ListEmployeeAdapter;
import com.example.tungpham.orderfood.ui.adapter.ListFoodAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodFragment extends Fragment {
    ListView lv_food;
    Button btn_go_addFoodPage;
    public FoodFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_food,container, false);

        // List view Employee
        lv_food=view.findViewById(R.id.lv_food);
        ArrayList<Food> listFood = new ArrayList<>();
        FoodModel em=new FoodModel();
        listFood=em.getAllFood();
        ListFoodAdapter foodAdapter = new ListFoodAdapter(view.getContext(), R.layout.item_list_food, listFood);
        lv_food.setAdapter(foodAdapter);
        return view;
    }
}
