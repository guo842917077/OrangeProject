package com.orange.smileapp.news;

import javax.inject.Inject;

/**
 * 新闻的业务类。
 */
public class NewsPresenterImp implements NewsContract.NewsPresenter {
    private NewsContract.NewsView mView;

    @Inject
    public NewsPresenterImp(NewsContract.NewsView mView) {
        this.mView = mView;
        mView.createPresenter(this);
    }

    @Override
    public void start() {
        mView.initWebSetting();
        mView.loadNews();
        mView.initWebClient();
        mView.loadNews();
    }
}
