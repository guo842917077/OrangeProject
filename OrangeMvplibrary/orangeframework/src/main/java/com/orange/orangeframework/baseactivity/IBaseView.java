package com.orange.orangeframework.baseactivity;

import android.content.Context;

/**
 * 由BasePresenter持有，负责Presenter和Activity交互，
 * 拿到Activity的上下文对象.
 */
public interface IBaseView {
    public Context getContext();//拿到Activity的上下文对象
}
