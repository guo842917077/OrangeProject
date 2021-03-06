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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

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
    private Semaphore mSemphore = new Semaphore(1);//设置信号量个数
    private final ExecutorService mExecutors = Executors.newFixedThreadPool(1);//定义一个线程池


    public WeatherPresenter(@NonNull WeatherContract.WeatherView view, Context mContext) {
        this.mView = view;
        this.mContext = mContext;
        mView.createPresenter(this);//将自己传递出去 在WeatherFragment中被拿到，在ContainerActivity中被创建
    }

    @Override
    public void loadWeatherData(final View view) {
        loadData(view);
    }
    /**
     * 尝试使用----线程池加信号量的方式 保证数据的同步
     * 只为了练习
     *
     * @param view
     */
    private void loadData(final View view) {
        mExecutors.submit(new Runnable() {
            @Override
            public void run() {
                if (view instanceof RecyclerView) {
                    try {
                        mSemphore.acquire();//拿到一个位置
                        RetrofitFactory.weatherInstance(WeatherAPI.BaseWeather).getWeather("北京", "fe1120411a704783ba788dce11a6d152")
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Subscriber<WeatherData>() {
                                    @Override
                                    public void onCompleted() {
                                        mView.loadWeatherData(mWeathers);
                                        mSemphore.release();//释放位置
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
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void start() {

    }
}
