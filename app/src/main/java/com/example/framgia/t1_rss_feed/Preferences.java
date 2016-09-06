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
}
