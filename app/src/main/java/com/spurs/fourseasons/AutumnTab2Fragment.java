package com.spurs.fourseasons;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class AutumnTab2Fragment extends Fragment {

    RecyclerView recyclerView;

    ArrayList<PlaceItem> items = new ArrayList<>();

    PlaceAdapter placeAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.spring_tab2_fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        placeAdapter = new PlaceAdapter(items, this.getContext());
        recyclerView.setAdapter(placeAdapter);

        LoadData loadData=new LoadData();
        loadData.start();

        return view;
    }

    class LoadData extends Thread{
        @Override
        public void run() {
            String loadUrl = "http://spursweb.dothome.co.kr/fourseosons/loadAutumnDB.php";
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

                    if(datas.length<9) continue;

                    String title=datas[0];
                    String img=datas[1];
                    String addr=datas[2];
                    String mapx=datas[3];
                    String mapy=datas[4];
                    String overview=datas[5];
                    String hp=datas[6];
                    String info=datas[7];
                    String rest=datas[8];

                    items.add(new PlaceItem(title,img,addr,mapx,mapy,overview,hp,info,rest));
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        placeAdapter.notifyDataSetChanged();
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
