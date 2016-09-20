package com.example.framgia.t1_rss_feed.ui.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.framgia.t1_rss_feed.BaseActivity;
import com.example.framgia.t1_rss_feed.Constants;
import com.example.framgia.t1_rss_feed.Preferences;
import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.helper.EventListenerInterface;
import com.example.framgia.t1_rss_feed.ui.dialog.ClearHistoryDialog;
import com.example.framgia.t1_rss_feed.ui.fragment.DetailFragment;
import com.example.framgia.t1_rss_feed.ui.fragment.HistoryFragment;
import com.example.framgia.t1_rss_feed.util.CommonUtil;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 31/08/2016.
 */
public class HistoryActivity extends BaseActivity
    implements EventListenerInterface.OnEditModeListener {
    private Toolbar mToolbarHistory;
    private Button mButtonClear;
    private Button mButtonCancel;
    private EventListenerInterface.OnClearListener mClearListener;
    private EventListenerInterface.OnCancelListener mCancelListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Preferences.with(this).getStyle() == Constants.DARK_STYLE)
            setTheme(R.style.AppThemeDark_NoActionBar);
        setContentView(R.layout.activity_history);
        initToolbar();
        initView();
        handleEvent();
        changeFragment(R.id.frame_container_history, HistoryFragment.newInstance(), "");
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof HistoryFragment) {
            mCancelListener = (EventListenerInterface.OnCancelListener) fragment;
            mClearListener = (EventListenerInterface.OnClearListener) fragment;
        }
    }

    private void initView() {
        mButtonClear = (Button) findViewById(R.id.button_clear_select);
        mButtonCancel = (Button) findViewById(R.id.button_cancel_select);
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

    private void handleEvent() {
        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCancelListener != null) mCancelListener.onCancel();
            }
        });
        mButtonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mClearListener != null) mClearListener.onClear();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_clear_all_history:
                break;
            case R.id.action_select:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * show alert confirm delete all
     * TODO using later
     */
    private void showConfirmAlert() {
        new ClearHistoryDialog(this).show();
    }

    /**
     * method using show/hide edit mode
     *
     * @param isEdit isEdit? show : hide
     */
    @Override
    public void onEditMode(Boolean isEdit) {
        mButtonCancel.setVisibility(isEdit ? View.VISIBLE : View.GONE);
        mButtonClear.setVisibility(isEdit ? View.VISIBLE : View.GONE);
    }
}
