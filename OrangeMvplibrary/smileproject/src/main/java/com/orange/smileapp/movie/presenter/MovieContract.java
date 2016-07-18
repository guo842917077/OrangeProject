package com.orange.smileapp.movie.presenter;

import googlemvp.GoogleBasePresenter;
import googlemvp.GoogleBaseView;

/**
 * 电影的契约类
 */
public class MovieContract {
    public interface IMoviePresenter extends GoogleBasePresenter {
        public void loadMovieData();
    }

    public interface IMovieView extends GoogleBaseView<IMoviePresenter> {
        public void initToolbar();
    }
}
