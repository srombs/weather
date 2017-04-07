package com.sample.srombs.weather.dagger;

import com.sample.srombs.weather.api.ApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by srombs on 4/7/17.
 */
@Module
public class ApiModule {

    @Provides @Singleton
    public ApiService providesApiService() {
        return new ApiService();
    }
}
