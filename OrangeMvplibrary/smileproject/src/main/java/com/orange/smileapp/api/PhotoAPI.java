package com.orange.smileapp.api;

import com.orange.smileapp.photo.model.PhotoModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 照片的API
 */
public interface PhotoAPI {
    String BaseUrl= "http://jandan.net";
    @GET("/?oxwlxojflwblxbsapi=jandan.get_ooxx_comments")
    Observable<PhotoModel> getPhotoList(@Query("page") int page);
}
