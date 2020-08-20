package com.tencent.tmgp.yybtestsdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.ysdk.api.YSDKApi;
import com.tencent.ysdk.module.h5game.H5GameWebCore;
import com.tencent.ysdk.module.h5game.H5GameWebViewClient;

/**
 * Created by sundaysong on 2019/1/17.
 */

public class H5GameActivity extends Activity {
    private H5GameWebCore webView;
    private static Activity mActivity = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mActivity != null && !mActivity.equals(this)) {
            YSDKApi.handleIntent(getIntent());
            return;
        } else {
            mActivity = this;
            YSDKApi.onCreate(this);
            YSDKApi.handleIntent(getIntent());
        }
        init();
    }

    private void init() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.h5game_layout);
        webView = (H5GameWebCore) findViewById(R.id.h5game_web_core);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(false);
        settings.setDatabaseEnabled(true);
        String dir = webView.getContext().getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        settings.setDatabasePath(dir);
        settings.setDomStorageEnabled(true);
        settings.setGeolocationEnabled(true);
        settings.setAppCacheMaxSize(1024 * 1024 * 8);
        settings.setAppCachePath(webView.getContext().getApplicationContext().getCacheDir().getAbsolutePath());
        settings.setAppCacheEnabled(true);
        //设置缓存模式
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        //启用缓存
        settings.setAppCacheEnabled(true);
        settings.setAllowFileAccess(true);
        //增加webview的调试
        if (Build.VERSION.SDK_INT > 19) {
            webView.setWebContentsDebuggingEnabled(true);
        }

        webView.setWebViewClient(new MyH5GameWebViewClient(webView));
        webView.setWebChromeClient(new MyH5GameWebChromeClient());
        webView.loadUrl("http://yybstyle.sparta.html5.qq.com/open/mobile/h5gamesdk_v2/apiTest.html");
    }

    class MyH5GameWebViewClient extends H5GameWebViewClient {

        public MyH5GameWebViewClient(H5GameWebCore webCore) {
            super(webCore);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("http") || url.startsWith("https")) {
                return false;
            }
            //此处可添加自身关于WebView的设置和逻辑
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    class MyH5GameWebChromeClient extends WebChromeClient {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        YSDKApi.handleIntent(intent);
        super.onNewIntent(intent);
    }

    @Override
    protected void onPause() {
        YSDKApi.onPause(this);
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        YSDKApi.onResume(this);
    }

    @Override
    protected void onDestroy() {
        YSDKApi.onDestroy(this);
        super.onDestroy();

    }

    @Override
    protected void onStop() {
        YSDKApi.onStop(this);
        super.onStop();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        YSDKApi.onRestart(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        YSDKApi.onActivityResult(requestCode, resultCode, data);
    }

    private long lastClick = 0;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastClick > 3000) {
            lastClick = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "再次点击返回键退出游戏", Toast.LENGTH_LONG).show();
        } else {
            System.exit(0);
        }
    }
}
