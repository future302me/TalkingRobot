package com.gui.royal.youtalking.model;

/**
 * Created by Jeremy on 2015/5/11.
 */
public class Msg {

    public static final int TYPE_RECEIVED = 0;

    public  static final int TYPE_SENT = 1;

    private String content;

    private int type;

    public Msg (String content, int type){
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
