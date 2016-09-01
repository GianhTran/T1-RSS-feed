package com.example.framgia.t1_rss_feed.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.framgia.t1_rss_feed.BaseFragment;
import com.example.framgia.t1_rss_feed.Constants;
import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.data.models.NewsItem;
import com.example.framgia.t1_rss_feed.helper.EventListenerInterface;
import com.example.framgia.t1_rss_feed.ui.adapter.HistoryAdapter;
import com.example.framgia.t1_rss_feed.ui.view.DividerItemDecoration;

import io.realm.Realm;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 31/08/2016.
 */
public class HistoryFragment extends BaseFragment
    implements EventListenerInterface.OnItemNewsClickListener {
    private Realm mRealm;
    private RecyclerView mRecyclerViewHistory;
    private ProgressBar mProgressBarHistory;
    private HistoryAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        //get realm instance
        this.mRealm = Realm.getDefaultInstance();
        initView(view);
        initRecyclerView();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initRecyclerView() {
        mAdapter = new HistoryAdapter(getActivity(),
            mRealm.where(NewsItem.class).equalTo(Constants.KEY_VIEWED, true)
                .findAllSorted(Constants.KEY_ID),
            this);
        mRecyclerViewHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerViewHistory.setAdapter(mAdapter);
        mRecyclerViewHistory.setHasFixedSize(true);
        mRecyclerViewHistory.addItemDecoration(
            new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
    }

    private void initView(View view) {
        mRecyclerViewHistory = (RecyclerView) view.findViewById(R.id.recycler_history);
        mProgressBarHistory = (ProgressBar) view.findViewById(R.id.progress_bar_history);
    }

    private void showLoading(Boolean isLoading) {
        mProgressBarHistory.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }

    public static HistoryFragment newInstance() {
        HistoryFragment historyFragment = new HistoryFragment();
        return historyFragment;
    }

    @Override
    public void onItemNewsClick(long itemId, int position) {
        //todo UPDATE LATER
    }
}
