package com.example.neethu.openweathermapactivity.model;

import java.util.List;

/**
 * Created by neethu on 15/2/16.
 */
public class WeatherData {
    public   int id;
    public   String name;
    public Coord coord;
    public List<Weather> weather;
    public  Main main;
    public  Wind wind;
    public  Sys sys;
//    public   String country;
//    public   float temp;
//    public   float wind;
//    public   String description;
//    public   float pressure;
//    public   int humidity;
//    public   long sunrise;
//    public   long sunset;
//    public   float coord;


    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeatherList() {
        return weather;
    }

    public void setWeatherList(List<Weather> weather) {
        this.weather = weather;
    }

    public Weather getWeather() {
        if(weather == null) {
            return new Weather("Unable to fetch weather");
        }

        return weather.get(0);
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
