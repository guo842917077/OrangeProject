package mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import mvp.presenter.BasePresenter;

/**
 * Activity的基类，负责一些编码位置的规范
 * abstract抽象类的使用：可以有抽象方法，可以有public的对象
 */
public abstract class BaseActivity<T extends BasePresenter> extends FragmentActivity {
    protected String TAG = this.getClass().getSimpleName();
    protected T mPresenter;//在Base中实现，并初始化，子类只需实现createPresenter(),直接调用mPresenter对象即可

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();//实例化Presenter的操作
        onBeforeSetContentView();
        onSetContentView();
        registerListener();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestory();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    /**
     * 实例化presenter对象
     *
     * @return
     */
    protected abstract T createPresenter();

    /**
     * 在setContentView之前被调用
     */
    protected void onBeforeSetContentView() {
        mPresenter.onViewCreateBefore();
    }

    //是否分发事件


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //如果父布局派发了事件，子view拦截该事件
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();//获取焦点所在位置的控件
            if (isShouldHideInput(view, ev)) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }

        return onTouchEvent(ev);
    }

    //初始化组件
    protected abstract void initComponent();

    //注册事件的地方
    protected abstract void registerListener();

    //设置布局
    private void onSetContentView() {
        setContentView(getContentViewResource());
        initComponent();
    }

    /*
     *设置布局资源
     */
    protected abstract int getContentViewResource();

    /**
     * 解决软键盘的显示隐藏问题
     */
    public boolean isShouldHideInput(View view, MotionEvent event) {
        if (view != null && (view instanceof EditText)) {
            int[] leftTop = {0, 0};//左上角的位置
            //算出整个Edittext所占据的位置
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + view.getHeight();
            int right = left + view.getWidth();
            //如果你点击的位置在Edittext中
            if (event.getX() > left && event.getX() < right && event.getY() > top && event.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

}
