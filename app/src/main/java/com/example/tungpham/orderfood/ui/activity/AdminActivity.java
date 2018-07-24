package com.example.tungpham.orderfood.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.example.tungpham.orderfood.R;
import com.example.tungpham.orderfood.ui.adapter.AdminPagerAdapter;

public class AdminActivity extends AppCompatActivity {

    private TabLayout tlAdmin;
    private AdminPagerAdapter mAdapter;
    private ViewPager vpAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        tlAdmin = findViewById(R.id.tl_admin);
        vpAdmin = findViewById(R.id.vp_admin);
        mAdapter = new AdminPagerAdapter(getSupportFragmentManager());

        vpAdmin.setAdapter(mAdapter);
        tlAdmin.setupWithViewPager(vpAdmin);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_profile) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
