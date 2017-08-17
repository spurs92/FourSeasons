package com.spurs.fourseasons;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FestivalDetailActivity extends AppCompatActivity {

    ImageView imgToolbar;
    Toolbar toolbar;

    CollapsingToolbarLayout collapsing;

    ////////////////////////////////////map
    GoogleMap map;

    MySupportMapFragment mySupportMapFragment;
    NestedScrollView nestedScrollView;

    double mapx, mapy;

    String eventplace;
    String eventhomepage;
    String text;

    ///////////////////////////////////json1
    JsonObjectRequest jsonObjectRequest;
    JsonObjectRequest jsonObjectRequest2;
    RequestQueue requestQueue;

    TextView tvAddr, tvOverview, tvHomepage, tvDate, tvUsecash, tvTel, tvPlace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festival_detail);

        Intent intent = getIntent();
        String titleImg = intent.getStringExtra("titleImg");
        String titleText = intent.getStringExtra("titleText");
        String firstUrl = intent.getStringExtra("firstUrl");
        String secondUrl = intent.getStringExtra("secondUrl");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //text 색상
        collapsing = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsing.setTitle(titleText);
        collapsing.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsing.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);

        imgToolbar = (ImageView) findViewById(R.id.img_toolbar);
        Glide.with(this).load(titleImg).into(imgToolbar);

        /////////////////////////////////////////////////////////////////////////////////volley json
        tvAddr = (TextView) findViewById(R.id.tv_addr);
        tvOverview = (TextView) findViewById(R.id.tv_overview);
        tvHomepage = (TextView) findViewById(R.id.tv_homepage);
        tvDate = (TextView) findViewById(R.id.tv_date);
        tvUsecash = (TextView) findViewById(R.id.tv_usecash);
        tvTel = (TextView) findViewById(R.id.tv_tel);
        tvPlace = (TextView) findViewById(R.id.tv_place);

        requestQueue = Volley.newRequestQueue(this);

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, firstUrl, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONObject object = response.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONObject("item");
                    String addr = object.getString("addr1");
                    tvAddr.setText(addr);

                    final String tel = object.getString("tel");
                    tvTel.setText(tel);
                    tvTel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+tel));
                            startActivity(intent);
                        }
                    });

                    if(object.has("overview")){
                        String overview = object.getString("overview");
                        String html=overview;
                        Document doc=Jsoup.parse(html);
                        String text=doc.text();
                        tvOverview.setText(text);
                    }

                    if (object.has("homepage")) {
                        String homepage = object.getString("homepage");

                        Document doc= Jsoup.parse(homepage);
                        Element ele=doc.getElementsByTag("a").first();
                        if(ele!=null){
                            text=ele.attr("href");
                            tvHomepage.setText(text);
                            tvHomepage.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.setData(Uri.parse(text));
                                    startActivity(intent);
                                }
                            });
                        }
                    }

                    mapx = object.getDouble("mapx");
                    mapy = object.getDouble("mapy");

                    mySupportMapFragment.getMapAsync(new OnMapReadyCallback() {

                        @Override
                        public void onMapReady(GoogleMap googleMap) {

                            map = googleMap;

                            LatLng seoul = new LatLng(mapy, mapx);
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(seoul, 15));
/*                            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                            }
                            map.setMyLocationEnabled(true);*/

                            //맵 세팅
                            UiSettings settings=map.getUiSettings();
                            settings.setZoomControlsEnabled(true);

                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "인터넷을 연결해주세요", Toast.LENGTH_SHORT).show();
            }
        });

        jsonObjectRequest2=new JsonObjectRequest(Request.Method.GET, secondUrl, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONObject object=response.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONObject("item");
                    eventplace=object.getString("eventplace");
                    String eventenddate=object.getString("eventenddate");
                    String eventstartdate=object.getString("eventstartdate");
                    String usetimefestival=object.getString("usetimefestival");

                    if(object.has("eventhomepage")){
                        eventhomepage=object.getString("eventhomepage");

                        Log.i("event",eventhomepage);
                        tvHomepage.setText(eventhomepage);

                        tvHomepage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(eventhomepage));
                                startActivity(intent);
                            }
                        });
                    }

                    tvDate.setText(eventstartdate+" ~ "+eventenddate);
                    tvUsecash.setText(usetimefestival);


                    mySupportMapFragment.getMapAsync(new OnMapReadyCallback() {

                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            map=googleMap;

                            MarkerOptions marker=new MarkerOptions();
                            marker.title(eventplace);
                            marker.position(new LatLng(mapy, mapx));
                            marker.flat(true);
                            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

                            map.addMarker(marker);

                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "인터넷을 연결해주세요", Toast.LENGTH_SHORT).show();
            }
        });


        requestQueue.add(jsonObjectRequest);
        requestQueue.add(jsonObjectRequest2);


        ///////////////////////////////////////////////////////////////////map 고정
        nestedScrollView=(NestedScrollView)findViewById(R.id.nested);

        mySupportMapFragment = (MySupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

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

        if(id==android.R.id.home){
            finish();
            //overridePendingTransition(R.anim.stop_anim,R.anim.right_out_anim);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
