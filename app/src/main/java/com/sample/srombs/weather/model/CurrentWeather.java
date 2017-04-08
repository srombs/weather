package com.sample.srombs.weather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by srombs on 4/7/17.
 */

public class CurrentWeather {

    @SerializedName("coord")
    public LocationCoordinates coordinates;
    @SerializedName("main")
    public Main main;
    @SerializedName("weather")
    public List<Weather> weather;
    @SerializedName("wind")
    public Wind wind;


    @Override
    public String toString() {
        return "CurrentWeather{" +
                "coordinates=" + coordinates +
                ", main=" + main +
                ", weather=" + weather +
                ", wind=" + wind +
                '}';
    }
}
