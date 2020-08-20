package com.tencent.tmgp.yybtestsdk.module.submodule;

import android.widget.LinearLayout;

import com.tencent.tmgp.yybtestsdk.api.IDemoApiType;
import com.tencent.tmgp.yybtestsdk.appearance.FuncBlockView;
import com.tencent.tmgp.yybtestsdk.module.BaseModule;
import com.tencent.tmgp.yybtestsdk.module.YSDKDemoFunction;

import java.util.ArrayList;

/**
 * Created by hardyshi on 16/7/25.
 */
public class PayModule extends BaseModule {

    public static final String MIDAS_APPKEY = "FByg037oe5zIHa4FR72daR50YzOjtAiY";
    public PayModule() {
        this.name = "支付模块";
    }

    @Override
    public void init(LinearLayout parent) {
        FuncBlockView payView = new FuncBlockView(parent, this);

        // 通用接口
        ArrayList<YSDKDemoFunction> aboutPay = new ArrayList<YSDKDemoFunction>();
        aboutPay.add(new YSDKDemoFunction(
                IDemoApiType.TYPE_PAY,IDemoApiType.PAY_GET_PF_PFKEY,
                "getPf & getPfKey",
                "获取pf+pfKey",
                "pf+pfKey支付的时候会用到(两个接口)"));
        payView.addView("通用功能", aboutPay);

        // 游戏币相关
        ArrayList<YSDKDemoFunction> aboutRecharge = new ArrayList<YSDKDemoFunction>();
        aboutRecharge.add(new YSDKDemoFunction(
                IDemoApiType.TYPE_PAY,IDemoApiType.PAY_OF_RECHARGE,
                "recharge",
                "充值游戏币",
                "充值游戏币",
                "大区id 充值数额 充值数额是否可改，三个参数用空格分割",
                "1 1 1"));
        payView.addView( "游戏币相关", aboutRecharge);
        // 道具直购相关
        ArrayList<YSDKDemoFunction> aboutBuyGoods = new ArrayList<YSDKDemoFunction>();
        aboutBuyGoods.add(new YSDKDemoFunction(
                IDemoApiType.TYPE_PAY,IDemoApiType.PAY_OF_BUYGOODS_FROM_SERVER,
                "buyGoods",
                "道具直购-服务器下单",
                "道具直购-服务器下单",
                "大区id tokenurl（通过后台获取）",
                "1 "));
        aboutBuyGoods.add(new YSDKDemoFunction(
                IDemoApiType.TYPE_PAY,IDemoApiType.PAY_OF_BUYGOODS_FROM_CLIENT,
                "buyGoods",
                "道具直购-客户端下单",
                "道具直购-客户端下单",
                "大区id 道具id 道具名称 道具描述 道具单价 道具数量",
                "1 G1 道具名称 道具描述 1 5"));
        payView.addView("道具直购相关", aboutBuyGoods);
    }


}
