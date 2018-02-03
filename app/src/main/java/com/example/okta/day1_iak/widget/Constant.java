package com.example.okta.day1_iak.widget;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Okta on 20/01/2018.
 */

public class Constant {

    public static String getDate(long time) {
        Date date = new Date(time * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        return sdf.format(date);
    }
}
