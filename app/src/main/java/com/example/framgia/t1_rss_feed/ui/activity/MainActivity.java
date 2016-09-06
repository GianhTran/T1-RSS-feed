package com.example.framgia.t1_rss_feed.ui.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.framgia.t1_rss_feed.BaseActivity;
import com.example.framgia.t1_rss_feed.Constants;
import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.data.models.NewsItem;
import com.example.framgia.t1_rss_feed.ui.fragment.HomeFragment;
import com.example.framgia.t1_rss_feed.util.DateTimeUtil;

import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 23/08/2016.
 */
public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notifyData();
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

    private void notifyData() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, Constants.NUMBER_OF_KEEP_DATE);
        Date deadLine = cal.getTime();
        new RefineData(deadLine).execute();
    }

    private class RefineData extends AsyncTask<Void, Void, Void> {
        private Date mDeadline;

        public RefineData(Date date) {
            super();
            mDeadline = date;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Realm realm = Realm.getDefaultInstance();
            RealmResults<NewsItem> results = realm.where(NewsItem.class).findAll();
            for (final NewsItem item : results) {
                if (DateTimeUtil.compareDate(item.getPubDate(), DateTimeUtil.formatDateToString
                    (mDeadline)) >= 0) continue;
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        NewsItem newsItem = realm.where(NewsItem.class).equalTo(Constants
                            .KEY_ID, item.getId()).findFirst();
                        newsItem.deleteFromRealm();
                    }
                });
            }
            realm.close();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            changeFragment(R.id.frame_container, HomeFragment.newInstance(),
                Constants.FRAGMENT_TAG);
        }
    }
}