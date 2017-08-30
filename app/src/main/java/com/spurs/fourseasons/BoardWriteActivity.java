package com.spurs.fourseasons;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardWriteActivity extends AppCompatActivity {

    EditText contentText;

    FirebaseDatabase database;
    DatabaseReference myRef;

    String userEmail, userName, userid;

    Uri userUri;
    String currentdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_write);

        contentText=(EditText)findViewById(R.id.edit_contentText);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        Intent intent=getIntent();
        userEmail=intent.getStringExtra("userEmail");
        userName=intent.getStringExtra("userName");
        userUri=intent.getParcelableExtra("imgUri");

        Date date=new Date(System.currentTimeMillis());
        SimpleDateFormat CurDateFormat=new SimpleDateFormat("yyyy/MM/dd a hh:mm");
        currentdate=CurDateFormat.format(date);

        userid=myRef.child("users").push().getKey();

    }

    public void clickWrite(View v){

        BoardItem boardItem=new BoardItem(userName,contentText.getText().toString(),userid,userUri.toString(),currentdate);
        myRef.child("users").child(userid).setValue(boardItem);

        finish();
    }

    public void clickCancel(View v){
        finish();
    }


}
