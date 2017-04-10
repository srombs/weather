package com.sample.srombs.weather.dagger;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by srombs on 4/9/17.
 */
@Singleton
@Component(modules = {AppModule.class, ApiModule.class, PresenterModule.class})
public interface ApplicationComponent extends DemoComponent {
}
