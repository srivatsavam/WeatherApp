package com.vm.weather.jsonmodels;

/**
 * Created by vatsavm on 14-05-2017.
 */
public class Coord {
    private String lon;

    private String lat;

    public Coord(String lon, String lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public String getLon() {
        if(null == lon)
            return "";
        return lon;
    }

    public String getLat() {
        if(null == lat)
            return "";
        return lat;
    }

    @Override
    public String toString()
    {
        return  "Lat:" + getLat() + "\n Lon:" + getLon();
    }
}
