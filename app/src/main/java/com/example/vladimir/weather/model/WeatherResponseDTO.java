package com.example.vladimir.weather.model;

import java.util.List;

/**
 * Created by Vladimir on 20.01.2016.
 */
public class WeatherResponseDTO {
    private long date;
    private String cityName;
    private MainDTO main;
    private SysDTO sys;
    private WindDTO wind;
    private CloudsDTO clouds;

    private List<WeatherData> weatherData;

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<WeatherData> getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(List<WeatherData> weatherData) {
        this.weatherData = weatherData;
    }

    public void setMain(MainDTO main) {
        this.main = main;
    }

    public MainDTO getMain() {
        return main;
    }

    public void setSys(SysDTO sys) {
        this.sys = sys;
    }

    public SysDTO getSys() {
        return sys;
    }

    public void setWind(WindDTO wind) {
        this.wind = wind;
    }

    public WindDTO getWind() {
        return wind;
    }

    public void setClouds(CloudsDTO clouds) {
        this.clouds = clouds;
    }

    public CloudsDTO getClouds() {
        return clouds;
    }
}
