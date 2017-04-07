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
}
