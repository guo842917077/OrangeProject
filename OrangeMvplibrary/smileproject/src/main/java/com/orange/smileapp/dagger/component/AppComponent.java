package com.orange.smileapp.dagger.component;

import android.content.Context;

import com.orange.smileapp.dagger.module.AppModule;

import dagger.Component;

/**
 * 对外提供context 不要在Activity再次提供
 * 否则报多次绑定错误
 */


@Component(modules = {AppModule.class})
public interface AppComponent {
    Context  getContext();
}
