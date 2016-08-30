package com.example.framgia.t1_rss_feed;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 29/08/2016.
 */
public class Preferences {
    private static final String PRE_LOAD = "preLoad";
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

    public boolean getPreLoad() {
        return SHARE_PREFERENCES.getBoolean(PRE_LOAD, false);
    }

    public void setPreLoad(boolean totalTime) {
        SHARE_PREFERENCES
            .edit()
            .putBoolean(PRE_LOAD, totalTime)
            .apply();
    }
}
