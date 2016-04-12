package com.example.vladimir.weather.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Vladimir on 18.02.2016.
 */
public class WeatherData5DTO implements Serializable {
    @SerializedName("dt")
    private long date;
    @SerializedName("dt_txt")
    private String dateText;
    private Main5DTO main;
    private List<WeatherCondition5DTO> weather;
    @SerializedName("clouds")
    private Clouds5DTO clouds5DTO;
    @SerializedName("wind")
    private Wind5DTO wind5DTO;
    @SerializedName("sys")
    private Sys5DTO sys5DTO;


    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDateText() {
        return dateText;
    }

    public void setDateText(String dateText) {
        this.dateText = dateText;
    }

    public Main5DTO getMain() {
        return main;
    }

    public void setMain(Main5DTO main) {
        this.main = main;
    }

    public List<WeatherCondition5DTO> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherCondition5DTO> weather) {
        this.weather = weather;
    }

    public Clouds5DTO getClouds5DTO() {
        return clouds5DTO;
    }

    public void setClouds5DTO(Clouds5DTO clouds5DTO) {
        this.clouds5DTO = clouds5DTO;
    }

    public Wind5DTO getWind5DTO() {
        return wind5DTO;
    }

    public void setWind5DTO(Wind5DTO wind5DTO) {
        this.wind5DTO = wind5DTO;
    }

    public Sys5DTO getSys5DTO() {
        return sys5DTO;
    }

    public void setSys5DTO(Sys5DTO sys5DTO) {
        this.sys5DTO = sys5DTO;
    }

    @Override
    public String toString() {
        return "WeatherData5DTO{" +
                "date=" + date +
                ", dateText='" + dateText + '\'' +
                ", main=" + main +
                ", weather=" + weather +
                ", clouds5DTO=" + clouds5DTO +
                ", wind5DTO=" + wind5DTO +
                ", sys5DTO=" + sys5DTO +
                '}';
    }
}
