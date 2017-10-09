package com.spurs.fourseasons;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by alfo06-11 on 2017-08-11.
 */

public class PlaceAdapter extends RecyclerView.Adapter {


    ArrayList<PlaceItem> placeItems;
    Context context;

    public PlaceAdapter(ArrayList<PlaceItem> placeItems, Context context) {
        this.placeItems = placeItems;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.festival_item,parent,false);

        ViewHolder holder=new ViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Glide.with(context).load(placeItems.get(position).imgUrl).into(((ViewHolder)holder).titleImg);
        ((ViewHolder)holder).titleText.setText(placeItems.get(position).title);
    }

    @Override
    public int getItemCount() {
        return placeItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView titleImg;
        TextView titleText;

        View itemView;

        Intent intent;


        public ViewHolder(final View itemView) {
            super(itemView);
            this.itemView=itemView;

            titleImg=(ImageView)itemView.findViewById(R.id.title_img);
            titleText=(TextView)itemView.findViewById(R.id.title_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String imgUrl=placeItems.get(getLayoutPosition()).imgUrl;
                    String title=placeItems.get(getLayoutPosition()).title;
                    String addr=placeItems.get(getLayoutPosition()).addr;
                    String mapx=placeItems.get(getLayoutPosition()).mapx;
                    String mapy=placeItems.get(getLayoutPosition()).mapy;
                    String overview=placeItems.get(getLayoutPosition()).overview;
                    String hp=placeItems.get(getLayoutPosition()).hp;
                    String info=placeItems.get(getLayoutPosition()).info;
                    String rest=placeItems.get(getLayoutPosition()).rest;

                    intent=new Intent(context,PlaceDetailActivvity.class);
                    intent.putExtra("titleImg", imgUrl);
                    intent.putExtra("titleText", title);
                    intent.putExtra("addr", addr);
                    intent.putExtra("mapx", mapx);
                    intent.putExtra("mapy", mapy);
                    intent.putExtra("overview", overview);
                    intent.putExtra("hp", hp);
                    intent.putExtra("info", info);
                    intent.putExtra("rest", rest);

                    context.startActivity(intent);
                }
            });

        }
    }

}


