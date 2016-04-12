package com.example.vladimir.weather.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Vladimir on 03.03.2016.
 */
public class AppUtils {

    public static final String DATE_FORMAT_FROM_SERVER_YYY_MM_DD_HH_MM_SS = "yyy-MM-dd hh:MM:ss"; //2016-04-12 15:00:00
    public static final String DATE_FORMAT_FROM_SERVER_YYY_MM_DD = "yyy-MM-dd"; //2016-04-12
    public static final String DATE_FORMAT_FOR_UI_PRESENTER = "dd/MM/yyyy HH:mm:ss";
    public static final String DATE_FORMAT_JUST_DAY = "E";

    public static boolean checkIfCityExist(String rawJson) {
        boolean exist = false;
        try {
            JSONObject errorObjectJson = new JSONObject(rawJson);
            int code = errorObjectJson.getInt("cod");
            if (code == 200) {
                exist = true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return exist;
    }

    public static String parseDateToDDmmYYYYhhMMss(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_FROM_SERVER_YYY_MM_DD_HH_MM_SS, Locale.getDefault());
        Date d = null;
        try {
            d = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d != null ? new SimpleDateFormat(DATE_FORMAT_FOR_UI_PRESENTER, Locale.getDefault()).format(d) : "";
    }


    public static String parseDateToDay(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_FROM_SERVER_YYY_MM_DD_HH_MM_SS, Locale.getDefault());
        Date d = null;
        try {
            d = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d != null ? new SimpleDateFormat(DATE_FORMAT_JUST_DAY, Locale.getDefault()).format(d) : "";
    }

    public static String parseDateDDmmYYYY(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_FROM_SERVER_YYY_MM_DD_HH_MM_SS, Locale.getDefault());
        Date d = null;
        try {
            d = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d != null ? new SimpleDateFormat(DATE_FORMAT_FROM_SERVER_YYY_MM_DD, Locale.getDefault()).format(d) : "";
    }
}
