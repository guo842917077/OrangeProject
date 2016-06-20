package com.orange.smileapp.weather.presenter;

import android.view.View;

import com.orange.smileapp.weather.model.WeatherModel;

import java.util.List;

import googlemvp.GoogleBasePresenter;
import googlemvp.GoogleBaseView;

/**
 * Weather的契约类
 */
public class WeatherContract {
    public interface WeatherPresenter extends GoogleBasePresenter {
        void loadWeatherData(View view);
    }

    public interface WeatherView extends GoogleBaseView<WeatherPresenter> {
        void loadWeatherData(List<WeatherModel> data);

        void loadWeatherError();

        void loadWeatherSuccess();
    }
}
