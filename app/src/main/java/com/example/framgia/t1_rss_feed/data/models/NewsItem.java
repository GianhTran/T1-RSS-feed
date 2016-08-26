package com.example.framgia.t1_rss_feed.data.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 26/08/2016.
 */
@Element(name = "item")
public class NewsItem {
    @Element(name = "guid")
    private String mGuid;
    @Element(name = "pubDate")
    private String mPubDate;
    @Element(name = "title")
    private String mTitle;
    @ElementList(entry = "category", inline = true)
    private List<String> mCategory;
    @Element(name = "description")
    private String mDescription;
    @Element(name = "link", required = false)
    private String mLinkItem;
    @Element(name = "enclosure")
    private NewsEnclosure mEnclosure;
    @Element(name = "author", required = false)
    private String mAuthor;
    @Element(name = "comments", required = false)
    private String mComments;

    public String getComments() {
        return mComments;
    }

    public void setComments(String comments) {
        this.mComments = comments;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        this.mAuthor = author;
    }

    public NewsEnclosure getEnclosure() {
        return mEnclosure;
    }

    public void setEnclosure(NewsEnclosure enclosure) {
        this.mEnclosure = enclosure;
    }

    public String getGuid() {
        return mGuid;
    }

    public void setGuid(String guid) {
        this.mGuid = guid;
    }

    public String getPubDate() {
        return mPubDate;
    }

    public void setPubDate(String pubDate) {
        this.mPubDate = pubDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public List<String> getCategory() {
        return mCategory;
    }

    public void setCategory(List<String> category) {
        this.mCategory = category;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getLink() {
        return mLinkItem;
    }

    public void setLink(String link) {
        this.mLinkItem = link;
    }
}
