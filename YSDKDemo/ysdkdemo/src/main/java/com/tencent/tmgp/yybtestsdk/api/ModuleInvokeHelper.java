package com.tencent.tmgp.yybtestsdk.api;

import com.tencent.tmgp.yybtestsdk.module.BaseModule;
import com.tencent.tmgp.yybtestsdk.utils.ModuleUtils;

/**
 * 模块的调用辅助类
 *
 * create by stringwu 05/21/2019
 */
public class ModuleInvokeHelper {

    public static String execute(@IDemoApiType.SUBTYPE_MODULE_INVOKE int subType) {
        switch (subType) {
            case IDemoApiType.MODULE_INVOKE_PAY:
                return invokePayModule();
            case IDemoApiType.MODULE_INVOKE_ICON:
                return invokeIconModule();
            case IDemoApiType.MODULE_INVOKE_LAUNCH_GIFT:
                return invokeLaunchGiftModule();
                default:
                    throw new IllegalArgumentException("not support type:" + subType);
        }
    }

    private static String invokePayModule() {
        BaseModule module = ModuleUtils.invokePayModule();
        if (module == null) {
            YSDKDemoApi.sShowView.showToastTips("请登录后重试~");
            return null;
        }

        YSDKDemoApi.sShowView.showModule(module);
        return null;

    }

    private static String invokeIconModule() {
        BaseModule module = ModuleUtils.invokeIconModule();
        if (module == null) {
            YSDKDemoApi.sShowView.showToastTips("请登录后重试~");
            return null;
        }

        YSDKDemoApi.sShowView.showModule(module);
        return null;
    }

    private static String invokeLaunchGiftModule() {
        BaseModule module = ModuleUtils.invokeLaunchGiftModule();
        if (module == null) {
            YSDKDemoApi.sShowView.showToastTips("请登录后重试~");
            return null;
        }

        YSDKDemoApi.sShowView.showModule(module);
        return null;
    }
}
