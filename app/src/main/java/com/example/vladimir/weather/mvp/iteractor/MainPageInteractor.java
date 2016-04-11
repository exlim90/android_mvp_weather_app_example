package com.example.vladimir.weather.mvp.iteractor;

public interface MainPageInteractor {

    interface OnWeatherLoadFinishedListener {
        void onCityNotFound();

        void onSuccess();
    }

    void loadCityData(String city, OnWeatherLoadFinishedListener listener);

}