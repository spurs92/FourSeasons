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

public class AutumnTab3Fragment extends Fragment {

    RecyclerView recyclerView;

    ArrayList<FestivalItem> items=new ArrayList<>();

    AutumnFestivalAdapter autumnFestivalAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        items.add(new FestivalItem("http://tong.visitkorea.or.kr/cms/resource/47/2487947_image2_1.jpg","보신각타종행사"));
        items.add(new FestivalItem("http://tong.visitkorea.or.kr/cms/resource/55/2485655_image2_1.JPG","생생문화재 양화진 뱃길탐방 2017"));
        items.add(new FestivalItem("http://tong.visitkorea.or.kr/cms/resource/53/2376253_image2_1.jpg","서울 밤도깨비 야시장 2017"));
        items.add(new FestivalItem("http://tong.visitkorea.or.kr/cms/resource/03/2496103_image2_1.JPG","서울아리랑페스티벌 2017"));
        items.add(new FestivalItem("http://tong.visitkorea.or.kr/cms/resource/46/2507846_image2_1.jpg","한양도성문화제 2017"));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.spring_tab3_fragment,container,false);

        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        autumnFestivalAdapter=new AutumnFestivalAdapter(items,this.getContext());
        recyclerView.setAdapter(autumnFestivalAdapter);

        return view;
    }

}
