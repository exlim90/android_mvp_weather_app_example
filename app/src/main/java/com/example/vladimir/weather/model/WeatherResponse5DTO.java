package com.example.vladimir.weather.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Vladimir on 16.02.2016.
 */
public class WeatherResponse5DTO implements Serializable {
    private City5DTO city;
    private String cod;
    private double message;

    @SerializedName("list")
    private List<WeatherData5DTO> weatherData5DTOs;

    private Map<String, List<WeatherData5DTO>> groupedWeathers;

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

    public Map<String, List<WeatherData5DTO>> getGroupedWeathers() {
        return groupedWeathers;
    }

    public void setGroupedWeathers(Map<String, List<WeatherData5DTO>> groupedWeathers) {
        this.groupedWeathers = groupedWeathers;
    }

    @Override
    public String toString() {
        return "WeatherResponse5DTO{" +
                "city=" + city +
                ", cod='" + cod + '\'' +
                ", message=" + message +
                ", weatherData5DTOs=" + weatherData5DTOs +
                ", groupedWeathers=" + groupedWeathers +
                '}';
    }
}
