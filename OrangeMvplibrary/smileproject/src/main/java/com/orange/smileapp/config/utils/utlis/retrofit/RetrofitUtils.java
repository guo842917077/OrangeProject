package com.orange.smileapp.config.utils.utlis.retrofit;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 使用配置的方法构建Retrofit
 */
public class RetrofitUtils {
    private String mBaseUrl = "";
    private volatile static OkHttpClient mOkHttpClient = null;
    private boolean mNeedRxFactroy = false;//是否支持rxjava
    private boolean mNeedCache = false;//是否缓存
    private boolean mNeedCreateClient = false;//是否构建新的okhttp
    private int mTimeOut = 3000;//请求超时时间
    private TimeUnit mTimeFormat = TimeUnit.SECONDS;//支持的时间格式
    private Map<String, String> mInterceptHeader;
    private Context mContext;
    private File mCacheFile = null;
    private Retrofit mRetrofit;
    private boolean mNeedCookJar;

    public RetrofitUtils(Builder builder) {
        this.mInterceptHeader = builder.interceptHeader;
        this.mNeedCache = builder.needCache;
        this.mNeedRxFactroy = builder.needRxFactroy;
        this.mTimeOut = builder.timeout;
        this.mNeedCreateClient = builder.needCreateClient;
        this.mTimeFormat = builder.timeFormat;
        this.mContext = builder.mContext;
        this.mRetrofit = builder.retrofitClient;
        this.mBaseUrl = builder.url;
        this.mNeedCookJar = builder.needCookie;
    }

    public Retrofit createRetrofit() {
        return mRetrofit;
    }

    //负责构建Retroift
    public static class Builder {
        private boolean needRxFactroy = false;
        private boolean needCache = false;
        private int timeout = 3000;
        private Map<String, String> interceptHeader;
        private boolean needCreateClient = false;
        private TimeUnit timeFormat = TimeUnit.SECONDS;
        private String url = "";
        private Context mContext;
        private File cacheFile = null;
        private String TAG = "RetrofitUtils";
        private Retrofit retrofitClient;
        private boolean needCookie = false;

        public Builder addRxFactory(boolean isNeed) {
            this.needRxFactroy = isNeed;
            return this;
        }

        public Builder addTimeFormat(TimeUnit unit) {
            this.timeFormat = unit;
            return this;
        }

        public Builder addCookie(boolean needCookie) {
            this.needCookie = needCookie;
            return this;
        }

        public Builder addOkHttpCache(boolean isCache) {
            this.needCache = isCache;
            return this;
        }

        public Builder addOkhttpTimeOut(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder needCreateClient(boolean isNeed) {
            this.needCreateClient = isNeed;
            return this;
        }

        public Builder addBaseUrl(String baseUrl) {
            this.url = baseUrl;
            return this;
        }


        public Builder addOkhttpCacheFile(File cacheFile) {
            this.cacheFile = cacheFile;
            return this;
        }

        public Builder addOkHttpCookJar(boolean isCookJar) {
            this.needCookie = isCookJar;
            return this;
        }

        public RetrofitUtils create(Context context) {
            this.mContext = context;
            if (url.equals("")) {
                throw new NullPointerException("you should have a baseUrl please use addBaseUrl");
            }
            Retrofit.Builder retrofit = new Retrofit.Builder();
            Log.e(TAG, "needCreateClient : " + needCreateClient);
            //所有OkHttp的配置
            if (needCreateClient) {
                OkHttpClient.Builder client = new OkHttpClient.Builder();
                Cache cache = null;
                Log.e(TAG, "needCache : " + needCache);
                if (needCache) {
                    Log.e(TAG, "cacheFile : " + cacheFile);
                    if (cacheFile != null) {
                        cache = new Cache(cacheFile, 10 * 1024 * 1024);
                    } else {
                        cache = new Cache(new File(context.getCacheDir(), "Okhttpcache"), 10 * 1024 * 1024);
                        Log.e(TAG, "default cach file : " + cache);
                    }
                    client.cache(cache);
                }
                //设置cookie
                if (needCookie) {
                    if (mContext == null) {
                        throw new NullPointerException("you should set a context");
                    } else {
                        client.cookieJar(new NovateCookieManger(context));
                        Log.e(TAG, "add Cookie jar ");
                    }
                }
                Log.e(TAG, "timeout : " + timeout);
                Log.e(TAG, "timeFormat : " + timeFormat);
                client.connectTimeout(timeout, timeFormat);
                mOkHttpClient = client.build();
                Log.e(TAG, "mOkHttpClient : " + mOkHttpClient);
                retrofit.client(mOkHttpClient);
            }
            if (needRxFactroy) {
                retrofit.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
            }
            retrofit.addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(url);
            retrofitClient = retrofit.build();
            Log.e(TAG, "retrofit client : " + retrofitClient);
            return new RetrofitUtils(this);
        }
    }
}
