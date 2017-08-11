package com.spurs.fourseasons;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by alfo06-11 on 2017-08-11.
 */

public class FestivalAdapter extends RecyclerView.Adapter {

    ArrayList<FestivalItem> festivalItems;
    Context context;

    public FestivalAdapter(ArrayList<FestivalItem> festivalItems, Context context) {
        this.festivalItems = festivalItems;
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
        Glide.with(context).load(festivalItems.get(position).imgUrl).into(((ViewHolder)holder).titleImg);
        ((ViewHolder)holder).titleText.setText(festivalItems.get(position).title);
    }

    @Override
    public int getItemCount() {
        return festivalItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView titleImg;
        TextView titleText;

        View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;

            titleImg=(ImageView)itemView.findViewById(R.id.title_img);
            titleText=(TextView)itemView.findViewById(R.id.title_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    switch (getLayoutPosition()){
                        case 0:

                            break;
                        case 1:

                            break;
                        case 2:

                            break;
                        case 3:

                            break;
                        case 4:

                            break;
                    }
                }
            });

        }
    }

}


