package com.orange.smileapp.dagger.module;

import com.orange.smileapp.dagger.scope.PhotoScope;
import com.orange.smileapp.news.NewsContract;

import dagger.Module;
import dagger.Provides;

/**
 * 新闻对象的module提供者
 */
@Module
public class NewsModule {
    public NewsContract.NewsView mView;

    public NewsModule(NewsContract.NewsView view) {
        this.mView = view;
    }
    @PhotoScope
    @Provides
    public NewsContract.NewsView provideNewsView() {
        return mView;
    }
}
