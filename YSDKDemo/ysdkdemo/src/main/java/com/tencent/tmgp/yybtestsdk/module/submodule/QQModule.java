package com.tencent.tmgp.yybtestsdk.module.submodule;

import android.widget.LinearLayout;

import com.tencent.tmgp.yybtestsdk.api.IDemoApiType;
import com.tencent.tmgp.yybtestsdk.appearance.FuncBlockView;
import com.tencent.tmgp.yybtestsdk.module.BaseModule;
import com.tencent.tmgp.yybtestsdk.module.YSDKDemoFunction;

import java.util.ArrayList;

public class QQModule extends BaseModule {
	
	public QQModule() {
		this.name = "QQ登录";
	}
	
	public void init(LinearLayout parent) {
		FuncBlockView qqView = new FuncBlockView(parent, this);
		
		// 登录相关
        ArrayList<YSDKDemoFunction> aboutLogin = new ArrayList<YSDKDemoFunction>();
        aboutLogin.add(new YSDKDemoFunction(IDemoApiType.TYPE_USER,
                IDemoApiType.USER_LOGOUT, "logout", "登出", "退出QQ登录"));
        aboutLogin.add(new YSDKDemoFunction(
                IDemoApiType.TYPE_USER,IDemoApiType.USER_GET_LOGIN_RECORD,
        		"getLoginRecord",
        		"登录记录", 
        		"读取本次QQ登录票据"));
        qqView.addView( "登录相关", aboutLogin);
        
        // 通用接口
        ArrayList<YSDKDemoFunction> globalList = new ArrayList<YSDKDemoFunction>();
        globalList.add(new YSDKDemoFunction(
                IDemoApiType.TYPE_OTHERS,IDemoApiType.OTHERS_IS_QQ_INSTALL,
                "isPlatformInstalled",
                "检查手Q是否安装",
                "检查手Q是否安装"
        ));
        globalList.add(new YSDKDemoFunction(
                IDemoApiType.TYPE_OTHERS,IDemoApiType.OTHERS_GET_QQ_VERSION,
                "getPlatformAPPVersion",
                "检查手Q版本",
                "检查手Q版本号。部分接口是不支持低版本手Q的，请仔细接入文档的相关说明。"
                ));
        qqView.addView("通用", globalList);

        // 关系链功能集合
        ArrayList<YSDKDemoFunction> relationList = new ArrayList<YSDKDemoFunction>();
        relationList.add(new YSDKDemoFunction(
                IDemoApiType.TYPE_USER,IDemoApiType.USER_GET_QQ_USER_INFO,
                "queryUserInfo",
                "个人信息", 
                "查询个人基本信息"));
        qqView.addView("关系链", relationList);

        // 支付相关
        ArrayList<YSDKDemoFunction> payList = new ArrayList<YSDKDemoFunction>();
        payList.add(new YSDKDemoFunction(
                IDemoApiType.TYPE_MODULE_INVOKE,IDemoApiType.MODULE_INVOKE_PAY,
                "callPay",
                "拉起支付模块",
                "拉起支付模块"));
        qqView.addView("支付相关", payList);


        ArrayList<YSDKDemoFunction> iconList = new ArrayList<YSDKDemoFunction>();
        iconList.add(new YSDKDemoFunction(
                IDemoApiType.TYPE_MODULE_INVOKE,
                IDemoApiType.MODULE_INVOKE_ICON,
                "",
                "拉起Icon模块",
                "拉起游戏Icon模块",
                ""));
        qqView.addView("游戏Icon相关", iconList);
	}

}
