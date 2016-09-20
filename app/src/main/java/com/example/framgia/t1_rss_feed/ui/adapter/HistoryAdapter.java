package com.example.framgia.t1_rss_feed.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.data.models.NewsItem;
import com.example.framgia.t1_rss_feed.helper.EventListenerInterface;
import com.example.framgia.t1_rss_feed.ui.fragment.HistoryFragment;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 31/08/2016.
 */
public class HistoryAdapter extends
    RealmRecyclerViewAdapter<NewsItem, HistoryAdapter.HistoryHolder> {
    private EventListenerInterface.OnItemNewsClickListener mOnItemNewsClickListener;
    private EventListenerInterface.OnItemCheckListener mOnItemCheckListener;
    private Boolean mIsEdit = false;

    public HistoryAdapter(@NonNull Context context,
                          @Nullable OrderedRealmCollection<NewsItem> data,
                          HistoryFragment listener) {
        super(context, data, true);
        this.mOnItemNewsClickListener = listener;
        this.mOnItemCheckListener = listener;
    }

    @Override
    public HistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HistoryHolder(inflater.inflate(R.layout.item_new, parent, false));
    }

    @Override
    public void onBindViewHolder(HistoryHolder holder, int position) {
        holder.injectData(getData().get(position), position);
    }

    class HistoryHolder extends RecyclerView.ViewHolder {
        private TextView mTvTitle;
        private TextView mTvTime;
        private TextView mTvContent;
        private CheckBox mCheckBoxDelete;

        public HistoryHolder(View itemView) {
            super(itemView);
            mTvContent = (TextView) itemView.findViewById(R.id.text_content);
            mTvTime = (TextView) itemView.findViewById(R.id.text_time);
            mTvTitle = (TextView) itemView.findViewById(R.id.text_title);
            mCheckBoxDelete = (CheckBox) itemView.findViewById(R.id.check_box_delete);
        }

        public void injectData(final NewsItem item, final int position) {
            mTvContent.setText(Html.fromHtml(item.getDescription()));
            mTvTime.setText(item.getPubDate());
            mTvTitle.setText(item.getTitle());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!mIsEdit)
                        mOnItemNewsClickListener.onItemNewsClick(item.getId(), position);
                }
            });
            mCheckBoxDelete.setChecked(item.getChecked());
            mCheckBoxDelete.setVisibility((mIsEdit) ? View.VISIBLE : View.GONE);
            if (!mIsEdit) mCheckBoxDelete.setChecked(false);
            mCheckBoxDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemCheckListener.onItemCheck(item.getId(), mCheckBoxDelete.isChecked());
                }
            });
        }
    }

    public void enableEdit(Boolean isEdit) {
        mIsEdit = isEdit;
        notifyDataSetChanged();
    }
}
