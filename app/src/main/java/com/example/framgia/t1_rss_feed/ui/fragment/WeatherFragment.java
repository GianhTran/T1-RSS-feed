package com.example.framgia.t1_rss_feed.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.framgia.t1_rss_feed.BaseFragment;
import com.example.framgia.t1_rss_feed.Constants;
import com.example.framgia.t1_rss_feed.R;
import com.example.framgia.t1_rss_feed.data.models.WeatherWrapper;
import com.example.framgia.t1_rss_feed.helper.EventListenerInterface;
import com.example.framgia.t1_rss_feed.network.ApiInterface;
import com.example.framgia.t1_rss_feed.network.ServiceGenerator;
import com.example.framgia.t1_rss_feed.ui.view.WeatherIcon;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 16/09/2016.
 */
public class WeatherFragment extends BaseFragment
    implements EventListenerInterface.OnUserChangeListener {
    // todo using google location to load lat long, because have no devices here so update it later
    private long mLat = 0;
    private long mLong = 0;
    private TextView mTvDescription;
    private TextView mTvMaxTemp;
    private TextView mTvMinTemp;
    private WeatherIcon mTvIcon;
    private TextView mTvUserOnline;
    private LinearLayout mLlWeather;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        initView(view);
        loadWeather();
        return view;
    }

    public static WeatherFragment newInstance() {
        WeatherFragment weatherFragment = new WeatherFragment();
        return weatherFragment;
    }

    private void initView(View view) {
        mTvDescription = (TextView) view.findViewById(R.id.text_description);
        mTvMaxTemp = (TextView) view.findViewById(R.id.text_maximum_temperature);
        mTvMinTemp = (TextView) view.findViewById(R.id.text_minimum_temperature);
        mTvIcon = (WeatherIcon) view.findViewById(R.id.text_weather_icon);
        mTvUserOnline = (TextView) view.findViewById(R.id.text_user_online);
        mLlWeather = (LinearLayout) view.findViewById(R.id.linear_weather);
    }

    // // TODO: 16/09/2016 using Observable to handle weather listener
    private void loadWeather() {
        ApiInterface apiInterface = ServiceGenerator.createGsonService(ApiInterface.class);
        Call<WeatherWrapper> call = apiInterface.loadWeather(Constants.WEATHER_API_KEY,
            mLat,
            mLong);
        call.enqueue(new Callback<WeatherWrapper>() {
            @Override
            public void onResponse(Call<WeatherWrapper> call, Response<WeatherWrapper> response) {
                if (response.body() != null)
                    injectData(response.body());
            }

            @Override
            public void onFailure(Call<WeatherWrapper> call, Throwable t) {
                Snackbar.make(mLlWeather,
                    R.string.msg_update_weather,
                    Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void injectData(WeatherWrapper weatherWrapper) {
        // TODO: 16/09/2016 inject full data
        mTvDescription.setText(weatherWrapper.getWeathers().get(0).getDescription());
        mTvMaxTemp.setText(String.format(mTvMaxTemp.getText().toString()
            , weatherWrapper.getWeatherValue().getTempMax()));
        mTvMinTemp.setText(String.format(mTvMinTemp.getText().toString(),
            weatherWrapper.getWeatherValue().getTempMin()));
        setWeatherIcon(weatherWrapper.getWeathers().get(0).getId(),
            weatherWrapper.getWeatherSys().getSunrise(),
            weatherWrapper.getWeatherSys().getSunset());
    }

    /**
     * method using set icon for weather icon
     *
     * @param actualId id of weather
     * @param sunrise  time sun rise
     * @param sunset   time sun set
     */
    private void setWeatherIcon(int actualId, long sunrise, long sunset) {
        int id = actualId / 100;
        String icon;
        if (id == Constants.WEATHER_SUNNY) {
            long currentTime = new Date().getTime();
            if (currentTime >= sunrise && currentTime < sunset) {
                icon = getActivity().getString(R.string.weather_sunny);
            } else {
                icon = getActivity().getString(R.string.weather_clear_night);
            }
        } else {
            switch (id) {
                case Constants.WEATHER_THUNDER:
                    icon = getActivity().getString(R.string.weather_thunder);
                    break;
                case Constants.WEATHER_DRIZZLE:
                    icon = getActivity().getString(R.string.weather_drizzle);
                    break;
                case Constants.WEATHER_FOGGY:
                    icon = getActivity().getString(R.string.weather_foggy);
                    break;
                case Constants.WEATHER_CLOUDY:
                    icon = getActivity().getString(R.string.weather_cloudy);
                    break;
                case Constants.WEATHER_SNOWY:
                    icon = getActivity().getString(R.string.weather_snowy);
                    break;
                case Constants.WEATHER_RAINY:
                    icon = getActivity().getString(R.string.weather_rainy);
                    break;
                default:
                    icon = Constants.EMPTY_STRING;
            }
        }
        mTvIcon.setText(icon);
    }

    @Override
    public void onUserChange(long userNumber) {
        mTvUserOnline.setText(String.valueOf(userNumber));
    }
}
