package com.example.framgia.t1_rss_feed.data.models;


/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 23/08/2016.
 */
public class New {
    private String id;
    private Feed feed;
    private Item item;

    public New(String id, Feed feed, Item item) {
        this.id = id;
        this.feed = feed;
        this.item = item;
    }

    public New() {
    }

    // dummy data
    public New newIntanst() {
        return new New("", new Feed().newInstant(), new Item().newInstant());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
