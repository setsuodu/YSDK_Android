package com.tencent.tmgp.yybtestsdk;

import android.app.Activity;
import androidx.annotation.Nullable;

import com.tencent.ysdk.framework.common.ePlatform;

import java.lang.ref.WeakReference;

public class AppUtils {

    private static WeakReference<Activity> sActivityRef = null;

    public static void updateActivity(Activity activity) {
        sActivityRef = new WeakReference<>(activity);
    }

    public static  @Nullable
    Activity getCurActivity() {
        if (sActivityRef == null) {
            return null;
            }

        return sActivityRef.get();
    }

    private static int sCurPlatform = ePlatform.None.val();
    public static int getCurLoginPlatform() {
        return sCurPlatform;
    }
    public static void updateLoginPlatform(int platformValue) {
        sCurPlatform = platformValue;
    }

}
