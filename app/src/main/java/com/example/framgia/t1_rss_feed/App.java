package com.example.framgia.t1_rss_feed;

import android.app.Application;

import com.example.framgia.t1_rss_feed.data.models.RssSource;
import com.firebase.client.Firebase;

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
        Firebase.setAndroidContext(this);
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
     * set up default data: tinh te rss
     */
    private void createChannel() {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                final int id = new RssSource().getNextPrimaryKey(mRealm);
                RssSource rssSource = realm.createObject(RssSource.class);
                rssSource.setActive(true);
                rssSource.setId(id);
                rssSource.setRssName(Constants.TINHTE_CHANNEL_NAME);
                rssSource.setRssLink(Constants.TINHTE_CHANNEL_LINK);
                rssSource.setDefault(true);
            }
        });
    }
}
