package com.example.framgia.t1_rss_feed.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.helper.EventListenerInterface;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 14/09/2016.
 */
public class AddMoreRssDialog extends Dialog {
    private EditText mEdtRssLink;
    private EditText mEdtRssName;
    private Button mBntSubmit;
    private Button mBtnCancel;
    private EventListenerInterface.OnSubmitAddRssListener mSubmitAddRssListener;

    public AddMoreRssDialog(Context context) {
        super(context);
        View view = getLayoutInflater().inflate(R.layout.dialog_add_rss, null);
        setContentView(view);
        getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        mSubmitAddRssListener = (EventListenerInterface.OnSubmitAddRssListener) context;
        initView(view);
        handleEvent();
    }

    private void initView(View view) {
        mEdtRssLink = (EditText) view.findViewById(R.id.edit_rss_link);
        mEdtRssName = (EditText) view.findViewById(R.id.edit_rss_name);
        mBntSubmit = (Button) view.findViewById(R.id.button_add_rss_submit);
        mBtnCancel = (Button) view.findViewById(R.id.button_add_rss_cancel);
    }

    private void handleEvent() {
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        mBntSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mEdtRssName.getText().toString();
                String link = mEdtRssLink.getText().toString();
                if (name.isEmpty()) {
                    mEdtRssName.setError(getContext().getString(R.string.msg_name_empty));
                    return;
                }
                if (link.isEmpty()) {
                    mEdtRssLink.setError(getContext().getString(R.string.msg_link_empty));
                    return;
                }
                if (!Patterns.WEB_URL.matcher(link).matches()) {
                    mEdtRssLink.setError(getContext().getString(R.string.msg_link_incorrect));
                    return;
                }
                mSubmitAddRssListener.onSubmitAddRss(name, link);
                dismiss();
            }
        });
    }
}
