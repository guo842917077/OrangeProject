package com.orange.smileapp.main.view;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.gigamole.library.NavigationTabBar;
import com.orange.smileapp.R;
import com.orange.smileapp.config.APPConfig;
import com.orange.smileapp.config.brocadCast.BootBrocadCast;
import com.orange.smileapp.config.utils.utlis.commonutils.FragmentViewPagerAdapter;
import com.orange.smileapp.config.weiget.MyViewPager;
import com.orange.smileapp.home.view.HomeFragment;
import com.orange.smileapp.main.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import mvp.view.BaseComponetActivity;

public class MainActivity extends BaseComponetActivity<MainPresenter> implements IMainView {

    @Bind(R.id.viewpager_fragment)
    MyViewPager mViewPager;
    @Bind(R.id.ntb_bottom)
    NavigationTabBar mNativegationTab;
    @Bind(R.id.wrapper_ntb_bottom)
    FrameLayout mFrameContain;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.img_lvtoolbar)
    ImageButton mImageLeftbar;
    @Bind(R.id.toolbar_title)
    TextView mToolbarTitle;
    private FragmentViewPagerAdapter mViewPagerAdapter;
    private final List<Fragment> list_fragment = new ArrayList<>();
    private BootBrocadCast mBrocadCastRecieve;//全局的监听

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.onCreate(savedInstanceState);
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void initComponent() {
        ButterKnife.bind(this);
        //全局的监听
        //初始化viewpager content
        list_fragment.add(new HomeFragment());
        list_fragment.add(new HomeFragment());
        list_fragment.add(new HomeFragment());
        list_fragment.add(new HomeFragment());
        list_fragment.add(new HomeFragment());

        mViewPagerAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), mViewPager, list_fragment);
        mViewPager.setAdapter(mViewPagerAdapter);
        final ArrayList<NavigationTabBar.Model> mModels = new ArrayList<>();
        //初始化TabBar
        mModels.add(new NavigationTabBar.Model.Builder(
                getResources().getDrawable(R.mipmap.ic_smile_gray),
                getResources().getColor(R.color.colorAccent))
                .selectedIcon(getResources().getDrawable(R.mipmap.ic_smile))
                .title("")
                .badgeTitle("NTB")
                .build());
        mModels.add(new NavigationTabBar.Model.Builder(
                getResources().getDrawable(R.mipmap.ic_smile_gray),
                getResources().getColor(R.color.colorPrimary))
                .selectedIcon(getResources().getDrawable(R.mipmap.ic_smile))
                .title("")
                .build());
        mModels.add(new NavigationTabBar.Model.Builder(
                getResources().getDrawable(R.mipmap.ic_smile_gray),
                getResources().getColor(R.color.blue_light))
                .selectedIcon(getResources().getDrawable(R.mipmap.ic_smile))
                .title("")
                .build());
        mModels.add(new NavigationTabBar.Model.Builder(
                getResources().getDrawable(R.mipmap.ic_smile_gray),
                getResources().getColor(R.color.blue_light))
                .selectedIcon(getResources().getDrawable(R.mipmap.ic_smile))
                .title("")
                .build());
        mModels.add(new NavigationTabBar.Model.Builder(
                getResources().getDrawable(R.mipmap.ic_smile_gray),
                getResources().getColor(R.color.blue_light))
                .selectedIcon(getResources().getDrawable(R.mipmap.ic_smile))
                .title("")
                .build());
        mNativegationTab.setModels(mModels);
        // mNativegationTab.setCornersRadius(60);
        mNativegationTab.setViewPager(mViewPager);

        //给NTB设置背景颜色
        mNativegationTab.post(new Runnable() {
            @Override
            public void run() {
                final View background = findViewById(R.id.bg_ntb_bottom);
                background.getLayoutParams().height = (int) mNativegationTab.getBarHeight();
                background.requestLayout();
            }
        });
    }

    @Override
    protected void registerListener() {

    }

    @Override
    protected int getContentViewResource() {
        return R.layout.activity_main;
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void initToobar() {
        //设置标题栏
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        mImageLeftbar.setBackgroundResource(R.mipmap.ic_function);
        mImageLeftbar.setVisibility(View.VISIBLE);
        mToolbarTitle.setText(R.string.action_title);
    }

    //注册监听
    @Override
    protected void registerBroadCast() {
        super.registerBroadCast();
        mBrocadCastRecieve = new BootBrocadCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction(APPConfig.ACTION_ALTER_DEBUG);//DEBUG监听
        registerReceiver(mBrocadCastRecieve, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBrocadCastRecieve);
    }
}
