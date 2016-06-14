package mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mvp.presenter.BasePresenter;

/**
 * Created by Administrator on 2016/5/31.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment {
    protected String TAG = this.getClass().getSimpleName();
    protected View viewRoot;//返回整个布局对象
    protected T mPresenter;//业务实例

    public abstract T createPresenter();//子类创建presenter方法

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();
        super.onCreate(savedInstanceState);
        onViewCreateBefore();
        onSetContentView();
        onViewCreated();
        setOnListener();
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

    /**
     * 在setContentView之后被调用
     */
    protected void onViewCreated() {
        mPresenter.onViewCreated();
    }


    protected void onSetContentView() {
        viewRoot = View.inflate(getActivity(), getContentViewResource(), null);
        initComponent(viewRoot);
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
