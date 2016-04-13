package com.example.vladimir.weather.ui.activity;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vladimir.weather.R;
import com.example.vladimir.weather.adapter.WeatherDataAdapter;
import com.example.vladimir.weather.model.Main5DTO;
import com.example.vladimir.weather.model.WeatherCondition5DTO;
import com.example.vladimir.weather.model.WeatherData5DTO;
import com.example.vladimir.weather.model.WeatherResponse5DTO;
import com.example.vladimir.weather.util.LogWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Vladimir on 21.01.2016.
 */
public class WeatherDetailsActivity extends Activity {
    public final static String TAG = "WeatherDetailsActivity";

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

    private WeatherResponse5DTO weatherResponse5DTO;

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
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void showResults(WeatherResponse5DTO weatherResponse5DTO) {
        LogWrapper.d(TAG, weatherResponse5DTO + "");
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
            List<WeatherData5DTO> data = new ArrayList<>(5);
            for (Map.Entry<String, List<WeatherData5DTO>> entry : weatherResponse5DTO.getGroupedWeathers().entrySet()) {
                data.add(entry.getValue().get(0));
            }
            WeatherDataAdapter adapter = new WeatherDataAdapter(data);
            mRecyclerView.setAdapter(adapter);
        }
    }
}
