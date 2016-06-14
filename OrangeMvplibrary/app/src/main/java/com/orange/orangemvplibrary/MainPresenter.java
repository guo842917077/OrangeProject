package com.orange.orangemvplibrary;

import mvp.presenter.BasePresenter;
import mvp.view.IBaseView;

/**
 * Created by Administrator on 2016/5/12.
 */
public class MainPresenter extends BasePresenter{
    public MainPresenter(IBaseView mBaseView) {
        super(mBaseView);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

    }
}
