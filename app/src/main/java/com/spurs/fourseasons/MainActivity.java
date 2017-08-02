package com.spurs.fourseasons;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {


    ActionBarDrawerToggle drawerToggle;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ImageView springImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_bar);

        drawerLayout=(DrawerLayout)findViewById(R.id.layout_drawer);
        navigationView=(NavigationView)findViewById(R.id.navi);

        navigationView.setItemIconTintList(null);

        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        springImg=(ImageView)findViewById(R.id.spring_img);
        Glide.with(this).load(R.drawable.spring).into(springImg);


    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.stop_anim,R.anim.right_out_anim);
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        drawerToggle.onOptionsItemSelected(item);

        int id=item.getItemId();

        if(id==R.id.item){
            Intent intent=new Intent(this,BookmarkActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.rignt_in_anim,R.anim.stop_anim);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_icon,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void clickSpring(View v){

    }

    public void clickSummer(View v){

    }

    public void clickAutumn(View v){

    }

    public void clickWinter(View v){

    }



}
