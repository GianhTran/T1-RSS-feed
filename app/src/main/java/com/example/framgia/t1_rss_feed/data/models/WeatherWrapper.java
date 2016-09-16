package com.example.framgia.t1_rss_feed.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 16/09/2016.
 */
public class WeatherWrapper {
    @SerializedName("weather")
    private List<Weather> mWeathers;
    @SerializedName("wind")
    private WeatherWind mWeatherWind;
    @SerializedName("main")
    private WeatherValue mWeatherValue;
    @SerializedName("sys")
    private WeatherSys mWeatherSys;
    @SerializedName("name")
    private String mName;

    public WeatherWrapper(List<Weather> weathers,
                          WeatherWind weatherWind,
                          WeatherValue weatherValue,
                          WeatherSys weatherSys, String name) {
        mWeathers = weathers;
        mWeatherWind = weatherWind;
        mWeatherValue = weatherValue;
        mWeatherSys = weatherSys;
        mName = name;
    }

    public List<Weather> getWeathers() {
        return mWeathers;
    }

    public void setWeathers(
        List<Weather> weathers) {
        mWeathers = weathers;
    }

    public WeatherWind getWeatherWind() {
        return mWeatherWind;
    }

    public void setWeatherWind(WeatherWind weatherWind) {
        mWeatherWind = weatherWind;
    }

    public WeatherValue getWeatherValue() {
        return mWeatherValue;
    }

    public void setWeatherValue(WeatherValue weatherValue) {
        mWeatherValue = weatherValue;
    }

    public WeatherSys getWeatherSys() {
        return mWeatherSys;
    }

    public void setWeatherSys(WeatherSys weatherSys) {
        mWeatherSys = weatherSys;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
