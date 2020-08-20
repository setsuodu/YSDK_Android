package com.tencent.tmgp.yybtestsdk.api;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;

import com.tencent.tmgp.yybtestsdk.AppUtils;
import com.tencent.ysdk.module.icon.IconApi;
import com.tencent.ysdk.module.immersiveicon.ImmersiveIconApi;

/**
 * 图标相关的接口调用示例
 * <p>
 * create by stringwu 05/21/2019
 */
public class IconDemoApi {

    public static String execute(@IDemoApiType.SUBTYPE_ICON int subType, String extraParams) {
        switch (subType) {
            case IDemoApiType.ICON_SHOW:
                return showIcon();
            case IDemoApiType.ICON_HIDE:
                return hideIcon();
            case IDemoApiType.ICON_CHANGE_ORIENTATION:
                return changeOrientation();
            case IDemoApiType.ICON_PERFORM_FEATURE_V:
                return performFeatureV();
            case IDemoApiType.ICON_PERFORM_FEATURE_BBS:
                return performFeatureBBS();
            case IDemoApiType.ICON_PERFORM_FEATURE_ACTION:
                return performFeatureAction();
            default:
                throw new IllegalArgumentException("not support type:" + subType);

        }

    }

    private static String showIcon() {
        IconApi.getInstance().loadIcon();
        return "";
    }

    private static String hideIcon() {
        IconApi.getInstance().hideIcon();
        return "";
    }

    private static String changeOrientation() {

        Activity activity = AppUtils.getCurActivity();
        if (activity == null) {
            return "";
        }
        int orientation = activity.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        return "";
    }

    private static String performFeatureV() {
        ImmersiveIconApi.getInstance().performFeature("vplayer");
        return "";
    }

    private static String performFeatureBBS() {
        ImmersiveIconApi.getInstance().performFeature("bbs");
        return "";
    }

    private static String performFeatureAction() {
        ImmersiveIconApi.getInstance().performFeature("活动");
        return "";
    }
}
