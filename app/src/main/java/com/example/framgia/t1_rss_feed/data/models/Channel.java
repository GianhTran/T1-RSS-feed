package com.example.framgia.t1_rss_feed.data.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import java.util.List;


/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 26/08/2016.
 */
@Root(name = "channel", strict = false)
public class Channel {
    @Element(name = "title")
    private String mTitle;
    @Element(name = "description")
    private String mDescription;
    @Element(name = "link", required = false)
    private String mLinkChannel;
    @Element(name = "lastBuildDate")
    private String mLastBuildDate;
    @Element(name = "generator")
    private String mGenerator;
    @Element(name = "language")
    private String mLanguage;
    @Element(name = "copyright")
    private String mCopyright;
    @Element(name = "ttl")
    private String mTtl;
    @ElementList(entry = "item", inline = true)
    private List<NewsItem> mItems;
    @Element(name = "image")
    private NewsImage mImage;

    public NewsImage getImage() {
        return mImage;
    }

    public void setImage(NewsImage image) {
        this.mImage = image;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getLink() {
        return mLinkChannel;
    }

    public void setLink(String link) {
        this.mLinkChannel = link;
    }

    public String getLastBuildDate() {
        return mLastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.mLastBuildDate = lastBuildDate;
    }

    public String getGenerator() {
        return mGenerator;
    }

    public void setGenerator(String generator) {
        this.mGenerator = generator;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String language) {
        this.mLanguage = language;
    }

    public String getCopyright() {
        return mCopyright;
    }

    public void setCopyright(String copyright) {
        this.mCopyright = copyright;
    }

    public String getTtl() {
        return mTtl;
    }

    public void setTtl(String ttl) {
        this.mTtl = ttl;
    }

    public List<NewsItem> getItems() {
        return mItems;
    }

    public void setItems(List<NewsItem> items) {
        this.mItems = items;
    }
}
