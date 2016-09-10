package com.orange.smileapp.photo.presenter;

import com.orange.smileapp.photo.model.PhotoModel;

import googlemvp.GoogleBasePresenter;
import googlemvp.GoogleBaseView;

/**
 * Photo对应的Presenter类
 */
public class PhotoContract {
    public interface PhotoPresenter extends GoogleBasePresenter{
        void loadPhotoData();
    }
    public interface PhotoView extends GoogleBaseView<PhotoPresenter>{
        void loadWeatherData(PhotoModel datas);
    }
}
