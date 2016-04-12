package com.example.vladimir.weather.model;

import java.io.Serializable;

/**
 * Created by Vladimir on 18.02.2016.
 */
public class Sys5DTO implements Serializable {
    private String pod;

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

    @Override
    public String toString() {
        return "Sys5DTO{" +
                "pod='" + pod + '\'' +
                '}';
    }
}
