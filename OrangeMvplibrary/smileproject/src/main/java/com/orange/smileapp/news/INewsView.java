package com.orange.smileapp.news;

import mvp.view.IBaseView;

/**
 * Created by Administrator on 2016/9/12.
 */
public interface INewsView extends IBaseView {
    void loadNews();
    void initWebSetting();
    void initWebClient();
}
