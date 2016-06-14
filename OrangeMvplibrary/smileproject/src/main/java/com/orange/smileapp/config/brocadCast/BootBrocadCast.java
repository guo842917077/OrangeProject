package com.orange.smileapp.config.brocadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.orange.smileapp.config.APPConfig;

/**
 * 全局的监听器
 */
public class BootBrocadCast extends BroadcastReceiver {
    private String TAG = BootBrocadCast.this.getClass().getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        //改变监听的行为
        if (action.equals(APPConfig.ACTION_ALTER_DEBUG)) {
            APPConfig.DeBug = intent.getBooleanExtra(APPConfig.ACTION_ORDER, false);
            Log.d(TAG, "debug模式已经开启");
        }
    }
}
