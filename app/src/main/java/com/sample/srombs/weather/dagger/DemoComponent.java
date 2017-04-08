package com.sample.srombs.weather.dagger;

import com.sample.srombs.weather.MainActivity;
import com.sample.srombs.weather.ViewWeatherFragment;
import com.sample.srombs.weather.WeatherApplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by srombs on 4/6/17.
 */
@Singleton
@Component(modules = {AppModule.class, ApiModule.class, PresenterModule.class})
public interface DemoComponent {

    void inject(WeatherApplication target);
    void inject(MainActivity target);
    void inject(ViewWeatherFragment target);

}
