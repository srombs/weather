package com.sample.srombs.weather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by srombs on 4/7/17.
 */

public class Wind {

    @SerializedName("speed")
    public float speed;
    @SerializedName("deg")
    public int deg;

    public int getSpeed() {
        return Math.round(speed);
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }
}
