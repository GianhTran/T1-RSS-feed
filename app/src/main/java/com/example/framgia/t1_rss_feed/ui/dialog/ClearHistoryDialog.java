package com.example.framgia.t1_rss_feed.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.example.framgia.t1_rss_feed.R;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 13/09/2016.
 */
public class ClearHistoryDialog extends Dialog {
    private Button mButtonSubmit;
    private Button mButtonCancel;
    private CheckBox mCheckBoxClear;
    private ImageView mImageStartTime;
    private ImageView mImageEndTime;

    public ClearHistoryDialog(Context context) {
        super(context);
        setContentView(R.layout.dialog_clear_history);
        getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        initView();
        handleEvent();
    }

    private void initView() {
        mButtonSubmit = (Button) findViewById(R.id.button_clear_submit);
        mButtonCancel = (Button) findViewById(R.id.button_settings_cancel);
        mCheckBoxClear = (CheckBox) findViewById(R.id.check_box_clear);
        mImageStartTime = (ImageView) findViewById(R.id.image_start_time);
        mImageEndTime = (ImageView) findViewById(R.id.image_end_time);
    }

    private void handleEvent() {
        //todo update later
        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        mImageStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        mImageEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        mCheckBoxClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableChoiceTime(mCheckBoxClear.isChecked());
            }
        });
    }

    private void enableChoiceTime(Boolean isEnable) {
        mImageStartTime.setClickable(isEnable);
        mImageEndTime.setClickable(isEnable);
    }
}
