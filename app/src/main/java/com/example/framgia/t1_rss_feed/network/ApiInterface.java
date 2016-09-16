package com.example.framgia.t1_rss_feed.network;

import com.example.framgia.t1_rss_feed.data.models.News;
import com.example.framgia.t1_rss_feed.data.models.WeatherWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 26/08/2016.
 */
public interface ApiInterface {
    @GET("zo$o_egviy")
    Call<News> loadNewsOfAsian();
    @GET("zq$omekvi_")
    Call<News> loadNewsOfUsa();
    @GET("z-$otevtiq")
    Call<News> loadNewsOfAfrica();
    @GET("zr$opeuvim")
    Call<News> loadNewsOfMiddleEast();
    @GET("zj$oveytit")
    Call<News> loadNewsOfEurope();
    @GET("zt$opeitim")
    Call<News> loadNewsOfHealth();
    @GET("zp$ove-vir")
    Call<News> loadNewsOfArt();
    @GET("zuriqiepuiqm")
    Call<News> loadNewsOf2016UsaVotes();
    @GET("z$roqmetuoqm")
    Call<News> loadNewsOfOneMinuteFeatures();
    @GET("z$uiqyetppqv")
    Call<News> loadNewsOfExtremismWatch();
    @GET("z$-jqetv-i")
    Call<News> loadNewsOfDayInPhotos();
    @GET("weather")
    Call<WeatherWrapper> loadWeather(@Query("apikey") String apiKey,
                                     @Query("lat") long lat,
                                     @Query("lon") long lon);
    Call<News> getCustomFeed(@Url String url);
}
