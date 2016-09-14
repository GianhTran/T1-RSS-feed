package com.example.framgia.t1_rss_feed.data.models;

import com.example.framgia.t1_rss_feed.Constants;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 12/09/2016.
 */
public class RssSource extends RealmObject {
    private int mId;
    private String mRssLink;
    private Boolean mIsActive;
    private String mRssName;
    private String mRssChannel;
    private Boolean mIsDefault;

    public String getRssLink() {
        return mRssLink;
    }

    public void setRssLink(String rssLink) {
        mRssLink = rssLink;
    }

    public RssSource() {
    }

    public RssSource(int id, String rssLink, Boolean isActive, String rssName,
                     String rssChannel) {
        mId = id;
        mRssLink = rssLink;
        mIsActive = isActive;
        mRssName = rssName;
        mRssChannel = rssChannel;
    }

    public int getNextPrimaryKey(Realm realm) {
        RealmResults<RssSource> newsItems =
            realm.where(RssSource.class).findAllSorted(Constants.KEY_ID);
        return newsItems.isEmpty() ? 0 : newsItems.last().getId() + 1;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public Boolean getActive() {
        return mIsActive;
    }

    public void setActive(Boolean active) {
        mIsActive = active;
    }

    public String getRssName() {
        return mRssName;
    }

    public void setRssName(String rssName) {
        mRssName = rssName;
    }

    public String getRssChannel() {
        return mRssChannel;
    }

    public void setRssChannel(String rssChannel) {
        mRssChannel = rssChannel;
    }

    public Boolean getDefault() {
        return mIsDefault;
    }

    public void setDefault(Boolean aDefault) {
        mIsDefault = aDefault;
    }
}
