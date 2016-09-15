package com.example.framgia.t1_rss_feed.ui.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.framgia.t1_rss_feed.BaseActivity;
import com.example.framgia.t1_rss_feed.Constants;
import com.example.framgia.t1_rss_feed.Preferences;
import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.data.models.NewsItem;
import com.example.framgia.t1_rss_feed.data.models.RssSource;
import com.example.framgia.t1_rss_feed.helper.EventListenerInterface;
import com.example.framgia.t1_rss_feed.ui.adapter.MenuAdapter;
import com.example.framgia.t1_rss_feed.ui.dialog.AddMoreRssDialog;
import com.example.framgia.t1_rss_feed.ui.dialog.SettingsDialog;
import com.example.framgia.t1_rss_feed.ui.fragment.DetailFragment;
import com.example.framgia.t1_rss_feed.ui.fragment.HomeFragment;
import com.example.framgia.t1_rss_feed.ui.view.DividerItemDecoration;
import com.example.framgia.t1_rss_feed.util.DateTimeUtil;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 23/08/2016.
 */
public class MainActivity extends BaseActivity
    implements EventListenerInterface.OnMenuItemClickListener,
    EventListenerInterface.OnClickAddRssListener,
    EventListenerInterface.OnSubmitAddRssListener {
    private DrawerLayout mDrawerLayout;
    private Realm mRealm;
    private MenuAdapter mMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Preferences.with(this).getStyle() == Constants.DARK_STYLE)
            setTheme(R.style.AppThemeDark_NoActionBar);
        setContentView(R.layout.activity_main);
        mRealm = Realm.getDefaultInstance();
        initNavigationDrawer();
        notifyData();
        handleUserCount();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }

    private void initNavigationDrawer() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_home);
        RecyclerView recyclerViewMenu = (RecyclerView) findViewById(R.id.recycler_menu);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_home);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
            mDrawerLayout,
            toolbar,
            R.string.app_name,
            R.string.app_title);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        RealmResults<RssSource> menuItems = mRealm.where(RssSource.class)
            .equalTo(Constants.RSS_ACTIVE, true)
            .findAll();
        recyclerViewMenu.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMenu.addItemDecoration(new DividerItemDecoration(this,
            DividerItemDecoration.VERTICAL_LIST));
        mMenuAdapter = new MenuAdapter(this, menuItems);
        recyclerViewMenu.setAdapter(mMenuAdapter);
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
                startActivity(new Intent(this, HistoryActivity.class));
                break;
            case R.id.action_settings:
                new SettingsDialog(this).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return;
        }
        Fragment fragment = getFragmentManager().findFragmentById(R.id.frame_container);
        if (fragment instanceof DetailFragment) {
            getFragmentManager().popBackStack();
            return;
        }
        super.onBackPressed();
    }

    private void notifyData() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, Constants.NUMBER_OF_KEEP_DATE);
        Date deadLine = cal.getTime();
        new RefineData(deadLine).execute();
    }

    @Override
    public void onMenuItemClick(int rssId) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        changeFragment(R.id.frame_container,
            HomeFragment.newInstance(rssId),
            Constants.FRAGMENT_TAG);
    }

    @Override
    public void onClickAddRss() {
        new AddMoreRssDialog(this).show();
    }

    @Override
    public void onSubmitAddRss(String name, String link) {
        saveRssSource(name, link);
        mMenuAdapter.updateData(mRealm.where(RssSource.class)
            .equalTo(Constants.RSS_ACTIVE, true)
            .findAll());
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
                if (DateTimeUtil.compareDate(item.getPubDate(),
                    DateTimeUtil.formatDateToString(mDeadline),
                    DateTimeUtil.DEFAULT_FORMAT) >= 0) continue;
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
            changeFragment(R.id.frame_container, HomeFragment.newInstance(getBaseRssId()),
                Constants.FRAGMENT_TAG);
        }
    }

    /**
     * method using fire base to get number of user is online
     */
    public void handleUserCount() {
        Firebase listRef = new Firebase(Constants.FIRE_BASE_URL + Constants.FIRE_BASE_PRESENCE);
        final Firebase userRef = listRef.push();
        // Add ourselves to presence list when online.
        Firebase presenceRef =
            new Firebase(Constants.FIRE_BASE_URL + Constants.FIRE_BASE_INFOR);
        ValueEventListener myPresence = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // Remove ourselves when we disconnect.
                userRef.onDisconnect().removeValue();
                userRef.setValue(true);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        };
        presenceRef.addValueEventListener(myPresence);
        // Number of online users is the number of objects in the presence list.
        ValueEventListener myList = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //todo set user count = String.valueOf(snapshot.getChildrenCount()));
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        };
        listRef.addValueEventListener(myList);
    }

    /**
     * method using get rss id default
     *
     * @return id of first rss in database
     */
    private int getBaseRssId() {
        RssSource rssSource = mRealm.where(RssSource.class)
            .equalTo(Constants.RSS_ACTIVE, true)
            .findFirst();
        if (rssSource != null) return rssSource.getId();
        return Constants.DEFAULT_RSS_ID;
    }

    /**
     * method using save rss source
     *
     * @param name name of rss
     * @param link link to rss
     */
    private void saveRssSource(final String name, final String link) {
        final int id = new RssSource().getNextPrimaryKey(mRealm);
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RssSource rssSource = mRealm.createObject(RssSource.class);
                rssSource.setId(id);
                rssSource.setRssLink(link);
                rssSource.setRssName(name);
                rssSource.setActive(true);
                rssSource.setDefault(false);
            }
        });
        ;
    }
}