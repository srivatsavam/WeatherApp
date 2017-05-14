package com.vm.weather.models;

/**
 * Created by vatsavm on 14-05-2017.
 */
public class DateRecyclerItem {

    private String dayOfWeek;
    private String dateToDisplay;
    private String date;
    private boolean bTodaysDate = false;
//    private String temp;

    public DateRecyclerItem(String dayOfWeek, String dateToDisplay, String date, boolean bTodaysDate){//, String temp
        this.dayOfWeek = dayOfWeek;
        this.dateToDisplay = dateToDisplay;
        this.date = date;
        this.bTodaysDate = bTodaysDate;
//        this.temp = temp;
    }

    public String getDayofWeek(){
        if(null == dayOfWeek)
            return "";
        return dayOfWeek;
    }
    public String getDateYear(){
        if(null == date)
            return "";
        return date;
    }
    public String getDateToDisplay(){
        if(null == dateToDisplay)
            return "";
        return dateToDisplay;
    }
    public boolean isTodaysDate(){
        return bTodaysDate;
    }
    /*public String getTemp(){
        if(null == temp)
            return "";
        return temp;
    }*/
}
