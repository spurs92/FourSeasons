package com.spurs.fourseasons;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BoardActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<BoardItem> boardItems=new ArrayList<>();

    BoardAdapter adapter;

    FirebaseDatabase database;
    DatabaseReference myRef;

    String userEmail,userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        Intent intent=getIntent();
        userEmail=intent.getStringExtra("userEmail");
        userName=intent.getStringExtra("userName");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("게시판");

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        adapter=new BoardAdapter(boardItems,this);
        recyclerView.setAdapter(adapter);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();


        myRef.child("users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                BoardItem boardItem = dataSnapshot.getValue(BoardItem.class);
                boardItems.add(new BoardItem(boardItem.name,boardItem.contentText));
                //Log.i("dbName",boardItem.name);
                //Log.d("onChildAdded",dataSnapshot.getKey());
                adapter.notifyItemInserted(0);
                adapter.notifyDataSetChanged();

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


    }

    public void clickFab(View v) {
        Intent intent=new Intent(this,BoardWriteActivity.class);
        intent.putExtra("userEmail",userEmail);
        intent.putExtra("userName",userName);
        startActivity(intent);
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
