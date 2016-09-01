package com.example.framgia.t1_rss_feed.ui.activity;

import android.os.Bundle;

import com.example.framgia.t1_rss_feed.BaseActivity;
import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.ui.fragment.HistoryFragment;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 31/08/2016.
 */
public class HistoryActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        changeFragment(R.id.frame_container_history, HistoryFragment.newInstance(), "");
    }
}
