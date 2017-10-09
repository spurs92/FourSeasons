package com.spurs.fourseasons;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class SpringActivity extends AppCompatActivity {

    TabLayout tabLayout;

    SpringPageAdapter springPageAdapter;
    SummerPageAdapter summerPageAdapter;
    AutumnPageAdapter autumnPageAdapter;
    WinterPageAdapter winterPageAdapter;

    ViewPager viewPager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spring);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        Intent intent=getIntent();

        String barName=intent.getStringExtra("toolbar");
        toolbar.setTitle(barName);

        if(barName.toString().equals("spring")){
            tabLayout=(TabLayout)findViewById(R.id.layout_tab);
            springPageAdapter=new SpringPageAdapter(getSupportFragmentManager());
            viewPager=(ViewPager)findViewById(R.id.viewPager);
            viewPager.setAdapter(springPageAdapter);
            tabLayout.setupWithViewPager(viewPager,true);
        }
        else if(barName.toString().equals("summer")){
            tabLayout=(TabLayout)findViewById(R.id.layout_tab);
            summerPageAdapter=new SummerPageAdapter(getSupportFragmentManager());
            viewPager=(ViewPager)findViewById(R.id.viewPager);
            viewPager.setAdapter(summerPageAdapter);
            tabLayout.setupWithViewPager(viewPager,true);
        }
        else if(barName.toString().equals("autumn")){
            tabLayout=(TabLayout)findViewById(R.id.layout_tab);
            autumnPageAdapter=new AutumnPageAdapter(getSupportFragmentManager());
            viewPager=(ViewPager)findViewById(R.id.viewPager);
            viewPager.setAdapter(autumnPageAdapter);
            tabLayout.setupWithViewPager(viewPager,true);
        }
        else if(barName.toString().equals("winter")){
            tabLayout=(TabLayout)findViewById(R.id.layout_tab);
            winterPageAdapter=new WinterPageAdapter(getSupportFragmentManager());
            viewPager=(ViewPager)findViewById(R.id.viewPager);
            viewPager.setAdapter(winterPageAdapter);
            tabLayout.setupWithViewPager(viewPager,true);
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.stop_anim,R.anim.right_out_anim);
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.stop_anim,R.anim.right_out_anim);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }




}
