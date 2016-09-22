package com.example.framgia.t1_rss_feed.network;

import com.example.framgia.t1_rss_feed.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 26/08/2016.
 */
public class ServiceGenerator {
    public static final String API_BASE_URL = "http://www.voanews.com/api/";
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
        .connectTimeout(Constants.CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
        .readTimeout(Constants.READ_TIME_OUT, TimeUnit.MILLISECONDS);
    private static Retrofit.Builder builder =
        new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }

    private static Retrofit.Builder jSonBuilder =
        new Retrofit.Builder()
            .baseUrl(Constants.WEATHER_BASE_LINK)
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createGsonService(Class<S> serviceClass) {
        Retrofit retrofit = jSonBuilder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }

    private static Retrofit.Builder jSonFCMBuilder =
        new Retrofit.Builder()
            .baseUrl(Constants.FIRE_BASE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createFCMService(Class<S> serviceClass) {
        Retrofit retrofit = jSonFCMBuilder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}
