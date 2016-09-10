package com.orange.smileapp;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.orange.smileapp.dagger.component.AppComponent;
import com.orange.smileapp.dagger.component.DaggerAppComponent;
import com.orange.smileapp.dagger.module.AppModule;
import com.tencent.bugly.crashreport.CrashReport;


public class SmileApplication extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        //腾讯Bugly
        CrashReport.initCrashReport(getApplicationContext(), "900038800", false);
        //初始化Component
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        //Stetho调试
        Stetho.newInitializerBuilder(this).enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this)).build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
