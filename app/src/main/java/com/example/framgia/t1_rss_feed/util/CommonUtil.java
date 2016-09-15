package com.example.framgia.t1_rss_feed.util;

import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 30/08/2016.
 */
public class CommonUtil {
    /**
     * method using handle style of toolbar
     *
     * @param activity
     * @param hasBackButton show button back
     * @param hasTittle     show title
     */
    public static void setToolbarStyle(AppCompatActivity activity,
                                       Boolean hasBackButton,
                                       Boolean hasTittle) {
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(hasBackButton);
            activity.getSupportActionBar().setDisplayShowHomeEnabled(hasBackButton);
            activity.getSupportActionBar().setDisplayShowTitleEnabled(hasTittle);
        }
    }

    /**
     * Check valid link
     */
    private static Boolean checkLink(String link) {
        return Patterns.WEB_URL.matcher(link).matches();
    }
}
