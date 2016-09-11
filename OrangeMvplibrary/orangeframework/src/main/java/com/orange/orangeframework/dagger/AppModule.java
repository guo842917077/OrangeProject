package com.orange.orangeframework.dagger;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 提供全局的依赖
 */
@Module
public class AppModule {
    private Application mAppliction;

    public AppModule(Application application) {
        this.mAppliction = application;
    }
    @Provides
    @Singleton
    public Application  provideAppliction(){
        return mAppliction;
    }
}
