package com.tencent.tmgp.yybtestsdk.module.submodule;

import android.widget.Button;
import android.widget.LinearLayout;

import com.tencent.tmgp.yybtestsdk.api.IDemoApiType;
import com.tencent.tmgp.yybtestsdk.appearance.FuncBlockView;
import com.tencent.tmgp.yybtestsdk.module.BaseModule;
import com.tencent.tmgp.yybtestsdk.module.YSDKDemoFunction;
import com.tencent.ysdk.module.immersiveicon.ImmersiveIconApi;
import com.tencent.ysdk.module.immersiveicon.OnStateChangeListener;

import java.util.ArrayList;

/**
 * Created by hardyshi on 16/7/25.
 */
public class IconModule extends BaseModule {

    private static final String MODULE_NAME = "游戏ICON";
    private Button vplayer, bbs,huodong;
    private static final String TAG_VPLAYER = "vplayer";
    private static final String TAG_BBS = "bbs";
    private static final String TAG_HUODONG = "活动";

    public IconModule() {
        this.name = MODULE_NAME;
    }

    @Override
    public void init(LinearLayout parent) {
        FuncBlockView iconView = new FuncBlockView(parent, this);

        ArrayList<YSDKDemoFunction> aboutIcon = new ArrayList<YSDKDemoFunction>();
        aboutIcon.add(new YSDKDemoFunction(
                IDemoApiType.TYPE_ICON,IDemoApiType.ICON_SHOW,
                "loadIcon",
                "展示游戏icon",
                ""));
        aboutIcon.add(new YSDKDemoFunction(
                IDemoApiType.TYPE_ICON,IDemoApiType.ICON_HIDE,
                "hideIcon",
                "隐藏游戏icon",
                ""));

        aboutIcon.add(new YSDKDemoFunction(
                IDemoApiType.TYPE_ICON,IDemoApiType.ICON_CHANGE_ORIENTATION,
                "changeOrientation",
                "点击切换横竖屏",
                ""));

        aboutIcon.add(new YSDKDemoFunction(
                IDemoApiType.TYPE_ICON,IDemoApiType.ICON_PERFORM_FEATURE_V,
                "performFeature",
                "V+特权",
                ""));
        aboutIcon.add(new YSDKDemoFunction(
                IDemoApiType.TYPE_ICON,IDemoApiType.ICON_PERFORM_FEATURE_BBS,
                "performFeature",
                "社区",
                ""));
        aboutIcon.add(new YSDKDemoFunction(
                IDemoApiType.TYPE_ICON,IDemoApiType.ICON_PERFORM_FEATURE_ACTION,
                "performFeature",
                "活动",
                ""));
        iconView.addView( "通用功能", aboutIcon);
        vplayer = iconView.findView("V+特权");
        bbs = iconView.findView("社区");
        huodong = iconView.findView("活动");
        //注册游戏内Icon新消息提醒回调
        ImmersiveIconApi.getInstance().regOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(String tag, boolean hasNew) {
                if (tag.equals(TAG_VPLAYER)) {//判断哪个按钮有新提醒

                    if (hasNew) { //改变页面样式
                        vplayer.setText("V+特权[新提醒]");
                    } else {
                        vplayer.setText("V+特权");
                    }
                } else if (tag.equals(TAG_BBS)) {//判断哪个按钮有新提醒

                    if (hasNew) {//改变页面样式

                        bbs.setText("社区[新提醒]");
                    } else {
                        bbs.setText("社区");
                    }

                } else if (tag.equals(TAG_HUODONG)) {//判断哪个按钮有新提醒

                    if (hasNew) {//改变页面样式

                        huodong.setText("活动[新提醒]");
                    } else {
                        huodong.setText("活动");
                    }

                }
            }
        });
    }

}
