package com.sample.srombs.weather;

import com.sample.srombs.weather.api.ApiInterface;
import com.sample.srombs.weather.weather.ViewWeatherPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by srombs on 4/19/17.
 */

@Module
public class MockPresenterModule {

    @Provides
    public ViewWeatherPresenter providesViewWeather(ApiInterface api) {
        return new ViewWeatherPresenter(api);
    }



}
