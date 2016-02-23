package com.example.neethu.openweathermapactivity.api;

import com.example.neethu.openweathermapactivity.model.WeatherData;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by neethu on 15/2/16.
 */
public interface WeatherAPI {

    @GET("/data/2.5/weather")
    void getWeatherData(@Query("id") String id, @Query("appid") String appID,
                        @Query("units") String units,
                        Callback<WeatherData> weatherDataCallback);

}
