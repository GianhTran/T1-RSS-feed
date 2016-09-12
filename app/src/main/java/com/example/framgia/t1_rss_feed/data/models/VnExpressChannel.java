package com.example.framgia.t1_rss_feed.data.models;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 13/09/2016.
 */
public enum VnExpressChannel {
    HOME("Tin mới nhất - VnExpress RSS", 0),
    NEWS("Thời sự - VnExpress RSS", 1),
    WORLD("Thế giới - VnExpress RSS", 2),
    BUSINESS("Kinh doanh - VnExpress RSS", 3),
    ENTERTAINMENT("Giải trí - VnExpress RSS", 4),
    SPORT("Thể thao - VnExpress RSS", 5),
    LAW("Pháp luật - VnExpress RSS", 6),
    EDUCATION("Giáo dục - VnExpress RSS", 7),
    HEALTH("Sức khỏe - VnExpress RSS", 8),
    FAMILY("Gia đình - VnExpress RSS", 9),
    TRAVEL("Du lịch - VnExpress RSS", 10),
    SCIENCE("Khoa học - VnExpress RSS", 11),
    IT("Số hóa - VnExpress RSS", 12),
    OTO("Xe - VnExpress RSS", 13),
    COMMUNITY("Cộng đồng - VnExpress RSS", 14),
    SHARE("Tâm sự - VnExpress RSS", 15),
    FUNNY("Cười - VnExpress RSS", 16);
    private String mName;
    private int mCode;

    VnExpressChannel(String name, int code) {
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
        for (VnExpressChannel channel : values()) {
            if (channel.getCode() == code) return channel.getName();
        }
        return null;
    }
}
