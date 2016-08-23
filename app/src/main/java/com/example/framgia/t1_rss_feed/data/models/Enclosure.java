package com.example.framgia.t1_rss_feed.data.models;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 23/08/2016.
 */
public class Enclosure {
    private String id;
    private String link;
    private String length;
    private String type;

    public Enclosure(String id, String link, String length, String type) {
        this.id = id;
        this.link = link;
        this.length = length;
        this.type = type;
    }

    public Enclosure() {
    }

    // dummy data
    public Enclosure newInstant() {
        return new Enclosure("", "link", "length", "type");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
