package com.spurs.fourseasons;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class FestivalDetailActivity extends AppCompatActivity {

    ImageView imgToolbar;
    Toolbar toolbar;

    CollapsingToolbarLayout collapsing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festival_detail);

        Intent intent=getIntent();
        String titlImg=intent.getStringExtra("titleImg");
        String titleText=intent.getStringExtra("titleText");


        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        collapsing=(CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsing.setTitle(titleText);
        collapsing.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsing.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);


        imgToolbar=(ImageView)findViewById(R.id.img_toolbar);
        Glide.with(this).load(titlImg).into(imgToolbar);



    }
}
