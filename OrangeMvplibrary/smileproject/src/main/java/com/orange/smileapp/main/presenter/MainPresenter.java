package com.orange.smileapp.main.presenter;

import android.os.Bundle;

import com.orange.smileapp.main.view.IMainView;

import mvp.presenter.BasePresenter;

/**
 * Main的业务逻辑
 */
public class MainPresenter extends BasePresenter<IMainView> {
    public MainPresenter(IMainView mBaseView) {
        super(mBaseView);
    }
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        mBaseView.initToobar();
    }

}
