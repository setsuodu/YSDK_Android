package com.tencent.tmgp.yybtestsdk.api;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.tencent.tmgp.yybtestsdk.AppUtils;
import com.tencent.ysdk.module.share.ShareApi;

import java.lang.ref.WeakReference;

/**
 * 分享相关的调用示例接口
 *
 * create by stringwu 05/21/2019
 */
public class ShareDemoApi {

    private static WeakReference<Bitmap> sCacheBitmap;

    public static String execute(@IDemoApiType.SUBTYPE_SHARE int subType,String extraParams) {
        switch (subType) {
            case IDemoApiType.SHARE_TO_QQ:
                return shareToQQ();
            case IDemoApiType.SHARE_TO_WX:
                return shareToWX();
            case IDemoApiType.SHARE_TO_WX_TIME_LINE:
                return shareToWXTimeLine();
            case IDemoApiType.SHARE_TO_QZONE:
                return shareToQZone();
            case IDemoApiType.OPEN_SHARE_VIEW:
                return showShareView();
            default:
                throw new IllegalArgumentException("not support type for share api:" + subType);

        }
    }

    //游戏自定义透传信息，用来区分当前分享场景，会通过回调结果回传给游戏、输出至统计报表。
    // 建议不同分享场景构造不同extInfo做区分
    private static final String EXTRA_INFO ="demo_sdk";//只可包含字母数字下划线20位以内
    private static String shareToQQ() {
        ShareApi.getInstance().shareToQQFriend(getCacheBitMap(),"标题","描述",EXTRA_INFO);
        return "";
    }

    private static String shareToWX() {
        ShareApi.getInstance().shareToWXFriend(getCacheBitMap(),"标题","描述",EXTRA_INFO);
        return "";
    }

    private static String shareToWXTimeLine() {
        ShareApi.getInstance().shareToWXTimeline(getCacheBitMap(),"标题","描述",EXTRA_INFO);
        return "";
    }

    private static String shareToQZone() {
        ShareApi.getInstance().shareToQZone(getCacheBitMap(),"标题","描述",
                "游戏透传信息");
        return "";
    }


    private static String showShareView() {
        ShareApi.getInstance().share(getCacheBitMap(),"标题","描述",EXTRA_INFO);
        return "";
    }

    private static Bitmap getCacheBitMap() {
        Bitmap bitmap;
        if (sCacheBitmap != null) {
            bitmap = sCacheBitmap.get();
            if (bitmap != null) {
                return bitmap;
            }
        }

        Activity activity = AppUtils.getCurActivity();
        if (activity == null) {
            return null;
        }

        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        int statusBarHeight = rect.top;
        WindowManager windowManager = activity.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;
        bitmap = Bitmap.createBitmap(view.getDrawingCache(), 0, statusBarHeight, width,
                height - statusBarHeight);
        sCacheBitmap = new WeakReference<>(bitmap);
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(false);

        return bitmap;
    }
}
