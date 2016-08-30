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
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * string to date format
     *
     * @param date: string input
     * @return
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
     * @return
     * @throws ParseException
     */
    public static Long compareDate(String date1, String date2) throws ParseException {
        final SimpleDateFormat format = new SimpleDateFormat(DEFAULT_FORMAT, Locale.getDefault());
        return format.parse(date1).getTime() - format.parse(date2).getTime();
    }
}
