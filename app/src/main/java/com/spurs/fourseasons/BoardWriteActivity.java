package com.spurs.fourseasons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

public class BoardWriteActivity extends AppCompatActivity {

    EditText contentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_write);

        contentText=(EditText)findViewById(R.id.edit_contentText);
    }

    public void clickWrite(View v){

    }

    public void clickCancel(View v){
        finish();
    }


}
