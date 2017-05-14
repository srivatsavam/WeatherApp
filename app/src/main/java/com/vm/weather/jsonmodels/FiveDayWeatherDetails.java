package com.vm.weather.jsonmodels;

import java.util.List;

/**
 * Created by vatsavm on 14-05-2017.
 */
public class FiveDayWeatherDetails {

    private String dt_txt;
    private MainDetails main;
    private List<DayWeatherDetails> weather;

    public FiveDayWeatherDetails(String dt_txt, MainDetails main, List<DayWeatherDetails> weather){
        this.dt_txt = dt_txt;
        this.main = main;
        this.weather = weather;
    }

    public String getDateText(){
        if(null == dt_txt)
            return "";
        return dt_txt;
    }

    public MainDetails getMainDetails(){
        return main;
    }

    public List<DayWeatherDetails> getWeatherDetails() {
        return weather;
    }
}

