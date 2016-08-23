package com.example.framgia.t1_rss_feed.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.framgia.t1_rss_feed.BaseFragment;
import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.ui.adapter.HomeAdapter;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 23/08/2016.
 */
public class HomeFragment extends BaseFragment {

    private SwipeRefreshLayout mSwipeRefreshHome;
    private RecyclerView mRecyclerViewHome;
    private HomeAdapter mHomeAdapter;

    public static HomeFragment newInstant() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container);
        initView(view);
        initRecyclerView();
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

    private void initRecyclerView() {
        mRecyclerViewHome.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
