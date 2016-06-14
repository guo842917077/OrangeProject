package com.orange.smileapp.luncher.view;

import mvp.view.IBaseView;

/**
 *LaucherActivity对应的页面操作
 */
public interface ILauncherView extends IBaseView{
    public void initLineChar();//初始化线性表
    public void startLoadingAnimation();//加载页面的动画
}
