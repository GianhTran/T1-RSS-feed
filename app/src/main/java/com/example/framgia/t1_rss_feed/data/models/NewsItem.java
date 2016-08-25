package com.example.framgia.t1_rss_feed.data.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 23/08/2016.
 */
public class NewsItem {
    private String mId;
    private String mTitle;
    private String mGuid;
    private String mPubDate;
    private String mAuthor;
    private String mThumbnail;
    private String mDescription;
    private String mContent;
    private Boolean mViewed;
    private List<Category> mCategories;
    private NewsEnclosure mEnclosure;

    public NewsItem(String id,
                    String title,
                    String guid,
                    String pubDate,
                    String author,
                    String thumbnail,
                    String description,
                    String content,
                    Boolean viewed,
                    List<Category> categories,
                    NewsEnclosure enclosure) {
        this.mId = id;
        this.mTitle = title;
        this.mGuid = guid;
        this.mPubDate = pubDate;
        this.mAuthor = author;
        this.mThumbnail = thumbnail;
        this.mDescription = description;
        this.mContent = content;
        this.mViewed = viewed;
        this.mCategories = categories;
        this.mEnclosure = enclosure;
    }

    public NewsItem() {
    }

    public static NewsItem newInstance() {
        return new NewsItem("",
                "title",
                "guid",
                "10/10/2010",
                "author",
                "thumnail",
                "description",
                "content",
                false,
                new ArrayList<Category>(),
                new NewsEnclosure().newInstance());
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

    public String getGuid() {
        return mGuid;
    }

    public void setGuid(String mGuid) {
        this.mGuid = mGuid;
    }

    public String getPubDate() {
        return mPubDate;
    }

    public void setPubDate(String mPubDate) {
        this.mPubDate = mPubDate;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(String mThumbnail) {
        this.mThumbnail = mThumbnail;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public Boolean getViewed() {
        return mViewed;
    }

    public void setViewed(Boolean mViewed) {
        this.mViewed = mViewed;
    }

    public List<Category> getCategories() {
        return mCategories;
    }

    public void setCategories(List<Category> mCategories) {
        this.mCategories = mCategories;
    }

    public NewsEnclosure getEnclosure() {
        return mEnclosure;
    }

    public void setEnclosure(NewsEnclosure mEnclosure) {
        this.mEnclosure = mEnclosure;
    }
}
