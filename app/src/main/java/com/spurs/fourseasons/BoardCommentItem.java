package com.spurs.fourseasons;

/**
 * Created by alfo06-11 on 2017-08-28.
 */

public class BoardCommentItem {

    String name;
    String comment;

    public BoardCommentItem(String name, String comment) {
        this.name = name;
        this.comment = comment;
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
}
