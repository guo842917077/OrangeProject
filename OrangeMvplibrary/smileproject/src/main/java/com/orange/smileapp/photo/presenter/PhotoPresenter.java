package com.orange.smileapp.photo.presenter;

import android.content.Context;
import android.util.Log;

import com.orange.smileapp.api.PhotoAPI;
import com.orange.smileapp.config.utils.utlis.retrofit.RetrofitFactory;
import com.orange.smileapp.photo.model.PhotoModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Photo的业务逻辑
 */
public class PhotoPresenter implements PhotoContract.PhotoPresenter {
    private String TAG = "PhotoPresenter";
    private PhotoContract.PhotoView mView;
    private Context mContext;
    private Semaphore mSemaphore = new Semaphore(1);
    private List<PhotoModel> mData = new ArrayList<>();

    @Inject
    public PhotoPresenter(Context context, PhotoContract.PhotoView view) {
        this.mContext = context;
        this.mView = view;
        mView.createPresenter(this);
    }

    @Override
    public void loadPhotoData() {
        Log.d(TAG, "执行了加载数据");
        try {
            mSemaphore.acquire();
            RetrofitFactory.photoInstance(PhotoAPI.BaseUrl).getPhotoList(1)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<PhotoModel>() {
                        @Override
                        public void onCompleted() {
                            Log.d(TAG, "加载数据完成" + mData.get(0).status);
                            mView.loadWeatherData(mData.get(0));
                            mSemaphore.release();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, e.toString());
                        }

                        @Override
                        public void onNext(PhotoModel photoModel) {
                            Log.d(TAG, "请求成功" + photoModel.status);
                            mData.add(photoModel);
                        }
                    });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {

    }
}
