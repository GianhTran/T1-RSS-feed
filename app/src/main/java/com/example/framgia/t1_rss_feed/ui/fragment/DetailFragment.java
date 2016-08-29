package com.example.framgia.t1_rss_feed.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.framgia.t1_rss_feed.BaseFragment;
import com.example.framgia.t1_rss_feed.Constants;
import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.data.models.NewsItem;
import com.example.framgia.t1_rss_feed.util.CommonUtil;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 24/08/2016.
 */
public class DetailFragment extends BaseFragment {
    private Toolbar mToolbarHome;
    private NewsItem mNewsItem;
    private ImageView mImgDetail;
    private TextView mTvTitleDetail;
    private TextView mTvTimeDetail;
    private TextView mTvContentDetail;
    private TextView mTvLink;
    private TextView mTvAuthor;

    public static DetailFragment newInstance(NewsItem item) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.INTENT_KEY_NEWS_ITEM, item);
        detailFragment.setArguments(args);
        return detailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        initToolbar();
        initView(view);
        mNewsItem = getArguments().getParcelable(Constants.INTENT_KEY_NEWS_ITEM);
        if (mNewsItem != null) loadData(mNewsItem);
        return view;
    }

    private void initView(View view) {
        mImgDetail = (ImageView) view.findViewById(R.id.image_detail);
        mTvTitleDetail = (TextView) view.findViewById(R.id.text_title_detail);
        mTvTimeDetail = (TextView) view.findViewById(R.id.text_time_detail);
        mTvContentDetail = (TextView) view.findViewById(R.id.text_content_detail);
        mTvLink = (TextView) view.findViewById(R.id.text_link);
        mTvAuthor = (TextView) view.findViewById(R.id.text_author);
    }

    private void loadData(NewsItem item) {
        mTvTitleDetail.setText(item.getTitle());
        mTvContentDetail.setText(item.getDescription());
        mTvAuthor.setText(item.getAuthor());
        mTvTimeDetail.setText(item.getPubDate());
        mTvLink.setText(item.getLink());
        Glide.with(this)
            .load(item.getEnclosure().getLink())
            .centerCrop()
            .placeholder(R.drawable.img_no_image_placeholder)
            .crossFade()
            .into(mImgDetail);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initToolbar() {
        mToolbarHome = (Toolbar) getActivity().findViewById(R.id.toolbar_home);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mToolbarHome);
        CommonUtil.setToolbarStyle(activity, true, false);
        mToolbarHome.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(R.id.frame_container, HomeFragment.newInstance());
            }
        });
    }
}
