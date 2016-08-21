package com.orange.smileapp.photo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.orange.smileapp.R;
import com.orange.smileapp.photo.model.PhotoModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Photo的Adapter
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
    private Context mContext;
    private PhotoModel mData;

    public PhotoAdapter(Context context, PhotoModel data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_photo, parent, false));
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        holder.bind(position, mData.comments);
    }

    @Override
    public int getItemCount() {
        return mData.comments.size();
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.image)
        ImageView mImage;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(int position, List<PhotoModel.JianDanMeiziData> data) {
            if (data.get(position).pics.length > 0)
                Glide.with(mContext).load(data.get(position).pics[0]).into(mImage);
        }
    }
}
