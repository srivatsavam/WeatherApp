package com.vm.weather.jsonmodels;

import java.util.List;

/**
 * Created by vatsavm on 14-05-2017.
 */
public class WeatherForecast {
    private City city;
    private List<FiveDayWeatherDetails> list;


    public WeatherForecast(City city, List<FiveDayWeatherDetails> list){
        this.city = city;
        this.list = list;
    }

    public City getCity() { return city; }
    public List<FiveDayWeatherDetails> getWeatherList() {
        return list;
    }
    @Override
    public String toString()
    {
        return getCity().toString();
    }
}
