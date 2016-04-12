package com.example.vladimir.weather.model;

import java.io.Serializable;

/**
 * Created by Vladimir on 18.02.2016.
 */
public class Main5DTO implements Serializable {
    private double temp;
    private double pressure;
    private int humidity;
    private double temp_min;
    private double temp_max;
    private int temp_kf;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public int getTemp_kf() {
        return temp_kf;
    }

    public void setTemp_kf(int temp_kf) {
        this.temp_kf = temp_kf;
    }

    @Override
    public String toString() {
        return "Main5DTO{" +
                "temp=" + temp +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", temp_min=" + temp_min +
                ", temp_max=" + temp_max +
                ", temp_kf=" + temp_kf +
                '}';
    }
}
