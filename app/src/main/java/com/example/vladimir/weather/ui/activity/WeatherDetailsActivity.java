package com.example.vladimir.weather.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vladimir.weather.R;
import com.example.vladimir.weather.adapter.MyAdapter;
import com.example.vladimir.weather.model.Main5DTO;
import com.example.vladimir.weather.model.WeatherCondition5DTO;
import com.example.vladimir.weather.model.WeatherData;
import com.example.vladimir.weather.model.WeatherResponse5DTO;

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
    RecyclerView mRecyclerView;

    WeatherResponse5DTO weatherResponse5DTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);
        ButterKnife.bind(this);

        if (getIntent().getExtras() != null && getIntent().hasExtra("cityNameExtraParam")) {
            cityNameParam = getIntent().getExtras().getString("cityNameExtraParam");
        }

        if (getIntent().getExtras() != null && getIntent().hasExtra("result")) {
            weatherResponse5DTO = (WeatherResponse5DTO) getIntent().getExtras().getSerializable("result");
        }


        initView();
        showResults(weatherResponse5DTO);
    }

    private void initView() {
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void showResults(WeatherResponse5DTO weatherResponse5DTO) {
        cityName.setText(weatherResponse5DTO.getCity().getName());
        if (weatherResponse5DTO != null) {
            if (weatherResponse5DTO.getWeatherData5DTOs().get(0).getWeather().get(0) != null) {
                WeatherCondition5DTO weather = weatherResponse5DTO.getWeatherData5DTOs().get(0).getWeather().get(0);
                condition.setText(weather.getDescription());
                icon.setImageResource(getResources().getIdentifier(
                        "a_" + weather.getIcon(), "drawable", getPackageName()));
            }

            if (weatherResponse5DTO.getWeatherData5DTOs().get(0).getMain() != null) {
                Main5DTO main = weatherResponse5DTO.getWeatherData5DTOs().get(0).getMain();
                temp.setText(main.getTemp() + " \u2103");
            }
        }


        if (weatherResponse5DTO.getWeatherData5DTOs() != null) {
            MyAdapter adapter = new MyAdapter(weatherResponse5DTO.getWeatherData5DTOs(), this);
            mRecyclerView.setAdapter(adapter);
        }
    }
}
