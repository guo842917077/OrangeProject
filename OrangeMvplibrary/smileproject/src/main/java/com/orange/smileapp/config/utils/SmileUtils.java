package com.orange.smileapp.config.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * SmileApp的一些工具类
 */
public class SmileUtils {
    /**
     * android使用Typeface来自定义字体
     * 使用时出现的问题：Native typeface can not be made
     * 1.assets文件在main folder中，创建时右键点击mian-->folder--->assets folder
     * 将你自己的字体文件--->myfont.ttf拷入到文件夹中使用typeface即可
     * 2.在编译成apk后也可能存放在sd卡中，所以用createFile替代createFromAsset即可。
     */
    public static Typeface smileFontUtil(Context context, String fontPath) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontPath);
        return typeface;
    }
}
