package com.example.simpleweatherappst10120981;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    TideFragment tideFragment;
    WeatherFragment weatherFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tideFragment = new TideFragment();
        weatherFragment = new WeatherFragment();

        FragmentManager manager = getSupportFragmentManager();

            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.weather_fragment_container, weatherFragment);
            transaction.replace(R.id.tide_fragment_container, tideFragment);
            transaction.commit();

    }



}