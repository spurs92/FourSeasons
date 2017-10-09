package com.spurs.fourseasons;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by spurs on 2017/08-05(005).
 */

public class SummerTab3Fragment extends Fragment {

    RecyclerView recyclerView;

    ArrayList<FestivalItem> items=new ArrayList<>();

    SummerFestivalAdapter summerFestivalAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        items.add(new FestivalItem("http://tong.visitkorea.or.kr/cms/resource/53/2499653_image2_1.JPG","서대문독립민주축제 2017"));
        items.add(new FestivalItem("http://tong.visitkorea.or.kr/cms/resource/76/2483576_image2_1.jpg","서울드럼페스티벌 2017"));
        items.add(new FestivalItem("http://tong.visitkorea.or.kr/cms/resource/42/2499942_image2_1.JPG","성북문화바캉스 2017"));
        items.add(new FestivalItem("http://tong.visitkorea.or.kr/cms/resource/07/2016107_image2_1.jpg","한강몽땅 여름축제 2017"));
        items.add(new FestivalItem("http://tong.visitkorea.or.kr/cms/resource/72/2501272_image2_1.jpg","원 썸머 무비 나이트 2017"));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.spring_tab3_fragment,container,false);

        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        summerFestivalAdapter=new SummerFestivalAdapter(items,this.getContext());
        recyclerView.setAdapter(summerFestivalAdapter);

        return view;
    }

}
