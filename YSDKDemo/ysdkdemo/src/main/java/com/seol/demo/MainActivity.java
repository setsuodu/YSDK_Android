package com.seol.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.tmgp.yybtestsdk.AppUtils;
import com.tencent.tmgp.yybtestsdk.R;
import com.tencent.tmgp.yybtestsdk.YSDKCallback;
import com.tencent.tmgp.yybtestsdk.api.YSDKDemoApi;
import com.tencent.ysdk.api.YSDKApi;
import com.tencent.ysdk.framework.common.ePlatform;

public class MainActivity extends Activity {

//    public ProgressDialog mAutoLoginWaitingDlg;
    public static final String LOG_TAG = YSDKDemoApi.TAG;
    private static Activity mActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("UnityPlayerActivity onCreate");

        AppUtils.updateActivity(this);
        YSDKCallback callback = new YSDKCallback();
        YSDKDemoApi.sBugylyListener = callback;
        YSDKDemoApi.sUserListener = callback;
        YSDKDemoApi.sPayListener = callback;
        YSDKDemoApi.sAntiAddictListener = callback;
        YSDKDemoApi.sRegisterWindowCloseListener = callback;

//        YSDKDemoApi.sShowView = this;
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

//        YSDKApi.handleIntent(this.getIntent());

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(com.seol.demo.MainActivity.this,"初始化",Toast.LENGTH_SHORT).show();
            }
        });

        Button btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(com.seol.demo.MainActivity.this,"登录",Toast.LENGTH_SHORT).show();

//                ePlatform platform = ModuleUtils.getPlatform();
                loginByType(ePlatform.WX);
            }
        });

        Button btn3 = findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(com.seol.demo.MainActivity.this,"支付",Toast.LENGTH_SHORT).show();

                com.tencent.ysdk.api.YSDKApi.recharge("1","1",false,null,"ysdkExt",YSDKDemoApi.sPayListener);
            }
        });
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

}
