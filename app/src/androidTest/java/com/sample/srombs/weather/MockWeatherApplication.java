package com.sample.srombs.weather;

import com.sample.srombs.weather.dagger.DemoComponent;

/**
 * Created by srombs on 4/9/17.
 */

public class MockWeatherApplication extends WeatherApplication {

    @Override
    public DemoComponent createComponent() {
        return DaggerTestingComponent.builder()
                .mockApiModule(new MockApiModule())
                .build();
    }
}
