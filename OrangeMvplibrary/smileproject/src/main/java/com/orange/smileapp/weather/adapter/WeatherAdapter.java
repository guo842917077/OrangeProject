package com.orange.smileapp.weather.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orange.smileapp.R;
import com.orange.smileapp.weather.model.WeatherModel;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Weather页面的adapter
 */
public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String TAG = WeatherAdapter.class.getSimpleName();
    private Context mContext;
    private final int TYPE_ONE = 0;
    private final int TYPE_TWO = 1;
    private WeatherModel mWeathers;

    public WeatherAdapter(WeatherModel mWeathers, Context mContext) {
        this.mWeathers = mWeathers;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ONE:
                return new NowWeatherViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_temperature, parent, false));
            case TYPE_TWO:
                return new HourWeatherViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_hour, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (position) {
            case TYPE_ONE:
                ((NowWeatherViewHolder) holder).bind(mContext, mWeathers);
                break;
            case TYPE_TWO:
                ((HourWeatherViewHolder) holder).bind(mContext, mWeathers);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == TYPE_ONE)
            return TYPE_ONE;
        else if (position == TYPE_TWO)
            return TYPE_TWO;

        return super.getItemViewType(position);
    }

    /**
     * 当前天气情况
     */
    class NowWeatherViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.weather_icon)
        ImageView weatherIcon;
        @Bind(R.id.temp_flu)
        TextView tempFlu;
        @Bind(R.id.temp_max)
        TextView tempMax;
        @Bind(R.id.temp_min)
        TextView tempMin;
        @Bind(R.id.temp_pm)
        TextView tempPm;
        @Bind(R.id.temp_quality)
        TextView tempQuality;
        @Bind(R.id.cardView)
        CardView cardView;

        public NowWeatherViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Context context, WeatherModel weather) {
            try {
                tempFlu.setText(String.format("%s℃", weather.now.tmp));
                tempMax.setText(
                        String.format("↑ %s °", weather.dailyForecast.get(0).tmp.max));
                tempMin.setText(
                        String.format("↓ %s °", weather.dailyForecast.get(0).tmp.min));
                tempPm.setText(TextUtils.concat("PM25： ", weather.aqi.city.pm25));
                tempQuality.setText(TextUtils.concat("空气质量： ", weather.aqi.city.qlty));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //每小时的天气情况
    class HourWeatherViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout item;
        private TextView[] mClock = new TextView[mWeathers.hourlyForecast.size()];
        private TextView[] mTemp = new TextView[mWeathers.hourlyForecast.size()];
        private TextView[] mHumidity = new TextView[mWeathers.hourlyForecast.size()];
        private TextView[] mWind = new TextView[mWeathers.hourlyForecast.size()];

        public HourWeatherViewHolder(View itemView) {
            super(itemView);
            item = (LinearLayout) itemView.findViewById(R.id.item_hour_info_linearlayout);
            for (int i = 0; i < mWeathers.hourlyForecast.size(); i++) {
                View view = View.inflate(mContext, R.layout.item_hour_weather, null);
                mClock[i] = (TextView) view.findViewById(R.id.one_clock);
                mTemp[i] = (TextView) view.findViewById(R.id.one_temp);
                mHumidity[i] = (TextView) view.findViewById(R.id.one_humidity);
                mWind[i] = (TextView) view.findViewById(R.id.one_wind);
                item.addView(view);
            }
        }

        public void bind(Context context, WeatherModel weather) {

            try {
                for (int i = 0; i < weather.hourlyForecast.size(); i++) {
                    //s.subString(s.length-3,s.length);
                    //第一个参数是开始截取的位置，第二个是结束位置。
                    String mDate = weather.hourlyForecast.get(i).date;
                    mClock[i].setText(
                            mDate.substring(mDate.length() - 5, mDate.length()));
                    mTemp[i].setText(
                            String.format("%s°", weather.hourlyForecast.get(i).tmp));
                    mHumidity[i].setText(
                            String.format("%s%%", weather.hourlyForecast.get(i).hum)
                    );
                    mWind[i].setText(
                            String.format("%sKm", weather.hourlyForecast.get(i).wind.spd)
                    );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
