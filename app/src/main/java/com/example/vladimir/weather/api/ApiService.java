package com.example.vladimir.weather.api;

import com.example.vladimir.weather.model.WeatherResponse5DTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    public static final String BASE_URL = "http://api.openweathermap.org";
    public static final String API_URL_FORECAST = "http://api.openweathermap.org/data/2.5/forecast?q=[city]&mode=json&appid=5ae54136c3bce516d6b06adf29a91489&units=metric";

    @GET("/data/2.5/forecast?mode=json&appid=5ae54136c3bce516d6b06adf29a91489&units=metric")
    Call<WeatherResponse5DTO> getWeather(@Query("q") String city);
}