package com.spurs.fourseasons;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by spurs on 2017/08-05(005).
 */

public class SpringTab2Fragment extends Fragment {

    SwipeRefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.spring_tab2_fragment,container,false);

        refreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.layout_swipe);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                String serverUrl="http://spursweb.dothome.co.kr/seasons/urlSaveData.php";
                Log.i("aa",serverUrl);

                refreshLayout.setRefreshing(false);
            }
        });

        return view;
    }

}
