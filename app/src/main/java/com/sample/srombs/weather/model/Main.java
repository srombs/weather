package com.sample.srombs.weather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by srombs on 4/7/17.
 */

public class Main {

    @SerializedName("temp")
    public float temperature;
    @SerializedName("pressure")
    public int pressure;
    @SerializedName("humidity")
    public int humidity;


    @Override
    public String toString() {
        return "Main{" +
                "temperature=" + temperature +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                '}';
    }
}
