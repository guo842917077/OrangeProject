package com.orange.smileapp.weather.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ValueAnimator;
import com.orange.smileapp.R;
import com.orange.smileapp.config.utils.utlis.deviceutils.DeviceUtils;
import com.orange.smileapp.weather.adapter.WeatherAdapter;
import com.orange.smileapp.weather.anim.ItemDelayAnimator;
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
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mToolbar;
    @Bind(R.id.mFab)
    FloatingActionButton mFab;
    private int mToolbarHeight;
    private WeatherContract.WeatherPresenter mPresenter;
    private List<WeatherModel> mData = new ArrayList<>();
    private WeatherAdapter mAdapter;
    private Handler mHander = new Handler(Looper.myLooper());

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
        return view;
    }

    /**
     * 使Toolbar做出回收的动画
     */
    @Override
    public void collapseToolbar() {
        int targetHeight = 0;
        TypedValue value = new TypedValue();
        if (getActivity().getTheme().resolveAttribute(android.R.attr.actionBarSize,value,true)){
            //目标高度是系统默认的toolbar高
            targetHeight=TypedValue.complexToDimensionPixelSize(value.data, getResources().getDisplayMetrics());
            Log.d("WeatherFragment","is get Height "+targetHeight);
        }
        //从当前toolbar的高到目标toolbar的高之间计算动画值
        ValueAnimator valueAnimator=ValueAnimator.ofInt(mToolbarHeight, DeviceUtils.dip2px(getActivity(),120));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //拿到Toolbar的布局参数
                ViewGroup.LayoutParams lp = mToolbar.getLayoutParams();
                lp.height =(Integer) animation.getAnimatedValue();//将每次产生的值 作为参数赋给toolbar，让toolbar不断改变高度
                mToolbar.setLayoutParams(lp);
                mToolbar.invalidate();
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.start();
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //当toolbar动画完成时加载数据
                mPresenter.loadWeatherData(mRecycle);
                YoYo.with(Techniques.ZoomIn).delay(600).duration(400).playOn(mFab);
              /*  // Animate fab
                ViewCompat.animate(mFab).setStartDelay(600)
                        .setDuration(400).scaleY(1).scaleX(1).start();*/
            }
        });
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
        //延时进入效果
        mRecycle.setItemAnimator(ItemDelayAnimator.slidein());
        /**
         * getViewTreeObserver方法是view对象发生改变时的观察者对象
         * 我们无法在一个view 在onCreate时拿到它的宽高，这是因为View组件布局要在onResume回调后完成
         * 但是我们可以通过监听它的改变，从而拿到一个view的宽高
         * addOnPreDrawListener：
         * 当一个视图树将要绘制时，所要调用的回调函数的接口类
         */
        mToolbar.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mToolbar.getViewTreeObserver().removeOnPreDrawListener(this);
                final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                mToolbar.measure(widthSpec, heightSpec);
                mToolbarHeight = mToolbar.getHeight();
                /**
                 * 执行Toolbar动画
                 */
                collapseToolbar();
                return true;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
