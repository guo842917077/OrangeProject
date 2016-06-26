package com.orange.smileapp.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.orange.smileapp.R;
import com.orange.smileapp.config.utils.SmileUtils;
import com.orange.smileapp.home.model.NavigationTab;

import java.util.List;

/**
 * 顶部GridView的适配器
 */
public class TagChooseGridViewAdapter2 extends BaseAdapter {

    private LayoutInflater inflater;
    private Context mContext;
    private List<NavigationTab> mData;
    private int index;
    private int itemSize;
    private OnTabItemClickListener onTabItemClickListener;

    public TagChooseGridViewAdapter2(Context context, List<NavigationTab> data, int index,OnTabItemClickListener callback) {
        this.inflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mData = data;
        this.index = index;
        itemSize = 4 * 2;
        onTabItemClickListener=callback;
    }

    @Override
    public int getCount() {
        return mData.size() > (index + 1) * itemSize ? itemSize : (mData.size() - index * itemSize);
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position + index * itemSize);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = this.inflater.inflate(R.layout.layout_tag_choose, null);
            holder.tagName = (TextView) convertView.findViewById(R.id.tag_name);
            holder.tagImage = (ImageView) convertView.findViewById(R.id.tag_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //设置正确的position
        final int pos = position + index * itemSize;
        holder.tagName.setText(mData.get(pos).getName());
        holder.tagName.setTypeface(SmileUtils.smileFontUtil(mContext, "fonts/LatoLatin-Light.ttf"));
        holder.tagImage.setImageResource(mData.get(pos).getIcon());
        holder.tagImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTabItemClickListener != null)
                    onTabItemClickListener.onItemClick(pos, mData.get(pos));
            }
        });
        return convertView;
    }


    private class ViewHolder {
        TextView tagName;
        ImageView tagImage;
    }

    public interface OnTabItemClickListener {
        public void onItemClick(int position, NavigationTab tab);
    }
}
