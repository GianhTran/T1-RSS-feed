package com.example.framgia.t1_rss_feed.helper;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 24/08/2016.
 */
public class TypefaceCache {

    private static final Hashtable<String, Typeface> CACHE = new Hashtable<String, Typeface>();

    public static Typeface get(Context c, String name) {
        synchronized (CACHE) {
            if (!CACHE.containsKey(name)) {
                Typeface t = Typeface.createFromAsset(c.getAssets(), name);
                CACHE.put(name, t);
            }
            return CACHE.get(name);
        }
    }
}