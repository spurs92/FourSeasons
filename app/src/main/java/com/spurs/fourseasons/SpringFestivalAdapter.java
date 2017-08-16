package com.spurs.fourseasons;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
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

public class SpringFestivalAdapter extends RecyclerView.Adapter {

    ArrayList<FestivalItem> festivalItems;
    Context context;

    public SpringFestivalAdapter(ArrayList<FestivalItem> festivalItems, Context context) {
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

        Intent intent;
        String firstUrl;
        String secondUrl;

        public ViewHolder(final View itemView) {
            super(itemView);
            this.itemView=itemView;

            titleImg=(ImageView)itemView.findViewById(R.id.title_img);
            titleText=(TextView)itemView.findViewById(R.id.title_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String imgUrl=festivalItems.get(getLayoutPosition()).imgUrl;
                    String title=festivalItems.get(getLayoutPosition()).title;

                    switch (getLayoutPosition()){

                        case 0:
                            firstUrl="http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=2e6cMrrGABpQS6omz5Zd4OjKpXHK3zS4hdatS4MnT5y43aB4cR7LE3H3c2u7km477%2BWVKyNvcx9ZwopchevQgg%3D%3D&contentTypeId=15&contentId=1817686&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y&_type=json";
                            secondUrl="http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?ServiceKey=2e6cMrrGABpQS6omz5Zd4OjKpXHK3zS4hdatS4MnT5y43aB4cR7LE3H3c2u7km477%2BWVKyNvcx9ZwopchevQgg%3D%3D&contentTypeId=15&contentId=1817686&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&introYN=Y&_type=json";
                            intent=new Intent(context,FestivalDetailActivity.class);
                            intent.putExtra("firstUrl",firstUrl);
                            intent.putExtra("secondUrl",secondUrl);
                            intent.putExtra("titleImg", imgUrl);
                            intent.putExtra("titleText", title);
                            context.startActivity(intent);
                            break;

                        case 1:
                            firstUrl="http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=2e6cMrrGABpQS6omz5Zd4OjKpXHK3zS4hdatS4MnT5y43aB4cR7LE3H3c2u7km477%2BWVKyNvcx9ZwopchevQgg%3D%3D&contentTypeId=15&contentId=2002493&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y&_type=json";
                            secondUrl="http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?ServiceKey=2e6cMrrGABpQS6omz5Zd4OjKpXHK3zS4hdatS4MnT5y43aB4cR7LE3H3c2u7km477%2BWVKyNvcx9ZwopchevQgg%3D%3D&contentTypeId=15&contentId=2002493&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&introYN=Y&_type=json";
                            intent=new Intent(context,FestivalDetailActivity.class);
                            intent.putExtra("firstUrl",firstUrl);
                            intent.putExtra("secondUrl",secondUrl);
                            intent.putExtra("titleImg", imgUrl);
                            intent.putExtra("titleText", title);
                            context.startActivity(intent);
                            break;

                        case 2:
                            firstUrl="http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=2e6cMrrGABpQS6omz5Zd4OjKpXHK3zS4hdatS4MnT5y43aB4cR7LE3H3c2u7km477%2BWVKyNvcx9ZwopchevQgg%3D%3D&contentTypeId=15&contentId=525755&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y&_type=json";
                            secondUrl="http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?ServiceKey=2e6cMrrGABpQS6omz5Zd4OjKpXHK3zS4hdatS4MnT5y43aB4cR7LE3H3c2u7km477%2BWVKyNvcx9ZwopchevQgg%3D%3D&contentTypeId=15&contentId=525755&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&introYN=Y&_type=json";
                            intent=new Intent(context,FestivalDetailActivity.class);
                            intent.putExtra("firstUrl",firstUrl);
                            intent.putExtra("secondUrl",secondUrl);
                            intent.putExtra("titleImg", imgUrl);
                            intent.putExtra("titleText", title);
                            context.startActivity(intent);
                            break;
                        case 3:
                            firstUrl="http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=2e6cMrrGABpQS6omz5Zd4OjKpXHK3zS4hdatS4MnT5y43aB4cR7LE3H3c2u7km477%2BWVKyNvcx9ZwopchevQgg%3D%3D&contentTypeId=15&contentId=1801281&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y&_type=json";
                            secondUrl="http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?ServiceKey=2e6cMrrGABpQS6omz5Zd4OjKpXHK3zS4hdatS4MnT5y43aB4cR7LE3H3c2u7km477%2BWVKyNvcx9ZwopchevQgg%3D%3D&contentTypeId=15&contentId=1801281&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&introYN=Y&_type=json";
                            intent=new Intent(context,FestivalDetailActivity.class);
                            intent.putExtra("firstUrl",firstUrl);
                            intent.putExtra("secondUrl",secondUrl);
                            intent.putExtra("titleImg", imgUrl);
                            intent.putExtra("titleText", title);
                            context.startActivity(intent);
                            break;
                        case 4:
                            firstUrl="http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=2e6cMrrGABpQS6omz5Zd4OjKpXHK3zS4hdatS4MnT5y43aB4cR7LE3H3c2u7km477%2BWVKyNvcx9ZwopchevQgg%3D%3D&contentTypeId=15&contentId=1269314&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y&_type=json";
                            secondUrl="http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?ServiceKey=2e6cMrrGABpQS6omz5Zd4OjKpXHK3zS4hdatS4MnT5y43aB4cR7LE3H3c2u7km477%2BWVKyNvcx9ZwopchevQgg%3D%3D&contentTypeId=15&contentId=1269314&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&introYN=Y&_type=json";
                            intent=new Intent(context,FestivalDetailActivity.class);
                            intent.putExtra("firstUrl",firstUrl);
                            intent.putExtra("secondUrl",secondUrl);
                            intent.putExtra("titleImg", imgUrl);
                            intent.putExtra("titleText", title);
                            context.startActivity(intent);
                            break;
                    }
                }
            });

        }
    }

}


