package com.example.framgia.t1_rss_feed.ui.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.framgia.t1_rss_feed.BaseFragment;
import com.example.framgia.t1_rss_feed.Constants;
import com.example.framgia.t1_rss_feed.Preferences;
import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.data.models.News;
import com.example.framgia.t1_rss_feed.data.models.NewsItem;
import com.example.framgia.t1_rss_feed.data.models.RssSource;
import com.example.framgia.t1_rss_feed.helper.EndlessRecyclerViewScrollListener;
import com.example.framgia.t1_rss_feed.helper.EventListenerInterface;
import com.example.framgia.t1_rss_feed.network.ApiInterface;
import com.example.framgia.t1_rss_feed.network.ServiceGenerator;
import com.example.framgia.t1_rss_feed.ui.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 23/08/2016.
 */
public class HomeFragment extends BaseFragment
    implements EventListenerInterface.OnItemNewsClickListener {
    private SwipeRefreshLayout mSwipeRefreshHome;
    private RecyclerView mRecyclerViewHome;
    private HomeAdapter mHomeAdapter;
    private TextView mTvDataEmpty;
    private Realm mRealm;
    private FloatingActionButton mFloatingActionHome;
    private Boolean mIsLoadMore = true;
    private CoordinatorLayout mCoordinatorLayout;
    private int mRssId;
    private RssSource mRssSource;
    private EventListenerInterface.OnSetTitleListener mSetTitleListener;

    public static HomeFragment newInstance(int rssId) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.KEY_RSS_ID, rssId);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Override
    public void setRetainInstance(boolean retain) {
        super.setRetainInstance(retain);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mSetTitleListener = (EventListenerInterface.OnSetTitleListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mRealm = Realm.getDefaultInstance();
        mRssId = getArguments().getInt(Constants.KEY_RSS_ID);
        initView(view);
        initSource();
        initToolbar();
        handleEvent();
        initRecyclerView();
        initSwipeToRefresh();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData();
    }

    private void initSource() {
        mRssSource = mRealm.where(RssSource.class).equalTo(Constants.KEY_ID, mRssId).findFirst();
    }

    private void initView(View view) {
        mSwipeRefreshHome = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_home);
        mRecyclerViewHome = (RecyclerView) view.findViewById(R.id.recycler_home);
        mTvDataEmpty = (TextView) view.findViewById(R.id.text_no_data);
        mFloatingActionHome = (FloatingActionButton) view.findViewById(R.id.fab_home);
        mCoordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinator_home);
    }

    private void handleEvent() {
        mFloatingActionHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerViewHome.smoothScrollToPosition(Constants.TOP_POSITION);
            }
        });
    }

    private void initSwipeToRefresh() {
        mSwipeRefreshHome.setColorSchemeResources(
            R.color.colorAccent,
            R.color.colorPrimary,
            R.color.backgroundColor);
        mSwipeRefreshHome.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                resetSavedPosition();
                reloadData();
                showLoading(false);
            }
        });
    }

    /**
     * method using to reload data
     */
    private void reloadData() {
        loadData();
    }

    private void initRecyclerView() {
        mHomeAdapter = new HomeAdapter(getActivity(), new RealmList<NewsItem>(),
            this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewHome.setLayoutManager(layoutManager);
        mRecyclerViewHome.setAdapter(mHomeAdapter);
        mRecyclerViewHome.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                if (mIsLoadMore)
                    updateData(getChannelName(), page + 1);
            }
        });
    }

    /**
     * method handler show progress bar of refresh layout
     */
    private void showLoading(final Boolean isShowLoading) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshHome.setRefreshing(isShowLoading);
            }
        }, Constants.DEFAULT_DELAY);
    }

    private void loadData() {
        clearList();
        showEmpty(false);
        showLoading(true);
        getCallBack().enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.body() == null) {
                    Snackbar.make(mCoordinatorLayout,
                        R.string.msg_error,
                        Snackbar.LENGTH_SHORT).show();
                    showLoading(false);
                    return;
                }
                String channel = response.body().getChannel().getTitle();
                List<NewsItem> items = response.body().getChannel().getItems();
                checkData(items, channel);
                setChannel(channel);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Snackbar.make(mCoordinatorLayout,
                    R.string.msg_update_error,
                    Snackbar.LENGTH_SHORT).show();
                updateData(getChannelName(), Constants.FIRST_PAGE);
            }
        });
    }

    @Override
    public void onItemNewsClick(long itemId, int position) {
        Preferences.with(getActivity()).setPreLoad(position);
        addFragment(R.id.frame_container, DetailFragment.newInstance(itemId, true));
    }

    /**
     * method using change tittle of toolbar
     */
    private void initToolbar() {
        if (mSetTitleListener != null)
            mSetTitleListener.onSetTittle(mRssSource.getRssName());
    }

    /**
     * method using check data before save in database and push to list
     *
     * @param items: if items is able in data base, ignore it, else save it into realm
     */
    private void checkData(List<NewsItem> items, String channel) {
        new SaveDataAsyncTask(items, channel).execute();
    }

    private void showEmpty(Boolean isEmpty) {
        mTvDataEmpty.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroy();
        mRealm.close(); // Remember to close Realm when done.
    }

    /**
     * method using update data
     *
     * @param channel chanel of news ex: asian
     * @param page    pager index
     */
    private void updateData(final String channel, int page) {
        RealmResults<NewsItem> results = mRealm.where(NewsItem.class)
            .equalTo(Constants.KEY_CHANNEL, channel)
            .findAllSorted(Constants.KEY_INDEX, Sort.ASCENDING);
        if (results.isEmpty()) {
            showLoading(false);
            return;
        }
        long firstIndex = results.last().getIndex();
        long lastIndex = results.first().getIndex();
        long secondIndex = firstIndex - (page * Constants.ITEMS_PER_PAGE) + 1;
        mIsLoadMore = secondIndex > lastIndex;
        mHomeAdapter.updateData(mRealm.where(NewsItem.class)
            .equalTo(Constants.KEY_CHANNEL, channel)
            .between(Constants.KEY_INDEX, (mIsLoadMore) ? secondIndex : lastIndex, firstIndex)
            .findAll());
        showLoading(false);
    }

    private void resetSavedPosition() {
        Preferences.with(getActivity()).setPreLoad(Constants.TOP_POSITION);
    }

    private String getChannelName() {
        return mRssSource.getRssChannel();
    }

    /**
     * asyncTask using to save list News update from server, ignore news which saved
     */
    private class SaveDataAsyncTask extends AsyncTask<Void, Void, Boolean> {
        private List<NewsItem> mItems = new ArrayList<>();
        private String mChannel;
        private Boolean mHasNews = false;

        public SaveDataAsyncTask(List<NewsItem> itemList, String channel) {
            super();
            this.mItems = itemList;
            this.mChannel = channel;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    if (mItems == null)
                        return;
                    // we don not need check condition pub day > 10, because of rss always return
                    // pub day <=3
                    for (NewsItem itemNew : mItems) {
                        if (realm.where(NewsItem.class).equalTo(Constants.KEY_LINK_ITEM,
                            itemNew.getLink())
                            .findAll().size() != 0)
                            continue;
                        itemNew.setId(itemNew.getNextPrimaryKey(realm));
                        itemNew.setChannel(mChannel);
                        itemNew.setIndex(itemNew.getNextIndex(realm, mChannel));
                        itemNew.setViewed(false);
                        itemNew.setReadTime(Constants.LONG_ZERO_VALUE);
                        itemNew.setHistoryIndex(Constants.DEF_HISTOTY_INDEX_VALUE);
                        realm.copyToRealm(itemNew);
                        mHasNews = true;
                    }
                }
            });
            realm.close();
            return mHasNews;
        }

        @Override
        protected void onPostExecute(Boolean hasNew) {
            super.onPostExecute(hasNew);
            updateData(mChannel, Constants.FIRST_PAGE);
            if (!hasNew) return;
            Snackbar.make(mCoordinatorLayout, R.string.msg_has_news, Snackbar.LENGTH_SHORT).show();
        }
    }

    /**
     * this method using clear all data of recycler view
     * because realm adapter have no method clear data
     * so we need update by blank list data
     */
    private void clearList() {
        if (mHomeAdapter != null)
            mHomeAdapter.updateData(new RealmList<NewsItem>());
    }

    private Call<News> getCallBack() {
        return ServiceGenerator
            .createService(ApiInterface.class)
            .getCustomFeed(mRssSource.getRssLink());
    }

    /**
     * set channel for rss which registered
     *
     * @param channel chanel of rss
     */
    private void setChannel(final String channel) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mRssSource.setRssChannel(channel);
            }
        });
    }
}
