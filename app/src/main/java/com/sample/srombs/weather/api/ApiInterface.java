package com.sample.srombs.weather.api;

import com.sample.srombs.weather.model.CurrentWeather;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by srombs on 4/6/17.
 */

public interface ApiInterface {

    @GET("/data/2.5/weather?units=imperial")
    Observable<CurrentWeather> getCurrentWeatherZipCode(@Query("zip") String zipcode, @Query("APPID") String key);

    @GET("/data/2.5/weather?units=imperial")
    Observable<CurrentWeather> getCurrentWeatherGps(@Query("lon") float lon, @Query("lat") float lat, @Query("APPID") String key);
}
