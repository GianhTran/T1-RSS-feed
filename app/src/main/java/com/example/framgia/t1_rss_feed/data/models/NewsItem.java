package com.example.framgia.t1_rss_feed.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 26/08/2016.
 */
@Element(name = "item")
public class NewsItem extends RealmObject implements Parcelable {
    public static final Parcelable.Creator<NewsItem> CREATOR
        = new Parcelable.Creator<NewsItem>() {
        public NewsItem createFromParcel(Parcel parcel) {
            return new NewsItem(parcel);
        }

        public NewsItem[] newArray(int size) {
            return new NewsItem[size];
        }
    };
    @PrimaryKey
    private String mId;
    @Element(name = "guid")
    private String mGuid;
    @Element(name = "pubDate")
    private String mPubDate;
    @Element(name = "title")
    private String mTitle;
    @ElementList(entry = "category", inline = true, required = false)
    private RealmList<Category> mCategory;
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
    private String mChannel;

    public NewsItem() {
    }

    private NewsItem(Parcel parcel) {
        mId = parcel.readString();
        mTitle = parcel.readString();
        mDescription = parcel.readString();
        mPubDate = parcel.readString();
        mLinkItem = parcel.readString();
        mAuthor = parcel.readString();
        mEnclosure.setLink(parcel.readString());
    }

    public String getmChannel() {
        return mChannel;
    }

    public void setmChannel(String mChannel) {
        this.mChannel = mChannel;
    }

    public String getId() {
        return mId;
    }

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

    public RealmList<Category> getCategory() {
        return mCategory;
    }

    public void setCategory(RealmList<Category> category) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mId);
        parcel.writeString(mTitle);
        parcel.writeString(mDescription);
        parcel.writeString(mPubDate);
        parcel.writeString(mLinkItem);
        parcel.writeString(mAuthor);
        parcel.writeString(mEnclosure.getLink());
    }
}
