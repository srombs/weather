package com.sample.srombs.weather.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by srombs on 4/6/17.
 */

public class ApiService {

    ApiInterface api;



    public ApiService() {
        setup();
    }

    private void setup() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


        api = retrofit.create(ApiInterface.class);

    }

    public ApiInterface getApi() {
        return api;
    }



}
