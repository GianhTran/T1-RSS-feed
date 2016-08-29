package com.example.framgia.t1_rss_feed.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.framgia.t1_rss_feed.BaseActivity;
import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.helper.RealmController;
import com.example.framgia.t1_rss_feed.ui.fragment.HomeFragment;

import io.realm.Realm;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 23/08/2016.
 */
public class MainActivity extends BaseActivity {
    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get realm instance
        this.mRealm = RealmController.with(this).getRealm();
        changeFragment(R.id.frame_container, HomeFragment.newInstance(), "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_history:
                Toast.makeText(MainActivity.this, "history App", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                Toast.makeText(MainActivity.this, "settings App", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}