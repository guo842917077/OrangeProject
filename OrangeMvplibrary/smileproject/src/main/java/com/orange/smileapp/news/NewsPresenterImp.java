package com.orange.smileapp.news;

import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import mvp.presenter.BaseFragmentPresenter;

/**
 * 新闻的业务类。
 */
public class NewsPresenterImp extends BaseFragmentPresenter<INewsView> {

    @Inject
    public NewsPresenterImp() {
        super();
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        Log.d(TAG,"hhhh mBaseView in presenter "+mBaseView);
        mBaseView.initWebSetting();
        mBaseView.loadNews();
        mBaseView.initWebClient();
        mBaseView.loadNews();
    }




    /* @Inject
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
    }*/
}
