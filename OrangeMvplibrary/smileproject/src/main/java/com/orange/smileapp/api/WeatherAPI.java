package com.orange.smileapp.api;

import com.orange.smileapp.weather.model.WeatherData;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 天气API 来自和风天气
 */
public interface WeatherAPI {
    String BaseWeather="https://api.heweather.com/x3/";
    @GET("weather")
    Observable<WeatherData> getWeather(@Query("city") String city, @Query("key") String key);
}
