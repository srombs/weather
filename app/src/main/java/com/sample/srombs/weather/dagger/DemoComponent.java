package com.sample.srombs.weather.dagger;

import com.sample.srombs.weather.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by srombs on 4/6/17.
 */
@Singleton
@Component(modules = {AppModule.class, ApiModule.class, PresenterModule.class})
public interface DemoComponent {
    void inject(MainActivity target);
}
