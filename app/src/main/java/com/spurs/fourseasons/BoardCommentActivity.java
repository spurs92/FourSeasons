package com.spurs.fourseasons;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class BoardCommentActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<BoardCommentItem> boardCommentItems=new ArrayList<>();

    BoardCommentAdapter commentAdapter;

    TextView userNick,userContent;

    FirebaseDatabase database;
    DatabaseReference myRef;
    Intent intent;

    FirebaseAuth mAuth=FirebaseAuth.getInstance();

    EditText commentContent;

    CircleImageView userImg;
    TextView date;

    String currentdate;

    Uri photoUrl;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_comment);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("게시판");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        commentContent=(EditText)findViewById(R.id.edit_contentText);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        commentAdapter=new BoardCommentAdapter(boardCommentItems,this);
        recyclerView.setAdapter(commentAdapter);

        userNick=(TextView)findViewById(R.id.tv_nickName);
        userContent=(TextView)findViewById(R.id.tv_contentText);
        userImg=(CircleImageView)findViewById(R.id.userImg);
        date=(TextView)findViewById(R.id.tv_date);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


        Date date1=new Date(System.currentTimeMillis());
        SimpleDateFormat CurDateFormat=new SimpleDateFormat("yyyy/MM/dd a hh:mm");
        currentdate=CurDateFormat.format(date1);

        intent=getIntent();

        userNick.setText(intent.getStringExtra("userName"));
        userContent.setText(intent.getStringExtra("userContentText"));
        Glide.with(this).load(intent.getStringExtra("imgUri")).into(userImg);
        date.setText(intent.getStringExtra("date"));

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        myRef.child("users").child(intent.getStringExtra("userid")).child("Comment").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                BoardCommentItem boardCommentItem = dataSnapshot.getValue(BoardCommentItem.class);
                boardCommentItems.add(new BoardCommentItem(boardCommentItem.name,boardCommentItem.comment,boardCommentItem.userImgUri,boardCommentItem.date));

                commentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) { }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            for (UserInfo profile : user.getProviderData()) {
                // Id of the provider (ex: google.com)
                photoUrl = profile.getPhotoUrl();
            }
    }

    public void clickBtn(View v){
        BoardCommentItem boardCommentItem = new BoardCommentItem(mAuth.getCurrentUser().getDisplayName(),commentContent.getText().toString(),photoUrl.toString(),currentdate);
        myRef.child("users").child(intent.getStringExtra("userid")).child("Comment").push().setValue(boardCommentItem);
        commentContent.setText("");
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

        switch (id){
            case android.R.id.home:
                finish();
                return  true;
        }

        return super.onOptionsItemSelected(item);
    }

}
