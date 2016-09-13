package com.example.framgia.t1_rss_feed.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.framgia.t1_rss_feed.Constants;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;
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
    private Long mId;
    @Element(name = "guid", required = false)
    private String mGuid;
    @Element(name = "pubDate", required = false)
    private String mPubDate;
    @Element(name = "title", required = false)
    private String mTitle;
    @ElementList(entry = "category", inline = true, required = false)
    private RealmList<Category> mCategory;
    @Element(name = "description", required = false)
    private String mDescription;
    @Element(name = "link", required = false)
    private String mLinkItem;
    @Element(name = "enclosure", required = false)
    private NewsEnclosure mEnclosure;
    @Element(name = "author", required = false)
    private String mAuthor;
    @Element(name = "comments", required = false)
    private String mComments;
    private String mChannel;
    private Boolean mViewed;
    // realm list object can not using as java list, so need to create values below
    private Long mIndex;
    private Long mReadTime;
    private Long mHistoryIndex;
    private Boolean mIsChecked;

    public NewsItem() {
    }

    private NewsItem(Parcel parcel) {
        mId = parcel.readLong();
        mTitle = parcel.readString();
        mDescription = parcel.readString();
        mPubDate = parcel.readString();
        mLinkItem = parcel.readString();
        mAuthor = parcel.readString();
        mEnclosure.setLink(parcel.readString());
        mViewed = parcel.readByte() != 0;
    }

    public long getNextPrimaryKey(Realm realm) {
        RealmResults<NewsItem> newsItems =
            realm.where(NewsItem.class).findAllSorted(Constants.KEY_ID);
        return newsItems.isEmpty() ? 0 : newsItems.last().getId() + 1;
    }

    public long getNextIndex(Realm realm, String channel) {
        RealmResults<NewsItem> newsItems = realm.where(NewsItem.class)
            .equalTo(Constants.KEY_CHANNEL, channel)
            .findAllSorted(Constants.KEY_INDEX);
        return newsItems.isEmpty() ? 0 : newsItems.last().getIndex() + 1;
    }

    public long getNextHistoryIndex(Realm realm) {
        RealmResults<NewsItem> newsItems = realm.where(NewsItem.class)
            .equalTo(Constants.KEY_VIEWED, true)
            .findAllSorted(Constants.KEY_HISTORY_INDEX, Sort.ASCENDING);
        return newsItems.isEmpty() ? 0 : newsItems.last().getHistoryIndex() + 1;
    }

    public Boolean getChecked() {
        return mIsChecked;
    }

    public void setChecked(Boolean checked) {
        mIsChecked = checked;
    }

    public Long getReadTime() {
        return mReadTime;
    }

    public void setReadTime(Long readTime) {
        mReadTime = readTime;
    }

    public long getIndex() {
        return mIndex;
    }

    public Long getHistoryIndex() {
        return mHistoryIndex;
    }

    public void setHistoryIndex(Long historyIndex) {
        mHistoryIndex = historyIndex;
    }

    public void setIndex(long index) {
        mIndex = index;
    }

    public Boolean getViewed() {
        return mViewed;
    }

    public void setViewed(Boolean viewed) {
        mViewed = viewed;
    }

    public String getChannel() {
        return mChannel;
    }

    public void setChannel(String mChannel) {
        this.mChannel = mChannel;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
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
        parcel.writeLong(mId);
        parcel.writeString(mTitle);
        parcel.writeString(mDescription);
        parcel.writeString(mPubDate);
        parcel.writeString(mLinkItem);
        parcel.writeString(mAuthor);
        parcel.writeString(mEnclosure.getLink());
        parcel.writeByte((byte) (mViewed ? 1 : 0));
    }
}
