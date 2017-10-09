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

public class WinterTab3Fragment extends Fragment {

    RecyclerView recyclerView;

    ArrayList<FestivalItem> items=new ArrayList<>();

    WinterFestivalAdapter winterFestivalAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        items.add(new FestivalItem("http://tong.visitkorea.or.kr/cms/resource/11/2487711_image2_1.jpg","서울디자인페스티벌 2017"));
        items.add(new FestivalItem("http://tong.visitkorea.or.kr/cms/resource/09/2032509_image2_1.jpg","난타 (충정로)"));
        items.add(new FestivalItem("http://tong.visitkorea.or.kr/cms/resource/76/2499976_image2_1.jpg","앱쇼코리아 2017"));


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.spring_tab3_fragment,container,false);

        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        winterFestivalAdapter=new WinterFestivalAdapter(items,this.getContext());
        recyclerView.setAdapter(winterFestivalAdapter);

        return view;
    }

}
