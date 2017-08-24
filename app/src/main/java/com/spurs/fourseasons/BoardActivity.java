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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BoardActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<BoardItem> boardItems=new ArrayList<>();

    BoardAdapter adapter;

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

/*        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                //데이터를 화면에 출력해 준다.
                boardItems.add(new BoardItem("ddd",value));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(BoardActivity.this, "DB 등록 실패", Toast.LENGTH_SHORT).show();
            }
        });*/

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
