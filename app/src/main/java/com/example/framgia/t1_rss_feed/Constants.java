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
    public static final int DEFAULT_RSS_ID = -1;
    // channel voa ID
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
    // channel vn express ID
    public static final int HOME = 0;
    public static final int NEWS = 1;
    public static final int WORLD = 2;
    public static final int BUSINESS = 3;
    public static final int ENTERTAINMENT = 4;
    public static final int SPORT = 5;
    public static final int LAW = 6;
    public static final int EDUCATION = 7;
    public static final int HEALTH = 8;
    public static final int FAMILY = 9;
    public static final int TRAVEL = 10;
    public static final int SCIENCE = 11;
    public static final int IT = 12;
    public static final int OTO = 13;
    public static final int COMMUNITY = 14;
    public static final int SHARE = 15;
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
    // base url rss
    public static final String VOA_BASE_URL = "http://www.voanews.com/api/";
    public static final String VN_EXPRESS_BASE_URL = "http://vnexpress.net/rss/";
    public static final String TINHTE_BASE_URL = "https://tinhte.vn/";
    public static final int TINHTE_RSS_ID = 0;
    public static final int VN_EXPRESS_RSS_ID = 1;
    public static final int VOA_RSS_ID = 2;
}
