package com.tencent.tmgp.yybtestsdk.module;

import android.util.SparseArray;

import com.tencent.tmgp.yybtestsdk.module.submodule.FreeLoginModule;
import com.tencent.tmgp.yybtestsdk.module.submodule.GuestModule;
import com.tencent.tmgp.yybtestsdk.module.submodule.LaunchGiftModule;
import com.tencent.tmgp.yybtestsdk.module.submodule.OthersFunction;
import com.tencent.tmgp.yybtestsdk.module.submodule.QQModule;
import com.tencent.tmgp.yybtestsdk.module.submodule.ShareModule;
import com.tencent.tmgp.yybtestsdk.module.submodule.WXModule;

public class ModuleManager {

	public static final int QQ_MODULE = 1;
	public static final int WX_MODULE = 1 << 1;
	public static final int GUEST_MODULE = 1 << 3;
	public static final int OTHERS_MODULE = 1 << 4;
	public static final int SHARE_MODULE = 1 << 5;
	public static final int LAUNCH_GIFT_MODULE = 1 << 6;
	public static final int INNER_MODULE = 1 << 7;
	public static final int ICON_MODULE = 1 << 8;
	public static final int FREE_LOGIN_MOUDLE = 1<<9;
	public static String LANG;
	public static SparseArray<BaseModule> sModulesList;



	static {
		// 添加模块
		sModulesList = new SparseArray<>();
		sModulesList.append(QQ_MODULE,new QQModule());
		sModulesList.append(WX_MODULE,new WXModule());
		sModulesList.append(GUEST_MODULE,new GuestModule());
		sModulesList.append(OTHERS_MODULE,new OthersFunction());
		sModulesList.append(SHARE_MODULE,new ShareModule());
		sModulesList.append(LAUNCH_GIFT_MODULE,new LaunchGiftModule());
//		sModulesList.append(ICON_MODULE,new IconModule());
		sModulesList.append(FREE_LOGIN_MOUDLE,new FreeLoginModule());
	}


	private ModuleManager() {
	}

}
