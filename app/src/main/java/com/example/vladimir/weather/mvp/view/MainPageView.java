package com.example.vladimir.weather.mvp.view;

public interface MainPageView<T> {
    void showProgress();

    void hideProgress();

    void setCityError();

    void showError(String message);

    void navigateWeatherDetails(T result);
}