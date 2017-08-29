package com.spurs.fourseasons;

/**
 * Created by alfo06-11 on 2017-08-28.
 */

public class BoardCommentItem {

    String name;
    String comment;
    String userImgUri;
    String date;

    public BoardCommentItem(){}

    public BoardCommentItem(String name, String comment, String userImgUri, String date) {
        this.name = name;
        this.comment = comment;
        this.userImgUri=userImgUri;
        this.date=date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserImgUri() {
        return userImgUri;
    }

    public void setUserImgUri(String userImgUri) {
        this.userImgUri = userImgUri;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
