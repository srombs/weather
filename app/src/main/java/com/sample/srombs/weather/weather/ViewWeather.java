package com.sample.srombs.weather.weather;

import com.sample.srombs.weather.model.CurrentWeather;
import com.sample.srombs.weather.presenter.BaseView;

/**
 * Created by srombs on 4/7/17.
 */

public interface ViewWeather extends BaseView {

    void showLoadingIndicator();
    void hideLoadingIndicator();
    void showCurrentWeather(CurrentWeather currentWeather);
    void showError();

}
