package com.tencent.tmgp.yybtestsdk.module.submodule;


import android.widget.LinearLayout;

import com.tencent.tmgp.yybtestsdk.api.IDemoApiType;
import com.tencent.tmgp.yybtestsdk.appearance.FuncBlockView;
import com.tencent.tmgp.yybtestsdk.module.BaseModule;
import com.tencent.tmgp.yybtestsdk.module.YSDKDemoFunction;

import java.util.ArrayList;

public class OthersFunction extends BaseModule {

	public OthersFunction() {
		this.name = "其他";
	}
	
	@Override
	public void init(LinearLayout parent) {
		FuncBlockView othersView = new FuncBlockView(parent, this);
		
		// ^_^
        ArrayList<YSDKDemoFunction> othersFunction = new ArrayList<YSDKDemoFunction>();
        othersFunction.add(new YSDKDemoFunction(
				IDemoApiType.TYPE_OTHERS,IDemoApiType.OTHERS_GET_INSTALL_CHANNEL,
        		"getChannelId",
        		"获取安装渠道号", 
        		"游戏上线前打包会根据不同渠道(例如应用宝,豌豆荚,91等)生成不同渠道号的apk包, " +
        		"在安装包中的渠道号称之为安装渠道"));
        othersFunction.add(new YSDKDemoFunction(
				IDemoApiType.TYPE_OTHERS,IDemoApiType.OTHERS_GET_REGISTER_CHANNEL,
        		"getRegisterChannelId",
        		"获取注册渠道号", 
        		"用户首次登录时, 游戏的安装渠道, 会在YSDK后台记录, 算作用户注册渠道"));
		othersFunction.add(new YSDKDemoFunction(
				IDemoApiType.TYPE_OTHERS,IDemoApiType.OTHERS_GET_SDK_VERSION,
				"getVersion",
				"获取SDK的版本",
				"获取YSDK的版本"));
        
        ArrayList<YSDKDemoFunction> crashReport = new ArrayList<YSDKDemoFunction>();
        crashReport.add(new YSDKDemoFunction(
				IDemoApiType.TYPE_OTHERS,IDemoApiType.OTHERS_NATIVE_CRASH_TEST,
        		" ",
        		"Native异常测试",
        		"测试异常上报功能，Crash信息可在RDM上查看。Crash信息为实时上报，但必须" +
        		"触发另一种Crash才能看到此Crash信息(可下次再启动Demo点击另一种异常测试" +
        		"来实现)。这里测试C++层的Crash。"));
        crashReport.add(new YSDKDemoFunction(
				IDemoApiType.TYPE_OTHERS,IDemoApiType.OTHERS_MATH_CRASH_TEST,
        		" ",
        		"算术异常测试",
        		"测试异常上报功能，Crash信息可在RDM上查看。Crash信息为实时上报，但必须" +
        		"触发另一种Crash才能看到此Crash信息(可下次再启动Demo点击另一种异常测试" +
        		"来实现)。这里测试除0的异常。"));
        crashReport.add(new YSDKDemoFunction(
				IDemoApiType.TYPE_OTHERS,IDemoApiType.OTHERS_NPE_CRASH_TEST,
        		" ",
        		"空指针异常测试",
        		"测试异常上报功能，Crash信息可在RDM上查看。Crash信息为实时上报，但必须" +
        		"触发另一种Crash才能看到此Crash信息(可下次再启动Demo点击另一种异常测试" +
        		"来实现)。这里测试空指针的Crash。"));

        //todo:这个类型待确认
        othersFunction.add(new YSDKDemoFunction(IDemoApiType.TYPE_OTHERS,
				IDemoApiType.OTHERS_NPE_CRASH_TEST,
				"异常上报测试", crashReport));
        othersView.addView("^_^", othersFunction);
	}
	
}
