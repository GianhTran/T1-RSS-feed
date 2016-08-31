package com.example.framgia.t1_rss_feed.data.models;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 31/08/2016.
 */
public class TempNews {
    private String mPubDate;
    private String mTitle;
    private String mDescription;
    private String mLinkItem;
    private String mImage;
    private String mAuthor;

    public String getPubDate() {
        return mPubDate;
    }

    public void setPubDate(String pubDate) {
        mPubDate = pubDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getLinkItem() {
        return mLinkItem;
    }

    public void setLinkItem(String linkItem) {
        mLinkItem = linkItem;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }
}
