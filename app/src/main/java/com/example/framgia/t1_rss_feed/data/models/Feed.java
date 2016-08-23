package com.example.framgia.t1_rss_feed.data.models;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 23/08/2016.
 */
public class Feed {

    private String id;
    private String title;
    private String link;
    private String author;
    private String description;

    public Feed(String id, String title, String link, String author, String description) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.author = author;
        this.description = description;
    }

    public Feed() {
    }

    // Dummy data
    public Feed newInstant() {
        return new Feed("", "title", "link", "author", "description");
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
