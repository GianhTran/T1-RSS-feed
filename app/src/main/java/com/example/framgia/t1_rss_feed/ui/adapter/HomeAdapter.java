package com.example.framgia.t1_rss_feed.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.framgia.t1_rss_feed.Constants;
import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.data.models.NewsItem;
import com.example.framgia.t1_rss_feed.helper.EventListenerInterface;
import com.example.framgia.t1_rss_feed.util.DateTimeUtil;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 23/08/2016.
 */
public class HomeAdapter extends RealmRecyclerViewAdapter<NewsItem, RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 1;
    private final int VIEW_TYPE_LOAD_MORE = 2;
    public EventListenerInterface.OnItemNewsClickListener mOnItemNewClickListener;

    public HomeAdapter(Context context, RealmList<NewsItem> news,
                       EventListenerInterface.OnItemNewsClickListener listener) {
        super(context, news, false);
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
                ((ItemHolder) holder).injectData(getData().get(position), position);
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
        return super.getItemCount() + 1;// 1 item for load more
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
        private TextView mNew;

        public ItemHolder(View view) {
            super(view);
            mTvContent = (TextView) view.findViewById(R.id.text_content);
            mTvTime = (TextView) view.findViewById(R.id.text_time);
            mTvTitle = (TextView) view.findViewById(R.id.text_title);
            mNew = (TextView) view.findViewById(R.id.text_new);
        }

        public void injectData(final NewsItem item, final int position) {
            Boolean isNEW = (DateTimeUtil.compareDate(item.getPubDate(),
                DateTimeUtil.formatDateToString(new Date()),
                DateTimeUtil.SECOND_FORMAT) == Constants.SAME_DAY);
            mNew.setVisibility((isNEW && !item.getViewed()) ? View.VISIBLE : View.GONE);
            mTvContent.setText(Html.fromHtml(item.getDescription()));
            mTvTime.setText(item.getPubDate());
            mTvTitle.setText(item.getTitle());
            itemView.setBackgroundColor(ContextCompat.getColor(context,
                (item.getViewed()) ? R.color.backgroundColor : R.color.backgroundColorSecond));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemNewClickListener.onItemNewsClick(item.getId(), position);
                    itemView.setBackgroundColor(ContextCompat.getColor(context,
                        R.color.backgroundColor));
                    mNew.setVisibility(View.GONE);
                }
            });
        }
    }
}
