package com.example.framgia.t1_rss_feed.data.models;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 23/08/2016.
 */
public class NewsFeed {

    private String mId;
    private String mTitle;
    private String mLink;
    private String mAuthor;
    private String mDescription;

    public NewsFeed(String id, String title, String link, String author, String description) {
        this.mId = id;
        this.mTitle = title;
        this.mLink = link;
        this.mAuthor = author;
        this.mDescription = description;
    }

    public NewsFeed() {
    }

    // Dummy data
    public static NewsFeed newInstance() {
        return new NewsFeed("", "title", "link", "author", "description");
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String mLink) {
        this.mLink = mLink;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
