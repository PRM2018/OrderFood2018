package com.example.tungpham.orderfood.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.example.tungpham.orderfood.R;
import com.example.tungpham.orderfood.ui.adapter.SectionsPagerAdapter;

public class AdminActivity extends AppCompatActivity {

    private TabLayout tlAdmin;
    private SectionsPagerAdapter mAdapter;
    private ViewPager vpAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        tlAdmin = findViewById(R.id.tl_admin);
        vpAdmin = findViewById(R.id.vp_admin);
        mAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        vpAdmin.setAdapter(mAdapter);
        vpAdmin.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlAdmin));
        tlAdmin.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
