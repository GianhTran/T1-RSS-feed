package com.example.framgia.t1_rss_feed.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 16/09/2016.
 */
public class WeatherWind {
    @SerializedName("speed")
    private String mSpeed;
    @SerializedName("deg")
    private String mDeg;

    public WeatherWind(String speed, String deg) {
        mSpeed = speed;
        mDeg = deg;
    }

    public String getSpeed() {
        return mSpeed;
    }

    public void setSpeed(String speed) {
        mSpeed = speed;
    }

    public String getDeg() {
        return mDeg;
    }

    public void setDeg(String deg) {
        mDeg = deg;
    }
}
