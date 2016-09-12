package mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orange.smileapp.dagger.scope.FragmentScope;

import javax.inject.Inject;

import butterknife.ButterKnife;
import mvp.presenter.BaseFragmentPresenter;

/**
 * Created by Administrator on 2016/5/31.
 */
public abstract class BaseFragment<T extends BaseFragmentPresenter> extends Fragment implements IBaseView {
    protected String TAG = this.getClass().getSimpleName();
    protected View viewRoot;//返回整个布局对象

    @FragmentScope
    @Inject
    T mPresenter;//业务实例

    /**
     * 负责对象的注入
     */
    public abstract void injectComponent();//使用Dagger2注入依赖

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectComponent();
        onViewCreateBefore();
        onSetContentView();
        setOnListener();
        Log.d(TAG, "hhhh oncreate presenter is : " + mPresenter);
        if (mPresenter != null)
            mPresenter.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return viewRoot;
    }

    /**
     * 在onCreateView中被调用
     */
    protected void onViewCreateBefore() {
        mPresenter.onViewCreateBefore();
    }

    /**
     * 在onCreateView中调用
     */
    protected void initComponent(View view) {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //对全局的控件进行注入
        ButterKnife.bind(this, view);
        initComponent(viewRoot);
        mPresenter.attchView(this);
        mPresenter.onViewCreated();
    }

    protected void onSetContentView() {
        viewRoot = View.inflate(getActivity(), getContentViewResource(), null);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onDestroy() {
        mPresenter.onDestory();
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    protected abstract int getContentViewResource();

    protected abstract void setOnListener();

    public void addFragment(Fragment fragment) {
        ViewGroup parentView = (ViewGroup) getView().getParent();
        getFragmentManager().beginTransaction().add(parentView.getId(), fragment).commitAllowingStateLoss();
    }

    public void replaceFragment(Fragment fragment) {
        ViewGroup parentView = (ViewGroup) getView().getParent();
        getFragmentManager().beginTransaction().replace(parentView.getId(), fragment).commitAllowingStateLoss();
    }
}
