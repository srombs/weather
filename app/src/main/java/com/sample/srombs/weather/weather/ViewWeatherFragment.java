package com.sample.srombs.weather.weather;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.google.android.gms.location.LocationRequest;
import com.sample.srombs.weather.R;
import com.sample.srombs.weather.WeatherApplication;
import com.sample.srombs.weather.model.CurrentWeather;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;



public class ViewWeatherFragment extends Fragment implements ViewWeather {

    @Inject
    ViewWeatherPresenter presenter;
    @BindView(R.id.submit_zipcode_btn)
    Button submitBtn;

    @BindView(R.id.zip_input)
    EditText zipInput;

    @BindView(R.id.current_temp)
    TextView currentTemp;

    @BindView(R.id.current_clouds)
    TextView currentClouds;

    @BindView(R.id.current_wind)
    TextView currentWind;

    @BindView(R.id.submit_gps_btn)
    Button gpsBtn;

    @BindView(R.id.loading_indicator)
    ProgressBar loadingIndicator;

    public ViewWeatherFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        ViewWeatherFragment fragment = new ViewWeatherFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((WeatherApplication) getActivity().getApplicationContext()).getComponent().inject(this);
        presenter.onAttach(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_view_weather, container, false);

        ButterKnife.bind(this, rootView);

        submitBtn.setOnClickListener(v -> {
            presenter.loadCurrentWeatherByZip(zipInput.getText().toString());
        });

        gpsBtn.setOnClickListener(v -> {
            location();
        });


        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showLoadingIndicator() {
        loadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        loadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public void showCurrentWeather(CurrentWeather currentWeather) {
        String temperature = String.valueOf(currentWeather.main.getTemperature());
        String mainCondition = currentWeather.weather.get(0).mainConditions;
        String description = currentWeather.weather.get(0).description;
        String windSpeed = String.valueOf(currentWeather.wind.speed);
        currentTemp.setText(getString(R.string.current_temperature, temperature));
        currentClouds.setText(getString(R.string.current_sky_conditions, mainCondition, description));
        currentWind.setText(getString(R.string.current_wind_conditions, windSpeed));
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), R.string.error_weather_api, Toast.LENGTH_SHORT).show();
    }


    private void location() {

        getGps()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(location -> {
                    presenter.loadCurrentWeatherByGps(location);
                }, er -> Timber.d(er.getMessage()));
    }

    @RxLogObservable
    private Observable<Location> getGps() {
        ReactiveLocationProvider locationProvider = new ReactiveLocationProvider(getContext());

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Timber.d("Check permission is not granted for if statement");
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
//            return;
        }
        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        
        return locationProvider
                .getUpdatedLocation(locationRequest);
    }
}
