package com.example.vladimir.weather.model;

import java.io.Serializable;

/**
 * Created by Vladimir on 18.02.2016.
 */
public class Wind5DTO implements Serializable {
    private double speed;
    private double deg;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDeg() {
        return deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }

    @Override
    public String toString() {
        return "Wind5DTO{" +
                "speed=" + speed +
                ", deg=" + deg +
                '}';
    }
}
