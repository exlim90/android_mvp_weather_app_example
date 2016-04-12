package com.example.vladimir.weather.util;

import com.example.vladimir.weather.model.City5DTO;
import com.example.vladimir.weather.model.Clouds5DTO;
import com.example.vladimir.weather.model.CloudsDTO;
import com.example.vladimir.weather.model.Coords;
import com.example.vladimir.weather.model.Main5DTO;
import com.example.vladimir.weather.model.MainDTO;
import com.example.vladimir.weather.model.Sys5DTO;
import com.example.vladimir.weather.model.SysDTO;
import com.example.vladimir.weather.model.WeatherCondition5DTO;
import com.example.vladimir.weather.model.WeatherData;
import com.example.vladimir.weather.model.WeatherData5DTO;
import com.example.vladimir.weather.model.WeatherResponse5DTO;
import com.example.vladimir.weather.model.WeatherResponseDTO;
import com.example.vladimir.weather.model.Wind5DTO;
import com.example.vladimir.weather.model.WindDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

/**
 * Created by Vladimir on 03.03.2016.
 */
public class AppUtils {
    //2016-04-12 15:00:00
    public static final String DATE_FORMAT_FROM_SERVER = "dd/MM/yyyy hh:MM:ss";
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

    public static String parseDateToDDmmYYYY(long date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_FOR_UI_PRESENTER);
        return simpleDateFormat.format(new Date(date));
    }


    public static String parseDateToDay(long date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_JUST_DAY);
        return simpleDateFormat.format(new Date(date));
    }
}
