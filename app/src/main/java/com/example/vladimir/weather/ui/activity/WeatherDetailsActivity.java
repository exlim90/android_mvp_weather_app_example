package com.example.vladimir.weather.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vladimir.weather.R;
import com.example.vladimir.weather.async.GetWeather5Data;
import com.example.vladimir.weather.async.GetWeatherData;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Vladimir on 21.01.2016.
 */
public class WeatherDetailsActivity extends Activity {

    @Bind(R.id.cityName)
    TextView cityName;

    @Bind(R.id.icon)
    ImageView icon;

    @Bind(R.id.temp)
    TextView temp;

    @Bind(R.id.condition)
    TextView condition;

    private String cityNameParam;

    @Bind(R.id.weatherRecyclerView)
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);
        /*mRecyclerView = (RecyclerView) findViewById(R.id.weatherRecyclerView);
        cityName = (TextView) findViewById(R.id.cityName);
        icon = (ImageView) findViewById(R.id.icon);
        temp = (TextView) findViewById(R.id.temp);
        condition = (TextView) findViewById(R.id.condition);*/
        ButterKnife.bind(this);

        if (getIntent().getExtras() != null && getIntent().hasExtra("cityNameExtraParam")) {
            cityNameParam = getIntent().getExtras().getString("cityNameExtraParam");
        }

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        GetWeatherData request = new GetWeatherData(this, cityName, icon, temp, condition);
        request.execute(MainPage.API_URL_MAIN.replace("[city]", cityNameParam));

        GetWeather5Data request5 = new GetWeather5Data(this, mRecyclerView);
        request5.execute(MainPage.API_URL_FORECASt.replace("[city]", cityNameParam));

    }
}
