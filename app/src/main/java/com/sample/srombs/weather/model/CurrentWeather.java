package com.sample.srombs.weather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by srombs on 4/7/17.
 */

public class CurrentWeather {

    @SerializedName("coord")
    public LocationCoordinates coordinates;
    @SerializedName("main")
    public Main main;
    @SerializedName("weather")
    public Weather weather;
    @SerializedName("wind")
    public Wind wind;


}
