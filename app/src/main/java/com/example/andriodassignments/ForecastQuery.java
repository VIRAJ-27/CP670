//package com.example.andriodassignments;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.AsyncTask;
//import android.util.Log;
//
//import org.xmlpull.v1.XmlPullParser;
//import org.xmlpull.v1.XmlPullParserFactory;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.lang.ref.WeakReference;
//
//public class ForecastQuery extends AsyncTask<Void, Integer, WeatherData> {
//
//    private static final String TAG = "ForecastQuery";
//    private WeakReference<MainActivity> mainActivityReference;
//    private String temperatureValue;
//    private String temperatureMin;
//    private String temperatureMax;
//    private String iconName;
//
//    public ForecastQuery(MainActivity activity) {
//        this.mainActivityReference = new WeakReference<>(activity);
//    }
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//        MainActivity activity = mainActivityReference.get();
//        if (activity != null) {
//            activity.showProgressBar();
//        }
//    }
//
//    @Override
//    protected WeatherData doInBackground(Void... voids) {
//        String urlString = "https://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=b8189875884fb468d13cbecf7263b8d4&mode=xml&units=metric";
//        InputStream inputStream = null;
//        try {
//            URL url = new URL(urlString);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            connection.connect();
//
//            inputStream = connection.getInputStream();
//            publishProgress(25);
//
//            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//            XmlPullParser parser = factory.newPullParser();
//            parser.setInput(inputStream, null);
//
//            int eventType = parser.getEventType();
//            while (eventType != XmlPullParser.END_DOCUMENT) {
//                if (eventType == XmlPullParser.START_TAG) {
//                    String tagName = parser.getName();
//                    if ("temperature".equals(tagName)) {
//                        temperatureValue = parser.getAttributeValue(null, "value");
//                        publishProgress(50);
//                        temperatureMin = parser.getAttributeValue(null, "min");
//                        publishProgress(75);
//                        temperatureMax = parser.getAttributeValue(null, "max");
//                    } else if ("weather".equals(tagName)) {
//                        iconName = parser.getAttributeValue(null, "icon");
//                    }
//                }
//                eventType = parser.next();
//            }
//
//            if (iconName != null) {
//                String imageUrl = "http://openweathermap.org/img/w/" + iconName + ".png";
//                Bitmap image = getImageFromUrl(imageUrl);
//                if (image != null) {
//                    saveImageToLocal(iconName, image);
//                    publishProgress(100);
//                }
//            }
//
//            WeatherData weatherData = new WeatherData(temperatureValue, temperatureMin, temperatureMax, iconName);
//            return weatherData;
//
//        } catch (Exception e) {
//            Log.e(TAG, "Error in doInBackground", e);
//        } finally {
//            try {
//                if (inputStream != null) {
//                    inputStream.close();
//                }
//            } catch (Exception e) {
//                Log.e(TAG, "Error closing inputStream", e);
//            }
//        }
//        return null;
//    }
//
//    private Bitmap getImageFromUrl(String imageUrl) {
//        if (fileExists(iconName + ".png")) {
//            Log.i(TAG, "Image file " + iconName + ".png already exists locally.");
//            try (FileInputStream fis = mainActivityReference.get().openFileInput(iconName + ".png")) {
//                return BitmapFactory.decodeStream(fis);
//            } catch (FileNotFoundException e) {
//                Log.e(TAG, "File not found", e);
//            } catch (Exception e) {
//                Log.e(TAG, "Error reading file", e);
//            }
//        } else {
//            Log.i(TAG, "Downloading image file " + iconName + ".png from the internet.");
//            try {
//                URL url = new URL(imageUrl);
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setDoInput(true);
//                connection.connect();
//                InputStream input = connection.getInputStream();
//                return BitmapFactory.decodeStream(input);
//            } catch (Exception e) {
//                Log.e(TAG, "Error downloading image", e);
//            }
//        }
//        return null;
//    }
//
//    private void saveImageToLocal(String iconName, Bitmap image) {
//        try (FileOutputStream outputStream = mainActivityReference.get().openFileOutput(iconName + ".png", Context.MODE_PRIVATE)) {
//            image.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
//            outputStream.flush();
//        } catch (Exception e) {
//            Log.e(TAG, "Error saving image", e);
//        }
//    }
//
//    public boolean fileExists(String fname) {
//        File file = mainActivityReference.get().getFileStreamPath(fname);
//        return file.exists();
//    }
//
//    @Override
//    protected void onProgressUpdate(Integer... values) {
//        super.onProgressUpdate(values);
//        MainActivity activity = mainActivityReference.get();
//        if (activity != null) {
//            activity.updateProgress(values[0]);
//        }
//    }
//
//    @Override
//    protected void onPostExecute(WeatherData weatherData) {
//        super.onPostExecute(weatherData);
//        MainActivity activity = mainActivityReference.get();
//        if (activity != null) {
//            activity.hideProgressBar();
//            if (weatherData != null) {
//                activity.updateUI(weatherData);
//            } else {
//                Log.e(TAG, "Weather data is null.");
//            }
//        }
//    }
//}
