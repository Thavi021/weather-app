package com.example.simpleweatherappst10120981;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.net.URL;

public class TideFragment extends Fragment {

    TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tide_fragment,container,false);

        textView = view.findViewById(R.id.text_tide);
        textView.setText("Tide");

        return view;

    }

    private class FetchWeatherData extends AsyncTask<URL,Void,String>
    {
        @Override
        protected  String doInBackground(URL... urls)
        {
            URL weatherUrl = urls[0];
            String weatherData = null;

            try{
                weatherData = NetworkUtil.getResponse(weatherUrl);
            }catch(IOException e)
            {
                e.printStackTrace();
            }
            return weatherData;
        }
    }
}
