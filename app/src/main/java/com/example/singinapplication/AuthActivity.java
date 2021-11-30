package com.example.singinapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import utils.PreferenceUtil;

public class AuthActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    LoginAndRegisterViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_regester);
        init();

        String userid = PreferenceUtil.getInstance(this).getUserId();
        if(!userid.isEmpty()){

            goToHome();
        }
    }
    private void init()
    {
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_page);
        adapter = new LoginAndRegisterViewAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void goToHome()
    {
        Intent intent = new Intent(AuthActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}