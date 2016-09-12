package com.example.framgia.t1_rss_feed.data.models;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 12/09/2016.
 */
public enum VoaChannel {
    ASIAN_CHANNEL("Asia - Voice of America", 0),
    USA_CHANNEL("USA - Voice of America", 1),
    AFRICA_CHANNEL("Africa - Voice of America", 2),
    MIDDLE_EAST_CHANNEL("Middle East - Voice of America", 3),
    EUROPE_CHANNEL("Europe - Voice of America", 4),
    HEATH_CHANNEL("Health - Voice of America", 5),
    ART_CHANNEL("Arts &amp; Entertainment - Voice of America", 6),
    USA_VOTE_CHANNEL("2016 USA Votes - Voice of America", 7),
    ONE_MINUTE_FEATURE_CHANNEL("One-Minute Features - Voice of America", 8),
    EXTREME_CHANNEL("Extremism Watch - Voice of America", 9),
    PHOTO_CHANNEL("Day in Photos - Voice of America", 10);
    private String mName;
    private int mCode;

    VoaChannel(String name, int code) {
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
        for (VoaChannel channel : values()) {
            if (channel.getCode() == code) return channel.getName();
        }
        return null;
    }
}
