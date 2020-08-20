package com.tencent.tmgp.yybtestsdk.module.submodule;

import android.widget.LinearLayout;

import com.tencent.tmgp.yybtestsdk.api.IDemoApiType;
import com.tencent.tmgp.yybtestsdk.appearance.FuncBlockView;
import com.tencent.tmgp.yybtestsdk.module.BaseModule;
import com.tencent.tmgp.yybtestsdk.module.YSDKDemoFunction;

import java.util.ArrayList;


public class WXModule extends BaseModule {

    public WXModule() {
        this.name = "微信登录";
    }

    @Override
    public void init(LinearLayout parent) {
        FuncBlockView weixinView = new FuncBlockView(parent, this);

        // 登录相关
        ArrayList<YSDKDemoFunction> aboutLogin = new ArrayList<YSDKDemoFunction>();
        aboutLogin.add(new YSDKDemoFunction(IDemoApiType.TYPE_USER,IDemoApiType.USER_LOGOUT,
                 "logout", "登出", "退出微信登录"));
        aboutLogin.add(new YSDKDemoFunction(
                IDemoApiType.TYPE_USER,IDemoApiType.USER_GET_LOGIN_RECORD,
                "getLoginRecord",
                "登录记录",
                "读取本次微信登录票据"));
        weixinView.addView( "登录相关", aboutLogin);

        // 通用接口
        ArrayList<YSDKDemoFunction> globalList = new ArrayList<YSDKDemoFunction>();
        globalList.add(new YSDKDemoFunction(
                IDemoApiType.TYPE_OTHERS,IDemoApiType.OTHERS_IS_WX_INSTALL,
                "isPlatformInstalled",
                "检查微信是否安装","检查微信是否安装"
        ));
        globalList.add(new YSDKDemoFunction(
                IDemoApiType.TYPE_OTHERS,IDemoApiType.OTHERS_GET_WX_VERSION,
                "getPlatformAPPVersion",
                "检查微信版本",
                "检查微信版本号。部分接口是不支持低版本微信，请仔细接入文档的相关说明。"
        ));
        weixinView.addView("通用", globalList);

        // 关系链功能集合
        ArrayList<YSDKDemoFunction> relationList = new ArrayList<YSDKDemoFunction>();
        relationList.add(new YSDKDemoFunction(
                IDemoApiType.TYPE_USER,
                IDemoApiType.USER_GET_WX_USER_INFO,
                "queryWXUserInfo",
                "个人信息",
                "查询个人基本信息"));
        weixinView.addView("关系链", relationList);

        // 支付相关
        ArrayList<YSDKDemoFunction> payList = new ArrayList<YSDKDemoFunction>();
        payList.add(new YSDKDemoFunction(
                IDemoApiType.TYPE_MODULE_INVOKE,
                IDemoApiType.MODULE_INVOKE_PAY,
                "",
                "拉起支付模块",
                "拉起支付模块",
                ""));
        weixinView.addView( "支付相关", payList);

        // 游戏icon相关
        ArrayList<YSDKDemoFunction> iconList = new ArrayList<YSDKDemoFunction>();
        iconList.add(new YSDKDemoFunction(
                IDemoApiType.TYPE_MODULE_INVOKE,
                IDemoApiType.MODULE_INVOKE_ICON,
                "",
                "拉起Icon模块",
                "拉起游戏Icon模块",
                ""));
        weixinView.addView("游戏Icon相关", iconList);
    }

}
