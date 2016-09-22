package com.example.framgia.t1_rss_feed.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.example.framgia.t1_rss_feed.Constants;
import com.example.framgia.t1_rss_feed.Preferences;
import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.data.models.NewsItem;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import io.realm.Realm;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 15/09/2016.
 */
public class MessagingService extends FirebaseMessagingService {
    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Check if message contains a data payload.
        if (Preferences.with(this).getSubTopic() && remoteMessage.getData().size() > 0) {
            sendNotification(remoteMessage.getData()
                .toString()
                .replace(Constants.FIRE_BASE_LINK_PRE, "")
                .replace(Constants.FIRE_BASE_LINK_LAST, ""));
        }
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(String messageBody) {
        if (!messageBody.startsWith(Constants.HTTP) && !messageBody.startsWith(Constants.HTTPS))
            messageBody = Constants.HTTP + messageBody;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(messageBody));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
            0,
            intent,
            PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.ic_logo)
            .setContentTitle(getResources().getString(R.string.app_name))
            .setContentText(getResources().getString(R.string.msg_has_news))
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent);
        NotificationManager notificationManager =
            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }

    /**
     * method using check link is able in data base or not, if not, open via browser else open via
     * app
     * todo using later
     *
     * @param link link news
     * @return if new able -> id else -1
     */
    private long getId(String link) {
        Realm realm = Realm.getDefaultInstance();
        NewsItem newsItem = realm.where(NewsItem.class)
            .equalTo(Constants.KEY_LINK_ITEM, link)
            .findFirst();
        if (newsItem != null) return newsItem.getId();
        return Constants.VALUE_OF_NEW_NEWS;
    }
}
