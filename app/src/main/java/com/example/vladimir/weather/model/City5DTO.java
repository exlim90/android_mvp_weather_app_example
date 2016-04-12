package com.example.vladimir.weather.model;

import java.io.Serializable;

/**
 * Created by Vladimir on 18.02.2016.
 */
public class City5DTO  implements Serializable {

    private int id;
    private String name;
    private String country;
    private int population;
    private Coords coords;
    //private Sys sys;

    public City5DTO(){}

    public City5DTO(int id, String name, String country, int population, Coords coords) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.population = population;
        this.coords = coords;
    }

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "City5DTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", population=" + population +
                ", coords=" + coords +
                '}';
    }
}
