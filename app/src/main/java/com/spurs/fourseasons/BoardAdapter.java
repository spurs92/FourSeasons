package com.spurs.fourseasons;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

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
    }

    @Override
    public int getItemCount() {
        return boardItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        View itemView;

        TextView name;
        TextView contentText;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;

            name=(TextView)itemView.findViewById(R.id.tv_nickName);
            contentText=(TextView)itemView.findViewById(R.id.tv_contentText);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    switch (getItemCount()){

                        case 0:
                            Intent intent=new Intent(context,BoardCommentActivity.class);
                            context.startActivity(intent);
                            break;
                    }
                }
            });
        }
    }
}
