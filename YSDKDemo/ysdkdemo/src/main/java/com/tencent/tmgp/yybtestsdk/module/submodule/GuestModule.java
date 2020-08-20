package com.tencent.tmgp.yybtestsdk.module.submodule;

import android.widget.LinearLayout;

import com.tencent.tmgp.yybtestsdk.api.IDemoApiType;
import com.tencent.tmgp.yybtestsdk.appearance.FuncBlockView;
import com.tencent.tmgp.yybtestsdk.module.BaseModule;
import com.tencent.tmgp.yybtestsdk.module.YSDKDemoFunction;

import java.util.ArrayList;

public class GuestModule extends BaseModule {

	public GuestModule() {
		this.name = "游客登录";
	}

	@Override
	public void init(LinearLayout parent) {
		FuncBlockView guestView = new FuncBlockView(parent, this);
		
		// 登录相关
        ArrayList<YSDKDemoFunction> aboutLogin = new ArrayList<YSDKDemoFunction>();
        aboutLogin.add(new YSDKDemoFunction(IDemoApiType.TYPE_USER,IDemoApiType.USER_LOGOUT,
                "logout", "登出", "退出游客登录"));
        aboutLogin.add(new YSDKDemoFunction(
                IDemoApiType.TYPE_USER,IDemoApiType.USER_GET_LOGIN_RECORD,
        		"getLoginRecord",
        		"登录记录", 
        		"读取本次游客登录票据"));
        guestView.addView( "登录相关", aboutLogin);

        // 支付相关
        ArrayList<YSDKDemoFunction> payList = new ArrayList<YSDKDemoFunction>();
        payList.add(new YSDKDemoFunction(
                IDemoApiType.TYPE_MODULE_INVOKE,IDemoApiType.MODULE_INVOKE_PAY,
                "",
                "拉起支付模块",
                ""));
        guestView.addView( "支付相关", payList);
        // 游戏icon相关
        ArrayList<YSDKDemoFunction> iconList = new ArrayList<YSDKDemoFunction>();
        iconList.add(new YSDKDemoFunction(
                IDemoApiType.TYPE_MODULE_INVOKE,IDemoApiType.MODULE_INVOKE_ICON,
                "",
                "拉起游戏Icon模块",
                ""));
        guestView.addView("游戏Icon相关", iconList);
    }
}
