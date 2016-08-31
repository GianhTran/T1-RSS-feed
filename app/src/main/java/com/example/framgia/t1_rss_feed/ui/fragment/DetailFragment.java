package com.example.framgia.t1_rss_feed.ui.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.framgia.t1_rss_feed.BaseFragment;
import com.example.framgia.t1_rss_feed.Constants;
import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.data.models.NewsItem;
import com.example.framgia.t1_rss_feed.data.models.TempNews;
import com.example.framgia.t1_rss_feed.ui.view.FontIcon;
import com.example.framgia.t1_rss_feed.util.CommonUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 24/08/2016.
 */
public class DetailFragment extends BaseFragment {
    private Toolbar mToolbarHome;
    private ImageView mImgDetail;
    private TextView mTvTitleDetail;
    private TextView mTvTimeDetail;
    private TextView mTvContentDetail;
    private TextView mTvLink;
    private TextView mTvAuthor;
    private FontIcon mFontIconPrint;
    private FontIcon mFontIconShare;
    private String mShareLink = null;
    private DetailFragment mDetailFragment;
    private ProgressBar mProgressBarDetail;

    public static DetailFragment newInstance(long itemId) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putLong(Constants.INTENT_KEY_NEWS_ITEM_ID, itemId);
        detailFragment.setArguments(args);
        return detailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        mDetailFragment = this;
        initToolbar();
        initView(view);
        handleEvent();
        new LoadNewsDetailAsyncTask(getArguments().getLong(Constants.INTENT_KEY_NEWS_ITEM_ID))
            .execute();
        return view;
    }

    private void initView(View view) {
        mImgDetail = (ImageView) view.findViewById(R.id.image_detail);
        mTvTitleDetail = (TextView) view.findViewById(R.id.text_title_detail);
        mTvTimeDetail = (TextView) view.findViewById(R.id.text_time_detail);
        mTvContentDetail = (TextView) view.findViewById(R.id.text_content_detail);
        mTvLink = (TextView) view.findViewById(R.id.text_link);
        mTvAuthor = (TextView) view.findViewById(R.id.text_author);
        mFontIconPrint = (FontIcon) view.findViewById(R.id.font_print);
        mFontIconShare = (FontIcon) view.findViewById(R.id.font_share);
        mProgressBarDetail = (ProgressBar) view.findViewById(R.id.progress_bar_detail);
    }

    private void handleEvent() {
        mFontIconShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mShareLink != null)
                    startActivity(getIntentShare(getActivity(), mShareLink));
            }
        });
        mFontIconPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CreatePdfAsyncTask(mTvTitleDetail.getText().toString(),
                    mTvContentDetail.getText().toString(),
                    mTvAuthor.getText().toString()).execute();
            }
        });
    }

    private void initToolbar() {
        mToolbarHome = (Toolbar) getActivity().findViewById(R.id.toolbar_home);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mToolbarHome);
        CommonUtil.setToolbarStyle(activity, true, false);
        mToolbarHome.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(R.id.frame_container, HomeFragment.newInstance());
            }
        });
    }

    /**
     * async task using to create pdf file
     */
    private class CreatePdfAsyncTask extends AsyncTask<Void, Void, String> {
        private String mTitle;
        private String mContent;
        private String mAuthor;

        public CreatePdfAsyncTask(String author, String content, String title) {
            super();
            mTitle = title;
            mContent = content;
            mAuthor = author;
        }

        @Override
        protected String doInBackground(Void... voids) {
            //Using time to make file name
            Date date = new Date();
            String timeStamp = new SimpleDateFormat(Constants.DATE_TIME_FORMAT).format(date);
            File myFile = new File(getActivity().getExternalFilesDir(Constants.FILE_PATH),
                Constants.PDF_TITLE + timeStamp + Constants.PDF_TYPE);
            OutputStream output;
            try {
                output = new FileOutputStream(myFile);
                Document document = new Document(PageSize.LETTER);
                PdfWriter.getInstance(document, output);
                document.open();
                //Add content
                document.add(new Paragraph(mTitle));
                document.add(new Paragraph(mContent));
                document.add(new Paragraph(mAuthor));
                //Close the document
                document.close();
                output.close();
                return getResources().getText(R.string.msg_export_pdf_success).toString();
            } catch (DocumentException | IOException error) {
                return getResources().getText(R.string.msg_export_pdf_error).toString();
            }
        }

        @Override
        protected void onPostExecute(String msg) {
            super.onPostExecute(msg);
            Toast.makeText(getActivity(),
                msg,
                Toast.LENGTH_SHORT).show();
        }
    }

    private void showLoading(Boolean isLoading) {
        mProgressBarDetail.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    /**
     * AsyncTask using to load news by id and update ui
     */
    private class LoadNewsDetailAsyncTask extends AsyncTask<Void, Void, TempNews> {
        private long mId;
        private Realm mRealm;

        public LoadNewsDetailAsyncTask(long id) {
            super();
            this.mId = id;
            showLoading(true);
        }

        @Override
        protected TempNews doInBackground(Void... voids) {
            try {
                mRealm = Realm.getInstance(new RealmConfiguration.Builder(getActivity()).build());
                NewsItem newsItem = mRealm.where(NewsItem.class).equalTo(Constants.KEY_ID, mId)
                    .findFirst();
                TempNews tempNews = new TempNews();
                tempNews.setTitle(newsItem.getTitle());
                tempNews.setAuthor(newsItem.getAuthor());
                tempNews.setDescription(newsItem.getDescription());
                tempNews.setImage(newsItem.getEnclosure().getLink());
                tempNews.setPubDate(newsItem.getPubDate());
                tempNews.setLinkItem(newsItem.getLink());
                return tempNews;
            } finally {
                mRealm.close();
            }
        }

        @Override
        protected void onPostExecute(TempNews newsItem) {
            super.onPostExecute(newsItem);
            mTvTitleDetail.setText(newsItem.getTitle());
            mTvContentDetail.setText(newsItem.getDescription());
            mTvAuthor.setText(newsItem.getAuthor());
            mTvTimeDetail.setText(newsItem.getPubDate());
            mTvLink.setText(newsItem.getLinkItem());
            Glide.with(mDetailFragment)
                .load(newsItem.getImage())
                .centerCrop()
                .placeholder(R.drawable.img_no_image_placeholder)
                .crossFade()
                .into(mImgDetail);
            showLoading(false);
        }
    }

    /**
     * this method support intent to share content via facebook, twitter, linkedin,google+ app
     *
     * @param context context
     * @param link    share content
     * @return
     */
    public Intent getIntentShare(Context context, String link) {
        //sharing implementation
        List<Intent> targetedShareIntents = new ArrayList<Intent>();
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType(Constants.INTENT_TYPE);
        sharingIntent.setAction(Intent.ACTION_SEND);
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> activityList = pm.queryIntentActivities(sharingIntent, 0);
        for (ResolveInfo app : activityList) {
            String packageName = app.activityInfo.packageName;
            if (packageName.contains(Constants.PACKAGE_NAME_TWITTER) ||
                packageName.contains(Constants.PACKAGE_NAME_FACEBOOK) ||
                packageName.contains(Constants.PACKAGE_NAME_LINKEDIN) ||
                packageName.contains(Constants.PACKAGE_NAME_GOOGLE_PLUS)) {
                Intent targetedShareIntent = new Intent(android.content.Intent.ACTION_SEND);
                targetedShareIntent.setComponent(new ComponentName(packageName, app.activityInfo
                    .name));
                targetedShareIntent.setType(Constants.INTENT_TYPE);
                targetedShareIntent.putExtra(Intent.EXTRA_TEXT, link);
                targetedShareIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R
                    .string.title_share));
                targetedShareIntent.setPackage(packageName);
                targetedShareIntents.add(targetedShareIntent);
            }
        }
        Intent chooserIntent =
            Intent.createChooser(targetedShareIntents.remove(0), getResources().getString(R
                .string.title_share));
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
            targetedShareIntents.toArray(new Parcelable[]{}));
        return chooserIntent;
    }
}
