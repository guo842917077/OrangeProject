package com.orange.orangeframework.baseapp;

import android.app.Application;
import android.util.Log;

import com.orange.orangeframework.dagger.AppComponent;
import com.orange.orangeframework.dagger.AppModule;
import com.orange.orangeframework.dagger.DaggerAppComponent;

/**
 * 负责进行全局的配置
 */
public class BaseApplication extends Application {
    private  final String TAG =BaseApplication.class.getSimpleName() ;
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent(BaseApplication.this);
    }

    private void initComponent(Application application) {
        Log.d(TAG,"注入成功...");
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(application)).build();
        mAppComponent.inject(application);
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
