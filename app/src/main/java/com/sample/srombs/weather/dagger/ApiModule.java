package com.sample.srombs.weather.dagger;

import com.sample.srombs.weather.api.ApiInterface;
import com.sample.srombs.weather.api.ApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by srombs on 4/7/17.
 */
@Module
public class ApiModule {

    @Provides
    public HttpLoggingInterceptor providesHttpLogging() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return logging;
    }


    @Provides
    public OkHttpClient providesOkHttpClient(HttpLoggingInterceptor logging) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        return client;
    }

    @Provides
    public Retrofit providesRetrofit(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }

    @Provides @Singleton
    public ApiInterface providesApi(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }

}
