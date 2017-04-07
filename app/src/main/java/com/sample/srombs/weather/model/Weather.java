package com.sample.srombs.weather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by srombs on 4/7/17.
 */

public class Weather {

    @SerializedName("id")
    public int id;
    @SerializedName("main")
    public String mainConditions;
    @SerializedName("description")
    public String description;
    @SerializedName("icon")
    public String icon;


}
