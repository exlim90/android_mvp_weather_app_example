package com.example.vladimir.weather.di;

import com.example.vladimir.weather.mvp.iteractor.RetrofitMainPageInteractorImp;
import com.example.vladimir.weather.ui.activity.MainPage;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

@Singleton
@Component(modules = ApiModule.class)
public interface AppComponent {
    void inject(RetrofitMainPageInteractorImp retrofitMainPageInteractorImp);

    void inject(MainPage mainPage);

    OkHttpClient client();
}