package com.spurs.fourseasons;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {


    ActionBarDrawerToggle drawerToggle;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    NavigationView navigationViewTwo;

    ImageView springImg,summerImg,autumnImg,winterImg;

    Intent intent;

    ////////////////////drawer_header1
    SignInButton signInButton;
    FirebaseAuth mAuth;
    GoogleApiClient mGoogleApiClient;
    FirebaseAuth.AuthStateListener mAuthListener;

    String userEmail;
    String userName;
    Uri imgUri;

    FirebaseDatabase database;
    DatabaseReference myRef;

    CircleImageView userImg;
    CircleImageView userLogoutImg;

    TextView tvLogin, tvInfo;
    TextView tvLoginText;
    TextView tvEmail, tvName;
    TextView logout;

    ImageView emailImg,nameImg;
    ImageView loginBefore, loginAfter;
    ImageView logoutX;

    FirebaseUser user;

    ////////////////////drawer_header2
    TextView tvTemp, tvDescription, tvTempMax, tvTempMin, tvHumidity, tvClouds;
    ImageView imgWeather;

    RequestQueue requestQueue;
    String description_data = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_bar);

        drawerLayout=(DrawerLayout)findViewById(R.id.layout_drawer);
        navigationView=(NavigationView)findViewById(R.id.navi);
        navigationViewTwo=(NavigationView)findViewById(R.id.naviTwo);

        navigationViewTwo.setItemIconTintList(null);

        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        springImg=(ImageView)findViewById(R.id.spring_img);
        Glide.with(this).load(R.drawable.spring).into(springImg);
        summerImg=(ImageView)findViewById(R.id.summer_img);
        Glide.with(this).load(R.drawable.summer).into(summerImg);
        autumnImg=(ImageView)findViewById(R.id.autumn_img);
        Glide.with(this).load(R.drawable.autumn).into(autumnImg);
        winterImg=(ImageView)findViewById(R.id.winter_img);
        Glide.with(this).load(R.drawable.winter).into(winterImg);


        ////////////////////////////////////////////////////////////////// Configure Google Sign In

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mAuth=FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        /////////////////////////////////////////////////////////////////////////////첫번째 헤더
        View vTwo=navigationView.getHeaderView(0);
        userImg=(CircleImageView)vTwo.findViewById(R.id.circle_userImg);
        userLogoutImg=(CircleImageView)vTwo.findViewById(R.id.circle_userLogoutImg);
        logout=(TextView)vTwo.findViewById(R.id.tv_logout);
        tvLogin=(TextView)vTwo.findViewById(R.id.tv_login);
        tvInfo=(TextView)vTwo.findViewById(R.id.tv_info);
        tvEmail=(TextView)vTwo.findViewById(R.id.tv_email);
        tvName=(TextView)vTwo.findViewById(R.id.tv_name);
        tvLoginText=(TextView)vTwo.findViewById(R.id.tv_loginText);

        emailImg=(ImageView)vTwo.findViewById(R.id.email_img);
        nameImg=(ImageView)vTwo.findViewById(R.id.name_Img);

        loginBefore=(ImageView)vTwo.findViewById(R.id.loginBefore_Img);
        loginAfter=(ImageView)vTwo.findViewById(R.id.loginAfter_Img);
        logoutX=(ImageView)vTwo.findViewById(R.id.logoutX_Img);

        signInButton=(SignInButton)vTwo.findViewById(R.id.singBtn);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, 10);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);     // 여기서 this는 Activity의 this

                // 여기서 부터는 알림창의 속성 설정
                builder.setTitle("로그아웃")        // 제목 설정
                        .setMessage("로그아웃 하시겠습니까?")        // 메세지 설정
                        .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                        .setPositiveButton("확인", new DialogInterface.OnClickListener(){
                            // 확인 버튼 클릭시 설정
                            public void onClick(DialogInterface dialog, int whichButton){
                                mAuth.signOut();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener(){
                            // 취소 버튼 클릭시 설정
                            public void onClick(DialogInterface dialog, int whichButton){
                                dialog.cancel();
                            }
                        });

                AlertDialog dialog = builder.create();    // 알림창 객체 생성
                dialog.show();    // 알림창 띄우기

            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    for (UserInfo profile : user.getProviderData()) {

                        imgUri=profile.getPhotoUrl();
                        Glide.with(getApplicationContext()).load(imgUri).into(userImg);
                        Log.i("userUri1", String.valueOf(imgUri));

                    }

                    tvLogin.setVisibility(View.INVISIBLE);
                    tvInfo.setVisibility(View.VISIBLE);

                    tvLoginText.setVisibility(View.INVISIBLE);
                    tvEmail.setVisibility(View.VISIBLE);
                    tvName.setVisibility(View.VISIBLE);

                    tvEmail.setText(mAuth.getCurrentUser().getEmail());
                    tvName.setText(mAuth.getCurrentUser().getDisplayName());

                    signInButton.setVisibility(View.INVISIBLE);
                    logout.setVisibility(View.VISIBLE);
                    emailImg.setVisibility(View.VISIBLE);
                    nameImg.setVisibility(View.VISIBLE);
                    userLogoutImg.setVisibility(View.INVISIBLE);
                    userImg.setVisibility(View.VISIBLE);

                    userEmail=mAuth.getCurrentUser().getUid();
                    userName=mAuth.getCurrentUser().getDisplayName();

                    loginBefore.setVisibility(View.INVISIBLE);
                    loginAfter.setVisibility(View.VISIBLE);
                    logoutX.setVisibility(View.VISIBLE);


                } else {
                    // User is signed out
                    userImg.setVisibility(View.VISIBLE);
                    logout.setVisibility(View.INVISIBLE);

                    tvLogin.setVisibility(View.VISIBLE);
                    tvInfo.setVisibility(View.INVISIBLE);

                    tvLoginText.setVisibility(View.VISIBLE);
                    tvEmail.setVisibility(View.INVISIBLE);
                    tvName.setVisibility(View.INVISIBLE);

                    signInButton.setVisibility(View.VISIBLE);
                    emailImg.setVisibility(View.INVISIBLE);
                    nameImg.setVisibility(View.INVISIBLE);
                    userLogoutImg.setVisibility(View.VISIBLE);
                    userImg.setVisibility(View.INVISIBLE);

                    loginBefore.setVisibility(View.VISIBLE);
                    loginAfter.setVisibility(View.INVISIBLE);
                    logoutX.setVisibility(View.INVISIBLE);

                }
                // ...
            }
        };

        ////////////////////////////////////////////////////////////////////////////// 두번째 헤더
        View v=navigationViewTwo.getHeaderView(0);
        tvTemp=(TextView)v.findViewById(R.id.tv_temp);
        tvDescription=(TextView)v.findViewById(R.id.tv_description);
        tvTempMax=(TextView)v.findViewById(R.id.tv_tempMax);
        tvTempMin=(TextView)v.findViewById(R.id.tv_tempMin);
        tvHumidity=(TextView)v.findViewById(R.id.tv_humidity);
        tvClouds=(TextView)v.findViewById(R.id.tv_clouds);

        imgWeather=(ImageView)v.findViewById(R.id.weatherImg);

        requestQueue= Volley.newRequestQueue(this);

        String url="http://api.openweathermap.org/data/2.5/weather?id=1835848&lang=kr&APPID=462730b6b64130d99945a94751ceaf34";

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray arry=response.getJSONArray("weather");

                    for (int i=0; i<arry.length(); i++){
                        JSONObject jsonObject=arry.getJSONObject(i);
                        String description=jsonObject.getString("description");
                        String icon=jsonObject.getString("icon");

                        description_data=description;
                        new MyThread(icon).start();

                    }
                    tvDescription.setText(description_data);


                    JSONObject object=response.getJSONObject("main");
                    Double temp= object.getDouble("temp");
                    String tempNum = String.format("%.1f ", temp-273.15);
                    tvTemp.setText("현재 온도"+" : "+tempNum+"℃");

                    Double tempMin= object.getDouble("temp_min");
                    String tempMinNum = String.format("%.1f ", tempMin-273.15);
                    tvTempMin.setText("최저 온도"+" : "+tempMinNum+"℃");

                    Double tempMax= object.getDouble("temp_max");
                    String tempMaxNum = String.format("%.1f ", tempMax-273.15);
                    tvTempMax.setText("최고 온도"+" : "+tempMaxNum+"℃");

                    int humidity= object.getInt("humidity");
                    tvHumidity.setText("습도"+" : "+humidity+"%");

                    JSONObject object1=response.getJSONObject("clouds");
                    int clouds = object1.getInt("all");
                    tvClouds.setText("구름"+" : "+clouds+"%");
                    //Log.i("se",description_data);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "인터넷을 연결해주세요", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);

        /////////////////////////////////////////////////////////////////////menu
        navigationViewTwo.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.spring:
                        drawerLayout.closeDrawer(navigationView);
                        intent=new Intent(getApplicationContext(),SpringActivity.class);
                        intent.putExtra("toolbar","spring");
                        startActivity(intent);
                        overridePendingTransition(R.anim.rignt_in_anim,R.anim.stop_anim);
                        break;

                    case R.id.summer:
                        drawerLayout.closeDrawer(navigationView);
                        intent=new Intent(getApplicationContext(),SpringActivity.class);
                        intent.putExtra("toolbar","summer");
                        startActivity(intent);
                        overridePendingTransition(R.anim.rignt_in_anim,R.anim.stop_anim);
                        break;

                    case R.id.autumn:
                        drawerLayout.closeDrawer(navigationView);
                        intent=new Intent(getApplicationContext(),SpringActivity.class);
                        intent.putExtra("toolbar","autumn");
                        startActivity(intent);
                        overridePendingTransition(R.anim.rignt_in_anim,R.anim.stop_anim);
                        break;

                    case R.id.winter:
                        drawerLayout.closeDrawer(navigationView);
                        intent=new Intent(getApplicationContext(),SpringActivity.class);
                        intent.putExtra("toolbar","winter");
                        startActivity(intent);
                        overridePendingTransition(R.anim.rignt_in_anim,R.anim.stop_anim);
                        break;

                    case R.id.board:

                        if (user != null) {
                            drawerLayout.closeDrawer(navigationView);
                            intent=new Intent(getApplicationContext(),BoardActivity.class);
                            intent.putExtra("userEmail",userEmail);
                            intent.putExtra("userName",userName);
                            intent.putExtra("imgUri",imgUri);
                            startActivity(intent);
                            overridePendingTransition(R.anim.left_in_anim,R.anim.stop_anim);

                        } else {
                            Toast.makeText(MainActivity.this, "로그인이 필요한 서비스 입니다", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.inquiry:
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("plain/text");
                        String[] address = {"spurs92@naver.com"};
                        intent.putExtra(Intent.EXTRA_EMAIL, address);
                        startActivity(intent);
                        break;
                }

                return false;
            }
        });

    }////////////////////////////////////////////////////////////onCreate method..

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 10) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "로그인 실패.", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                        }
                        // ...
                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

    class MyThread extends Thread{

        String icon;

        public MyThread(String icon) {
            this.icon = icon;
        }

        @Override
        public void run() {

            try {
                URL url=new URL("http://openweathermap.org/img/w/"+icon+".png");

                InputStream is=url.openStream();

                final Bitmap bm= BitmapFactory.decodeStream(is);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imgWeather.setImageBitmap(bm);
                    }
                });

            } catch (MalformedURLException e) {
                Toast.makeText(MainActivity.this, "주소를 찾지 못함", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(MainActivity.this, "스트림 연결 실패", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        drawerToggle.onOptionsItemSelected(item);

        int id=item.getItemId();

/*        if(id==R.id.item){
            Intent intent=new Intent(this,BookmarkActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.rignt_in_anim,R.anim.stop_anim);

            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.toolbar_icon,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void clickSpring(View v){
        Intent intent=new Intent(this,SpringActivity.class);
        intent.putExtra("toolbar","spring");
        startActivity(intent);
        overridePendingTransition(R.anim.rignt_in_anim,R.anim.stop_anim);
    }

    public void clickSummer(View v){
        Intent intent=new Intent(this,SpringActivity.class);
        intent.putExtra("toolbar","summer");
        startActivity(intent);
        overridePendingTransition(R.anim.rignt_in_anim,R.anim.stop_anim);
    }

    public void clickAutumn(View v){
        Intent intent=new Intent(this,SpringActivity.class);
        intent.putExtra("toolbar","autumn");
        startActivity(intent);
        overridePendingTransition(R.anim.rignt_in_anim,R.anim.stop_anim);
    }

    public void clickWinter(View v){
        Intent intent=new Intent(this,SpringActivity.class);
        intent.putExtra("toolbar","winter");
        startActivity(intent);
        overridePendingTransition(R.anim.rignt_in_anim,R.anim.stop_anim);
    }

}
