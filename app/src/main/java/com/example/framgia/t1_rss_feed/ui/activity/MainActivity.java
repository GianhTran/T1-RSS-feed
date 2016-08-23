package com.example.framgia.t1_rss_feed.ui.activity;

import android.os.Bundle;

import com.example.framgia.t1_rss_feed.BaseActivity;
import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.ui.fragment.HomeFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeFragment(R.id.rlContainer, HomeFragment.newInstant(), "");
    }
}