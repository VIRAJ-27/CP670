package com.example.andriodassignments;

public class WeatherData {
    private String currentTemp;
    private String minTemp;
    private String maxTemp;
    private int weatherIcon;

    public WeatherData(String currentTemp, String minTemp, String maxTemp, int weatherIcon) {
        this.currentTemp = currentTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.weatherIcon = weatherIcon;
    }

    // Add getters and setters as needed
    public String getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(String currentTemp) {
        this.currentTemp = currentTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(int weatherIcon) {
        this.weatherIcon = weatherIcon;
    }
}
