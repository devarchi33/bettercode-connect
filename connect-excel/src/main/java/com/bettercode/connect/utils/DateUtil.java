package com.bettercode.connect.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    public static Date getDateFromCalendar(int year, int month, int date, int hour, int minute, int second) {

        Calendar cal = Calendar.getInstance();

        cal.set(year, month-1, date, hour, minute, second);

        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();

    }

    public static Date getDateFromString(String date) {
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(date + " parsing exception");
        }
    }

    private static final SimpleDateFormat sdfYYYYMMDD = new SimpleDateFormat("yyyy.MM.dd");

    public static Date getDateFromStringYYYYMMDD(String date) {
        try {
            return sdfYYYYMMDD.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(date + " parsing exception");
        }
    }

}
