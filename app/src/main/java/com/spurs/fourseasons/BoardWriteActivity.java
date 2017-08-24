package com.spurs.fourseasons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BoardWriteActivity extends AppCompatActivity {

    EditText contentText;

    FirebaseDatabase database;
    DatabaseReference myRef;

    String userEmail, userName;

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


/*        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                value = dataSnapshot.getValue(String.class);
                //데이터를 화면에 출력해 준다.

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(BoardWriteActivity.this, "DB 등록 실패", Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    public void clickWrite(View v){

        BoardItem boardItem=new BoardItem(userName,contentText.getText().toString());
        myRef.child(userEmail).push().setValue(boardItem);

        finish();
    }

    public void clickCancel(View v){
        finish();
    }


}
