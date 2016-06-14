package com.orange.smileapp.config.utils.utlis.commonutils;

import android.os.Looper;

/**
 * Created by Administrator on 2016/6/3.
 */
public class ThreadUtils {
    public static void checkUiThread() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException(
                    "Must be called from the main thread. Was: " + Thread.currentThread());
        }
    }
}
