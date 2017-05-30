package com.study.mylist6;

public class SingerItem {

    private String name;
    private String telnum;
    private int resId;

    public SingerItem(String name, String telnum, int resId) {
        this.name = name;
        this.telnum = telnum;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }


    public String getTelnum() {
        return telnum;
    }


    public int getResId() {
        return resId;
    }

}