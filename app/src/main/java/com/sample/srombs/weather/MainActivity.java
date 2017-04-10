package com.sample.srombs.weather;

import android.Manifest;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tbruyelle.rxpermissions.RxPermissions;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    RxPermissions rxPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rxPermissions = new RxPermissions(this);

        checkGpsPermissions();

        loadFragment();
    }


    private void checkGpsPermissions() {
        rxPermissions
                .request(Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(granted -> {
                    if(granted) {
                        Timber.d("Gps granted");
                    } else {
                        Timber.d("Gps not granted");
                    }
                });
    }

    private void loadFragment() {
        getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment_layout, ViewWeatherFragment.newInstance()).commit();
    }
}
