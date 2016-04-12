package com.example.vladimir.weather.mvp.iteractor;

public interface MainPageInteractor {

    interface OnWeatherLoadFinishedListener<T> {
        void onCityNotFound(String message);

        void onSuccess(T result);
    }

    void loadCityData(String city, OnWeatherLoadFinishedListener listener);

}