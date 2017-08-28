package com.spurs.fourseasons;

/**
 * Created by alfo06-11 on 2017-08-23.
 */

public class BoardItem {

    String name;
    String contentText;
    String userid;

    public BoardItem(){}

    public BoardItem(String name, String contentText, String userid) {
        this.name = name;
        this.contentText = contentText;
        this.userid= userid;
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
}
