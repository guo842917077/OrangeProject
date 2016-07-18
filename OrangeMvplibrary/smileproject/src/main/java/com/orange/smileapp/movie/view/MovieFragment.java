package com.orange.smileapp.movie.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orange.smileapp.R;
import com.orange.smileapp.movie.adapter.MovieAdapter;
import com.orange.smileapp.movie.presenter.MovieContract;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements MovieContract.IMovieView {
    private MovieContract.IMoviePresenter mPresenter;
    @Bind(R.id.recycle)
    public RecyclerView mRecycleView;
    private List<Integer> mImageDatas = new ArrayList<Integer>();
    private MovieAdapter mAdapter;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, null);
        ButterKnife.bind(this, view);
        mImageDatas.add(R.drawable.haizei);
        mImageDatas.add(R.drawable.haizei2);
        mImageDatas.add(R.drawable.huoying);
        mImageDatas.add(R.drawable.haizei2);
        mAdapter = new MovieAdapter(getActivity(), mImageDatas, new MovieAdapter.OnImageClickListener() {
            @Override
            public void onClickListener(View view) {
                startAnimations(view);
            }
        });
        mRecycleView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecycleView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void initToolbar() {

    }

    @Override
    public void createPresenter(MovieContract.IMoviePresenter presenter) {
        mPresenter = presenter;
    }

    private void startAnimations(View view) {
        ActivityOptionsCompat compat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                        view, "shareview");
        ActivityCompat.startActivity(getActivity(), new Intent(getActivity(), MovieActivity.class
        ), compat.toBundle());

    }
}
