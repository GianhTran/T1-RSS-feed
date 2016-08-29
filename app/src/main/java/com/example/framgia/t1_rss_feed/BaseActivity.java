package com.example.framgia.t1_rss_feed;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 23/08/2016.
 */
public class BaseActivity extends AppCompatActivity {
    protected void changeFragment(int containerId, Fragment fragment, String tag) {
        getFragmentManager().beginTransaction()
            .replace(containerId, fragment, tag)
            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            .commitAllowingStateLoss();
    }
}
