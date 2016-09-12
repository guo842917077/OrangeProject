package com.orange.smileapp.dagger.component;

import com.orange.smileapp.dagger.module.NewsModule;
import com.orange.smileapp.dagger.module.PhotoModule;
import com.orange.smileapp.dagger.scope.PhotoScope;
import com.orange.smileapp.home.view.ContaineActivity;
import com.orange.smileapp.news.NewsFragment;
import com.orange.smileapp.photo.view.PhotoFragment;

import dagger.Component;

@PhotoScope
@Component(dependencies = AppComponent.class, modules = {PhotoModule.class, NewsModule.class})
public interface PhotoComponent {
    void inject(ContaineActivity activity);

    void inject(PhotoFragment fragment);

    void inject(NewsFragment fragment);
}
