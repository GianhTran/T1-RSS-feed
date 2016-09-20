package com.example.framgia.t1_rss_feed.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.framgia.t1_rss_feed.Constants;
import com.example.framgia.t1_rss_feed.Preferences;
import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.helper.EventListenerInterface;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 09/09/2016.
 */
public class SettingsDialog extends Dialog {
    private Button mButtonSubmit;
    private Button mButtonCancel;
    private AppCompatActivity mAppCompatActivity;
    private RadioButton mRadioButtonDarkStyle;
    private RadioGroup mRadioGroupTextSize;
    private CheckBox mCheckBoxSetting;
    private CheckBox mCheckBoxSubTopic;
    private Preferences mPreferences;
    private EventListenerInterface.OnSubmitSettingsListener mSubmitSettingsListener;

    public SettingsDialog(Context context) {
        super(context);
        this.mAppCompatActivity = (AppCompatActivity) context;
        this.mSubmitSettingsListener = (EventListenerInterface.OnSubmitSettingsListener) context;
        setContentView(R.layout.dialog_settings);
        getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        initView();
        handleEvent();
    }

    private void initView() {
        mButtonCancel = (Button) findViewById(R.id.button_settings_cancel);
        mButtonSubmit = (Button) findViewById(R.id.button_settings_submit);
        RadioButton radioButtonLightStyle =
            (RadioButton) findViewById(R.id.radio_button_light_style);
        mRadioButtonDarkStyle = (RadioButton) findViewById(R.id.radio_button_dark_style);
        RadioButton radioButtonTextSizeSmall = (RadioButton) findViewById(R.id.radio_button_small);
        RadioButton radioButtonTextSizeMedium =
            (RadioButton) findViewById(R.id.radio_button_medium);
        RadioButton radioButtonTextSizeLarge = (RadioButton) findViewById(R.id.radio_button_large);
        mCheckBoxSetting = (CheckBox) findViewById(R.id.check_box_setting);
        mCheckBoxSubTopic = (CheckBox) findViewById(R.id.check_box_sub_topic);
        mRadioGroupTextSize = (RadioGroup) findViewById(R.id.radio_group_setting_size);
        // set state
        radioButtonLightStyle.setChecked(true);
        mPreferences = Preferences.with(mAppCompatActivity);
        if (mPreferences.getStyle() == Constants.DARK_STYLE) mRadioButtonDarkStyle.setChecked(true);
        mCheckBoxSetting.setChecked(mPreferences.getAllowImage());
        mCheckBoxSubTopic.setChecked(mPreferences.getSubTopic());
        switch (mPreferences.getTextSize()) {
            case Constants.TEXT_SIZE_SMALL:
                radioButtonTextSizeSmall.setChecked(true);
                break;
            case Constants.TEXT_SIZE_LARGE:
                radioButtonTextSizeLarge.setChecked(true);
                break;
            default:
                radioButtonTextSizeMedium.setChecked(true);
                break;
        }
    }

    private void handleEvent() {
        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPreferences.setAllowImage(mCheckBoxSetting.isChecked());
                mPreferences.setSubTopic(mCheckBoxSubTopic.isChecked());
                mPreferences.setStyle(
                    (mRadioButtonDarkStyle.isChecked()) ? Constants.DARK_STYLE :
                        Constants.LIGHT_STYLE
                );
                switch (mRadioGroupTextSize.getCheckedRadioButtonId()) {
                    case R.id.radio_button_small:
                        mPreferences.setTextSize(Constants.TEXT_SIZE_SMALL);
                        break;
                    case R.id.radio_button_large:
                        mPreferences.setTextSize(Constants.TEXT_SIZE_LARGE);
                        break;
                    default:
                        mPreferences.setTextSize(Constants.TEXT_SIZE_MEDIUM);
                        break;
                }
                dismiss();
                mSubmitSettingsListener.onSubmitSettings();
            }
        });
        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
