package com.example.vladimir.weather.model;

import java.io.Serializable;

/**
 * Created by Vladimir on 18.02.2016.
 */
public class Coords implements Serializable {

    private double lon;
    private double lat;

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
