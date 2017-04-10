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


    public int getTemperature() {
        return Math.round(temperature);
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "Main{" +
                "temperature=" + temperature +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                '}';
    }
}
