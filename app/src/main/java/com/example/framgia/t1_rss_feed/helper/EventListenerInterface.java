package com.example.framgia.t1_rss_feed.helper;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 31/08/2016.
 */
public class EventListenerInterface {
    /**
     * handler item click listener
     */
    public interface OnItemNewsClickListener {
        void onItemNewsClick(long itemId, int position);
    }
}
