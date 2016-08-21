package com.orange.smileapp.photo.view;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orange.smileapp.R;
import com.orange.smileapp.photo.adapter.PhotoAdapter;
import com.orange.smileapp.photo.model.PhotoModel;
import com.orange.smileapp.photo.presenter.PhotoContract;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * PhotoFragment
 */
public class PhotoFragment extends Fragment implements PhotoContract.PhotoView, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.recycle_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.refresh)
    SwipeRefreshLayout mRefreshView;
    private RecyclerView.LayoutManager mLayoutManager;
    private PhotoContract.PhotoPresenter mPresenter;
    private PhotoAdapter mAdapter;
    private PhotoModel mData;
    private Handler mHander = new Handler(Looper.myLooper());

    public PhotoFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, container, false);
        ButterKnife.bind(this, view);
        initComponent();
        return view;
    }

    public void initComponent() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRefreshView.setOnRefreshListener(this);
        mPresenter.loadPhotoData(mRecyclerView);
    }

    @Override
    public void loadWeatherData(final PhotoModel datas) {
        mHander.post(new Runnable() {
            @Override
            public void run() {
                mData = datas;
                Log.d("tag", "从view中给适配器加载数据 ：" + mData.comments.size());
                mAdapter = new PhotoAdapter(getActivity(), mData);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void createPresenter(PhotoContract.PhotoPresenter presenter) {
        if (presenter != null)
            mPresenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {

    }
}
