package com.example.tungpham.orderfood.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.tungpham.orderfood.ui.fragment.EmployeeFragment;
import com.example.tungpham.orderfood.ui.fragment.FoodFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new EmployeeFragment();
            default:
                return new FoodFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
