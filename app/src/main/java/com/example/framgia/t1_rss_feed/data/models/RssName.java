package com.example.framgia.t1_rss_feed.data.models;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 12/09/2016.
 */
public enum RssName {
    TINHTE("Tinh Tế", 0),
    VNEXRESS("Vn Express", 1),
    VOA("VOA news", 2);
    private String mName;
    private int mCode;

    RssName(String name, int code) {
        mName = name;
        mCode = code;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        mCode = code;
    }
}
