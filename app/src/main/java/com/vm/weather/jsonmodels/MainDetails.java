package com.vm.weather.jsonmodels;

/**
 * Created by vatsavm on 14-05-2017.
 */
public class MainDetails {
    private String temp;

    private String pressure;

    private String humidity;

    private String temp_min;

    private String temp_max;

    public MainDetails(String temp, String pressure, String humidity, String temp_min, String temp_max) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
    }

    public String getTemp() {
        return temp;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumudity() {
        return humidity;
    }

    public String getTemp_min() {
        return temp_min;
    }

    public String getTemp_max() {
        return temp_max;
    }
}
