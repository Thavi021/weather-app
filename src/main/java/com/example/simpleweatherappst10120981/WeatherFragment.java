package com.example.simpleweatherappst10120981;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class WeatherFragment extends Fragment {

    TextView textView;

    String TAG = "ourDATA";

    ArrayList<Forecast> DailyForecasts = new ArrayList<>();

    ListView weatherListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.weather_fragment,container,false);

        textView = view.findViewById(R.id.text_weather);
        textView.setText("Weather");
        weatherListView = view.findViewById(R.id.weather_listView);

        URL url = NetworkUtil.buildURLForWeather();

        new FetchWeatherData().execute(url);
        return view;

    }

    class FetchWeatherData extends AsyncTask<URL,Void,String>
    {
        @Override
        protected  String doInBackground(URL... urls)
        {
            URL weatherUrl = urls[0];
            String weatherResults = null;

            try{
                weatherResults = NetworkUtil.getResponse(weatherUrl);
            }catch(IOException e)
            {
                e.printStackTrace();
            }
            Log.i(TAG,"doInBackground: "+weatherResults);
            return weatherResults;
        }

        @Override
        protected  void onPostExecute(String weatherResults){
            super.onPostExecute(weatherResults);

            if(weatherResults!=null)
            {
consumeJSON(weatherResults);
            }
        }

        public ArrayList<Forecast> consumeJSON(String weatherResults)
        {

            Forecast forecast = new Forecast();

            if(DailyForecasts!=null)
            {
                DailyForecasts.clear();
            }
            try {


                    JSONObject allWeatherData = new JSONObject(weatherResults);
                    JSONArray fiveDayWeatherData = allWeatherData.getJSONArray("DailyForecasts");

                    for(int i = 0; i <fiveDayWeatherData.length();i++)
                    {
                        JSONObject individualDayData = fiveDayWeatherData.getJSONObject(i);
                    String date = individualDayData.getString("Date");
                    forecast.setDate(date);
                    Log.i(TAG,"consumeJSON: DATE "+date);

                    JSONObject Temperature = individualDayData.getJSONObject("Temperature");
                    JSONObject Minimum = Temperature.getJSONObject("Minimum");
                    String minTemp = Minimum.getString("Value");
                    forecast.setfMinTemp(minTemp);
                    Log.i(TAG, "consumeJSON: MINTEMP " + minTemp);


                       JSONObject Maximum = Temperature.getJSONObject("Maximum");
                        String maxTemp = Maximum.getString("Value");
                        Log.i(TAG, "consumeJSON: MAXTEMP " + maxTemp);

forecast.setfMaxTemp(maxTemp);

DailyForecasts.add(forecast);

        if(DailyForecasts !=null)
        {
            ForecastAdapter adapter = new ForecastAdapter(getContext(),DailyForecasts);
       weatherListView.setAdapter(adapter);
        }

                    }
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            return null;
        }
        //https://www.youtube.com/watch?v=vQkURbXQrX8&list=PL480DYS-b_kf-pheFMX1W_-YmnMpg4Gs7&index=11
    }
}
