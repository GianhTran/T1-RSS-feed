package com.example.framgia.t1_rss_feed.data.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import io.realm.RealmObject;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 29/08/2016.
 */
@Root(strict = false)
public class Category extends RealmObject {
    @Element(name = "category", required = false)
    private String mCategory;
    @Element(name = "domain", required = false)
    private String mDomain;

    public Category() {
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        this.mCategory = category;
    }
}
