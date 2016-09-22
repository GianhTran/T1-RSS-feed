package com.example.framgia.t1_rss_feed.network;

import com.example.framgia.t1_rss_feed.Constants;
import com.example.framgia.t1_rss_feed.data.models.FCMResponce;
import com.example.framgia.t1_rss_feed.data.models.News;
import com.example.framgia.t1_rss_feed.data.models.WeatherWrapper;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 26/08/2016.
 */
public interface ApiInterface {
    @GET("weather")
    Call<WeatherWrapper> loadWeather(@Query("apikey") String apiKey,
                                     @Query("units") String units,
                                     @Query("lat") double lat,
                                     @Query("lon") double lon);
    @GET
    Call<News> getCustomFeed(@Url String url);
    @Headers({
        "Content-Type: application/json",
        "Authorization:key=" + Constants.SERVER_FCM_KEY
    })
    @POST("fcm/send")
    Call<FCMResponce> post(@Body JsonObject requestBody);
}
