package com.example.vladimir.weather.model;

/**
 * Created by Vladimir on 20.01.2016.
 */
public class WindDTO {

    public WindDTO() {
    }

    private double speed;
    private int deg;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }



    public WindDTO(double speed, int deg) {
        this.speed = speed;
        this.deg = deg;
    }


}
