package com.orange.smileapp.dagger.module;

import com.orange.smileapp.dagger.scope.PhotoScope;
import com.orange.smileapp.photo.presenter.PhotoContract;

import dagger.Module;
import dagger.Provides;

@Module
public class PhotoModule {
    PhotoContract.PhotoView photoView;

    public PhotoModule(PhotoContract.PhotoView photoView) {
        this.photoView = photoView;
    }

    @PhotoScope
    @Provides
    public PhotoContract.PhotoView providePhotoView() {

        return photoView;
    }

}
