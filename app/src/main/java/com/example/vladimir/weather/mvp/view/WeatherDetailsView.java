package com.example.vladimir.weather.mvp.view;

/**
 * Created by Vladimir on 4/12/2016.
 */
public interface WeatherDetailsView<T> {

    void showProgress();

    void hideProgress();

    void showError(String message);

    void navigateWeatherDetails(T result);
}
