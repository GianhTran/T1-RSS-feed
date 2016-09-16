package com.example.framgia.t1_rss_feed;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 23/08/2016.
 */
public class Constants {
    public static final String TAG = "GianhTran";
    public static final int DEFAULT_DELAY = 2000;
    public static final int REALM_SCHEMA_VERSION = 0;
    public static final int REALM_SCHEMA_VERSION_1 = 1;
    public static final Long CONNECT_TIME_OUT = 20000L;
    public static final Long READ_TIME_OUT = 20000L;
    public static final String INTENT_KEY_NEWS_ITEM = "intent_key_news_item";
    public static final String DATE_TIME_FORMAT = "yyyyMMdd_HHmmss";
    public static final String INTENT_KEY_NEWS_ITEM_ID = "intent_key_news_item_id";
    public static final String INTENT_KEY_LINK = "intent_key_link";
    public static final int SERVICE_REQUEST_CODE = 0;
    public static final int SERVICE_FLAG = 0;
    public static final int TOP_POSITION = 0;
    public static final int VALUE_ONE = 1;
    public static final int NUMBER_OF_KEEP_DATE = -10;
    public static final String FRAGMENT_TAG = "fragment_tag";
    public static final String INTENT_KEY_CALL_FROM_HOME = "intent_key_call_from_home";
    public static final int FIRST_PAGE = 1;
    public static final int ITEMS_PER_PAGE = 20;
    public static final Long LONG_ZERO_VALUE = 0L;
    public static final Long DEF_HISTOTY_INDEX_VALUE = -1L;
    public static final String INTENT_OPEN_PDF_TYPE = "application/pdf";
    public static final int SAME_DAY = 0;
    public static final Long VALUE_OF_NEW_NEWS = -1L;
    public static final String HTTP = "http://";
    public static final String HTTPS = "https://";
    public static final String EMPTY_STRING = "";
    public static final int DEFAULT_RSS_ID = -1;
    // channel ID
    public static final int ASIAN_CHANNEL = 0;
    public static final int USA_CHANNEL = 1;
    public static final int AFRICA_CHANNEL = 2;
    public static final int MIDDLE_EAST_CHANNEL = 3;
    public static final int EUROPE_CHANNEL = 4;
    public static final int HEATH_CHANNEL = 5;
    public static final int ART_CHANNEL = 6;
    public static final int USA_VOTE_CHANNEL = 7;
    public static final int ONE_MINUTE_FEATURE_CHANNEL = 8;
    public static final int EXTREME_CHANNEL = 9;
    public static final int PHOTO_CHANNEL = 10;
    // tinh te
    public static final String TINHTE_CHANNEL_NAME = "Tinhte.vn";
    public static final String TINHTE_CHANNEL_LINK = "https://tinhte.vn/rss";
    // pdf settings
    public static final String PDF_TITLE = "news_";
    public static final String PDF_TYPE = ".pdf";
    public static final String FILE_PATH = "MyFileStorage";
    // intent type
    public static final String INTENT_TYPE = "text/plain";
    // package name
    public static final String PACKAGE_NAME_FACEBOOK = "com.facebook.katana";
    public static final String PACKAGE_NAME_LINKEDIN = "com.linkedin.android";
    public static final String PACKAGE_NAME_GOOGLE_PLUS = "com.google.android.apps.plus";
    public static final String PACKAGE_NAME_TWITTER = "com.twitter.android";
    // query key
    public static final String KEY_ID = "mId";
    public static final String KEY_LINK_ITEM = "mLinkItem";
    public static final String KEY_CHANNEL = "mChannel";
    public static final String KEY_VIEWED = "mViewed";
    public static final String KEY_INDEX = "mIndex";
    public static final String KEY_READ_TIME = "mReadTime";
    public static final String KEY_HISTORY_INDEX = "mHistoryIndex";
    public static final String KEY_CHECKED = "mIsChecked";
    public static final String RSS_ACTIVE = "mIsActive";
    public static final String KEY_RSS_ID = "key_rss_id";
    // realm config
    public static final String MY_REALM_NAME = "my_realm.realm";
    // settings value
    public static final int DARK_STYLE = 0;
    public static final int LIGHT_STYLE = 1;
    public static final int TEXT_SIZE_SMALL = 2;
    public static final int TEXT_SIZE_MEDIUM = 3;
    public static final int TEXT_SIZE_LARGE = 4;
    // fire base url
    public static final String FIRE_BASE_URL = "https://rss-feed-fef98.firebaseio.com";
    public static final String FIRE_BASE_PRESENCE = "/presence/";
    public static final String FIRE_BASE_INFOR = "/.info/connected";
    // api KEY of open weather map
    public static final String WEATHER_API_KEY = "1472edc8ae6d96af07549c89efe13930";
    // base url or weather api
    public static final String WEATHER_BASE_LINK = "http://api.openweathermap.org/data/2.5/";
    //weather icon
    public static final int WEATHER_THUNDER = 2;
    public static final int WEATHER_DRIZZLE = 3;
    public static final int WEATHER_FOGGY = 7;
    public static final int WEATHER_CLOUDY = 4;
    public static final int WEATHER_SNOWY = 6;
    public static final int WEATHER_RAINY = 5;
    public static final int WEATHER_SUNNY = 8;
    // google location
    public static final Byte REQUEST_CHECK_SETTINGS = 0x1;
    public static final Long UPDATE_INTERVAL_IN_MILLISECONDS = 10000L;
    public static final Long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
        UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    public static final int REQUEST_CODE_GRANT_PERMISSION = 113;
}
