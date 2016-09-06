package com.example.framgia.t1_rss_feed.service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.framgia.t1_rss_feed.Constants;
import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.data.models.News;
import com.example.framgia.t1_rss_feed.data.models.NewsItem;
import com.example.framgia.t1_rss_feed.network.ApiInterface;
import com.example.framgia.t1_rss_feed.network.ServiceGenerator;
import com.example.framgia.t1_rss_feed.ui.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 05/09/2016.
 */
public class SyncDataService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
        Call<News> call;
        // todo update for all of channel
        call = apiInterface.loadNewsOfAsian();
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                List<NewsItem> items = response.body().getChannel().getItems();
                checkData(items, response.body().getChannel().getTitle());
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                stopSelf();
            }
        });
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        //restart this service again in 24 hour
        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarm.set(
            AlarmManager.RTC_WAKEUP,
            //todo update : set time from ui
            System.currentTimeMillis() + AlarmManager.INTERVAL_DAY,
            PendingIntent.getService(this, Constants.SERVICE_REQUEST_CODE,
                new Intent(this, SyncDataService.class),
                Constants.SERVICE_FLAG)
        );
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * method using check data before save in database and push to list
     *
     * @param items: if items is able in data base, ignore it, else save it into realm
     */
    private void checkData(List<NewsItem> items, String channel) {
        new SaveDataAsyncTask(items, channel).execute();
    }

    /**
     * asyncTask using to save list News update from server, ignore news which saved
     */
    private class SaveDataAsyncTask extends AsyncTask<Void, Void, Boolean> {
        private List<NewsItem> mItems = new ArrayList<>();
        private String mChannel;
        private Boolean mHasNew = false;

        public SaveDataAsyncTask(List<NewsItem> itemList, String channel) {
            super();
            this.mItems = itemList;
            this.mChannel = channel;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    for (NewsItem itemNew : mItems) {
                        if (realm.where(NewsItem.class).equalTo
                            (Constants.KEY_LINK_ITEM, itemNew.getLink()).findAll().size() != 0)
                            continue;
                        itemNew.setId(itemNew.getNextPrimaryKey(realm));
                        itemNew.setChannel(mChannel);
                        itemNew.setViewed(false);
                        itemNew.setIndex(itemNew.getNextIndex(realm, mChannel));
                        itemNew.setReadTime(Constants.LONG_ZERO_VALUE);
                        realm.copyToRealm(itemNew);
                        mHasNew = true;
                    }
                }
            });
            realm.close();
            return mHasNew;
        }

        @Override
        protected void onPostExecute(Boolean hasNew) {
            super.onPostExecute(hasNew);
            if (hasNew) showNotification();
            stopSelf();
        }
    }

    /**
     * method using to push notification when have new items
     */
    private void showNotification() {
        String notificationTitle = getResources().getString(R.string.app_name);
        String notificationText = getResources().getString(R.string.msg_has_news);
        Intent myIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(),
            0,
            myIntent,
            PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(this)
            .setContentTitle(notificationTitle)
            .setContentText(notificationText).setSmallIcon(R.drawable.ic_logo)
            .setContentIntent(pendingIntent);
        NotificationManager notificationManager =
            (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0,
            (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) ? builder.build()
                : builder.getNotification());
    }
}
