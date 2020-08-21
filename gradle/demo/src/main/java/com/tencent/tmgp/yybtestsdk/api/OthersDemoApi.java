package com.tencent.tmgp.yybtestsdk.api;


import com.tencent.bugly.opengame.crashreport.CrashReport;
import com.tencent.ysdk.framework.common.ePlatform;

/**
 * 其他相关的调用示例接口
 * <p>
 * create by stringwu 05/21/2019
 */
public class OthersDemoApi {

    public static String execute(@IDemoApiType.SUBTYPE_OTHERS int subType, String extraParams) {
        switch (subType) {
            case IDemoApiType.OTHERS_GET_INSTALL_CHANNEL:
                return getChannelId();
            case IDemoApiType.OTHERS_GET_REGISTER_CHANNEL:
                return getRegisterChannelId();
            case IDemoApiType.OTHERS_GET_SDK_VERSION:
                return getSDKVersion();
            case IDemoApiType.OTHERS_GET_QQ_VERSION:
                return getPlatformVersion(ePlatform.QQ);
            case IDemoApiType.OTHERS_GET_WX_VERSION:
                return getPlatformVersion(ePlatform.WX);
            case IDemoApiType.OTHERS_IS_QQ_INSTALL:
                return isPlatformInstall(ePlatform.QQ);
            case IDemoApiType.OTHERS_IS_WX_INSTALL:
                return isPlatformInstall(ePlatform.WX);
            case IDemoApiType.OTHERS_NATIVE_CRASH_TEST:
                return nativeCrashTest();
            case IDemoApiType.OTHERS_MATH_CRASH_TEST:
                return mathCrashTest();
            case IDemoApiType.OTHERS_NPE_CRASH_TEST:
                return npeExceptionTest();
            default:
                throw new IllegalArgumentException("not support subType:" + subType);
        }
    }

    private static String getChannelId() {
       return com.tencent.ysdk.api.YSDKApi.getChannelId();
    }

    private static String getRegisterChannelId() {
        return com.tencent.ysdk.api.YSDKApi.getRegisterChannelId();
    }

    private static String getSDKVersion() {
        return com.tencent.ysdk.api.YSDKApi.getVersion();
    }

    private static String isPlatformInstall(ePlatform platform) {
        boolean isInstall = com.tencent.ysdk.api.YSDKApi.isPlatformInstalled(platform);
        return String.valueOf(isInstall);
    }

    private static String getPlatformVersion(ePlatform platform) {
        return com.tencent.ysdk.api.YSDKApi.getPlatformAppVersion(platform);
    }

    private static String nativeCrashTest() {
        CrashReport.testNativeCrash();
        return null;
    }

    private static String mathCrashTest() {
        int i = 0;
        int j = 100 / i;

        return null;
    }

    private static String npeExceptionTest() {
        String aa = null;
        boolean isOK = aa.equals("aa");
        return null;
    }


}
