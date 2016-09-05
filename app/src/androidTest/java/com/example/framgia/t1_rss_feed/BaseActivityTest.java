package com.example.framgia.t1_rss_feed;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 05/09/2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
public class BaseActivityTest {
    protected void startFragment(Fragment fragment, AppCompatActivity activity) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(fragment, null);
        fragmentTransaction.commit();
    }
}
