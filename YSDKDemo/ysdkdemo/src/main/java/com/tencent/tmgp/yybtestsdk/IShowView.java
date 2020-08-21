package com.tencent.tmgp.yybtestsdk;

//import com.tencent.tmgp.yybtestsdk.module.BaseModule;
//import com.tencent.tmgp.yybtestsdk.module.YSDKDemoFunction;

public interface IShowView {
    void showToastTips(String msg);

//    void showModule(BaseModule module);

    void hideModule();

//    void showResult(String result, YSDKDemoFunction function);

    void hideResult();

    void resetMainView();

    void showProgressBar();

    void hideProgressBar();
}
