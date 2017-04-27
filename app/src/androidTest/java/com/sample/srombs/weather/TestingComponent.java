package com.sample.srombs.weather;

import com.sample.srombs.weather.dagger.DemoComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by srombs on 4/9/17.
 */
@Singleton
@Component(modules = {MockApiModule.class, MockPresenterModule.class})
public interface TestingComponent extends DemoComponent {
    void inject(ViewWeatherPresenterTest target);

}
