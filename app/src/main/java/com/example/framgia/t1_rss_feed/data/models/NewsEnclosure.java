package com.example.framgia.t1_rss_feed.data.models;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 23/08/2016.
 */
public class NewsEnclosure {
    private String mId;
    private String mLink;
    private String mLength;
    private String mType;

    public NewsEnclosure(String id, String link, String length, String type) {
        this.mId = id;
        this.mLink = link;
        this.mLength = length;
        this.mType = type;
    }

    public NewsEnclosure() {
    }

    // dummy data
    public static NewsEnclosure newInstance() {
        return new NewsEnclosure("", "link", "length", "type");
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String mLink) {
        this.mLink = mLink;
    }

    public String getLength() {
        return mLength;
    }

    public void setLength(String mLength) {
        this.mLength = mLength;
    }

    public String getType() {
        return mType;
    }

    public void setType(String mType) {
        this.mType = mType;
    }
}
