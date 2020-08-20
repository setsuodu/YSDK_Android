package com.tencent.tmgp.yybtestsdk.api;

import android.util.Log;

import com.tencent.tmgp.yybtestsdk.MainActivity;
import com.tencent.ysdk.api.YSDKApi;
import com.tencent.ysdk.framework.common.ePlatform;
import com.tencent.ysdk.module.user.UserLoginRet;

/**
 * 用户相关的调用示例接口
 *
 * create by stringwu 05/21/2019
 */
public class UserDemoApi {

    public static String execute(@IDemoApiType.SUBTYPE_USER int subType,String extraParams) {
        switch (subType) {
            case IDemoApiType.USER_LOGIN_BY_GUEST:
                return loginByPlatform(ePlatform.Guest);
            case IDemoApiType.USER_LOGIN_BY_QQ:
                return loginByPlatform(ePlatform.QQ);
            case IDemoApiType.USER_LOGIN_BY_WX:
                return loginByPlatform(ePlatform.WX);
            case IDemoApiType.USER_LOGOUT:
                return logout();
            case IDemoApiType.USER_GET_LOGIN_RECORD:
                return getLoginRecord();
            case IDemoApiType.USER_GET_WX_USER_INFO:
                return getUserInfo(ePlatform.WX);
            case IDemoApiType.USER_GET_QQ_USER_INFO:
                return getUserInfo(ePlatform.QQ);
            default:
                return null;

        }
    }


    private static String loginByPlatform(ePlatform platform) {
        YSDKApi.login(platform);
        return null;
    }

    private static String logout() {
        YSDKDemoApi.userLogout();
        return null;
    }

    private static String getLoginRecord() {
        UserLoginRet ret = new UserLoginRet();
        int platform;
        platform = com.tencent.ysdk.api.YSDKApi.getLoginRecord(ret);

        Log.d(MainActivity.LOG_TAG, "ret:" + ret.toString());
        boolean isSupportPlatform = platform == ePlatform.PLATFORM_ID_QQ ||
                platform == ePlatform.PLATFORM_ID_WX;
        StringBuilder resultBuilder = new StringBuilder();
        if (isSupportPlatform) {
            String desc = platform == ePlatform.PLATFORM_ID_QQ ? "QQ登录" : "微信登录";
            resultBuilder.append("platform = ").append(ret.platform).append(desc).append("\n")
                    .append("accessToken = ").append(ret.getAccessToken()).append("\n")
                    .append("refreshToken = ").append(ret.getRefreshToken()).append("\n");
        }

        resultBuilder.append("openid = ").append(ret.open_id).append("\n")
                .append("flag = ").append(ret.flag).append("\n")
                .append("pf = ").append(ret.pf).append("\n")
                .append("pf_key = ").append(ret.pf_key).append("\n");
        return resultBuilder.toString();
    }

    private static String getUserInfo(ePlatform platform) {
        com.tencent.ysdk.api.YSDKApi.queryUserInfo(platform);
        return "";
    }
}
