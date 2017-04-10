package com.sample.srombs.weather;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
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
import com.sample.srombs.weather.api.ApiService;
import com.sample.srombs.weather.model.CurrentWeather;
import com.sample.srombs.weather.model.Weather;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewWeatherFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewWeatherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewWeatherFragment extends Fragment implements ViewWeather {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     //     * @param param1 Parameter 1.
     //     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewWeatherFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance() {
        ViewWeatherFragment fragment = new ViewWeatherFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((WeatherApplication) getActivity().getApplicationContext()).getComponent().inject(this);
        presenter.onAttach(this);

        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
                    Timber.d("locationrequest");

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
//                .getLastKnownLocation();
    }
}
