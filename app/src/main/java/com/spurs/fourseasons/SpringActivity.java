package com.spurs.fourseasons;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class SpringActivity extends AppCompatActivity {

    TabLayout tabLayout;

    SpringPageAdapter springPageAdapter;
    ViewPager viewPager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spring);

        tabLayout=(TabLayout)findViewById(R.id.layout_tab);

        springPageAdapter=new SpringPageAdapter(getSupportFragmentManager());
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        viewPager.setAdapter(springPageAdapter);

        tabLayout.setupWithViewPager(viewPager,true);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Spring");
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
