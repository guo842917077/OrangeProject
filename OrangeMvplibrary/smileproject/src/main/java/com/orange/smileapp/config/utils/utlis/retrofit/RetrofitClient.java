package com.orange.smileapp.config.utils.utlis.retrofit;

import com.orange.smileapp.api.SmileAPI;
import com.orange.smileapp.api.WeatherAPI;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求工具类
 */
public class RetrofitClient {
    private Retrofit client;
    private String BaseUrl;

    /**
     * 初始化Retrofit的内容
     */
    public RetrofitClient(String BaseUrl) {
        this.BaseUrl = BaseUrl;
        client = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BaseUrl)
                .build();
    }

    public SmileAPI getSmileAPI() {
        return client.create(SmileAPI.class);
    }

    public WeatherAPI getWeatherAPI() {
        return client.create(WeatherAPI.class);
    }
}
