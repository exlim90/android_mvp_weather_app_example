package com.example.vladimir.weather;

import android.app.Application;
import android.content.Context;

import com.example.vladimir.weather.di.ApiModule;
import com.example.vladimir.weather.di.AppComponent;
import com.example.vladimir.weather.di.DaggerAppComponent;

/**
 * Created by Vladimir on 4/12/2016.
 */
public class App extends Application {
    private static AppComponent component;
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder().apiModule(new ApiModule(this)).build();
        mContext=getApplicationContext();
    }

    public static AppComponent getComponent() {
        return component;
    }
}
