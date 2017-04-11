package com.sample.srombs.weather.dagger;

import com.sample.srombs.weather.api.ApiInterface;
import com.sample.srombs.weather.weather.ViewWeatherPresenter;
import com.sample.srombs.weather.api.ApiService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by srombs on 4/6/17.
 */
@Module
public class PresenterModule {

    @Provides
    public ViewWeatherPresenter providesViewWeather(ApiInterface api) {
        return new ViewWeatherPresenter(api);
    }



}
