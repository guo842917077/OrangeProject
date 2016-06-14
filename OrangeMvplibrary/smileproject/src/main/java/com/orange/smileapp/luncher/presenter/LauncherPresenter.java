package com.orange.smileapp.luncher.presenter;

import android.os.Bundle;

import com.orange.smileapp.luncher.view.ILauncherView;

import mvp.presenter.BasePresenter;

/**
 * Launcher页对应的业务操作
 */
public class LauncherPresenter extends BasePresenter<ILauncherView>{
    public LauncherPresenter(ILauncherView mBaseView) {
        super(mBaseView);
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        mBaseView.initLineChar();
    }

    @Override
    public void onDestory() {
        super.onDestory();
        System.gc();//强制垃圾回收
    }
}
