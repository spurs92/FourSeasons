package com.spurs.fourseasons;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;
import com.kakao.util.KakaoParameterException;

public class PlaceDetailActivvity extends AppCompatActivity {

    ImageView imgToolbar;
    Toolbar toolbar;

    CollapsingToolbarLayout collapsing;

    String titleText;
    String titleImg;
    double mapx;
    double mapy;
    String addr;
    String overview;
    String hp;
    String info;
    String rest;

    TextView tvAddr, tvOverview, tvHp, tvRest, tvInfo;

    GoogleMap map;
    MySupportMapFragment mySupportMapFragment;
    NestedScrollView nestedScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail_activvity);

        Intent intent = getIntent();
        titleImg = intent.getStringExtra("titleImg");
        titleText = intent.getStringExtra("titleText");
        String mapxx=intent.getStringExtra("mapx");
        String mapyy=intent.getStringExtra("mapy");
        addr = intent.getStringExtra("addr");
        overview = intent.getStringExtra("overview");
        hp = intent.getStringExtra("hp");
        rest = intent.getStringExtra("rest");
        info = intent.getStringExtra("info");

        mapx=Double.parseDouble(mapxx);
        mapy=Double.parseDouble(mapyy);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        tvAddr=(TextView)findViewById(R.id.tv_addr);
        tvOverview=(TextView)findViewById(R.id.tv_overview);
        tvHp=(TextView)findViewById(R.id.tv_homepage);
        tvRest=(TextView)findViewById(R.id.tv_rest);
        tvInfo=(TextView)findViewById(R.id.tv_info);

        tvAddr.setText(addr);
        tvOverview.setText(overview);
        tvHp.setText(hp);
        tvRest.setText(rest);
        tvInfo.setText(info);

        tvHp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(hp));
                startActivity(intent);
            }
        });

        tvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+info));
                startActivity(intent);
            }
        });

        collapsing = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsing.setTitle(titleText);
        collapsing.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsing.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);

        imgToolbar = (ImageView) findViewById(R.id.img_toolbar);
        Glide.with(this).load(titleImg).into(imgToolbar);

        nestedScrollView=(NestedScrollView)findViewById(R.id.nested);

        mySupportMapFragment = (MySupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mySupportMapFragment.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap googleMap) {
                map=googleMap;

                map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mapy, mapx), 15));

                MarkerOptions marker=new MarkerOptions();
                marker.title("aaa");
                marker.position(new LatLng(mapy, mapx));
                marker.flat(true);
                marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                map.addMarker(marker);

                UiSettings settings=map.getUiSettings();
                settings.setZoomControlsEnabled(true);
            }
        });

        if(mySupportMapFragment != null){

            mySupportMapFragment.setListener(new MySupportMapFragment.OnTouchListener() {
                @Override
                public void onTouch() {
                    nestedScrollView.requestDisallowInterceptTouchEvent(true);
                }
            });

        }
    }

    @Override
    public void onBackPressed() {
        finish();
        //overridePendingTransition(R.anim.stop_anim,R.anim.right_out_anim);
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        Intent intent;

        switch (id){
            case android.R.id.home:
                finish();
                return  true;

            case R.id.item_share:

                shareKakao();

                break;

            case R.id.item_home:
                intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_detail_icon,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void shareKakao(){
        try {
            KakaoLink kakaoLink = KakaoLink.getKakaoLink(this);
            KakaoTalkLinkMessageBuilder kakaoTalkBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();

            kakaoTalkBuilder.addText("["+titleText+"] - 정보");
            kakaoTalkBuilder.addImage(titleImg,160,160);
            kakaoTalkBuilder.addAppButton("앱 실행 혹은 다운로드");
            kakaoLink.sendMessage(kakaoTalkBuilder,this);

        } catch (KakaoParameterException e) {
            e.printStackTrace();
        }
    }
}
