package com.orange.smileapp.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.orange.smileapp.R;
import com.orange.smileapp.config.Contants;
import com.orange.smileapp.weather.presenter.WeatherPresenter;
import com.orange.smileapp.weather.view.WeatherFragment;

/**
 * 负责接管从HomeFragment的Tab中切换的页面
 */
public class ContaineActivity extends AppCompatActivity {
    private String PAGE = "";
    private String TAG = ContaineActivity.class.getSimpleName();
    private Intent intent;

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
            WeatherFragment weather = new WeatherFragment();
            replaceFragment(weather);
            new WeatherPresenter(weather, this);//初始化presenter
        }

    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frag_container, fragment);
        transaction.commit();
    }

}
