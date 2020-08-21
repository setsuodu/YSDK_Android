package com.tencent.tmgp.yybtestsdk.api;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.PopupWindow;

import com.tencent.tmgp.yybtestsdk.AppUtils;
import com.tencent.tmgp.yybtestsdk.IShowView;
import com.tencent.tmgp.yybtestsdk.R;
import com.tencent.tmgp.yybtestsdk.module.BaseModule;
import com.tencent.tmgp.yybtestsdk.module.ModuleManager;
import com.tencent.tmgp.yybtestsdk.module.YSDKDemoFunction;
import com.tencent.ysdk.api.YSDKApi;
import com.tencent.ysdk.framework.common.BaseRet;
import com.tencent.ysdk.framework.common.ePlatform;
import com.tencent.ysdk.module.AntiAddiction.listener.AntiRegisterWindowCloseListener;
import com.tencent.ysdk.module.bugly.BuglyListener;
import com.tencent.ysdk.module.pay.PayListener;
import com.tencent.ysdk.module.AntiAddiction.listener.AntiAddictListener;
import com.tencent.ysdk.module.AntiAddiction.model.AntiAddictRet;
import com.tencent.ysdk.module.user.UserListener;
import com.tencent.ysdk.module.user.UserLoginRet;

/**
 * YSDK Demo Api 的调用 （通过功能的类型来选择具体调用的接口）
 *
 * create by stringwu 05/21/2019
 */
public class YSDKDemoApi {
    public static final String TAG = "YSDK_DEMO:";
    // 防沉迷指令执行状态
    public static boolean mAntiAddictExecuteState = false;


    public static String execute(int type, int subType,String extraParams) {
        switch (type) {
            case IDemoApiType.TYPE_USER:
                return UserDemoApi.execute(subType,extraParams);
            case IDemoApiType.TYPE_PAY:
                return PayDemoApi.execute(subType,extraParams);
            case IDemoApiType.TYPE_OTHERS:
                return OthersDemoApi.execute(subType,extraParams);
            case IDemoApiType.TYPE_SHARE:
                return ShareDemoApi.execute(subType,extraParams);
            case IDemoApiType.TYPE_INNER_TEST:
            case IDemoApiType.TYPE_ICON:
                return IconDemoApi.execute(subType,extraParams);
            case IDemoApiType.TYPE_LAUNCH_GIFT:
                return LaunchGiftDemoApi.execute(subType,extraParams);
            case IDemoApiType.TYPE_MODULE_INVOKE:
                return ModuleInvokeHelper.execute(subType);
            default:
                throw new IllegalArgumentException("not support type:" + type);
        }
    }


    // TODO: 应用接入时，必须提供这几个回调监听
    public static UserListener sUserListener;
    public static BuglyListener sBugylyListener;
    public static PayListener sPayListener;
    public static AntiAddictListener sAntiAddictListener;
    public static AntiRegisterWindowCloseListener sRegisterWindowCloseListener;



    //这两个只是demo里用来展示相关调用结果,无实际意义
    public static IShowView sShowView;
    public static YSDKDemoFunction sLastFunction;
    public static Activity sActivity;

    public static void userLoginSuc() {
        UserLoginRet ret = new UserLoginRet();
        YSDKApi.getLoginRecord(ret);
        Log.d(TAG,"flag: " + ret.flag);
        Log.d(TAG,"platform: " + ret.platform);

        if (ret.ret != BaseRet.RET_SUCC) {
            sShowView.showToastTips( "UserLogin error!!!");
            Log.d(TAG,"UserLogin error!!!");
            userLogout();
            return;
        }

        if (ret.platform == ePlatform.PLATFORM_ID_WX) {
            Log.d(TAG,"登录成功，跳转到微信主界面");
        }

        /*
        //登录成功调用“启动特权”相关能力
        LaunchGiftDemoApi.autoShowLaunchGiftViewOnLaunch();

        if (ret.platform == ePlatform.PLATFORM_ID_QQ) {
            BaseModule module = ModuleManager.sModulesList.get(ModuleManager.QQ_MODULE);
            sShowView.showModule(module);

        } else if (ret.platform == ePlatform.PLATFORM_ID_WX) {
            BaseModule module = ModuleManager.sModulesList.get(ModuleManager.WX_MODULE);
            sShowView.showModule(module);

        } else if (ret.platform == ePlatform.PLATFORM_ID_GUEST) {
            BaseModule module = ModuleManager.sModulesList.get(ModuleManager.GUEST_MODULE);
            sShowView.showModule(module);
        } else if(ret.platform == ePlatform.PLATFORM_ID_FREE_LOGIN){
            BaseModule module = ModuleManager.sModulesList.get(ModuleManager.FREE_LOGIN_MOUDLE);
            sShowView.showModule(module);
        }
        */
    }

    public static void userLogout() {
        YSDKApi.logout();
        sShowView.hideModule();
        sShowView.resetMainView();

    }

    public static void choseUserToLogin() {
        AppUtils.getCurActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(AppUtils.getCurActivity());
                builder.setTitle("异账号提示");
                builder.setMessage("你当前拉起的账号与你本地的账号不一致，请选择使用哪个账号登陆：");
                builder.setPositiveButton("本地账号",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                sShowView.showToastTips("选择使用本地账号");
                                if (!YSDKApi.switchUser(false)) {
                                        userLogout();
                                    }
                            }
                        });
                builder.setNeutralButton("拉起账号",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                sShowView.showToastTips("选择使用拉起账号");
                                    if (!YSDKApi.switchUser(true)) {
                                        userLoginSuc();
                                    }
                            }
                        });
                builder.show();
            }
        });
    }

    public static void executeInstruction(AntiAddictRet ret) {
        final int modal = ret.modal;
        switch (ret.type) {
            case AntiAddictRet.TYPE_TIPS:
            case AntiAddictRet.TYPE_LOGOUT:
                if (!mAntiAddictExecuteState) {
                    mAntiAddictExecuteState = true;
                    AlertDialog.Builder builder = new AlertDialog.Builder(sActivity);
                    builder.setTitle(ret.title);
                    builder.setMessage(ret.content);
                    builder.setPositiveButton("知道了",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int whichButton) {
                                    if (modal == 1) {
                                        // 强制用户下线
                                        userLogout();
                                    }
                                    changeExecuteState(false);
                                }
                            });
                    builder.setCancelable(false);
                    builder.show();
                    // 已执行指令
                    YSDKApi.reportAntiAddictExecute(ret, System.currentTimeMillis());
                }

                break;

            case AntiAddictRet.TYPE_OPEN_URL:
                if (!mAntiAddictExecuteState) {
                    mAntiAddictExecuteState = true;
//                    View popwindowView = View.inflate(sActivity, R.layout.pop_window_web_layout, null);
//                    WebView webView = popwindowView.findViewById(R.id.pop_window_webview);
//                    Button closeButton = popwindowView.findViewById(R.id.pop_window_close);

//                    WebSettings settings= webView.getSettings();
//                    settings.setJavaScriptEnabled(true);
//                    webView.setWebViewClient(new WebViewClient());
//                    webView.loadUrl(ret.url);
//
//                    final PopupWindow popupWindow = new PopupWindow(popwindowView, 1000, 1000);
//                    popupWindow.setTouchable(true);
//                    popupWindow.setOutsideTouchable(false);
//                    popupWindow.setBackgroundDrawable(new BitmapDrawable());
//
//                    closeButton.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            if (modal == 1) {
//                                userLogout();
//                            }
//                            popupWindow.dismiss();
//                            changeExecuteState(false);
//                        }
//                    });
//
//                    popupWindow.showAtLocation(popwindowView, Gravity.CENTER, 0, 0);
                    // 已执行指令
                    YSDKApi.reportAntiAddictExecute(ret, System.currentTimeMillis());
                }

                break;

        }
    }

    private static void changeExecuteState(boolean state) {
        mAntiAddictExecuteState = state;
    }


}
