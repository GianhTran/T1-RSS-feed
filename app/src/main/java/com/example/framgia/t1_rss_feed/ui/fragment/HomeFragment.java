package com.example.framgia.t1_rss_feed.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.framgia.t1_rss_feed.BaseFragment;
import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.data.models.New;
import com.example.framgia.t1_rss_feed.others.EndlessRecyclerViewScrollListener;
import com.example.framgia.t1_rss_feed.ui.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 23/08/2016.
 */
public class HomeFragment extends BaseFragment implements HomeAdapter.OnItemNewClickListener {

    private SwipeRefreshLayout mSwipeRefreshHome;
    private RecyclerView mRecyclerViewHome;
    private HomeAdapter mHomeAdapter;

    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initRecyclerView();
        initSwipeToRefresh();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(View view) {
        mSwipeRefreshHome = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshHome);
        mRecyclerViewHome = (RecyclerView) view.findViewById(R.id.recyclerViewHome);
    }

    private void initSwipeToRefresh() {
        mSwipeRefreshHome.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadData();
                showLoading(false);
            }
        });
    }

    /**
     * method using to reload data
     */
    private void reloadData() {
        mHomeAdapter.clearData();
        mHomeAdapter.addData(dummyData());
    }

    private void initRecyclerView() {
        mHomeAdapter = new HomeAdapter(dummyData(), getActivity(), this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewHome.setLayoutManager(layoutManager);
        mRecyclerViewHome.setAdapter(mHomeAdapter);
        mRecyclerViewHome.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // delay 5s before start load more
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHomeAdapter.addData(dummyData());
                    }
                }, 5000);
            }
        });
    }

    /**
     * create dummy data, return list of news
     */
    private List<New> dummyData() {
        // dummy data
        List<New> dummyData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            dummyData.add(new New().newIntanst());
        }
        return dummyData;
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
        }, 1000);
    }

    @Override
    public void temClick(String id) {
        Toast.makeText(getActivity(), id, Toast.LENGTH_SHORT).show();
    }
}
