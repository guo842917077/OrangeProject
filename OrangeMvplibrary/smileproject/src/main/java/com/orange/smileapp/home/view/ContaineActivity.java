package com.orange.smileapp.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.orange.smileapp.R;
import com.orange.smileapp.SmileApplication;
import com.orange.smileapp.config.Contants;
import com.orange.smileapp.dagger.component.DaggerPhotoComponent;
import com.orange.smileapp.dagger.module.PhotoModule;
import com.orange.smileapp.movie.presenter.MoviePresenter;
import com.orange.smileapp.movie.view.MovieFragment;
import com.orange.smileapp.photo.presenter.PhotoPresenter;
import com.orange.smileapp.photo.view.PhotoFragment;
import com.orange.smileapp.weather.presenter.WeatherPresenter;
import com.orange.smileapp.weather.view.WeatherFragment;

import javax.inject.Inject;

/**
 * 负责接管从HomeFragment的Tab中切换的页面
 */
public class ContaineActivity extends AppCompatActivity {
    private String PAGE = "";
    private String TAG = ContaineActivity.class.getSimpleName();
    private Intent intent;

    /**
     * Fragment
     */
    @Inject
    public PhotoPresenter mPhotoPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_containe);
        initData();
    }

    private void initData() {
        intent = getIntent();
        PAGE = intent.getStringExtra(Contants.REPLACE_FRAGMENT);
        Log.e(TAG, "PAGER----" + PAGE);
        changeFragment(PAGE);
    }

    private void changeFragment(String page) {
        if (page.equals(""))
            return;
        else if (page.equals(Contants.PAGE_WEATHER)) {
            WeatherFragment mWeather=new WeatherFragment();
            replaceFragment(mWeather);
            //初始化presenter
            new WeatherPresenter(mWeather, this);
        } else if (page.equals(Contants.PAGE_MOVIE)) {
            MovieFragment mMovie=new MovieFragment();
            replaceFragment(mMovie);
            new MoviePresenter(this, mMovie);
        } else if (page.equals(Contants.PAGE_PHTOT)) {
            PhotoFragment fragment=new PhotoFragment();
            initComponent(fragment);
            replaceFragment(fragment);
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //添加Fragment的进入动画
        //transaction.setCustomAnimations(R.anim.anim_sild_down,0,R.anim.anim_sild_down,0)
        transaction.replace(R.id.frag_container, fragment)
                .commit();
    }
    private void initComponent(PhotoFragment photoFragment){
        //添加注入关系
        DaggerPhotoComponent.builder().appComponent(((SmileApplication)getApplication()).getAppComponent()).photoModule(new PhotoModule(photoFragment)).build().inject(this);
    }
}
