package com.tencent.tmgp.yybtestsdk.appearance;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.tencent.tmgp.yybtestsdk.AppUtils;
import com.tencent.tmgp.yybtestsdk.R;
import com.tencent.tmgp.yybtestsdk.api.YSDKDemoApi;
import com.tencent.tmgp.yybtestsdk.module.BaseModule;
import com.tencent.tmgp.yybtestsdk.module.YSDKDemoFunction;

import java.util.ArrayList;

public class FuncSelectView {
    private BaseModule module;

    public FuncSelectView(BaseModule module) {
        this.module = module;
    }

    public void createDialogView(String viewTitle, ArrayList<YSDKDemoFunction> apiList) {
        LayoutParams btnParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        btnParams.setMargins(Util.dp(15), 0, Util.dp(15), Util.dp(4));
        final Activity activity = AppUtils.getCurActivity();
        LayoutInflater inflater = LayoutInflater.from(activity);
        LinearLayout rootView = (LinearLayout)inflater.inflate(R.layout.select_window, null);

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(rootView);
        Window dWindow = dialog.getWindow();
        WindowManager.LayoutParams params = dWindow.getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        if (apiList.size() > 7) {
            params.height = Util.dp(320);
        } else {
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        }
        dWindow.setGravity(Gravity.CENTER);
        dWindow.setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
        dWindow.setAttributes(params);
        dialog.show();

        LinearLayout selectItem = (LinearLayout) rootView.findViewById(R.id.select_item);

        TextView title = (TextView) rootView.findViewById(R.id.title_dialog);
        title.setText(viewTitle);

        for (final YSDKDemoFunction api : apiList) {
            Button btn = (Button) inflater.inflate(R.layout.btn_demo, null);
            btn.setText(api.displayName);
            btn.setTextSize(16);
            btn.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (api.apiSet != null) {
                        FuncSelectView selectDialog = new FuncSelectView(module);
                        selectDialog.createDialogView(api.displayName, api.apiSet);
                    } else if (!TextUtils.isEmpty(api.inputName)) {
                        FuncSelectView selectDialog = new FuncSelectView(module);
                        selectDialog.createInputView(api);
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                    } else {
                        try {
                            String result = YSDKDemoApi.execute(api.type, api.subType, null);
                            if (!TextUtils.isEmpty(result)) {
                                YSDKDemoApi.sShowView.showResult(result, api);
                                YSDKDemoApi.sLastFunction = null;
                            } else {
                                YSDKDemoApi.sLastFunction = api;
                            }
                            if ( dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        } catch (Exception e) {
                            // 重新抛出异常用于测试Crash上报
                            if (e instanceof ArithmeticException) {
                                throw (ArithmeticException) e;
                            } else if (e instanceof NullPointerException) {
                                throw (NullPointerException) e;
                            } else {
                                e.printStackTrace();
                            }
                        }
                    }
                }

            });
            selectItem.addView(btn, btnParams);
        }
    }

    public void createInputView(final YSDKDemoFunction api) {
        Activity activity = AppUtils.getCurActivity();
        LayoutInflater inflater = LayoutInflater.from(activity);
        LinearLayout rootView = (LinearLayout) inflater.inflate(R.layout.select_window, null);

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(rootView);

        Window dWindow = dialog.getWindow();
        WindowManager.LayoutParams params = dWindow.getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dWindow.setGravity(Gravity.CENTER);
        dWindow.setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
        dWindow.setAttributes(params);
        dialog.show();

        LinearLayout selectItem = (LinearLayout) rootView.findViewById(R.id.select_item);

        TextView title = (TextView) rootView.findViewById(R.id.title_dialog);
        title.setText(api.displayName);

        LayoutParams txetParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        txetParams.setMargins(Util.dp(12), Util.dp(20), Util.dp(10), Util.dp(0));
        txetParams.gravity = Gravity.LEFT;
        TextView inputItem = new TextView(activity);
        inputItem.setTextSize(Util.dp(6));
        inputItem.setText(api.inputName);
        selectItem.addView(inputItem, txetParams);

        LayoutParams editParams = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        editParams.setMargins(Util.dp(10), Util.dp(6), Util.dp(10), Util.dp(0));
        final EditText editText = new EditText(activity);
        editText.setTextSize(Util.dp(7));
        editText.setBackgroundDrawable(activity.
                getResources().getDrawable(R.drawable.editshape));
        if (null != api.defaultValue && !"".equals(api.defaultValue)) {
            editText.setText(api.defaultValue);
        }
        selectItem.addView(editText, editParams);

        LayoutParams btnParams = new LayoutParams(Util.dp(120),
                LayoutParams.WRAP_CONTENT);
        btnParams.setMargins(Util.dp(10), Util.dp(12), Util.dp(10), Util.dp(30));
        btnParams.gravity = Gravity.CENTER;
        Button openButton = (Button) inflater.inflate(R.layout.btn_demo, null);
        ;
        openButton.setText("确定");
        openButton.setTextSize(16);
        openButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String param = editText.getText().toString();
                try {
                    String result = YSDKDemoApi.execute(api.type, api.subType, param);
                    if (!TextUtils.isEmpty(result)) {
                        YSDKDemoApi.sShowView.showResult(result, api);
                        YSDKDemoApi.sLastFunction = null;
                    } else {
                        YSDKDemoApi.sLastFunction = api;
                    }
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                } catch (Exception e) {
                    // 重新抛出异常用于测试Crash上报
                    if (e instanceof ArithmeticException) {
                        throw (ArithmeticException) e;
                    } else if (e instanceof NullPointerException) {
                        throw (NullPointerException) e;
                    } else {
                        e.printStackTrace();
                    }
                }
            }

        });
        selectItem.addView(openButton, btnParams);

    }
}
