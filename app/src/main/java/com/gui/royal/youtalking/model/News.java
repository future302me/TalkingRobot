package com.gui.royal.youtalking.model;

import java.util.List;

/**
 * Created by Jeremy on 2015/5/26.
 */
public class News {
    private int id;
    private String code;
    private String text;
    //private List<String> list;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
