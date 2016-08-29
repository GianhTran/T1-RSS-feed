package com.example.framgia.t1_rss_feed.helper;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;

import io.realm.Realm;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 29/08/2016.
 */
public class RealmController {
    public static RealmController sInstance;
    private final Realm REALM;

    public RealmController(Application application) {
        REALM = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {
        if (sInstance == null) {
            sInstance = new RealmController(fragment.getActivity().getApplication());
        }
        return sInstance;
    }

    public static RealmController with(Activity activity) {
        if (sInstance == null) {
            sInstance = new RealmController((activity.getApplication()));
        }
        return sInstance;
    }

    public static RealmController with(Application application) {
        if (sInstance == null) {
            sInstance = new RealmController(application);
        }
        return sInstance;
    }

    public static RealmController getInstance() {
        return sInstance;
    }

    public Realm getRealm() {
        return REALM;
    }
}
