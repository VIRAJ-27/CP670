package com.example.andriodassignments;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherForecast extends AppCompatActivity {

    private static final String TAG = "WeatherForecast";
    private ImageView imageViewWeather;
    private TextView textViewCurrentTemp, textViewMinTemp, textViewMaxTemp;
    private ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        imageViewWeather = findViewById(R.id.image_weather);
        textViewCurrentTemp = findViewById(R.id.text_current_temp);
        textViewMinTemp = findViewById(R.id.text_min_temp);
        textViewMaxTemp = findViewById(R.id.text_max_temp);
//        progressBar = findViewById(R.id.progressBar);

//        showProgressBar();
        System.out.println("in weather class");
        new WeatherQueryTask().execute();
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void updateUI(WeatherData weatherData) {
        if (weatherData != null) {
//            imageViewWeather.setImageResource(weatherData.getWeatherIcon());
            textViewCurrentTemp.setText(weatherData.getCurrentTemp());
            textViewMinTemp.setText(weatherData.getMinTemp());
            textViewMaxTemp.setText(weatherData.getMaxTemp());
        } else {
            Toast.makeText(this, "Failed to fetch weather data", Toast.LENGTH_SHORT).show();
        }
//        hideProgressBar();
    }

    private class WeatherQueryTask extends AsyncTask<Void, Void, WeatherData> {

        private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=b8189875884fb468d13cbecf7263b8d4&mode=xml&units=metric";

        @Override
        protected WeatherData doInBackground(Void... voids) {
            try {
                URL url = new URL(API_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                InputStream inputStream = connection.getInputStream();
                return parseXML(inputStream);

            } catch (Exception e) {
                Log.e(TAG, "Error fetching weather data: " + e.getMessage());
                return null;
            }
        }

        @Override
        protected void onPostExecute(WeatherData weatherData) {
            super.onPostExecute(weatherData);
            updateUI(weatherData);
        }
    }

    private WeatherData parseXML(InputStream inputStream) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(inputStream, null);

            int eventType = parser.getEventType();
            String currentTemp = "", minTemp = "", maxTemp = "";
            int weatherIcon = R.drawable.ic_weather_sunny;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("temperature".equals(tagName)) {
                            currentTemp = parser.getAttributeValue(null, "value");
                            minTemp = parser.getAttributeValue(null, "min");
                            maxTemp = parser.getAttributeValue(null, "max");
                        }
                        break;
                }
                eventType = parser.next();
            }

            return new WeatherData(currentTemp, minTemp, maxTemp, weatherIcon);

        } catch (XmlPullParserException | IOException e) {
            Log.e(TAG, "Error parsing XML: " + e.getMessage());
            return null;
        }
    }
}
