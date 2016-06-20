package com.orange.smileapp.weather.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orange.smileapp.R;
import com.orange.smileapp.weather.adapter.WeatherAdapter;
import com.orange.smileapp.weather.model.WeatherModel;
import com.orange.smileapp.weather.presenter.WeatherContract;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 天气界面的Fragment
 */
public class WeatherFragment extends Fragment implements WeatherContract.WeatherView {
    @Bind(R.id.recycle)
    RecyclerView mRecycle;
    private WeatherContract.WeatherPresenter mPresenter;
    private List<WeatherModel> mData = new ArrayList<>();
    private WeatherAdapter mAdapter;
    private Handler mHander=new Handler(Looper.myLooper());
    public WeatherFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, view);
        initParams();
        mPresenter.loadWeatherData(mRecycle);
        return view;
    }


    @Override
    public void loadWeatherData(final List<WeatherModel> data) {
      mHander.post(new Runnable() {
          @Override
          public void run() {
              mData.addAll(data);
              mAdapter = new WeatherAdapter(mData.get(0), getActivity());
              mRecycle.setAdapter(mAdapter);
              mAdapter.notifyDataSetChanged();
          }
      });
    }

    @Override
    public void loadWeatherError() {
        //show empty view
    }

    @Override
    public void loadWeatherSuccess() {

    }

    @Override
    public void createPresenter(WeatherContract.WeatherPresenter presenter) {
        if (presenter != null)
            mPresenter = presenter;
    }

    private void initParams() {
        mRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
