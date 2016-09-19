package com.example.framgia.t1_rss_feed.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.data.models.RssSource;
import com.example.framgia.t1_rss_feed.helper.EventListenerInterface;

import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 12/09/2016.
 */
public class MenuAdapter extends RealmRecyclerViewAdapter<RssSource, RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 1;
    private final int VIEW_TYPE_BUTTON = 2;
    private EventListenerInterface.OnMenuItemClickListener mMenuItemClickListener;
    private EventListenerInterface.OnClickAddRssListener mAddRssListener;
    private EventListenerInterface.OnRemoveRssListener mRemoveRssListener;

    public MenuAdapter(Context context, RealmResults<RssSource> menuItems) {
        super(context, menuItems, false);
        this.mMenuItemClickListener = (EventListenerInterface.OnMenuItemClickListener) context;
        this.mAddRssListener = (EventListenerInterface.OnClickAddRssListener) context;
        this.mRemoveRssListener = (EventListenerInterface.OnRemoveRssListener) context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_BUTTON:
                return new ButtonHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_add_more,
                        parent,
                        false));
            default:
                return new MenuItemHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_menu,
                        parent,
                        false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_ITEM:
                ((MenuItemHolder) holder).injectData(getData().get(position));
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == (getItemCount() - 1)) return VIEW_TYPE_BUTTON;
        return VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + 1; // + 1 button add more
    }

    /**
     * holder of menu list
     */
    public class MenuItemHolder extends RecyclerView.ViewHolder {
        public MenuItemHolder(View itemView) {
            super(itemView);
        }

        private void injectData(final RssSource item) {
            ((TextView) itemView).setText(item.getRssName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mMenuItemClickListener.onMenuItemClick(item.getId());
                }
            });
            if (item.getDefault())
                itemView.setBackgroundColor(ContextCompat.getColor(context,
                    R.color.backgroundColor));
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mRemoveRssListener.onRemoveRss(item.getId());
                    return true;
                }
            });
        }
    }

    /**
     * holder of button add more
     */
    public class ButtonHolder extends RecyclerView.ViewHolder {
        public ButtonHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mAddRssListener.onClickAddRss();
                }
            });
        }
    }
}
