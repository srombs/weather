package com.sample.srombs.weather.dagger;

import com.sample.srombs.weather.MainActivity;
import com.sample.srombs.weather.weather.ViewWeatherFragment;
import com.sample.srombs.weather.weather.ViewWeatherPresenter;

/**
 * Created by srombs on 4/6/17.
 */
public interface DemoComponent {
    void inject(MainActivity target);
    void inject(ViewWeatherFragment target);
    void inject(ViewWeatherPresenter target);

}
