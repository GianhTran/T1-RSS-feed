package com.example.framgia.t1_rss_feed.data.models;

import org.simpleframework.xml.Attribute;

import io.realm.RealmObject;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 23/08/2016.
 */
public class NewsEnclosure extends RealmObject {
    private String mId;
    @Attribute(name = "url")
    private String mLinkEnclosure;
    @Attribute(name = "length")
    private String mLength;
    @Attribute(name = "type")
    private String mType;

    public NewsEnclosure() {
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getLink() {
        return mLinkEnclosure;
    }

    public void setLink(String mLink) {
        this.mLinkEnclosure = mLink;
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
