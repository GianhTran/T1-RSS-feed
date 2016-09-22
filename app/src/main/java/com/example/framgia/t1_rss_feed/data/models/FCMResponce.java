package com.example.framgia.t1_rss_feed.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by framgia on 22/09/2016.
 */
public class FCMResponce {
    @SerializedName("message_id")
    private String mMessageId;
    @SerializedName("error")
    private String mError;

    public FCMResponce(String messageId, String error) {
        mMessageId = messageId;
        mError = error;
    }

    public String getMessageId() {
        return mMessageId;
    }

    public void setMessageId(String messageId) {
        mMessageId = messageId;
    }

    public String getError() {
        return mError;
    }

    public void setError(String error) {
        mError = error;
    }
}
