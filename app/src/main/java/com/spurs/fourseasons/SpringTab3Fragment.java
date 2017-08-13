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

public class SpringTab3Fragment extends Fragment {

    RecyclerView recyclerView;

    ArrayList<FestivalItem> items=new ArrayList<>();

    SpringFestivalAdapter festivalAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        items.add(new FestivalItem("http://tong.visitkorea.or.kr/cms/resource/50/495150_image2_1.jpg","국립민속박물관 단오축제 2017"));
        items.add(new FestivalItem("http://tong.visitkorea.or.kr/cms/resource/56/2492956_image2_1.jpg","서울장미축제 2017"));
        items.add(new FestivalItem("http://tong.visitkorea.or.kr/cms/resource/52/1607952_image2_1.jpg","영등포여의도봄꽃축제 2017"));
        items.add(new FestivalItem("http://tong.visitkorea.or.kr/cms/resource/92/1257992_image2_1.jpg","응봉산 개나리축제 2017"));
        items.add(new FestivalItem("http://tong.visitkorea.or.kr/cms/resource/49/755649_image2_1.jpg","한강 서래섬 유채꽃축제 2017"));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.spring_tab3_fragment,container,false);

        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        festivalAdapter=new SpringFestivalAdapter(items,this.getContext());
        recyclerView.setAdapter(festivalAdapter);

        return view;
    }

}
