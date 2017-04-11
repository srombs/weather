package com.sample.srombs.weather.api;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by srombs on 4/6/17.
 */

public class ApiService {

    ApiInterface api;
//    OkHttpClient client;
//    Retrofit retrofit;


    @Inject
    public ApiService(ApiInterface api) {
//        this.client = client;
//        this.retrofit = retrofit;
        this.api = api;
//        setup();
    }


    private void setup() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        api = retrofit.create(ApiInterface.class);

    }

    public ApiInterface getApi() {
        return api;
    }

    public void setApi(ApiInterface apiInterface) {
        this.api = apiInterface;
    }



}
