package com.tencent.tmgp.yybtestsdk.appearance;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.tencent.tmgp.yybtestsdk.AppUtils;
import com.tencent.tmgp.yybtestsdk.R;
import com.tencent.tmgp.yybtestsdk.api.YSDKDemoApi;
import com.tencent.tmgp.yybtestsdk.module.BaseModule;
import com.tencent.tmgp.yybtestsdk.module.YSDKDemoFunction;

import java.util.ArrayList;
import java.util.HashMap;

public class FuncBlockView {
	private LinearLayout parentView;
	private BaseModule module;
	private HashMap<String ,Button> textViewArrayList=new HashMap<>();

	public FuncBlockView(LinearLayout parentView, BaseModule module)
	{
		this.parentView = parentView;
		this.module = module;
	}

	/**
	 * 根据title和apiList生成一个功能块视图
	 * @param blockTitle 功能块的标题
	 * @param apiList	该功能块具有的api
	 */
    public void addView(String blockTitle, ArrayList<YSDKDemoFunction> apiList)
	{
		final Activity activity = AppUtils.getCurActivity();
    	LayoutInflater inflater = activity.getLayoutInflater();
    	LinearLayout block = (LinearLayout)inflater.inflate(R.layout.block_view_layout,null);
    	TextView title = block.findViewById(R.id.tv_title);
    	title.setText(blockTitle);

		LayoutParams btnParams =
				new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		btnParams.setMargins(Util.dp(20), 0, Util.dp(20), Util.dp(5));

		for(final YSDKDemoFunction api : apiList)
		{
			Button btn = (Button)inflater.inflate(R.layout.btn_demo, null);
			btn.setLayoutParams(btnParams);
			btn.setText(api.displayName);
			btn.setTextSize(16);
			btn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if(api.apiSet != null) {
						FuncSelectView selectDialog = new FuncSelectView( module);
						selectDialog.createDialogView(api.displayName, api.apiSet);
					} else if (!TextUtils.isEmpty(api.inputName)) {
					    FuncSelectView selectDialog = new FuncSelectView( module);
                        selectDialog.createInputView(api);
					} else {
		                try {
							String result = YSDKDemoApi.execute(api.type,api.subType,null);
							if (!TextUtils.isEmpty(result)) {
								YSDKDemoApi.sShowView.showResult(result,api);
								YSDKDemoApi.sLastFunction = null;
							} else {
								YSDKDemoApi.sLastFunction = api;
							}

						} catch (Exception e) {
							// 重新抛出异常用于测试Crash上报
							if(e instanceof ArithmeticException) {
								throw (ArithmeticException)e;
							} else if(e instanceof NullPointerException) {
								throw (NullPointerException)e;
							} else {
	                        	e.printStackTrace();
	                        }
						}
					}
				}
			});
			block.addView(btn, btnParams);
			textViewArrayList.put(api.displayName, btn);
		}
		parentView.addView(block);
	}

    public Button findView(String displayName) {
        return textViewArrayList.get(displayName);
    }
}
