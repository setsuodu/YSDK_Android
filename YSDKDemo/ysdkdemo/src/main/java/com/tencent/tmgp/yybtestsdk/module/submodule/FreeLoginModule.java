package com.tencent.tmgp.yybtestsdk.module.submodule;

import android.widget.LinearLayout;

import com.tencent.tmgp.yybtestsdk.api.IDemoApiType;
import com.tencent.tmgp.yybtestsdk.appearance.FuncBlockView;
import com.tencent.tmgp.yybtestsdk.module.BaseModule;
import com.tencent.tmgp.yybtestsdk.module.YSDKDemoFunction;

import java.util.ArrayList;

public class FreeLoginModule extends BaseModule {


    public FreeLoginModule() {
        this.name = "云游/沙盒免登录";
    }

    @Override
    public void init(LinearLayout parent) {
        FuncBlockView freeLoginView = new FuncBlockView(parent, this);

        // 登录相关
        ArrayList<YSDKDemoFunction> aboutLogin = new ArrayList<YSDKDemoFunction>();
        aboutLogin.add(new YSDKDemoFunction(IDemoApiType.TYPE_USER,
                IDemoApiType.USER_LOGOUT, "logout", "登出", "退出登录"));
        aboutLogin.add(new YSDKDemoFunction(
                IDemoApiType.TYPE_USER,IDemoApiType.USER_GET_LOGIN_RECORD,
                "getLoginRecord",
                "登录记录",
                "读取本次免登录票据"));
        freeLoginView.addView( "登录相关", aboutLogin);
    }
}
