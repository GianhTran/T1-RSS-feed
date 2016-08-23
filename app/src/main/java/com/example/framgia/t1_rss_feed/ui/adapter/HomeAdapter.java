package com.example.framgia.t1_rss_feed.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.data.models.New;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 23/08/2016.
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 1;
    private final int VIEW_TYPE_LOAD_MORE = 2;
    private final int VIEW_TYPE_TRASH = 3;

    public OnItemNewClickListener mOnItemNewClickListener;

    private List<New> mNews = new ArrayList<>();
    private Context mContext;

    public HomeAdapter(List<New> news, Context context, OnItemNewClickListener listener) {
        this.mNews = news;
        this.mContext = context;
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
            case VIEW_TYPE_TRASH:
                // trash item is used to add an extra space in the end of RecyclerView
                view = new View(mContext);
                view.setLayoutParams(new ViewGroup.LayoutParams(0,
                        Math.round(mContext.getResources().getDimension(R.dimen.dimen_medium))));
                return new TrashHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_ITEM:
                ((ItemHolder) holder).injectData(mNews.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 2)
            return VIEW_TYPE_LOAD_MORE;
        else if (position == getItemCount() - 1)
            return VIEW_TYPE_TRASH;
        else
            return VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return mNews.size() + 2; // 1 item for load more and 1 for trash
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
            mTvContent = (TextView) view.findViewById(R.id.tvContent);
            mTvTime = (TextView) view.findViewById(R.id.tvTime);
            mTvTitle = (TextView) view.findViewById(R.id.tvTitle);
        }

        public void injectData(final New item) {
            mTvContent.setText(item.getItem().getContent());
            mTvTime.setText(item.getItem().getPubDate());
            mTvTitle.setText(item.getItem().getTitle());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemNewClickListener.temClick(item.getId());
                }
            });
        }
    }

    /**
     * holder of load more
     */
    public static class LoadMoreHolder extends RecyclerView.ViewHolder {
        ProgressBar mProgressBarHome;

        public LoadMoreHolder(View view) {
            super(view);
            mProgressBarHome = (ProgressBar) view.findViewById(R.id.progressBarHome);
        }
    }

    /**
     * holder of trash item
     */
    public static class TrashHolder extends RecyclerView.ViewHolder {
        public TrashHolder(View view) {
            super(view);
        }
    }

    /**
     * method add data into list
     */
    public void addData(List<New> data) {
        mNews.addAll(data);
        notifyItemRangeInserted(getItemCount() - 2, data.size());
    }

    public void clearData() {
        mNews.clear();
        notifyItemRangeRemoved(0, getItemCount() - 2);
    }

    /**
     * handler item click listener
     */
    public interface OnItemNewClickListener {
        void temClick(String id);
    }
}
