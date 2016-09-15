package com.example.framgia.t1_rss_feed;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 29/08/2016.
 */
public class Preferences {
    private static final String PRE_LOAD = "preLoad";
    private static final String PRE_CHANNEL = "channel";
    private static final String PRE_STYLE = "pref_style";
    private static final String PRE_ALLOW_IMAGE = "pref_allow_image";
    private static final String PRE_TEXT_SIZE = "pref_text_size";
    private static final String PRE_SUB_TOPIC = "pref_sub_topic";
    private static final String PREFS_NAME = "prefs";
    private static Preferences sInstance;
    private final SharedPreferences SHARE_PREFERENCES;

    public Preferences(Context context) {
        SHARE_PREFERENCES =
            context.getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static Preferences with(Context context) {
        if (sInstance == null) {
            sInstance = new Preferences(context);
        }
        return sInstance;
    }

    public int getPreLoad() {
        return SHARE_PREFERENCES.getInt(PRE_LOAD, 0);
    }

    public void setPreLoad(int preLoad) {
        SHARE_PREFERENCES.edit()
            .putInt(PRE_LOAD, preLoad)
            .apply();
    }

    public void setChannel(int channelId) {
        SHARE_PREFERENCES.edit()
            .putInt(PRE_CHANNEL, channelId)
            .apply();
    }

    public int getChannel() {
        return SHARE_PREFERENCES.getInt(PRE_CHANNEL, 0);
    }

    public int getStyle() {
        return SHARE_PREFERENCES.getInt(PRE_STYLE, Constants.LIGHT_STYLE);
    }

    public void setStyle(int style) {
        SHARE_PREFERENCES.edit()
            .putInt(PRE_STYLE, style)
            .apply();
    }

    public void setAllowImage(Boolean isAllow) {
        SHARE_PREFERENCES.edit()
            .putBoolean(PRE_ALLOW_IMAGE, isAllow)
            .apply();
    }

    public Boolean getAllowImage() {
        return SHARE_PREFERENCES.getBoolean(PRE_ALLOW_IMAGE, true);
    }

    public void setTextSize(int size) {
        SHARE_PREFERENCES.edit()
            .putInt(PRE_TEXT_SIZE, size)
            .apply();
    }

    public int getTextSize() {
        return SHARE_PREFERENCES.getInt(PRE_TEXT_SIZE, Constants.TEXT_SIZE_MEDIUM);
    }

    // isSub ? allow receiver notification : deny
    public void setSubTopic(Boolean isSub) {
        SHARE_PREFERENCES.edit()
            .putBoolean(PRE_SUB_TOPIC, isSub)
            .apply();
    }

    public Boolean getSubTopic() {
        return SHARE_PREFERENCES.getBoolean(PRE_SUB_TOPIC, true);
    }
}