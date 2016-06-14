package com.orange.smileapp.login.presenter;

import android.os.Bundle;

import com.orange.smileapp.login.view.ILoginView;

import mvp.presenter.BasePresenter;

/**
 * Login页面对应的业务处理
 */
public class LoginPresenter extends BasePresenter<ILoginView> {

    public LoginPresenter(ILoginView mBaseView) {
        super(mBaseView);
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
    }

    @Override
    public void onDestory() {
        super.onDestory();
    }

    public void doLogin(String username,String password) {
        mBaseView.loginApp(username,password);
    }
}
