package com.example.framgia.t1_rss_feed.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.data.models.NewsItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 23/08/2016.
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 1;
    private final int VIEW_TYPE_LOAD_MORE = 2;
    public OnItemNewsClickListener mOnItemNewClickListener;
    private List<NewsItem> mNews = new ArrayList<>();

    public HomeAdapter(List<NewsItem> news, OnItemNewsClickListener listener) {
        this.mNews = news;
        this.mOnItemNewClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case VIEW_TYPE_ITEM:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new,
                    parent,
                    false);
                return new ItemHolder(view);
            case VIEW_TYPE_LOAD_MORE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loadmore,
                    parent,
                    false);
                return new LoadMoreHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_ITEM:
                ((ItemHolder) holder).injectData(mNews.get(position));
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == (getItemCount() - 1))
            return VIEW_TYPE_LOAD_MORE;
        return VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return mNews.size() + 1; // 1 item for load more
    }

    /**
     * method add data into list
     */
    public void addData(List<NewsItem> data) {
        mNews.addAll(data);
        notifyItemRangeInserted(getItemCount() - 1, data.size());
    }

    public void clearData() {
        mNews.clear();
        notifyDataSetChanged();
    }

    /**
     * handler item click listener
     */
    public interface OnItemNewsClickListener {
        void onItemNewsClick(NewsItem item);
    }

    /**
     * holder of load more
     */
    public static class LoadMoreHolder extends RecyclerView.ViewHolder {
        public LoadMoreHolder(View view) {
            super(view);
        }
    }

    /**
     * holder of item new
     */
    public class ItemHolder extends RecyclerView.ViewHolder {
        private TextView mTvTitle;
        private TextView mTvTime;
        private TextView mTvContent;

        public ItemHolder(View view) {
            super(view);
            mTvContent = (TextView) view.findViewById(R.id.text_content);
            mTvTime = (TextView) view.findViewById(R.id.text_time);
            mTvTitle = (TextView) view.findViewById(R.id.text_title);
        }

        public void injectData(final NewsItem item) {
            mTvContent.setText(item.getDescription());
            mTvTime.setText(item.getPubDate());
            mTvTitle.setText(item.getTitle());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemNewClickListener.onItemNewsClick(item);
                }
            });
        }
    }
}
