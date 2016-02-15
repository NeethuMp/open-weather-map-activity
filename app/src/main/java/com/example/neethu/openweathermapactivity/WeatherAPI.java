package com.example.neethu.openweathermapactivity;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by neethu on 15/2/16.
 */
public interface WeatherAPI {

@GET("/weather/{id}/")
    void getWeatherData(@path("id") int id, Callback<WeatherData> weatherDataCallback );


}
