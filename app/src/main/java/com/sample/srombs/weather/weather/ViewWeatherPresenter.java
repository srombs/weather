package com.sample.srombs.weather.weather;

import android.location.Location;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.sample.srombs.weather.api.ApiInterface;
import com.sample.srombs.weather.model.CurrentWeather;
import com.sample.srombs.weather.presenter.BasePresenter;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by srombs on 4/7/17.
 */

public class ViewWeatherPresenter extends BasePresenter<ViewWeather> {

    ViewWeather view;
    ApiInterface api;

    Subscription zipCodeSubscription, gpsSubscription;

    @Inject
    public ViewWeatherPresenter(ApiInterface api) {
        this.api = api;
    }

    @Override
    public void onAttach(ViewWeather view) {
        this.view = view;
    }

    @Override
    public void onDetach() {
        if(zipCodeSubscription != null) {
            zipCodeSubscription.unsubscribe();
        }
        if(gpsSubscription != null) {
            gpsSubscription.unsubscribe();
        }
    }

    public void loadCurrentWeatherByZip(String zipcode) {
        view.showLoadingIndicator();
        zipCodeSubscription = getZipcodeWeather(zipcode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(currentWeather -> {
                    view.showCurrentWeather(currentWeather);
                    view.hideLoadingIndicator();
                }, error -> {
                    view.showError();
                    view.hideLoadingIndicator();
                    Timber.d("error: %s", error.getMessage());
                });
    }

    public void loadCurrentWeatherByGps(Location location) {
        view.showLoadingIndicator();
        gpsSubscription = getGpsWeather(location)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(currentWeather -> {
                    view.showCurrentWeather(currentWeather);
                    view.hideLoadingIndicator();
                }, error -> {
                    view.showError();
                    view.hideLoadingIndicator();
                    Timber.d("error: %s", error.getMessage());
                });
    }

    @RxLogObservable
    public Observable<CurrentWeather> getZipcodeWeather(String zipcode) {
        return api.getCurrentWeatherZipCode(zipcode);
    }

    @RxLogObservable
    public Observable<CurrentWeather> getGpsWeather(Location location) {
        return api.getCurrentWeatherGps((float) location.getLongitude(), (float) location.getLatitude());
    }


}
