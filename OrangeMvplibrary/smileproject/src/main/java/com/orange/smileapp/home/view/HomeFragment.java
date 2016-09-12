package com.orange.smileapp.home.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orange.smileapp.R;
import com.orange.smileapp.config.APPConfig;
import com.orange.smileapp.config.Contants;
import com.orange.smileapp.config.weiget.AutoGridView;
import com.orange.smileapp.home.adapter.TabPageAdapter;
import com.orange.smileapp.home.adapter.TagChooseGridViewAdapter2;
import com.orange.smileapp.home.model.NavigationTab;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 主页Fragment
 */
public class HomeFragment extends Fragment {
    private String TAG = HomeFragment.class.getSimpleName();
    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    private TagChooseGridViewAdapter2 mTabAdapter;
    private TabPageAdapter mAdapter;
    private List<View> mView;
    private List<NavigationTab> mTabs;
    //传递给containerActivity的键
    private String WEATHER = "天气";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        initData(inflater);
        registerListener();
        return view;
    }

    public void initData(LayoutInflater inflater) {
        mTabs = new ArrayList<>();
        mTabs.addAll(APPConfig.TAB_HOME_DATA.get(APPConfig.HOME_GET_TAB));
        mView = new ArrayList<>();
        for (int index = 0; index < 2; index++) {
            mTabAdapter = new TagChooseGridViewAdapter2(getActivity(), mTabs, index, new TagChooseGridViewAdapter2.OnTabItemClickListener() {
                @Override
                public void onItemClick(int position, NavigationTab tab) {
                    switch (position) {
                        case 0:
                            Intent news = new Intent(getActivity(), ContaineActivity.class);
                            news.putExtra(Contants.REPLACE_FRAGMENT, Contants.PAGE_NEWS);
                            startActivity(news);
                            break;
                        case 1:
                            //测试回调是否生效
                            Intent intent = new Intent(getActivity(), ContaineActivity.class);
                            intent.putExtra(Contants.REPLACE_FRAGMENT, Contants.PAGE_WEATHER);
                            startActivity(intent);
                            break;
                        case 2:
                            Intent movie = new Intent(getActivity(), ContaineActivity.class);
                            movie.putExtra(Contants.REPLACE_FRAGMENT, Contants.PAGE_MOVIE);
                            startActivity(movie);
                            break;
                        case 6:
                            Intent photo = new Intent(getActivity(), ContaineActivity.class);
                            photo.putExtra(Contants.REPLACE_FRAGMENT, Contants.PAGE_PHTOT);
                            startActivity(photo);
                            break;
                    }
                }
            });
            //每个页面都是inflate出一个新实例
            View view = inflater.inflate(R.layout.layout_gridview, null);
            AutoGridView gridView = (AutoGridView) view.findViewById(R.id.gridview);
            gridView.setAdapter(mTabAdapter);
            mView.add(gridView);
        }
        mAdapter = new TabPageAdapter(mView);
        mViewPager.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void registerListener() {

    }
}
