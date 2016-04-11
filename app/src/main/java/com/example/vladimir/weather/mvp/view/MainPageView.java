package com.example.vladimir.weather.mvp.view;

public interface MainPageView {
    void showProgress();

    void hideProgress();

    void setUsernameError();

    void navigateToHome();
}