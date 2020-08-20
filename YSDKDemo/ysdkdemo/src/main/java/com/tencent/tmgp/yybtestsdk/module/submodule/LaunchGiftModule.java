package com.tencent.tmgp.yybtestsdk.module.submodule;

import android.widget.LinearLayout;

import com.tencent.tmgp.yybtestsdk.api.IDemoApiType;
import com.tencent.tmgp.yybtestsdk.appearance.FuncBlockView;
import com.tencent.tmgp.yybtestsdk.module.BaseModule;
import com.tencent.tmgp.yybtestsdk.module.YSDKDemoFunction;

import java.util.ArrayList;

public class LaunchGiftModule extends BaseModule {

    public LaunchGiftModule() {
        this.name = "启动特权";
    }
    @Override
    public void init(LinearLayout parent) {
        FuncBlockView launchGiftView = new FuncBlockView(parent, this);

        ArrayList<YSDKDemoFunction> launchGiftIcon = new ArrayList<YSDKDemoFunction>();
        launchGiftIcon.add(new YSDKDemoFunction(
                IDemoApiType.TYPE_LAUNCH_GIFT,IDemoApiType.LAUNCH_GIFT_SHOW_LAUNCH_GIFT_VIEW,
                "showLaunchGiftView",
                "启动特权",
                ""));
        launchGiftView.addView("启动特权（登录后调用）",launchGiftIcon);
    }
}
