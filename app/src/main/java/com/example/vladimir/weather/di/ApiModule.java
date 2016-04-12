package com.example.vladimir.weather.di;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.vladimir.weather.App;
import com.example.vladimir.weather.api.ApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vladimir on 4/12/2016.
 */
@Module
public class ApiModule {
    private final App application;

    public ApiModule(App application) {
        this.application = application;
    }

    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Singleton
    @Provides
    ApiService provideApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiService.class);
    }
}
