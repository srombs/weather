package com.sample.srombs.weather.dagger;

import com.sample.srombs.weather.ViewWeatherPresenter;
import com.sample.srombs.weather.api.ApiService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by srombs on 4/6/17.
 */
@Module
public class PresenterModule {

    public PresenterModule() {}

    @Provides
    public ViewWeatherPresenter providesViewWeather(ApiService apiService) {
        return new ViewWeatherPresenter(apiService);
    }



}
