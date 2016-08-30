package com.example.framgia.t1_rss_feed.ui.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.framgia.t1_rss_feed.BaseFragment;
import com.example.framgia.t1_rss_feed.Constants;
import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.data.models.NewsItem;
import com.example.framgia.t1_rss_feed.data.models.TempNews;
import com.example.framgia.t1_rss_feed.util.CommonUtil;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 24/08/2016.
 */
public class DetailFragment extends BaseFragment {
    private Toolbar mToolbarHome;
    private ImageView mImgDetail;
    private TextView mTvTitleDetail;
    private TextView mTvTimeDetail;
    private TextView mTvContentDetail;
    private TextView mTvLink;
    private TextView mTvAuthor;
    private DetailFragment mDetailFragment;
    private ProgressBar mProgressBarDetail;

    public static DetailFragment newInstance(long itemId) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putLong(Constants.INTENT_KEY_NEWS_ITEM_ID, itemId);
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
        new LoadNewsDetailAsyncTask(getArguments().getLong(Constants.INTENT_KEY_NEWS_ITEM_ID))
            .execute();
        return view;
    }

    private void initView(View view) {
        mImgDetail = (ImageView) view.findViewById(R.id.image_detail);
        mTvTitleDetail = (TextView) view.findViewById(R.id.text_title_detail);
        mTvTimeDetail = (TextView) view.findViewById(R.id.text_time_detail);
        mTvContentDetail = (TextView) view.findViewById(R.id.text_content_detail);
        mTvLink = (TextView) view.findViewById(R.id.text_link);
        mTvAuthor = (TextView) view.findViewById(R.id.text_author);
        mProgressBarDetail = (ProgressBar) view.findViewById(R.id.progress_bar_detail);
        mDetailFragment = this;
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

    private void showLoading(Boolean isLoading) {
        mProgressBarDetail.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    /**
     * AsyncTask using to load news by id and update ui
     */
    private class LoadNewsDetailAsyncTask extends AsyncTask<Void, Void, TempNews> {
        private long mId;
        private Realm mRealm;

        public LoadNewsDetailAsyncTask(long id) {
            super();
            this.mId = id;
            showLoading(true);
        }

        @Override
        protected TempNews doInBackground(Void... voids) {
            try {
                mRealm = Realm.getInstance(new RealmConfiguration.Builder(getActivity()).build());
                NewsItem newsItem = mRealm.where(NewsItem.class).equalTo(Constants.KEY_ID, mId)
                    .findFirst();
                TempNews tempNews = new TempNews();
                tempNews.setTitle(newsItem.getTitle());
                tempNews.setAuthor(newsItem.getAuthor());
                tempNews.setDescription(newsItem.getDescription());
                tempNews.setImage(newsItem.getEnclosure().getLink());
                tempNews.setPubDate(newsItem.getPubDate());
                tempNews.setLinkItem(newsItem.getLink());
                return tempNews;
            } finally {
                mRealm.close();
            }
        }

        @Override
        protected void onPostExecute(TempNews newsItem) {
            super.onPostExecute(newsItem);
            mTvTitleDetail.setText(newsItem.getTitle());
            mTvContentDetail.setText(newsItem.getDescription());
            mTvAuthor.setText(newsItem.getAuthor());
            mTvTimeDetail.setText(newsItem.getPubDate());
            mTvLink.setText(newsItem.getLinkItem());
            Glide.with(mDetailFragment)
                .load(newsItem.getImage())
                .centerCrop()
                .placeholder(R.drawable.img_no_image_placeholder)
                .crossFade()
                .into(mImgDetail);
            showLoading(false);
        }
    }
}
