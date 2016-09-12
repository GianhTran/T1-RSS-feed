package com.example.framgia.t1_rss_feed.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.framgia.t1_rss_feed.BaseFragment;
import com.example.framgia.t1_rss_feed.Constants;
import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.data.models.NewsItem;
import com.example.framgia.t1_rss_feed.helper.EndlessRecyclerViewScrollListener;
import com.example.framgia.t1_rss_feed.helper.EventListenerInterface;
import com.example.framgia.t1_rss_feed.ui.adapter.HistoryAdapter;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 31/08/2016.
 */
public class HistoryFragment extends BaseFragment
    implements EventListenerInterface.OnItemNewsClickListener,
    EventListenerInterface.OnItemCheckListener,
    EventListenerInterface.OnCancelListener,
    EventListenerInterface.OnClearListener {
    private RecyclerView mRecyclerViewHistory;
    private ProgressBar mProgressBarHistory;
    private HistoryAdapter mAdapter;
    private Boolean mIsLoadMore = true;
    private Realm mRealm;
    private EventListenerInterface.OnEditModeListener mEditModeListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mEditModeListener = (EventListenerInterface.OnEditModeListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        setHasOptionsMenu(true);
        initView(view);
        initRecyclerView();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_clear_all_history:
                deleteDataAll();
                break;
            case R.id.action_select:
                enableEditMode(true);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initRecyclerView() {
        mAdapter = new HistoryAdapter(getActivity(),
            getResultByPage(Constants.FIRST_PAGE),
            this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewHistory.setLayoutManager(layoutManager);
        mRecyclerViewHistory.setAdapter(mAdapter);
        mRecyclerViewHistory.setHasFixedSize(true);
        showLoading(false);
        mRecyclerViewHistory
            .addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
                @Override
                public void onLoadMore(int page, int totalItemsCount) {
                    if (!mIsLoadMore) return;
                    mAdapter.updateData(getResultByPage(page + 1)); // start from 1
                }
            });
    }

    /**
     * this method using load data by paging, (20 item / page)
     *
     * @param page page number
     * @return list of items
     */
    private RealmResults<NewsItem> getResultByPage(int page) {
        RealmResults<NewsItem> results = mRealm.where(NewsItem.class)
            .equalTo(Constants.KEY_VIEWED, true)
            .findAllSorted(Constants.KEY_HISTORY_INDEX, Sort.ASCENDING);
        if (results.isEmpty()) return null;
        long firstIndex = results.last().getHistoryIndex();
        long lastIndex = results.first().getHistoryIndex();
        long secondIndex = firstIndex - (page * Constants.ITEMS_PER_PAGE) + 1;
        mIsLoadMore = secondIndex > lastIndex;
        return mRealm.where(NewsItem.class)
            .equalTo(Constants.KEY_VIEWED, true)
            .between(Constants.KEY_HISTORY_INDEX,
                (mIsLoadMore) ? secondIndex : lastIndex,
                firstIndex)
            .findAllSorted(Constants.KEY_HISTORY_INDEX, Sort.DESCENDING);
    }

    private void initView(View view) {
        mRealm = Realm.getDefaultInstance();
        mRecyclerViewHistory = (RecyclerView) view.findViewById(R.id.recycler_history);
        mProgressBarHistory = (ProgressBar) view.findViewById(R.id.progress_bar_history);
    }

    private void showLoading(Boolean isLoading) {
        mProgressBarHistory.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    public static HistoryFragment newInstance() {
        HistoryFragment historyFragment = new HistoryFragment();
        return historyFragment;
    }

    @Override
    public void onItemNewsClick(long itemId, int position) {
        addFragment(R.id.frame_container_history, DetailFragment.newInstance(itemId, false));
    }

    private void enableEditMode(boolean isEdit) {
        mAdapter.enableEdit(isEdit);
        mEditModeListener.onEditMode(isEdit);
    }

    /**
     * method using delete all history data
     */
    private void deleteDataAll() {
        final RealmResults<NewsItem> results = mRealm.where(NewsItem.class)
            .equalTo(Constants.KEY_VIEWED, true)
            .findAllSorted(Constants.KEY_HISTORY_INDEX, Sort.ASCENDING);
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (NewsItem item : results) {
                    item.setViewed(false);
                }
            }
        });
        mAdapter.updateData(results);
        enableEditMode(false);
    }

    /**
     * method using delete news which selected
     */
    private void deleteChecked() {
        final RealmResults<NewsItem> results = mRealm.where(NewsItem.class)
            .equalTo(Constants.KEY_VIEWED, true)
            .equalTo(Constants.KEY_CHECKED, true)
            .findAllSorted(Constants.KEY_HISTORY_INDEX, Sort.ASCENDING);
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (NewsItem item : results) {
                    item.setViewed(false);
                    item.setChecked(false);
                }
            }
        });
        mAdapter.updateData(mRealm.where(NewsItem.class)
            .equalTo(Constants.KEY_VIEWED, true)
            .findAllSorted(Constants.KEY_HISTORY_INDEX, Sort.ASCENDING));
        enableEditMode(false);
    }

    @Override
    public void onItemCheck(final long itemId, final Boolean isChecked) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                NewsItem newsItem = mRealm.where(NewsItem.class)
                    .equalTo(Constants.KEY_ID, itemId).findFirst();
                newsItem.setChecked(isChecked);
            }
        });
    }

    @Override
    public void onCancel() {
        enableEditMode(false);
    }

    @Override
    public void onClear() {
        deleteChecked();
    }
}
