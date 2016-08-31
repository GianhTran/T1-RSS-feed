package com.example.framgia.t1_rss_feed.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.data.models.NewsItem;
import com.example.framgia.t1_rss_feed.helper.EventListenerInterface;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 31/08/2016.
 */
public class HistoryAdapter extends
    RealmRecyclerViewAdapter<NewsItem, HistoryAdapter.HistoryHolder> {
    EventListenerInterface.OnItemNewsClickListener mOnItemNewsClickListener;

    public HistoryAdapter(@NonNull Context context,
                          @Nullable OrderedRealmCollection<NewsItem> data, EventListenerInterface
                              .OnItemNewsClickListener listener) {
        super(context, data, true);
        this.mOnItemNewsClickListener = listener;
    }

    @Override
    public HistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HistoryHolder(inflater.inflate(R.layout.item_new, parent, false));
    }

    @Override
    public void onBindViewHolder(HistoryHolder holder, int position) {
        // TODO UPDATE LATER
        NewsItem newsItem = getData().get(position);
    }

    class HistoryHolder extends RecyclerView.ViewHolder {
        public HistoryHolder(View itemView) {
            super(itemView);
            // TODO UPDATE LATER
        }
    }
}
