package com.orange.smileapp.config;

import android.util.SparseArray;

import com.orange.smileapp.R;
import com.orange.smileapp.home.model.NavigationTab;

import java.util.ArrayList;
import java.util.List;


public class APPConfig {
    //网站Key
    public static String KEY_WEATHER = "fe1120411a704783ba788dce11a6d152";
    //Debug数据
    public static boolean DeBug = false;
    //监听行为
    public static String ACTION_ALTER_DEBUG = "action_alter_debug";
    public static String ACTION_ORDER = "order";
    //share文件
    public static String SHARE_PATH = "smile";
    public static float DISTANCE_PAGER_SILDE = 3.0f;//viewpager的最小滑动距离
    public static SparseArray<List<NavigationTab>> TAB_HOME_DATA = new SparseArray<>();
    public static int HOME_GET_TAB = 1;

    static {
        List<NavigationTab> tabs = new ArrayList<NavigationTab>();
        tabs.add(new NavigationTab("新闻", R.mipmap.ic_information));
        tabs.add(new NavigationTab("天气", R.mipmap.ic_weather));
        tabs.add(new NavigationTab("电影", R.mipmap.ic_film));
        tabs.add(new NavigationTab("朋友", R.mipmap.ic_friend));
        tabs.add(new NavigationTab("理财", R.mipmap.ic_fund));
        tabs.add(new NavigationTab("游戏", R.mipmap.ic_game));
        tabs.add(new NavigationTab("美图", R.mipmap.ic_hobby));
        tabs.add(new NavigationTab("居家", R.mipmap.ic_home));
        tabs.add(new NavigationTab("水果", R.mipmap.ic_fruit));
        TAB_HOME_DATA.append(HOME_GET_TAB, tabs);
    }
}
