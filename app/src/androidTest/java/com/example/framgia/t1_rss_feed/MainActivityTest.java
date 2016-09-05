package com.example.framgia.t1_rss_feed;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.FrameLayout;

import com.example.framgia.t1_rss_feed.ui.activity.MainActivity;
import com.example.framgia.t1_rss_feed.ui.fragment.HomeFragment;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 05/09/2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest extends BaseActivityTest {
    private FrameLayout mFrameLayout;
    AppCompatActivity mActivity;

    @Before
    public void setUp() {
        mActivity = Robolectric.setupActivity(MainActivity.class);
        mFrameLayout = (FrameLayout) mActivity.findViewById(R.id.frame_container);
        HomeFragment homeFragment = HomeFragment.newInstance();
        startFragment(homeFragment, mActivity);
    }

    @Test
    public void checkActivityNotNull() {
        Assert.assertNotNull(mActivity);
    }

    @Test
    public void onCreateShouldInflateTheMenu() {
        AppCompatActivity activity = Robolectric.setupActivity(MainActivity.class);
        final Menu menu = Shadows.shadowOf(activity).getOptionsMenu();
        Assertions.assertThat(menu.findItem(R.id.action_history).getTitle()).isEqualTo("history");
        Assertions.assertThat(menu.findItem(R.id.action_settings).getTitle()).isEqualTo("settings");
    }
}
