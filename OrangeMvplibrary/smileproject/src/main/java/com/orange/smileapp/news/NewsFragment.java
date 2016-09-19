package com.orange.smileapp.news;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.orange.smileapp.R;
import com.orange.smileapp.SmileApplication;
import com.orange.smileapp.config.utils.utlis.retrofit.RetrofitUtils;
import com.orange.smileapp.dagger.scope.FragmentScope;
import com.orange.smileapp.home.view.ContaineActivity;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import mvp.dagger.DaggerFragmentComponent;
import mvp.dagger.FragmentModule;
import mvp.view.BaseFragment;
import retrofit2.Retrofit;

public class NewsFragment extends BaseFragment<NewsPresenterImp> implements INewsView {

    private final String TAG = NewsFragment.class.getSimpleName();
    @Bind(R.id.web_news)
    WebView mWebPage;
    @Bind(R.id.toolbar_leftTv)
    TextView mTv_left;
    @Bind(R.id.toolbar_title)
    TextView mTv_title;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.img_lvtoolbar)
    ImageButton mImageBack;
    /**
     * 新闻 Activity  内部嵌套WebView 使用腾讯新闻
     */

    private String mNewsUrl = "http://www.news.qq.com";
    @FragmentScope
    @Inject
    public NewsPresenterImp mPresenter;

    public RetrofitUtils utils;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public void injectComponent() {
        DaggerFragmentComponent.builder().appComponent(((SmileApplication) getActivity()
                .getApplication()).getAppComponent())
                .fragmentModule(new FragmentModule(this))
                .build().inject(this);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RetrofitUtils utils = new RetrofitUtils.Builder()
                .addBaseUrl("https://api.heweather.com/x3/")
                .addOkHttpCache(true)
                .needCreateClient(true)
                .addRxFactory(true)
                .addOkhttpTimeOut(1)
                .addTimeFormat(TimeUnit.HOURS)
                .addCookie(true)
                .create(getActivity());
        Retrofit client = utils.createRetrofit();
        Log.e("NewsFragment", "retrofit : " + client);
    }


    @Override
    protected int getContentViewResource() {
        return R.layout.fragment_news;
    }

    @Override
    protected void setOnListener() {

    }

    @Override
    protected void initComponent(View view) {
        mToolbar.setTitle("");
        ((ContaineActivity) getActivity()).setSupportActionBar(mToolbar);
        ((ContaineActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((ContaineActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((ContaineActivity) getActivity()).getSupportActionBar().setDisplayShowCustomEnabled(true);
        mTv_left.setText("腾讯新闻");
        mTv_left.setTextSize(22);
    }

    @Override
    public void loadNews() {
        mWebPage.loadUrl(mNewsUrl);
    }

    @Override
    public void initWebSetting() {
        WebSettings webSettings = mWebPage.getSettings();
        mWebPage.requestFocus();
        webSettings.setJavaScriptEnabled(true);//是否支持js
        //设置适应屏幕
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        //支持缩放
        webSettings.setSupportZoom(true);
        //隐藏原生的缩放控件
        webSettings.setDisplayZoomControls(false);
        //设置内容是否重新布局
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //是否支持多屏
        webSettings.setSupportMultipleWindows(true);
        //设置缓存模式
        webSettings.setDomStorageEnabled(false);
        webSettings.setDatabaseEnabled(false);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setAppCacheEnabled(true);
        //设置可访问文件
        //webSettings.setAllowFileAccess(true);
        //当webview调用requestFocus时为webview设置节点
        //webSettings.setNeedInitialFocus(true);
        //支持自动加载图片
      /*  if (Build.VERSION.SDK_INT >= 19) {
            webSettings.setLoadsImagesAutomatically(true);
        } else {
            webSettings.setLoadsImagesAutomatically(false);
        }*/
        //webSettings.setNeedInitialFocus(true);
        //设置编码格式
        //设置缓存模式
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setAppCachePath(mWebPage.getContext().getCacheDir().getAbsolutePath());
    }

    @Override
    public void initWebClient() {
        mWebPage.setWebViewClient(new WebViewClient() {
            //加载开始
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            //加载完成
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            //是否在webview内加载新的页面
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //收到服务端错误时
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
