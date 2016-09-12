package com.example.framgia.t1_rss_feed;

import android.app.Application;

import com.example.framgia.t1_rss_feed.data.models.RssName;
import com.example.framgia.t1_rss_feed.data.models.RssSource;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 29/08/2016.
 */
public class App extends Application {
    private Realm mRealm;

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
            .name(Realm.DEFAULT_REALM_NAME)
            .schemaVersion(Constants.REALM_SCHEMA_VERSION)
            .deleteRealmIfMigrationNeeded()
            .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        mRealm = Realm.getDefaultInstance();
        if (mRealm.where(RssSource.class).findFirst() == null)
            createChannel();
    }

    /**
     * set up data for list rss
     */
    private void createChannel() {
        for (final RssName name : RssName.values()) {
            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RssSource rssSource = realm.createObject(RssSource.class);
                    rssSource.setActive(true);
                    rssSource.setId(name.getCode());
                    rssSource.setRssName(name.getName());
                    rssSource.setDefault(true);
                }
            });
        }
    }
}
