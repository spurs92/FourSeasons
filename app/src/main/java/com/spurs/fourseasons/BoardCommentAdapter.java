package com.spurs.fourseasons;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by alfo06-11 on 2017-08-29.
 */

public class BoardCommentAdapter extends RecyclerView.Adapter {

    ArrayList<BoardCommentItem> boardCommentItems;
    Context context;

    public BoardCommentAdapter(ArrayList<BoardCommentItem> boardCommentItems, Context context) {
        this.boardCommentItems = boardCommentItems;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.comment_item,parent,false);

        BoardCommentAdapter.ViewHolder holder=new BoardCommentAdapter.ViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BoardCommentAdapter.ViewHolder)holder).name.setText(boardCommentItems.get(position).name);
        ((BoardCommentAdapter.ViewHolder)holder).contentText.setText(boardCommentItems.get(position).comment);
        Glide.with(context).load(boardCommentItems.get(position).userImgUri).into(((BoardCommentAdapter.ViewHolder) holder).userImg);
        ((BoardCommentAdapter.ViewHolder)holder).date.setText(boardCommentItems.get(position).date);

        Log.i("aaaaaaaaa",boardCommentItems.get(position).userImgUri);
    }

    @Override
    public int getItemCount() {
        return boardCommentItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        View itemView;

        TextView name;
        TextView contentText;
        ImageView userImg;
        TextView date;

        public ViewHolder(final View itemView) {
            super(itemView);
            this.itemView=itemView;

            name=(TextView)itemView.findViewById(R.id.tv_nickName);
            contentText=(TextView)itemView.findViewById(R.id.tv_contentText);
            userImg=(ImageView) itemView.findViewById(R.id.userImg);
            date=(TextView)itemView.findViewById(R.id.tv_date);
        }
    }
}
