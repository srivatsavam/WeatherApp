package com.vm.weather.jsonmodels;

/**
 * Created by vatsavm on 14-05-2017.
 */
public class City {
    private String id;
    private String name;
    private String country;
    private Coord coord;

    public City(String id, String name, Coord coord, String country){
        this.id = id;
        this.name = name;
        this.coord = coord;
        this.country = country;
    }
    public String getId() {
        if(null == id)
            return "";
        return id;
    }
    public String getName() {
        if(null == name)
            return "";
        return name;
    }
    public Coord getCoord() {return coord;}
    public String getCountry() {
        if(null == country)
            return "";
        return country;
    }

    @Override
    public String toString()
    {
        return "ID:"+ getId() +  "\n Name:" + getName() + "\n Coord" + getCoord().toString() + "\n Country:" + getCountry();
    }
}
