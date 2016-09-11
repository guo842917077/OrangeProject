package com.orange.orangeframework.dagger;

import android.app.Application;

import com.orange.orangeframework.utils.file.FileUtils;
import com.orange.orangeframework.utils.image.ImageUtils;

import javax.inject.Singleton;

import dagger.Component;

/**
 * App的Component
 * 向子类暴露对象。
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Application provideApplictoin();

    FileUtils provideFileUtils();

    ImageUtils provideImageUtils();

    void inject(Application application);
}
