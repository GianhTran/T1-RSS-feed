package com.example.framgia.t1_rss_feed.ui.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.framgia.t1_rss_feed.BaseActivity;
import com.example.framgia.t1_rss_feed.Constants;
import com.example.framgia.t1_rss_feed.Preferences;
import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.ui.fragment.DetailFragment;
import com.example.framgia.t1_rss_feed.ui.fragment.HistoryFragment;
import com.example.framgia.t1_rss_feed.util.CommonUtil;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 31/08/2016.
 */
public class HistoryActivity extends BaseActivity {
    private Toolbar mToolbarHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Preferences.with(this).getStyle() == Constants.DARK_STYLE)
            setTheme(R.style.AppThemeDark_NoActionBar);
        setContentView(R.layout.activity_history);
        initToolbar();
        changeFragment(R.id.frame_container_history, HistoryFragment.newInstance(), "");
    }

    private void initToolbar() {
        mToolbarHistory = (Toolbar) findViewById(R.id.toolbar_history);
        this.setSupportActionBar(mToolbarHistory);
        CommonUtil.setToolbarStyle(this, true, true);
        getSupportActionBar().setTitle(R.string.menu_history);
        mToolbarHistory.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getFragmentManager().findFragmentById(R.id.frame_container_history);
        if (fragment instanceof DetailFragment) {
            getFragmentManager().popBackStack();
            return;
        }
        super.onBackPressed();
    }
}
