package com.example.framgia.t1_rss_feed.data.models;


/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 23/08/2016.
 */
public class News {
    private String mId;
    private NewsFeed mFeed;
    private NewsItem mItem;

    public News(String id, NewsFeed feed, NewsItem item) {
        this.mId = id;
        this.mFeed = feed;
        this.mItem = item;
    }

    public News() {
    }

    // dummy data
    public News newInstance() {
        return new News("", new NewsFeed().newInstance(), new NewsItem().newInstance());
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public NewsFeed getFeed() {
        return mFeed;
    }

    public void setFeed(NewsFeed mFeed) {
        this.mFeed = mFeed;
    }

    public NewsItem getItem() {
        return mItem;
    }

    public void setItem(NewsItem mItem) {
        this.mItem = mItem;
    }
}
