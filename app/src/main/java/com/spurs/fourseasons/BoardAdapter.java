package com.spurs.fourseasons;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by alfo06-11 on 2017-08-23.
 */

public class BoardAdapter extends RecyclerView.Adapter {

    ArrayList<BoardItem> boardItems;
    Context context;

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
        Glide.with(context).load(boardItems.get(position).userUri).into(((ViewHolder) holder).uerImg);
        ((ViewHolder)holder).date.setText(boardItems.get(position).date);
    }

    @Override
    public int getItemCount() {
        return boardItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        View itemView;

        TextView name;
        TextView contentText;
        CircleImageView uerImg;
        TextView date;

        public ViewHolder(final View itemView) {
            super(itemView);
            this.itemView=itemView;

            name=(TextView)itemView.findViewById(R.id.tv_nickName);
            contentText=(TextView)itemView.findViewById(R.id.tv_contentText);
            uerImg=(CircleImageView)itemView.findViewById(R.id.userImg);
            date=(TextView)itemView.findViewById(R.id.tv_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String userid=boardItems.get(getLayoutPosition()).userid;
                    String userName=boardItems.get(getLayoutPosition()).name;
                    String userContentText=boardItems.get(getLayoutPosition()).contentText;
                    String imgUri=boardItems.get(getLayoutPosition()).userUri;
                    String date=boardItems.get(getLayoutPosition()).date;

                    Intent intent=new Intent(context,BoardCommentActivity.class);
                    intent.putExtra("userid",userid);
                    intent.putExtra("userName",userName);
                    intent.putExtra("userContentText",userContentText);
                    intent.putExtra("imgUri",imgUri);
                    intent.putExtra("date",date);

                    context.startActivity(intent);
                }
            });
        }
    }
}
