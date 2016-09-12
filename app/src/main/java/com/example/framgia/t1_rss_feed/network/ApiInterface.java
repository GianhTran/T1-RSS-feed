package com.example.framgia.t1_rss_feed.network;

import com.example.framgia.t1_rss_feed.data.models.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 26/08/2016.
 */
public interface ApiInterface {
    // voa rss
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
    // vn express rss
    @GET("tin-moi-nhat.rss")
    Call<News> getVnExpressHome();
    @GET("thoi-su.rss")
    Call<News> getVnExpressNews();
    @GET("the-gioi.rss")
    Call<News> getVnExpressWorld();
    @GET("kinh-doanh.rss")
    Call<News> getVnExpressBusiness();
    @GET("giai-tri.rss")
    Call<News> getVnExpressEntertainment();
    @GET("the-thao.rss")
    Call<News> getVnExpressSport();
    @GET("phap-luat.rss")
    Call<News> getVnExpressLaw();
    @GET("giao-duc.rss")
    Call<News> getVnExpressEducation();
    @GET("suc-khoe.rss")
    Call<News> getVnExpressHealth();
    @GET("gia-dinh.rss")
    Call<News> getVnExpressFamilly();
    @GET("du-lich.rss")
    Call<News> getVnExpressTravel();
    @GET("khoa-hoc.rss")
    Call<News> getVnExpressScience();
    @GET("so-hoa.rss")
    Call<News> getVnExpressIT();
    @GET("oto-xe-may.rss")
    Call<News> getVnExpressOto();
    @GET("cong-dong.rss")
    Call<News> getVnExpressCommunity();
    @GET("tam-su.rss")
    Call<News> getVnExpressShare();
    @GET("cuoi.rss")
    Call<News> getVnExpressFunny();
    // tinh te rss
    @GET("rss")
    Call<News> getTinhTeFeed();
    @GET
    Call<News> getCustomFeed(@Url String url);
}
