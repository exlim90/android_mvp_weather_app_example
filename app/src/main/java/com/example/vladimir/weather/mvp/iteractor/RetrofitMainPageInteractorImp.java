package com.example.vladimir.weather.mvp.iteractor;

import com.example.vladimir.weather.App;
import com.example.vladimir.weather.api.ApiService;
import com.example.vladimir.weather.model.WeatherResponse5DTO;
import com.example.vladimir.weather.parser.WeatherDataParser;
import com.example.vladimir.weather.util.LogWrapper;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vladimir on 4/11/2016.
 */
public class RetrofitMainPageInteractorImp implements MainPageInteractor {

    public static final String TAG = "RetrofitMainPageInteractorImp";
    @Inject
    ApiService apiService;

    public RetrofitMainPageInteractorImp() {
        App.getComponent().inject(this);
    }

    @Override
    public void loadCityData(String city, final OnWeatherLoadFinishedListener listener) {
        LogWrapper.d(TAG, "loadCityData city=" + city);
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);*/
        Call<WeatherResponse5DTO> call = apiService.getWeather(city);
        call.enqueue(new Callback<WeatherResponse5DTO>() {
            @Override
            public void onResponse(Call<WeatherResponse5DTO> call, Response<WeatherResponse5DTO> response) {
                WeatherResponse5DTO weatherResponse5DTO = WeatherDataParser.groupParsedWeatherData5(response.body());
                listener.onSuccess(weatherResponse5DTO);
            }

            @Override
            public void onFailure(Call<WeatherResponse5DTO> call, Throwable t) {
                LogWrapper.d(TAG, "onFailure exception=" + t);
                listener.onCityNotFound(t.getMessage());
            }
        });

    }
}
