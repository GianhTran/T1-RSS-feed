package com.example.framgia.t1_rss_feed.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Copyright @ 2016 Framgia inc
 * Created by GianhTNS on 29/08/2016.
 */
public class DateTimeUtil {
    public static final String DEFAULT_FORMAT = "EEE, d MMM yyyy HH:mm:ss Z";
    public static final String SECOND_FORMAT = "EEE, d MMM yyyy";
    private static final long DEFAULT_LONG_VALUE = 0L;

    /**
     * string to date format
     *
     * @param date: string input
     * @return Date value of input : EEE, d MMM yyyy HH:mm:ss Z
     * @throws ParseException
     */
    public static Date formatStringToDate(String date) throws ParseException {
        return new SimpleDateFormat(DEFAULT_FORMAT, Locale.getDefault()).parse(date);
    }

    /**
     * compare two string by date time format
     *
     * @param date1
     * @param date2
     * @return if (date1 > date2) return > 0 else return < 0
     * @throws ParseException
     */
    public static Long compareDate(String date1, String date2, String dateFormat) {
        final SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.getDefault());
        try {
            return format.parse(date1).getTime() - format.parse(date2).getTime();
        } catch (ParseException e) {
            return DEFAULT_LONG_VALUE;
        }
    }

    /**
     * date to string format
     *
     * @param date : Date value input
     * @return string value of input :  "EEE, d MMM yyyy HH:mm:ss Z"
     */
    public static String formatDateToString(Date date) {
        return new SimpleDateFormat(DEFAULT_FORMAT, Locale.getDefault()).format(date);
    }
}
