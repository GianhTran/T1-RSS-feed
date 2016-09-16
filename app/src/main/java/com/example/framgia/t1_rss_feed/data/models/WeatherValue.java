package com.example.framgia.t1_rss_feed.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 16/09/2016.
 */
public class WeatherValue {
    @SerializedName("temp")
    private String mTemp;
    @SerializedName("pressure")
    private String mPressure;
    @SerializedName("humidity")
    private String mHumidity;
    @SerializedName("temp_min")
    private String mTempMin;
    @SerializedName("temp_max")
    private String mTempMax;

    public WeatherValue(String temp, String pressure, String humidity, String tempMin,
                        String tempMax) {
        mTemp = temp;
        mPressure = pressure;
        mHumidity = humidity;
        mTempMin = tempMin;
        mTempMax = tempMax;
    }

    public String getTemp() {
        return mTemp;
    }

    public void setTemp(String temp) {
        mTemp = temp;
    }

    public String getPressure() {
        return mPressure;
    }

    public void setPressure(String pressure) {
        mPressure = pressure;
    }

    public String getHumidity() {
        return mHumidity;
    }

    public void setHumidity(String humidity) {
        mHumidity = humidity;
    }

    public String getTempMin() {
        return mTempMin;
    }

    public void setTempMin(String tempMin) {
        mTempMin = tempMin;
    }

    public String getTempMax() {
        return mTempMax;
    }

    public void setTempMax(String tempMax) {
        mTempMax = tempMax;
    }
}
