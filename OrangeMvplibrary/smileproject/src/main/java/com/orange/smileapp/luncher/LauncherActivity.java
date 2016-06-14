package com.orange.smileapp.luncher;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orange.smileapp.R;
import com.orange.smileapp.login.view.LoginActivity;
import com.orange.smileapp.luncher.presenter.LauncherPresenter;
import com.orange.smileapp.luncher.view.ILauncherView;
import com.orange.smileapp.config.utils.SmileUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import io.codetail.widget.RevealFrameLayout;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.view.LineChartView;
import mvp.view.BaseActivity;

public class LauncherActivity extends BaseActivity<LauncherPresenter> implements ILauncherView {

    @Bind(R.id.ly)
    public LinearLayout mlayout_ly;
    @Bind(R.id.image)
    public ImageView mImage_image;
    @Bind(R.id.app_name)
    public TextView mTv_app_name;
    @Bind(R.id.loading_text)
    public TextView mTv_loading;
    @Bind(R.id.chart)
    public LineChartView mLineChar;
    @Bind(R.id.reveal)
    public RevealFrameLayout mReveal;
    public LineChartData mLineData;//表格绘制数据
    public boolean isLoadComplete = false;//页面是否加载加载完毕

    @Override
    protected LauncherPresenter createPresenter() {
        return new LauncherPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.onCreate(savedInstanceState);
        Typeface typefaceLatoLight = SmileUtils.smileFontUtil(this, "fonts/LatoLatin-Light.ttf");
        mTv_app_name.setTypeface(typefaceLatoLight);
        mTv_loading.setTypeface(typefaceLatoLight);
    }

    @Override
    protected void initComponent() {
        ButterKnife.bind(this);
    }

    @Override
    protected void registerListener() {

    }

    @Override
    protected int getContentViewResource() {
        return R.layout.activity_launcher;
    }

    //LineCharView的使用
    @Override
    public void initLineChar() {

        List<Line> lines = new ArrayList<>();
        //绘制线性图
        for (int i = 0; i < 1; ++i) {
            //绘制点
            List<PointValue> point = new ArrayList<>();
            point.add(new PointValue(0, 0));
            point.add(new PointValue(1, 15));
            point.add(new PointValue(2, 10));
            point.add(new PointValue(3, 23));
            point.add(new PointValue(3.5f, 48));
            point.add(new PointValue(5, 60));
            //绘制线
            Line line = new Line(point);
            line.setColor(Color.WHITE);
            line.setShape(ValueShape.CIRCLE);
            line.setCubic(false);
            line.setFilled(false);
            line.setHasLabels(false);
            line.setHasLabelsOnlyForSelected(false);
            line.setHasLines(true);
            line.setHasPoints(true);
            lines.add(line);
        }
        mLineData = new LineChartData(lines);
        mLineData.setBaseValue(Float.NEGATIVE_INFINITY);
        mLineChar.setLineChartData(mLineData);
    }

    @Override
    public void startLoadingAnimation() {
        // get the center for the clipping circle
        int[] location = new int[2];
        mImage_image.getLocationOnScreen(location);
        int cx = location[0] + dpToPx(24);
        int cy = location[1] + dpToPx(24);

        // get the final radius for the clipping circle
        int dx = Math.max(cx, mlayout_ly.getWidth() - cx);
        int dy = Math.max(cy, mlayout_ly.getHeight() - cy);
        float finalRadius = (float) Math.hypot(dx, dy);//返回两个数平方和的平方根，传入3,4 结果为9+16的平方根 5
        //io.codetail.animation的扩散效果
        SupportAnimator animator =
                ViewAnimationUtils.createCircularReveal(mlayout_ly, cx, cy, 0, finalRadius);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(3000);
        animator.start();
        animator.addListener(new SupportAnimator.AnimatorListener() {
            @Override
            public void onAnimationStart() {

            }
            @Override
            public void onAnimationEnd() {
                isLoadComplete = true;
                if (isLoadComplete) {
                    Intent intent = new Intent(LauncherActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
            @Override
            public void onAnimationCancel() {

            }

            @Override
            public void onAnimationRepeat() {

            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!isLoadComplete)
            startLoadingAnimation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestory();//垃圾回收
    }

    @Override
    public Context getContext() {
        return this;
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = LauncherActivity.this.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}
