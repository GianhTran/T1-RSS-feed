package com.example.framgia.t1_rss_feed.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 16/09/2016.
 */
public class WeatherSys {
    @SerializedName("country")
    private String mCountry;
    @SerializedName("sunrise")
    private long mSunrise;
    @SerializedName("sunset")
    private long mSunset;

    public WeatherSys(String country, long sunrise, long sunset) {
        mCountry = country;
        mSunrise = sunrise;
        mSunset = sunset;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public long getSunrise() {
        return mSunrise;
    }

    public void setSunrise(long sunrise) {
        mSunrise = sunrise;
    }

    public long getSunset() {
        return mSunset;
    }

    public void setSunset(long sunset) {
        mSunset = sunset;
    }
}
