package com.orange.orangemvplibrary;

import android.os.Bundle;

import mvp.presenter.BasePresenter;
import mvp.view.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initComponent() {

    }

    @Override
    protected void registerListener() {

    }

    @Override
    protected int getContentViewResource() {
        return 0;
    }
}
