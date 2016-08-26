package com.example.framgia.t1_rss_feed.ui.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.helper.TypefaceCache;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 24/08/2016.
 */
public class FontIcon extends TextView {
    private static Typeface sFont;


    public FontIcon(Context context) {
        super(context);
        setFont(context);
    }

    public FontIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont(context);
    }

    public FontIcon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont(context);
    }

    private void setFont(Context context) {
        // prevent exception in Android Studio / ADT interface builder
        if (this.isInEditMode()) {
            return;
        }

        //Check for font is already loaded
        if (sFont == null) {
            //Cache the font load status to improve performance
            sFont = TypefaceCache.get(context, getResources().getString(R.string.font_material_icon));
        }

        //Finally set the font
        setTypeface(sFont);
    }
}