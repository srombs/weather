package com.sample.srombs.weather;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loadFragment();
    }

    private void loadFragment() {
        getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment_layout, ViewWeatherFragment.newInstance()).commit();
    }
}
