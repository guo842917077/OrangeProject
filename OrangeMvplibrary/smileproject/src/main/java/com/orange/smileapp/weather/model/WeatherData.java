package com.orange.smileapp.weather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/13.
 */
public class WeatherData {

    @SerializedName("HeWeather data service 3.0")
    @Expose
    public List<WeatherModel> mHeWeatherDataService30s
            = new ArrayList<>();
}
