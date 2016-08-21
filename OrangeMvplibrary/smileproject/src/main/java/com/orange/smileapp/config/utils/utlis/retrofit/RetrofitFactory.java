package com.orange.smileapp.config.utils.utlis.retrofit;

import com.orange.smileapp.api.PhotoAPI;
import com.orange.smileapp.api.SmileAPI;
import com.orange.smileapp.api.WeatherAPI;

/**
 * Retrofit工厂类  拿到对应的api
 */
public class RetrofitFactory {
    /**
     * 对应的网络接口
     */
    private volatile static SmileAPI smileInstance;
    private volatile static WeatherAPI weatherInstance;
    private volatile static PhotoAPI photoInstance;
    public static WeatherAPI weatherInstance(String baseUrl) {
        if (weatherInstance == null) {
            synchronized (SmileAPI.class) {
                if (weatherInstance == null) {
                    RetrofitClient client = new RetrofitClient(baseUrl);
                    weatherInstance = client.getWeatherAPI();
                }
            }
        }
        return weatherInstance;
    }

    public static PhotoAPI photoInstance(String baseUrl) {
        if (photoInstance == null) {
            synchronized (PhotoAPI.class) {
                if (photoInstance == null) {
                    RetrofitClient client = new RetrofitClient(baseUrl);
                    photoInstance = client.getPhotoAPI();
                }
            }
        }
        return photoInstance;
    }
}
