package com.orange.orangeframework.utils.system;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.util.UUID;

/**
 * Created by Administrator on 2016/9/11.
 */
public class SystemUtils {
    /**
     * 锁屏
     */
    public static boolean isScreenLocked(Context context) {
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(context.KEYGUARD_SERVICE);
        return keyguardManager.inKeyguardRestrictedInputMode();
    }

    public static String getImieStatus(Context context) {
        String deviceId="";
        if (Build.VERSION.SDK_INT < 23) {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = tm.getDeviceId();
        }else{
            deviceId= UUID.randomUUID().toString();
        }
        return deviceId;
    }
}
