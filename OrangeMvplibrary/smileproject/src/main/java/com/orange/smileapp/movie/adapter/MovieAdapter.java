package com.orange.smileapp.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.orange.smileapp.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * MovieAdapter;
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private Context mContext;
    private List<Integer> mDatas;
    private OnImageClickListener mListener;
    public MovieAdapter(Context mContext, List<Integer> mDrawables,OnImageClickListener listener) {
        this.mContext = mContext;
        this.mDatas = mDrawables;
        this.mListener=listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_image, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ((MovieAdapter.ViewHolder) holder).bind(mContext, mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.image)
        ImageView mIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClickListener(v);
                }
            });
        }

        public void bind(Context context, int id) {
            mIcon.setImageResource(id);
        }
    }

    public interface OnImageClickListener{
        public void onClickListener(View view);
    }
}
