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
import com.example.framgia.t1_rss_feed.Constants;
import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.data.models.News;
import com.example.framgia.t1_rss_feed.helper.EndlessRecyclerViewScrollListener;
import com.example.framgia.t1_rss_feed.network.ApiInterface;
import com.example.framgia.t1_rss_feed.network.ServiceGenerator;
import com.example.framgia.t1_rss_feed.ui.adapter.HomeAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 23/08/2016.
 */
public class HomeFragment extends BaseFragment implements HomeAdapter.OnItemNewsClickListener {

    private SwipeRefreshLayout mSwipeRefreshHome;
    private RecyclerView mRecyclerViewHome;
    private HomeAdapter mHomeAdapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
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
        loadData();
    }

    private void initView(View view) {
        mSwipeRefreshHome = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_home);
        mRecyclerViewHome = (RecyclerView) view.findViewById(R.id.recycler_home);
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
        //todo reload data
    }

    private void initRecyclerView() {
        mHomeAdapter = new HomeAdapter(new ArrayList<News>(), this);
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
                        //todo load more event
                    }
                }, Constants.DEFAULT_DELAY);
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
        ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
        Call<News> call = apiInterface.loadNewsOfAsian();
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
            }
        });
    }

    @Override
    public void onItemNewsClick(String id) {
        replaceFragment(R.id.frame_container, DetailFragment.newInstance());
    }
}
