package com.example.framgia.t1_rss_feed.data.models;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 12/09/2016.
 */
public enum TinhteChannel {
    TINHTE_CHANNEL("Tinhte.vn", 0);
    private String mName;
    private int mCode;

    TinhteChannel(String name, int code) {
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

    public static String getNameByCode(int code) {
        for (TinhteChannel channel : values()) {
            if (channel.getCode() == code) return channel.getName();
        }
        return null;
    }
}
