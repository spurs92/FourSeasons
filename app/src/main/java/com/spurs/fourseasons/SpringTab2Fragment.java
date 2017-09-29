package com.spurs.fourseasons;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by spurs on 2017/08-05(005).
 */

public class SpringTab2Fragment extends Fragment {

    RecyclerView recyclerView;

    ArrayList<FestivalItem> items = new ArrayList<>();

    SpringFestivalAdapter festivalAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.spring_tab2_fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        festivalAdapter = new SpringFestivalAdapter(items, this.getContext());
        recyclerView.setAdapter(festivalAdapter);

        LoadData loadData=new LoadData();
        loadData.start();

        return view;
    }

    class LoadData extends Thread{
        @Override
        public void run() {
            String loadUrl = "http://spursweb.dothome.co.kr/fourseosons/loadSpringDB.php";
            try {
                URL url=new URL(loadUrl);
                HttpURLConnection conn=(HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setUseCaches(false);

                InputStream is=conn.getInputStream();
                InputStreamReader isr=new InputStreamReader(is);
                BufferedReader reader=new BufferedReader(isr);

                StringBuffer buffer=new StringBuffer();
                String line=reader.readLine();

                while (line!=null){
                    buffer.append(line);
                    line=reader.readLine();
                }

                String str=buffer.toString();
                String[] rows=str.split(";");

                items.clear();
                for(String row : rows){
                    String[] datas=row.split("&");

                    if(datas.length<3) continue;

                    String title=datas[0];
                    String img=datas[1];

                    items.add(new FestivalItem(img, title));
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        festivalAdapter.notifyDataSetChanged();
                    }
                });

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
