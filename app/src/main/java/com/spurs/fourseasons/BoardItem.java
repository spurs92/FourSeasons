package com.spurs.fourseasons;

import android.net.Uri;

/**
 * Created by alfo06-11 on 2017-08-23.
 */

public class BoardItem {

    String name;
    String contentText;
    String userid;
    String userUri;
    String date;

    public BoardItem(){}

    public BoardItem(String name, String contentText, String userid, String userUri, String date) {
        this.name = name;
        this.contentText = contentText;
        this.userid= userid;
        this.userUri=userUri;
        this.date=date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserUri() {
        return userUri;
    }

    public void setUserUri(String userUri) {
        this.userUri = userUri;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
