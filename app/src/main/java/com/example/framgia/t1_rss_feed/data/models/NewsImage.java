package com.example.framgia.t1_rss_feed.data.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 26/08/2016.
 */
@Root(strict = false)
public class NewsImage {
    @Element(name = "title", required = false)
    private String mTitle;
    @Element(name = "link", required = false)
    private String mLinkImage;
    @Element(name = "url", required = false)
    private String mUrl;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getLink() {
        return mLinkImage;
    }

    public void setLink(String link) {
        this.mLinkImage = link;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }
}
