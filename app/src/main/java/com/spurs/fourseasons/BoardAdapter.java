package com.spurs.fourseasons;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by alfo06-11 on 2017-08-23.
 */

public class BoardAdapter extends RecyclerView.Adapter {

    ArrayList<BoardItem> boardItems;
    Context context;

    FirebaseDatabase database= FirebaseDatabase.getInstance();
    DatabaseReference myRef= database.getReference();

    DataSnapshot dataSnapshot;

    public BoardAdapter(ArrayList<BoardItem> boardItems, Context context) {
        this.boardItems = boardItems;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView= LayoutInflater.from(context).inflate(R.layout.board_item,parent,false);

        BoardAdapter.ViewHolder holder=new BoardAdapter.ViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).name.setText(boardItems.get(position).name);
        ((ViewHolder)holder).contentText.setText(boardItems.get(position).contentText);
    }

    @Override
    public int getItemCount() {
        return boardItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        View itemView;

        TextView name;
        TextView contentText;

        public ViewHolder(final View itemView) {
            super(itemView);
            this.itemView=itemView;

            name=(TextView)itemView.findViewById(R.id.tv_nickName);
            contentText=(TextView)itemView.findViewById(R.id.tv_contentText);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String userid=boardItems.get(getLayoutPosition()).userid;
                    Toast.makeText(context, userid, Toast.LENGTH_SHORT).show();

                    BoardCommentItem boardCommentItem = new BoardCommentItem("aaa","bbb");
                    myRef.child("users").child(userid).child("Comment").setValue(boardCommentItem);

                    Intent intent=new Intent(context,BoardCommentActivity.class);
                    context.startActivity(intent);

                }
            });
        }
    }
}
