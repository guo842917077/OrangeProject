package com.orange.smileapp.movie.presenter;

import android.content.Context;

/**
 * Movie的presenter类
 */
public class MoviePresenter implements MovieContract.IMoviePresenter {
    private MovieContract.IMovieView mView;
    private Context mContext;

    public MoviePresenter(Context context, MovieContract.IMovieView iMovieView) {
        this.mView = iMovieView;
        this.mContext = context;
        this.mView.createPresenter(this);
    }

    @Override
    public void loadMovieData() {

    }

    @Override
    public void start() {

    }
}
