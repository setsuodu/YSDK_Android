package com.tencent.tmgp.yybtestsdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.tencent.tmgp.yybtestsdk.AppUtils;
//import com.tencent.tmgp.yybtestsdk.R;
import com.tencent.tmgp.yybtestsdk.YSDKCallback;
import com.tencent.tmgp.yybtestsdk.api.YSDKDemoApi;
import com.tencent.ysdk.api.YSDKApi;
import com.tencent.ysdk.framework.common.ePlatform;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

public class MainActivity extends UnityPlayerActivity {

    public static final String LOG_TAG = YSDKDemoApi.TAG;
    private static Activity mActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("UnityPlayerActivity onCreate");

        YSDKCallback callback = new YSDKCallback();
        YSDKDemoApi.sBugylyListener = callback;
        YSDKDemoApi.sUserListener = callback;
        YSDKDemoApi.sPayListener = callback;
        YSDKDemoApi.sAntiAddictListener = callback;
        YSDKDemoApi.sRegisterWindowCloseListener = callback;

        YSDKDemoApi.sActivity = this;
        mActivity = this;

//        // TODO GAME YSDK初始化
        YSDKApi.onCreate(this);
//        // TODO GAME 设置java层或c++层回调,如果两层都设置了则会只回调到java层
        // 全局回调类，游戏自行实现
        YSDKApi.setUserListener(YSDKDemoApi.sUserListener);
        YSDKApi.setBuglyListener(YSDKDemoApi.sBugylyListener);
        YSDKApi.setAntiAddictListener(YSDKDemoApi.sAntiAddictListener);
        YSDKApi.setAntiRegisterWindowCloseListener(YSDKDemoApi.sRegisterWindowCloseListener);
        // TODO:上线前需要关闭
        YSDKApi.setAntiAddictLogEnable(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("onActivityResult: requestCode=" + requestCode + ", resultCode=" + resultCode);
//        switch (requestCode) {
//            case 100:
//                break;
//        }
        YSDKApi.onActivityResult(requestCode, resultCode,data);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            System.out.println("UnityPlayerActivity onKeyDown");
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        System.out.println("UnityPlayerActivity onNewIntent");
        setIntent(intent);
        super.onNewIntent(intent);
        YSDKApi.handleIntent(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("UnityPlayerActivity onResume");
        YSDKApi.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("UnityPlayerActivity onPause");
        YSDKApi.onPause(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("UnityPlayerActivity onStop");
        YSDKApi.onStop(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("UnityPlayerActivity onDestroy");
        YSDKApi.onDestroy(this);
    }

    @Override
    public void onRestart() {
        super.onRestart();
        System.out.println("UnityPlayerActivity onRestart");
        YSDKApi.onRestart(this);
    }



    private void loginByType(ePlatform platform) {
        YSDKApi.login(platform);
    }

    public void Init() {
        System.out.println("UnityPlayerActivity Init");
        UnityPlayer.UnitySendMessage("Hook", "NativeCallback", "Init");

        Pay();
    }

    public void Login(int platform) {
        System.out.println("UnityPlayerActivity Login: " + platform);
        UnityPlayer.UnitySendMessage("Hook", "NativeCallback", "Login");

        if (platform == 1) {
            loginByType(ePlatform.QQ);
        } else if (platform == 2) {
            loginByType(ePlatform.WX);
        }
//        Toast.makeText(MainActivity.this,"登录", Toast.LENGTH_SHORT).show();
    }

    public void Logout() {
        System.out.println("UnityPlayerActivity Logout");
        UnityPlayer.UnitySendMessage("Hook", "NativeCallback", "Logout");

        YSDKApi.logout();
        Toast.makeText(MainActivity.this,"登出成功", Toast.LENGTH_SHORT).show();
    }

    public void Pay() {
        System.out.println("UnityPlayerActivity Pay");
        UnityPlayer.UnitySendMessage("Hook", "NativeCallback", "Pay");

        com.tencent.ysdk.api.YSDKApi.recharge("1","1",false,null,"ysdkExt",YSDKDemoApi.sPayListener);
//        Toast.makeText(MainActivity.this,"支付", Toast.LENGTH_SHORT).show();
    }
}