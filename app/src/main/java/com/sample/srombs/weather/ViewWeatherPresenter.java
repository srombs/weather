package com.sample.srombs.weather;

import android.location.Location;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.google.android.gms.location.LocationRequest;
import com.sample.srombs.weather.api.ApiService;
import com.sample.srombs.weather.model.CurrentWeather;
import com.sample.srombs.weather.presenter.BasePresenter;

import javax.inject.Inject;

import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by srombs on 4/7/17.
 */

public class ViewWeatherPresenter extends BasePresenter<ViewWeather> {

    ViewWeather view;

    ApiService api;

    @Inject
    public ViewWeatherPresenter(ApiService api) {
        this.api = api;
    }

    @Override
    public void onAttach(ViewWeather view) {
        this.view = view;
    }

    @Override
    public void onDetach() {

    }

    public void loadCurrentWeatherByZip(String zipcode) {
        view.showLoadingIndicator();
        getZipcodeWeather(zipcode)
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
        getGpsWeather(location)
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
        return api.getApi().getCurrentWeatherZipCode(zipcode, "60cd6928576e8b913b16da027feac101");
    }

    @RxLogObservable
    public Observable<CurrentWeather> getGpsWeather(Location location) {
        return api.getApi().getCurrentWeatherGps((float) location.getLongitude(), (float) location.getLatitude(), "60cd6928576e8b913b16da027feac101");
    }

    public void loadWeatherByGps() {
        LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//                .setNumUpdates();

//        ReactiveLocationProvider locationProvider = new ReactiveLocationProvider()
    }
}
