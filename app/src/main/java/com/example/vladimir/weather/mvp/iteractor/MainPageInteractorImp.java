package com.example.vladimir.weather.mvp.iteractor;

import com.example.vladimir.weather.async.GetWeather5Data;
import com.example.vladimir.weather.ui.activity.MainPage;

/**
 * Created by Vladimir on 4/11/2016.
 */
public class MainPageInteractorImp implements MainPageInteractor {
    @Override
    public void loadCityData(String city, OnWeatherLoadFinishedListener listener) {
        GetWeather5Data weather5Data = new GetWeather5Data(listener);
        weather5Data.execute(MainPage.API_URL_FORECAST.replace("[city]", city));
    }
}
