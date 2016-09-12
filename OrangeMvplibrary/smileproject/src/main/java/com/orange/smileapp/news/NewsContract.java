package com.orange.smileapp.news;

import googlemvp.GoogleBasePresenter;
import googlemvp.GoogleBaseView;

/**
 * Created by Administrator on 2016/9/10.
 */
public class NewsContract {
    public interface NewsPresenter extends GoogleBasePresenter {

    }

    public interface NewsView extends GoogleBaseView<NewsPresenterImp> {
        void loadNews();
        void initWebSetting();
        void initWebClient();
    }
}
