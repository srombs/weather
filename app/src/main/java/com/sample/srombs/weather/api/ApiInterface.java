package com.sample.srombs.weather.api;

import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by srombs on 4/6/17.
 */

public interface ApiInterface {

    Observable<Object> getCurrentWeatherZipCode(@Query("zip") String zipcode);
}
