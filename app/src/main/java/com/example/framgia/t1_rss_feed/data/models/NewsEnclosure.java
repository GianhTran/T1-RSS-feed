package com.example.framgia.t1_rss_feed.data.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 23/08/2016.
 */
@Root(strict = false)
public class NewsEnclosure {
    private String mId;
    @Element(name = "url", required = false)
    private String mLinkEnclosure;
    @Element(name = "length", required = false)
    private String mLength;
    @Element(name = "type", required = false)
    private String mType;

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getLink() {
        return mLinkEnclosure;
    }

    public void setLink(String mLink) {
        this.mLinkEnclosure = mLink;
    }

    public String getLength() {
        return mLength;
    }

    public void setLength(String mLength) {
        this.mLength = mLength;
    }

    public String getType() {
        return mType;
    }

    public void setType(String mType) {
        this.mType = mType;
    }
}
