package com.sample.srombs.weather;

import com.sample.srombs.weather.api.ApiService;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by srombs on 4/9/17.
 */
@Module
public class MockApiModule {

    @Provides @Singleton
    public ApiService providesApiService() {
        return Mockito.mock(ApiService.class);
    }
}
