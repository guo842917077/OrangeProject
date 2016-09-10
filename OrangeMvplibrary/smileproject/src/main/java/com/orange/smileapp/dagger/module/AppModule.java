package com.orange.smileapp.dagger.module;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import dagger.Module;
import dagger.Provides;

/**
 * 负责提供公共的对象
 */
@Module
public class AppModule {
    private Context mContext;

    public AppModule(Context mContext) {
        //empty instance
        this.mContext = mContext;
    }

    @Provides
    public Context provideContext() {
        return mContext;
    }

    @Provides
    public Handler getMainHandler() {
        return new Handler(Looper.myLooper());
    }

}
