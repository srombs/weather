package com.sample.srombs.weather;

import android.app.Application;

import com.sample.srombs.weather.dagger.ApiModule;
import com.sample.srombs.weather.dagger.AppModule;
import com.sample.srombs.weather.dagger.DaggerApplicationComponent;
import com.sample.srombs.weather.dagger.DemoComponent;
import com.sample.srombs.weather.dagger.PresenterModule;

/**
 * Created by srombs on 4/7/17.
 */

public class WeatherApplication extends Application {

    DemoComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = createComponent();

    }

    public DemoComponent createComponent() {
        component = DaggerApplicationComponent.builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this))
                .presenterModule(new PresenterModule())
                .build();

        return component;
    }

    public DemoComponent getComponent() {
        return component;
    }
}
