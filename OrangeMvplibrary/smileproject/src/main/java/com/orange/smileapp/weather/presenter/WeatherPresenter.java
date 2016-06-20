package com.orange.smileapp.weather.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.orange.smileapp.api.WeatherAPI;
import com.orange.smileapp.config.utils.utlis.retrofit.RetrofitFactory;
import com.orange.smileapp.weather.adapter.WeatherAdapter;
import com.orange.smileapp.weather.model.WeatherData;
import com.orange.smileapp.weather.model.WeatherModel;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Weather业务
 */
public class WeatherPresenter implements WeatherContract.WeatherPresenter {
    private String TAG = WeatherPresenter.class.getSimpleName();
    private WeatherContract.WeatherView mView;
    private WeatherAdapter mAdapter;
    private List<WeatherModel> mWeathers = new ArrayList<>();
    private Context mContext;

    public WeatherPresenter(@NonNull WeatherContract.WeatherView view, Context mContext) {
        this.mView = view;
        this.mContext = mContext;
        mView.createPresenter(this);//将自己传递出去 在WeatherFragment中被拿到，在ContainerActivity中被创建
    }

    @Override
    public void loadWeatherData(final View view) {
        if (view instanceof RecyclerView) {
            RetrofitFactory.weatherInstance(WeatherAPI.BaseWeather).getWeather("北京", "fe1120411a704783ba788dce11a6d152")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<WeatherData>() {
                        @Override
                        public void onCompleted() {
                            mView.loadWeatherData(mWeathers);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("Tag", "weather err " + e.getMessage());
                        }

                        @Override
                        public void onNext(WeatherData weatherList) {
                            Log.e("tag", "weather list " + weatherList.mHeWeatherDataService30s.size());
                            mWeathers.addAll(weatherList.mHeWeatherDataService30s);
                        }
                    });
        }
    }

    @Override
    public void start() {

    }
}
