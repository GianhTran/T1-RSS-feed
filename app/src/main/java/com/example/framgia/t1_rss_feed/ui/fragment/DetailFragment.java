package com.example.framgia.t1_rss_feed.ui.fragment;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.framgia.t1_rss_feed.BaseFragment;
import com.example.framgia.t1_rss_feed.Constants;
import com.example.framgia.t1_rss_feed.Preferences;
import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.data.models.NewsItem;
import com.example.framgia.t1_rss_feed.data.models.TempNews;
import com.example.framgia.t1_rss_feed.ui.view.FontIcon;
import com.example.framgia.t1_rss_feed.util.CommonUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;

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
    private Boolean mIsHomeDetail;
    private String mImageLink;
    private LinearLayout mLinearLayout;
    private ImageView mImageViewBack;

    public static DetailFragment newInstance(long itemId, boolean isCallFromHome) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putLong(Constants.INTENT_KEY_NEWS_ITEM_ID, itemId);
        args.putBoolean(Constants.INTENT_KEY_CALL_FROM_HOME, isCallFromHome);
        detailFragment.setArguments(args);
        return detailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        mDetailFragment = this;
        mIsHomeDetail = getArguments().getBoolean(Constants.INTENT_KEY_CALL_FROM_HOME);
        initView(view);
        handleEvent();
        new LoadNewsDetailAsyncTask(getArguments().getLong(Constants.INTENT_KEY_NEWS_ITEM_ID))
            .execute();
        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        if (mIsHomeDetail) return;
        menu.findItem(R.id.action_clear_all_history).setVisible(false);
        menu.findItem(R.id.action_select).setVisible(false);
        super.onPrepareOptionsMenu(menu);
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
        mLinearLayout = (LinearLayout) view.findViewById(R.id.linear_detail);
        mImageViewBack = (ImageView) view.findViewById(R.id.image_back);
        // just using this view in home detail
        mImageViewBack.setVisibility(mIsHomeDetail ? View.VISIBLE : View.GONE);
    }

    private void handleEvent() {
        mFontIconShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mShareLink == null) return;
                Intent intent = getIntentShare(getActivity(), mShareLink);
                if (intent == null) {
                    Snackbar.make(mLinearLayout,
                        R.string.msg_install_app,
                        Snackbar.LENGTH_SHORT).show();
                    return;
                }
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
        mImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFragment(mDetailFragment);
            }
        });
    }

    private void initToolbar() {
        mToolbarHome = (Toolbar) getActivity().findViewById(R.id.toolbar_home);
        final AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mToolbarHome);
        CommonUtil.setToolbarStyle(activity, true, false);
        mToolbarHome.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonUtil.setToolbarStyle(activity, false, false);
                removeFragment(mDetailFragment);
            }
        });
    }

    /**
     * async task using to create pdf file
     */
    private class CreatePdfAsyncTask extends AsyncTask<Void, Void, Boolean> {
        private String mTitle;
        private String mContent;
        private String mAuthor;
        private String mName;

        public CreatePdfAsyncTask(String author, String content, String title) {
            super();
            mTitle = title;
            mContent = content;
            mAuthor = author;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            //Using time to make file name
            Date date = new Date();
            String timeStamp = new SimpleDateFormat(Constants.DATE_TIME_FORMAT).format(date);
            mName = Constants.PDF_TITLE + timeStamp + Constants.PDF_TYPE;
            File myFile = new File(getActivity().getExternalFilesDir(Constants.FILE_PATH), mName);
            OutputStream output;
            try {
                output = new FileOutputStream(myFile);
                Document document = new Document(PageSize.LETTER);
                PdfWriter.getInstance(document, output);
                document.open();
                //Add content
                document.add(new Paragraph(mTitle));
                if (mImageLink != null && Preferences.with(getActivity()).getAllowImage()) {
                    Image image = Image.getInstance(new URL(mImageLink));
                    document.add(image);
                }
                document.add(new Paragraph(mContent));
                document.add(new Paragraph(mAuthor));
                //Close the document
                document.close();
                output.close();
                return true;
            } catch (DocumentException | IOException error) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean isSuccess) {
            super.onPostExecute(isSuccess);
            Snackbar.make(mLinearLayout,
                (isSuccess) ? R.string.msg_export_pdf_success : R.string.msg_export_pdf_error,
                Snackbar.LENGTH_SHORT).show();
            if (isSuccess)
                viewFilePdf(mName);
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
                mRealm = Realm.getDefaultInstance();
                final NewsItem newsItem = mRealm
                    .where(NewsItem.class)
                    .equalTo(Constants.KEY_ID, mId)
                    .findFirst();
                TempNews tempNews = new TempNews();
                tempNews.setTitle(newsItem.getTitle());
                tempNews.setAuthor(newsItem.getAuthor());
                tempNews.setDescription(newsItem.getDescription());
                if (newsItem.getEnclosure() != null)
                    tempNews.setImage(newsItem.getEnclosure().getLink());
                tempNews.setPubDate(newsItem.getPubDate());
                if (newsItem.getLink() != null)
                    tempNews.setLinkItem(newsItem.getLink());
                return tempNews;
            } finally {
                if (mRealm != null)
                    mRealm.close();
            }
        }

        @Override
        protected void onPostExecute(TempNews newsItem) {
            super.onPostExecute(newsItem);
            if (mIsHomeDetail) updateData(mId);
            mTvTitleDetail.setText(newsItem.getTitle());
            mTvContentDetail.setText(Html.fromHtml(newsItem.getDescription()));
            mTvAuthor.setText(newsItem.getAuthor());
            mTvTimeDetail.setText(newsItem.getPubDate());
            mTvLink.setText(newsItem.getLinkItem());
            mShareLink = newsItem.getLinkItem();
            mImageLink = newsItem.getImage();
            if (Preferences.with(getActivity()).getAllowImage())
                Glide.with(mDetailFragment)
                    .load(newsItem.getImage())
                    .fitCenter()
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
     * @return intent chooser include apps which is installed
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
                targetedShareIntent.putExtra(Intent.EXTRA_SUBJECT,
                    getResources().getString(R.string.title_share));
                targetedShareIntent.setPackage(packageName);
                targetedShareIntents.add(targetedShareIntent);
            }
        }
        if (!targetedShareIntents.isEmpty())
            return Intent.createChooser(targetedShareIntents.remove(0),
                getResources().getString(R.string.title_share))
                .putExtra(Intent.EXTRA_INITIAL_INTENTS,
                    targetedShareIntents.toArray(new Parcelable[]{}));
        return null;
    }

    /**
     * method using to update value of viewed = true by id, set read time and history index
     *
     * @param id of news
     */
    private void updateData(long id) {
        Realm realm = Realm.getDefaultInstance();
        final long index = new NewsItem().getNextHistoryIndex(realm);
        final NewsItem newsItem =
            realm.where(NewsItem.class).equalTo(Constants.KEY_ID, id)
                .findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (newsItem.getHistoryIndex() == Constants.DEF_HISTOTY_INDEX_VALUE)
                    newsItem.setHistoryIndex(index);
                newsItem.setReadTime(System.currentTimeMillis());
                newsItem.setViewed(true);
                newsItem.setChecked(false);
            }
        });
        realm.close();
    }

    /**
     * method using to open file pdf in external storage
     *
     * @param name: file name
     */
    private void viewFilePdf(String name) {
        File pdfFile =
            new File(getActivity().getExternalFilesDir(Constants.FILE_PATH), name);//File path
        if (!pdfFile.exists()) {
            Snackbar.make(mLinearLayout,
                R.string.msg_file_not_exists,
                Snackbar.LENGTH_SHORT).show();
            return;
        }
        Intent target = new Intent(Intent.ACTION_VIEW);
        target.setDataAndType(Uri.fromFile(pdfFile), Constants.INTENT_OPEN_PDF_TYPE);
        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        Intent intent = Intent.createChooser(target,
            getResources().getString(R.string.title_intent_open_file_pdf));
        try {
            startActivity(intent); // open pdf file
        } catch (ActivityNotFoundException e) {
            Snackbar.make(mLinearLayout,
                R.string.msg_install_pdf_reader_app,
                Snackbar.LENGTH_SHORT).show();
        }
    }
}
