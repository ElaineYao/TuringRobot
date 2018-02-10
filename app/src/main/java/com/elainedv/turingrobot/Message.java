package com.elainedv.turingrobot;

/**
 * Created by Elaine on 18/2/9.
 */

public class Message {

    private int type;
    private String content;

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setType(int type) {
        this.type = type;
    }
}
