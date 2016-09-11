package com.orange.orangeframework.basepresenter;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.orange.orangeframework.baseactivity.IBaseView;

import java.lang.ref.WeakReference;


/**
 * Presenter基类 持有UIView对象 以及对应的生命周期方法，用来再不同的生命周期中处理不同的业务
 * 拥有生命周期方法，对应Activity的生命周期，在对应的生命周期中做指定的业务。
 * 1.定义泛型接口 指定泛型T的上限为IBaseView，即：T所传入的参数只能是IBaseView体系下的类
 * 2.持有对应Activity生命周期的方法，在这里面做具体的业务逻辑
 */
public abstract class BasePresenter<T extends IBaseView> {
    public String TAG = this.getClass().getSimpleName();
    /**
     * 对应Activity的上下文环境，集成BasePresenter的类可以访问
     * 负责消息的发送
     */
    private BaseHandler mBaseHandler;
    protected T mBaseView;

    public BasePresenter(T mBaseView) {
        this.mBaseView = mBaseView;
    }

    /**
     * 具体对应生命周期的方法
     * @return
     */
    public void onViewCreateBefore(){}

    /**
     * 对应onCreate方法，保存的数据
     * @param saveInstanceState
     */
    public void onCreate(Bundle saveInstanceState){}
    /**
     * 当布局创建完毕
     */
    public void onViewCreated(){}
    /**
     * 对应onResume方法
     */
    public void onResume(){}
    /**
     * 对应onPause方法
     */
    public void onPause(){}

    /**
     * 对应onDestory方法
     */
    public void onDestory(){}

    public Context getContext(){
        return mBaseView.getContext();
    }
    /**
     * 初始化Handler，初始化之后可以调用handleMessage方法
     * 以及其他的方法
     */
    protected void initHandler() {
        mBaseHandler = new BaseHandler(this);
    }

    protected Handler getHandler() {
        if (mBaseHandler != null) {
            return mBaseHandler;
        } else {
            throw new NullPointerException("请先调用---->initHandler()方法初始化handler");
        }
    }

    /**
     * 直接执行任务
     * @param thread 任务
     */
    protected void postRunnable(Runnable thread){
        if (mBaseHandler!=null){
            mBaseHandler.postDelayed(thread,0);
        }else {
            initHandler();
            mBaseHandler.postDelayed(thread,0);
        }
    }
    /**
     * 负责延时发送消息
     * @param thread 延时执行任务
     * @param delayedTime 延时发送的时间
     */
    protected void postRunnableDelayed(Runnable thread,long delayedTime){
        if (mBaseHandler!=null){
            mBaseHandler.postDelayed(thread,delayedTime);
        }else{
            initHandler();
            mBaseHandler.postDelayed(thread,delayedTime);
        }
    }
    /**
     * 负责拿到通过getHandler.sendMessage发送的消息
     * 在Handler中的handlerMessage方法中被调用，去做具体的任务
     */
    protected void takeMessageDoSomething(Message msg) {
    }

    /**
     * 1.负责接收handler.sendMessage方法接收到的消息
     * 使用弱引用的方式持有Activity对象，防止对象销毁时，handler中仍然持有BasePresenter的引用。
     * 1.强引用：使用最普遍的引用，通过new出来的对象为强引用
     * 2.软引用: 如果是用软引用持有，如果内存足够，垃圾就不回收，如果内存不足，则回收，并且只要垃圾回收没有回收它，该对象就可以被程序使用。
     * 软引用可以和一个引用队列一起使用ReferenceQueue
     * 3.弱引用：
     */
    public static class BaseHandler extends Handler {
        private final WeakReference<BasePresenter> reference;

        public BaseHandler(BasePresenter ref) {
            Log.e("BasePresenter", "检测Hander中持有的对象类型----->" + ref.getClass().getSimpleName());
            reference = new WeakReference<BasePresenter>(ref);

        }

        @Override
        public void handleMessage(Message msg) {
            BasePresenter presenter = reference.get();
            if (presenter != null) {
                presenter.takeMessageDoSomething(msg);
            }else{
                throw new NullPointerException("请先调用initHandler()方法----->");
            }
        }
    }
}
