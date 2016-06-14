package com.orange.smileapp.login.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.jakewharton.rxbinding.view.RxView;
import com.nineoldandroids.animation.Animator;
import com.orange.smileapp.R;
import com.orange.smileapp.login.presenter.LoginPresenter;
import com.orange.smileapp.main.view.MainActivity;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import mvp.view.BaseActivity;
import rx.functions.Action1;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView, View.OnClickListener {

    @Bind(R.id.btn_login)
    Button mBtnLogin;
    @Bind(R.id.img_logo)
    ImageView mImageLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.onCreate(savedInstanceState);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void initComponent() {
        ButterKnife.bind(this);
        //logo进入动画
        YoYo.with(Techniques.RollIn).duration(1000).playOn(mImageLogo);
    }

    @Override
    protected void registerListener() {
        mBtnLogin.setOnClickListener(this);
    }

    @Override
    protected int getContentViewResource() {
        return R.layout.activity_login;
    }

    @Override
    public void loginApp(String username, String password) {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public Context getContext() {
        return this;
    }


    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                //防止抖动
                RxView.clicks(mBtnLogin).throttleFirst(2000, TimeUnit.MILLISECONDS)
                        .subscribe(new Action1<Void>() {
                            @Override
                            public void call(Void aVoid) {
                                startLoginAnimator(mBtnLogin);
                            }
                        });
                break;
        }
    }

    private void startLoginAnimator(View view) {
        //放大的动画
        YoYo.with(Techniques.Pulse).duration(1000).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mPresenter.doLogin("xx", "xx");//执行输入的方法
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).playOn(view);
    }
}
