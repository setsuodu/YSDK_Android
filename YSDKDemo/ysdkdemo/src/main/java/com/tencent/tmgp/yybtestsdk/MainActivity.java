package com.tencent.tmgp.yybtestsdk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.tmgp.yybtestsdk.api.IDemoApiType;
import com.tencent.tmgp.yybtestsdk.api.ModuleInvokeHelper;
import com.tencent.tmgp.yybtestsdk.api.YSDKDemoApi;
import com.tencent.tmgp.yybtestsdk.appearance.ResultView;
import com.tencent.tmgp.yybtestsdk.appearance.Util;
import com.tencent.tmgp.yybtestsdk.module.BaseModule;
import com.tencent.tmgp.yybtestsdk.module.ModuleManager;
import com.tencent.tmgp.yybtestsdk.module.YSDKDemoFunction;
import com.tencent.tmgp.yybtestsdk.utils.ModuleUtils;
import com.tencent.ysdk.api.YSDKApi;
import com.tencent.ysdk.framework.common.ePlatform;
import com.tencent.ysdk.module.share.ShareApi;
import com.tencent.ysdk.module.share.ShareCallBack;
import com.tencent.ysdk.module.share.impl.IScreenImageCapturer;
import com.tencent.ysdk.module.share.impl.ShareRet;
import com.tencent.ysdk.module.user.impl.freelogin.FreeLoginInfoActivity;

/**
 * 说明:
 * 每个模块相关的接口调用示例在 .module.submodule 中;
 * 每个接口的详细使用说明在 jni/CommonFiles/YSDKApi.h 中;
 * PlatformTest是 YSDK c++ 接口调用示例;
 * 标签 TODO GAME 标识之处是游戏需要关注并处理的!!
 */
public class MainActivity extends Activity implements IShowView {

    private SparseArray<BaseModule> nameList;
    private BaseModule seletedModule;

    public  ProgressDialog mAutoLoginWaitingDlg;
    public  GridView mModuleListView;
    public  LinearLayout mModuleView;
    public  LinearLayout mResultView;

    public static final String LOG_TAG = YSDKDemoApi.TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main_layout);
        AppUtils.updateActivity(this);
        YSDKCallback callback = new YSDKCallback();
        YSDKDemoApi.sBugylyListener = callback;
        YSDKDemoApi.sUserListener = callback;
        YSDKDemoApi.sPayListener = callback;
        YSDKDemoApi.sAntiAddictListener = callback;
        YSDKDemoApi.sRegisterWindowCloseListener = callback;

        YSDKDemoApi.sShowView = this;
        YSDKDemoApi.sActivity = this;
        // TODO GAME YSDK初始化
        YSDKApi.onCreate(this);
        // TODO GAME 设置java层或c++层回调,如果两层都设置了则会只回调到java层
        // 全局回调类，游戏自行实现
        YSDKApi.setUserListener(YSDKDemoApi.sUserListener);
        YSDKApi.setBuglyListener(YSDKDemoApi.sBugylyListener);
        YSDKApi.setAntiAddictListener(YSDKDemoApi.sAntiAddictListener);
        YSDKApi.setAntiRegisterWindowCloseListener(YSDKDemoApi.sRegisterWindowCloseListener);
        // TODO:上线前需要关闭
        YSDKApi.setAntiAddictLogEnable(true);

		//游戏助手内截屏分享功能
        YSDKApi.setScreenCapturer(new IScreenImageCapturer() {
            @Override
            public Bitmap caputureImage() {
                // TODO GAME 游戏需要集成此方法并根据各自游戏引擎实现截图方法
                // ----以下部分代码仅作为一种示例不作为实际实现方式----
                View view = getWindow().getDecorView();
                view.setDrawingCacheEnabled(true);
                view.buildDrawingCache();
                Rect rect = new Rect();
                view.getWindowVisibleDisplayFrame(rect);
                int statusBarHeight = rect.top;
                WindowManager windowManager = getWindowManager();
                DisplayMetrics outMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(outMetrics);
                int width = outMetrics.widthPixels;
                int height = outMetrics.heightPixels;
                Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache(), 0, statusBarHeight, width,
                        height - statusBarHeight);
                view.destroyDrawingCache();
                view.setDrawingCacheEnabled(false);
                // ----以上部分代码仅作为示例不作为实际实现----
                return bitmap;
            }
        });
        //注册分享监听器接受分享状态信息
        ShareApi.getInstance().regShareCallBack(new ShareCallBack() {
            @Override
            public void onSuccess(ShareRet ret) {
                Log.d("Share","分享成功！  分享路径："+ret.shareType.name()+" 透传信息："+ret.extInfo);
            }

            @Override
            public void onError(ShareRet ret) {
                Log.d("Share","分享失败  分享路径："+ret.shareType.name()+" 错误码："+ret.retCode+" 错误信息："+ret.retMsg+" 透传信息："+ret.extInfo);
            }

            @Override
            public void onCancel(ShareRet ret) {
                Log.d("Share","分享用户取消！  分享路径："+ret.shareType.name()+" 透传信息："+ret.extInfo);
            }
        });
        // YSDKDemo 界面实现
        initView();
    }


    // TODO GAME 在onNewIntent中需要调用YSDKApi.handleIntent
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(YSDKDemoApi.TAG, "onNewIntent");
        YSDKApi.handleIntent(intent);
    }



    // TODO GAME 在onActivityResult中需要调用YSDKApi.onActivityResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(YSDKDemoApi.TAG,"onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
        YSDKApi.onActivityResult(requestCode, resultCode, data);
    }

    // TODO GAME 游戏需要集成此方法并调用YSDKApi.onRestart()
    @Override
    protected void onRestart() {
        super.onRestart();
        YSDKApi.onRestart(this);
    }

    // TODO GAME 游戏需要集成此方法并调用YSDKApi.onResume()
    @Override
    protected void onResume() {
        super.onResume();
        YSDKApi.onResume(this);
    }

    // TODO GAME 游戏需要集成此方法并调用YSDKApi.onPause()
    @Override
    protected void onPause() {
        super.onPause();
        YSDKApi.onPause(this);
    }

    // TODO GAME 游戏需要集成此方法并调用YSDKApi.onStop()
    @Override
    protected void onStop() {
        super.onStop();
        YSDKApi.onStop(this);
    }

    // TODO GAME 游戏需要集成此方法并调用YSDKApi.onDestory()
    @Override
    protected void onDestroy() {
        super.onDestroy();
        YSDKApi.onDestroy(this);
        Log.d(YSDKDemoApi.TAG, "onDestroy");

        if(null != mAutoLoginWaitingDlg){
            mAutoLoginWaitingDlg.cancel();
        }
    }


    // ***********************界面布局相关*************************
    // 初始化界面
    private void initView() {
        nameList = ModuleManager.sModulesList;

        mModuleListView = (GridView) findViewById(R.id.gridview);
        mModuleView = (LinearLayout) findViewById(R.id.module);
        mResultView = (LinearLayout) findViewById(R.id.result);
        //设置actionbar
        //隐藏后退按钮，并设置为不可点击
        LinearLayout llayout = (LinearLayout) findViewById(R.id.actionBarReturn);
        llayout.setFocusable(false);
        llayout.setClickable(false);
        llayout.setVisibility(View.GONE);

        TextView title = (TextView) findViewById(R.id.TactionBarTitle);
        LayoutParams textParams = (LayoutParams) title.getLayoutParams();
        textParams.leftMargin = Util.dp(9);
        title.setLayoutParams(textParams);
        title.setText("YSDKDemo 未登录");

        mModuleListView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        ListViewAdapter adapter = new ListViewAdapter(this,nameList);
        mModuleListView.setAdapter(adapter);
        resetMainView();

        mModuleListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                seletedModule = nameList.get(nameList.keyAt(position));
                ePlatform platform = ModuleUtils.getPlatform();
                boolean showModule = false;
                if ("QQ登录".equals(seletedModule.name)) {
                    if (ePlatform.QQ == platform) {
                        // 如已登录直接进入相应模块视图
                        showModule = true;
                    } else if (ePlatform.None == platform) {
                        loginByType(ePlatform.QQ);
                    }else{
                        Log.d(LOG_TAG,"QQ登录中~~~");
                    }
                } else if ("微信登录".equals(seletedModule.name)) {
                    if (ePlatform.WX == platform) {
                        // 如已登录直接进入相应模块视图
                        showModule = true;
                    } else if (ePlatform.None == platform) {
                        loginByType(ePlatform.WX);
                    }else{
                        Log.d(LOG_TAG,"微信登录中~~~");
                    }
                } else if ("游客登录".equals(seletedModule.name)) {
                    if (ePlatform.Guest == platform) {
                        // 如已登录直接进入相应模块视图
                        showModule = true;
                    } else if (ePlatform.None == platform) {
                        loginByType(ePlatform.Guest);
                    }else{
                        Log.d(LOG_TAG,"游客登录中~~~");
                    }
                }else if ("支付模块".equals(seletedModule.name)) {
                    ModuleInvokeHelper.execute(IDemoApiType.MODULE_INVOKE_PAY);
                } else if("云游/沙盒免登录".equals(seletedModule.name)){
                    //先进行传值
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("tencentysdk1104936059://?loginInfo={\"hostAppid\":\"1101070898\",\"hostUserID\":\"408037640\",\"hostUserType\":1,\"hostUserToken\":\"MhsAP9pMcz\",\"cloudUserID\":\"6797D9663C7036A074E996DD1FFDD5E4\",\"cloudToken\":\"4f78c9c74d9626c34f25fd0f540b1598\"}"));
                    startActivityForResult(intent,FreeLoginInfoActivity.REQ_CODE_LOGIN_INFO);

//                    if (ePlatform.FreeLogin == platform) {
//                        // 如已登录直接进入相应模块视图
//                        showModule = true;
//                    } else if (ePlatform.None == platform) {
//                        loginByType(ePlatform.FreeLogin);
//                    }
                } else{
                    // 进行其它功能模块
                    showModule = true;
                }

                if (showModule) {
                    showModule(null);
                }
            }
        });
    }

    private void loginByType(ePlatform platform) {
        YSDKApi.login(platform);
        showProgressBar();
    }


    /*******IShowView method start *********/

    public void showToastTips(String tips) {
        Toast.makeText(this,tips,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showModule(BaseModule module) {
        if (module != null) {
            seletedModule = module;
        }
        mModuleListView.setVisibility(View.GONE);
        mResultView.setVisibility(View.GONE);
        mModuleView.removeAllViews();
        seletedModule.init(mModuleView);
        mModuleView.setVisibility(View.VISIBLE);

        //设置actionbar、模块通用布局
        LinearLayout llayout = (LinearLayout) findViewById(R.id.actionBarReturn);
        llayout.setVisibility(View.VISIBLE);
        TextView title = (TextView) findViewById(R.id.TactionBarTitle);
        title.setTextColor(Color.WHITE);
        title.setText(seletedModule.name);
        llayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                hideModule();
            }

        });
    }

    @Override
    public void hideModule() {
        mModuleView.removeAllViews();
        mModuleView.setVisibility(View.GONE);
        mResultView.setVisibility(View.GONE);
        mModuleListView.setVisibility(View.VISIBLE);
        resetMainView();
        //设置actionbar
        //隐藏后退按钮，并设置为不可点击
        LinearLayout llayout = (LinearLayout) findViewById(R.id.actionBarReturn);
        llayout.setFocusable(false);
        llayout.setClickable(false);
        llayout.setVisibility(View.GONE);
        TextView title = (TextView) findViewById(R.id.TactionBarTitle);
        ePlatform platform = ModuleUtils.getPlatform();
        title.setTextColor(Color.RED);
        if (platform == ePlatform.QQ) {
            title.setText("YSDKDemo QQ登录中");
        } else if (platform == ePlatform.WX) {
            title.setText("YSDKDemo 微信登录中");
        }  else if (platform == ePlatform.Guest) {
            title.setText("YSDKDemo 游客登录中");
        } else {
            title.setText("YSDKDemo 未登录");
            title.setTextColor(Color.WHITE);
        }

    }

    @Override
    public void showResult(final String result, final YSDKDemoFunction function) {
        runOnUiThread(new Runnable() {
                          @Override
                          public void run() {
                              mModuleView.setVisibility(View.GONE);
                              mModuleListView.setVisibility(View.GONE);
                              mResultView.removeAllViews();

                              String name = "";
                              String desc = "";
                              if (function != null) {
                                  name = function.rawApiName;
                                  desc = function.desc;
                              }
                              ResultView block = new ResultView( mResultView);
                              block.addView("CallAPI", name);
                              block.addView("Desripton", desc);
                              block.addView("Result", result);

                              //设置actionbar、模块通用布局
                              LinearLayout llayout = (LinearLayout) findViewById(R.id.actionBarReturn);
                              llayout.setVisibility(View.VISIBLE);
                              TextView title = (TextView) findViewById(R.id.TactionBarTitle);
                              title.setText(function.displayName);
                              llayout.setOnClickListener(new OnClickListener() {

                                  @Override
                                  public void onClick(View v) {
                                      hideResult();
                                  }

                              });
                              mResultView.setVisibility(View.VISIBLE);
                          }
                      });
    }

    @Override
    public void hideResult() {
        mModuleView.setVisibility(View.VISIBLE);
        mResultView.removeAllViews();
        mResultView.setVisibility(View.GONE);
        mModuleListView.setVisibility(View.GONE);

        //设置actionbar、模块通用布局
        LinearLayout llayout = (LinearLayout) findViewById(R.id.actionBarReturn);
        llayout.setVisibility(View.VISIBLE);
        TextView title = (TextView) findViewById(R.id.TactionBarTitle);
        title.setText(seletedModule.name);
        llayout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                hideModule();
            }

        });
    }

    @Override
    @SuppressLint("NewApi")
    public void resetMainView() {
        ListViewAdapter adapter = (ListViewAdapter)mModuleListView.getAdapter();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressBar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(LOG_TAG,"startWaiting");
                if (mAutoLoginWaitingDlg == null) {
                    mAutoLoginWaitingDlg = new ProgressDialog(MainActivity.this);
                }
                if (mAutoLoginWaitingDlg.isShowing()) {
                    return;
                }
                mAutoLoginWaitingDlg.setTitle("YSDK DEMO登录中...");
                mAutoLoginWaitingDlg.show();
            }
        });
    }

    @Override
    public void hideProgressBar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(LOG_TAG,"stopWaiting");
                if (mAutoLoginWaitingDlg != null && mAutoLoginWaitingDlg.isShowing()) {
                    mAutoLoginWaitingDlg.dismiss();
                    mAutoLoginWaitingDlg = null;
                }
            }
        });

    }

    /*******IShowView method end *********/

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            hideProgressBar();
            if (mModuleListView.getVisibility() == View.VISIBLE) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("退出YSDKDemo");
                        builder.setMessage("你确定退出YSDK Demo么？");
                        builder.setPositiveButton("确定退出",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int whichButton) {
                                        finish();
                                        System.exit(0);
                                    }
                                });
                        builder.setNeutralButton("暂不退出",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int whichButton) {
                                    }
                                });
                        builder.show();
                    }
                });
            } else if (mModuleView.getVisibility() == View.VISIBLE) {
                hideModule();
            } else if (mResultView.getVisibility() == View.VISIBLE) {
                hideModule();
            }
        }
        return false;
    }

}