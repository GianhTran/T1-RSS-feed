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

    /**
     * handler check box click listener
     */
    public interface OnItemCheckListener {
        void onItemCheck(long itemId, Boolean isChecked);
    }

    /**
     * handle button cancel clear event
     */
    public interface OnCancelListener {
        void onCancel();
    }

    /**
     * handle button  clear event
     */
    public interface OnClearListener {
        void onClear();
    }

    /**
     * handle show/hide edit mode
     */
    public interface OnEditModeListener {
        void onEditMode(Boolean isEdit);
    }
}
