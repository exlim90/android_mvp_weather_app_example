package com.example.vladimir.weather.model;

import java.util.List;

/**
 * Created by Vladimir on 16.02.2016.
 */
public class WeatherResponse5DTO {
    private City5DTO city;
    private String cod;
    private double message;
    private List<WeatherData5DTO> weatherData5DTOs;

    //create geteri i seteri

    public void setCity(City5DTO city) {
        this.city = city;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }


    public void setMessage(double message) {
        this.message = message;
    }

    public City5DTO getCity() {
        return city;
    }

    public String getCod() {
        return cod;
    }

    public double getMessage() {
        return message;
    }

    public void setWeatherData5DTOs(List<WeatherData5DTO> weatherCondition5DTOs) {
        this.weatherData5DTOs = weatherCondition5DTOs;
    }


    public List<WeatherData5DTO> getWeatherData5DTOs() {
        return weatherData5DTOs;
    }
}
