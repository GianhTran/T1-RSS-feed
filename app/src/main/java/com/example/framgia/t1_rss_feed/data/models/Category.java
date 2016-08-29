package com.example.framgia.t1_rss_feed.data.models;

import org.simpleframework.xml.Element;

import io.realm.RealmObject;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 29/08/2016.
 */
public class Category extends RealmObject {
    @Element(name = "category", required = false)
    private String mCategory;

    public Category() {
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        this.mCategory = category;
    }
}
