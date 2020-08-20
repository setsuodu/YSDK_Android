package com.tencent.tmgp.yybtestsdk.utils;

import com.tencent.tmgp.yybtestsdk.module.BaseModule;
import com.tencent.tmgp.yybtestsdk.module.submodule.IconModule;
import com.tencent.tmgp.yybtestsdk.module.submodule.LaunchGiftModule;
import com.tencent.tmgp.yybtestsdk.module.submodule.PayModule;
import com.tencent.ysdk.api.YSDKApi;
import com.tencent.ysdk.framework.common.eFlag;
import com.tencent.ysdk.framework.common.ePlatform;
import com.tencent.ysdk.module.user.UserLoginRet;

public class ModuleUtils {

    public static BaseModule invokeIconModule() {
        UserLoginRet ret = new UserLoginRet();
        YSDKApi.getLoginRecord(ret);
        if (eFlag.Succ == ret.flag) {
            return new IconModule();
        }

        return null;

    }

    public static BaseModule invokePayModule() {
        UserLoginRet ret = new UserLoginRet();
        YSDKApi.getLoginRecord(ret);
        if (eFlag.Succ == ret.flag) {
            return new PayModule();
        }

        return null;
    }
    public static BaseModule invokeLaunchGiftModule() {
        UserLoginRet ret = new UserLoginRet();
        YSDKApi.getLoginRecord(ret);
        if (eFlag.Succ == ret.flag) {
            return new LaunchGiftModule();
        }

        return null;
    }
    public static ePlatform getPlatform() {
        UserLoginRet ret = new UserLoginRet();
        YSDKApi.getLoginRecord(ret);
        if (ret.flag == eFlag.Succ) {
            return ePlatform.getEnum(ret.platform);
        }
        return ePlatform.None;
    }
}
