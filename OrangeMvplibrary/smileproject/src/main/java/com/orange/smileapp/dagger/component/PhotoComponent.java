package com.orange.smileapp.dagger.component;

import com.orange.smileapp.dagger.module.PhotoModule;
import com.orange.smileapp.dagger.scope.PhotoScope;
import com.orange.smileapp.home.view.ContaineActivity;

import dagger.Component;

@PhotoScope
@Component(dependencies = AppComponent.class, modules = PhotoModule.class)
public interface PhotoComponent {
    void inject(ContaineActivity activity);
}
