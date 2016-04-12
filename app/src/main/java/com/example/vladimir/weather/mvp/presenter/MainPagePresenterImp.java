package com.example.vladimir.weather.mvp.presenter;

import com.example.vladimir.weather.model.WeatherData5DTO;
import com.example.vladimir.weather.model.WeatherResponse5DTO;
import com.example.vladimir.weather.mvp.iteractor.MainPageInteractor;
import com.example.vladimir.weather.mvp.iteractor.MainPageInteractorImp;
import com.example.vladimir.weather.mvp.view.MainPageView;

/**
 * Created by Vladimir on 4/11/2016.
 */
public class MainPagePresenterImp implements MainPagePresenter, MainPageInteractor.OnWeatherLoadFinishedListener<WeatherResponse5DTO> {
    private MainPageView view;
    private MainPageInteractor interactor;

    public MainPagePresenterImp(MainPageView view) {
        this.view = view;
        interactor = new MainPageInteractorImp();
    }

    @Override
    public void validateCity(String city) {
        if (city == null || city.equals("")) {
            view.setCityError();
        } else {
            view.showProgress();
            interactor.loadCityData(city, this);
        }
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onCityNotFound(String message) {
        view.hideProgress();
        view.showError(message);
    }

    @Override
    public void onSuccess(WeatherResponse5DTO result) {
        view.hideProgress();
        view.navigateWeatherDetails(result);
    }
}
