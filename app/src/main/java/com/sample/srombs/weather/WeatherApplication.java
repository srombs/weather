package com.sample.srombs.weather;

import android.app.Application;

import com.sample.srombs.weather.dagger.ApiModule;
import com.sample.srombs.weather.dagger.AppModule;
import com.sample.srombs.weather.dagger.DaggerDemoComponent;
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

        component = DaggerDemoComponent.builder()

                .apiModule(new ApiModule())
                .appModule(new AppModule(this))
                .presenterModule(new PresenterModule())
                .build();

        component.inject(this);


    }

    public DemoComponent getComponent() {
        return component;
    }
}
