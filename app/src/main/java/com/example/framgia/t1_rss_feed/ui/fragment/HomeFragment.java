package com.example.framgia.t1_rss_feed.ui.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.framgia.t1_rss_feed.BaseFragment;
import com.example.framgia.t1_rss_feed.Constants;
import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.data.models.News;
import com.example.framgia.t1_rss_feed.data.models.NewsItem;
import com.example.framgia.t1_rss_feed.helper.EndlessRecyclerViewScrollListener;
import com.example.framgia.t1_rss_feed.network.ApiInterface;
import com.example.framgia.t1_rss_feed.network.ServiceGenerator;
import com.example.framgia.t1_rss_feed.ui.adapter.HomeAdapter;
import com.example.framgia.t1_rss_feed.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 23/08/2016.
 */
public class HomeFragment extends BaseFragment implements HomeAdapter.OnItemNewsClickListener {
    private SwipeRefreshLayout mSwipeRefreshHome;
    private RecyclerView mRecyclerViewHome;
    private HomeAdapter mHomeAdapter;
    private Toolbar mToolbarHome;
    private Spinner mSpinnerChannel;
    private int mChannelId = Constants.ASIAN_CHANNEL;
    private TextView mTvDataEmpty;
    private Realm mRealm;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mRealm = Realm.getDefaultInstance();
        initToolbar();
        initView(view);
        handleEvent();
        initRecyclerView();
        initSwipeToRefresh();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData();
    }

    private void initView(View view) {
        mSwipeRefreshHome = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_home);
        mRecyclerViewHome = (RecyclerView) view.findViewById(R.id.recycler_home);
        mSpinnerChannel = (Spinner) view.findViewById(R.id.spinner_channel);
        mTvDataEmpty = (TextView) view.findViewById(R.id.text_no_data);
    }

    private void handleEvent() {
        mSpinnerChannel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int id, long l) {
                mChannelId = id;
                reloadData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void initSwipeToRefresh() {
        mSwipeRefreshHome.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reloadData();
                showLoading(false);
            }
        });
    }

    /**
     * method using to reload data
     */
    private void reloadData() {
        mHomeAdapter.clearData();
        loadData();
    }

    private void initRecyclerView() {
        mHomeAdapter = new HomeAdapter(getActivity(), new RealmList<NewsItem>(),
            this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewHome.setLayoutManager(layoutManager);
        mRecyclerViewHome.setAdapter(mHomeAdapter);
        mRecyclerViewHome.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // delay 5s before start load more
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //todo load more event
                    }
                }, Constants.DEFAULT_DELAY);
            }
        });
    }

    /**
     * method handler show progress bar of refresh layout
     */
    private void showLoading(final Boolean isShowLoading) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshHome.setRefreshing(isShowLoading);
            }
        }, Constants.DEFAULT_DELAY);
    }

    private void loadData() {
        showEmpty(false);
        showLoading(true);
        ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
        Call<News> call;
        switch (mChannelId) {
            case Constants.ASIAN_CHANNEL:
                call = apiInterface.loadNewsOfAsian();
                break;
            case Constants.AFRICA_CHANNEL:
                call = apiInterface.loadNewsOfAfrica();
                break;
            case Constants.ONE_MINUTE_FEATURE_CHANNEL:
                call = apiInterface.loadNewsOfOneMinuteFeatures();
                break;
            case Constants.ART_CHANNEL:
                call = apiInterface.loadNewsOfArt();
                break;
            case Constants.EUROPE_CHANNEL:
                call = apiInterface.loadNewsOfEurope();
                break;
            case Constants.USA_CHANNEL:
                call = apiInterface.loadNewsOfUsa();
                break;
            case Constants.MIDDLE_EAST_CHANNEL:
                call = apiInterface.loadNewsOfMiddleEast();
                break;
            case Constants.HEATH_CHANNEL:
                call = apiInterface.loadNewsOfHealth();
                break;
            case Constants.USA_VOTE_CHANNEL:
                call = apiInterface.loadNewsOf2016UsaVotes();
                break;
            case Constants.EXTREME_CHANNEL:
                call = apiInterface.loadNewsOfExtremismWatch();
                break;
            case Constants.PHOTO_CHANNEL:
                call = apiInterface.loadNewsOfDayInPhotos();
                break;
            default:
                call = apiInterface.loadNewsOfAsian();
                break;
        }
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                List<NewsItem> items = response.body().getChannel().getItems();
                checkData(items, response.body().getChannel().getTitle());
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                showEmpty(true);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
            case R.id.action_history:
                //todo update history
                break;
            case R.id.action_settings:
                //todo update settings
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemNewsClick(long itemId) {
        replaceFragment(R.id.frame_container, DetailFragment.newInstance(itemId));
    }

    private void initToolbar() {
        mToolbarHome = (Toolbar) getActivity().findViewById(R.id.toolbar_home);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mToolbarHome);
        CommonUtil.setToolbarStyle(activity, false, false);
    }

    /**
     * method using check data before save in database and push to list
     *
     * @param items: if items is able in data base, ignore it, else save it into realm
     */
    private void checkData(List<NewsItem> items, String channel) {
        new SaveDataAsyncTask(items, channel).execute();
    }

    private void showEmpty(Boolean isEmpty) {
        mTvDataEmpty.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroy();
        mRealm.close(); // Remember to close Realm when done.
    }

    /**
     * async method using to load list 20 items form data base
     */
    private void updateData(final String channel) {
        mHomeAdapter.updateData(mRealm.where(NewsItem.class).equalTo
            (Constants.KEY_CHANNEL, channel).findAllSorted(Constants.KEY_ID));
        showLoading(false);
    }

    /**
     * asyncTask using to save list News update from server, ignore news which saved
     */
    private class SaveDataAsyncTask extends AsyncTask<Void, Void, Void> {
        private List<NewsItem> mItems = new ArrayList<>();
        private String mChannel;

        public SaveDataAsyncTask(List<NewsItem> itemList, String channel) {
            super();
            this.mItems = itemList;
            this.mChannel = channel;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    for (NewsItem itemNew : mItems) {
                        if (realm.where(NewsItem.class).equalTo
                            (Constants.KEY_LINK_ITEM, itemNew.getLink()).findAll().size() == 0) {
                            itemNew.setId(itemNew.getNextPrimaryKey(realm));
                            itemNew.setChannel(mChannel);
                            itemNew.setViewed(false);
                            realm.copyToRealm(itemNew);
                        }
                    }
                }
            });
            realm.close();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            updateData(mChannel);
        }
    }
}
