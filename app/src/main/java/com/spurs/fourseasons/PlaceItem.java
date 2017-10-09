package com.spurs.fourseasons;

/**
 * Created by alfo06-11 on 2017-08-11.
 */

public class PlaceItem {

    String title;
    String imgUrl;
    String addr;
    String mapx;
    String mapy;
    String overview;
    String hp;
    String info;
    String rest;

    public PlaceItem(String title, String imgUrl, String addr, String mapx, String mapy, String overview, String hp, String info, String rest) {
        this.title = title;
        this.imgUrl = imgUrl;
        this.addr = addr;
        this.mapx = mapx;
        this.mapy = mapy;
        this.overview = overview;
        this.hp = hp;
        this.info = info;
        this.rest = rest;
    }
}
