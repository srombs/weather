package com.sample.srombs.weather;

import com.sample.srombs.weather.presenter.BasePresenter;

/**
 * Created by srombs on 4/7/17.
 */

public class ViewWeatherPresenter extends BasePresenter<ViewWeather> {

    ViewWeather view;


    @Override
    public void onAttach(ViewWeather view) {
        this.view = view;
    }

    @Override
    public void onDetach() {

    }

    public void loadCurrentWeatherByZip(String zipcode) {

    }
}
