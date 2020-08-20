package com.tencent.tmgp.yybtestsdk.module.submodule;


import android.graphics.Bitmap;
import android.widget.LinearLayout;

import com.tencent.tmgp.yybtestsdk.api.IDemoApiType;
import com.tencent.tmgp.yybtestsdk.appearance.FuncBlockView;
import com.tencent.tmgp.yybtestsdk.module.BaseModule;
import com.tencent.tmgp.yybtestsdk.module.YSDKDemoFunction;

import java.util.ArrayList;

public class ShareModule extends BaseModule {
	private Bitmap cacheBitmap;
	public ShareModule() {
		this.name = "分享模块";
	}
	
	@Override
	public void init(LinearLayout parent) {
		FuncBlockView othersView = new FuncBlockView(parent, this);
		
		// ^_^
        ArrayList<YSDKDemoFunction> othersFunction = new ArrayList<YSDKDemoFunction>();
        othersFunction.add(new YSDKDemoFunction(
				IDemoApiType.TYPE_SHARE,IDemoApiType.OPEN_SHARE_VIEW,
        		"showShareView",
        		"打开通用分享面板",
        		"通用分享面板"));
		othersFunction.add(new YSDKDemoFunction(
				IDemoApiType.TYPE_SHARE,IDemoApiType.SHARE_TO_WX,
				"ShareToWX",
				"微信分享",
				"微信"));
		othersFunction.add(new YSDKDemoFunction(
				IDemoApiType.TYPE_SHARE,IDemoApiType.SHARE_TO_WX_TIME_LINE,
				"ShareToWXTimeLine",
				"朋友圈分享",
				"朋友圈"));
		othersFunction.add(new YSDKDemoFunction(
				IDemoApiType.TYPE_SHARE,IDemoApiType.SHARE_TO_QQ,
				"ShareToQQ",
				"QQ分享",
				"QQ"));
		othersFunction.add(new YSDKDemoFunction(
				IDemoApiType.TYPE_SHARE,IDemoApiType.SHARE_TO_QZONE,
				"ShareToQZone",
				"空间分享",
				"空间"));
	othersView.addView( "请登录成功后调用", othersFunction);
	}

}
