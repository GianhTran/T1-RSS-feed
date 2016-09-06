package com.example.framgia.t1_rss_feed.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 05/09/2016.
 */
public class SyncDataReceive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, SyncDataService.class));
    }
}
