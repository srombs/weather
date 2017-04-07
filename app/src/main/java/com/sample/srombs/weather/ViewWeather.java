package com.sample.srombs.weather;

import com.sample.srombs.weather.presenter.BaseView;

/**
 * Created by srombs on 4/7/17.
 */

public interface ViewWeather extends BaseView {

    void showLoadingIndicator();
    void hideLoadingIndicator();
    void showCurrentLocationWeather();
    void showZipCodeWeather();


}
