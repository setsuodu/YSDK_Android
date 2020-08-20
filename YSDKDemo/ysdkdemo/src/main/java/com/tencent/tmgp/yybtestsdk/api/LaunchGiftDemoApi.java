package com.tencent.tmgp.yybtestsdk.api;


import android.util.Log;

import com.tencent.ysdk.module.launchgift.LaunchGiftApi;
import com.tencent.ysdk.module.launchgift.OnLaunchGiftListener;

public class LaunchGiftDemoApi {
    static String UID = "00000000";//TODO GAME "游戏自己可以识别的唯一标识用户的UID帐号"
    static LaunchGiftListenerImpl giftListener = new LaunchGiftListenerImpl();
    public static String execute(@IDemoApiType.SUBTYPE_LAUNCH_GIFT int subType, String extraParams) {
        switch (subType) {
            case IDemoApiType.LAUNCH_GIFT_SHOW_LAUNCH_GIFT_VIEW:
                return showLaunchGiftView();
            case IDemoApiType.LAUNCH_GIFT_AUTO_SHOW_LAUNCH_GIFT_VIEW:
                return autoShowLaunchGiftViewOnLaunch();

            default:
                throw new IllegalArgumentException("not support type:" + subType);

        }

    }




    /**
     * （YSDK登录后）游戏内主动点击“启动特权”按钮后执行此方法，弹出启动特权窗口
     */
    private static String showLaunchGiftView() {
        LaunchGiftApi.getInstance().regOnLaunchGiftListener(giftListener);
        LaunchGiftApi.getInstance().setGameUID(UID);//TODO GAME 游戏应在拿到用户标识后尽早设置UID标识
        LaunchGiftApi.getInstance().showLaunchGiftView();
        return "";
    }

    /**
     * （YSDK登录后）游戏内检查是否通过“启动特权”启动并自动弹出启动特权窗口
     */
    public static String autoShowLaunchGiftViewOnLaunch() {
        LaunchGiftApi.getInstance().regOnLaunchGiftListener(giftListener);


        if (LaunchGiftApi.getInstance().checkLaunchGift()) {
            LaunchGiftApi.getInstance().setGameUID(UID);//TODO GAME 游戏应在拿到用户标识后尽早设置UID标识
            LaunchGiftApi.getInstance().showLaunchGiftView();
        }
        return "";
    }


    /**
     * 完成启动特权的监听器
     */
    static class LaunchGiftListenerImpl implements OnLaunchGiftListener {

        /**
         * 游戏接收此回调给用户发放启动特权奖励
         * 参数说明参考官网“启动特权”接入部分
         *
         * @param conf
         * @param ts
         * @param sig
         */
        @Override
        public void notifyLaunchGift(String conf, long ts, String sig) {

            //TODO GAME 游戏在此处理给用户的发奖逻辑
            Log.i("launchGift", conf + String.valueOf(ts) + String.valueOf(sig));

        }
    }
}
