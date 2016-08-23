package com.example.framgia.t1_rss_feed.data.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 23/08/2016.
 */
public class Item {
    private String id;
    private String title;
    private String guid;
    private String pubDate;
    private String author;
    private String thumbnail;
    private String description;
    private String content;
    private Boolean viewed;
    private List<Category> categories;
    private Enclosure enclosure;

    public Item(String id,
                String title,
                String guid,
                String pubDate,
                String author,
                String thumbnail,
                String description,
                String content,
                Boolean viewed,
                List<Category> categories,
                Enclosure enclosure) {
        this.id = id;
        this.title = title;
        this.guid = guid;
        this.pubDate = pubDate;
        this.author = author;
        this.thumbnail = thumbnail;
        this.description = description;
        this.content = content;
        this.viewed = viewed;
        this.categories = categories;
        this.enclosure = enclosure;
    }

    public Item() {
    }

    public Item newInstant() {
        return new Item("",
                "title",
                "guid",
                "10/10/2010",
                "author",
                "thumnail",
                "description",
                "content",
                false,
                new ArrayList<Category>(),
                new Enclosure().newInstant());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getViewed() {
        return viewed;
    }

    public void setViewed(Boolean viewed) {
        this.viewed = viewed;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Enclosure getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }
}
