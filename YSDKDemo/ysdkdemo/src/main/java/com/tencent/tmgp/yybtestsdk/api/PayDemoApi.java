package com.tencent.tmgp.yybtestsdk.api;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.tencent.tmgp.yybtestsdk.AppUtils;
import com.tencent.tmgp.yybtestsdk.R;
import com.tencent.tmgp.yybtestsdk.module.submodule.PayModule;
import com.tencent.ysdk.api.YSDKApi;
import com.tencent.ysdk.module.pay.PayItem;

import java.io.ByteArrayOutputStream;

/**
 * 支付相关的调用示例接口
 *
 * create by stringwu 05/21/2019
 */
public class PayDemoApi {

    public static String execute(@IDemoApiType.SUBTYPE_PAY int subType,String extraParams) {

        switch (subType) {
            case IDemoApiType.PAY_OF_RECHARGE:
                return recharge(extraParams);
            case IDemoApiType.PAY_OF_BUYGOODS_FROM_SERVER:
                return buyGoodsFromServer(extraParams);
            case IDemoApiType.PAY_OF_BUYGOODS_FROM_CLIENT:
                return buyGoodsFromClient(extraParams);
            case IDemoApiType.PAY_GET_PF_PFKEY:
                return getPfAndPfKey();
                default:
                    throw new IllegalArgumentException("not support subType:" + subType);
        }
    }

    private static String getPfAndPfKey() {
        String pf = com.tencent.ysdk.api.YSDKApi.getPf();
        String pfKey = com.tencent.ysdk.api.YSDKApi.getPfKey();

        return "Pf = " + pf + "\n pfKey = " + pfKey;
    }

    private static String recharge(String input) {
        String[] paraArr = input.split(" ");
        if(paraArr.length > 0 && null != paraArr[0]){
            boolean isCanChange = true;
            if(null != paraArr[2]){
                try {
                    int value = Integer.parseInt(paraArr[2]);
                    if(value > 0){
                        isCanChange = false;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            Activity activity = AppUtils.getCurActivity();
            Bitmap bmp = BitmapFactory.decodeResource(activity.getResources(), R.drawable.sample_yuanbao);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] appResData = baos.toByteArray();
            String ysdkExt = "ysdkExt";
            com.tencent.ysdk.api.YSDKApi.recharge(paraArr[0],paraArr[1],isCanChange,appResData,
                    ysdkExt,YSDKDemoApi.sPayListener);

        }else{
            Log.e(YSDKDemoApi.TAG,"para is bad:"+ input);
        }
        return "";
    }

    private static String buyGoodsFromServer(String input) {
        String[] paraArr = input.split(" ");
        if(paraArr.length > 1 && null != paraArr[0]){
            Activity activity = AppUtils.getCurActivity();
            Bitmap bmp = BitmapFactory.decodeResource(activity.getResources(), R.drawable.sample_yuanbao);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] appResData = baos.toByteArray();
            String ysdkExt = "ysdkExt";
            com.tencent.ysdk.api.YSDKApi.buyGoods(paraArr[0],paraArr[1],appResData,ysdkExt,
                    YSDKDemoApi.sPayListener);
        }else{
            Log.e(YSDKDemoApi.TAG,"para is bad:"+ input);
        }
        return "";
    }

    private static String buyGoodsFromClient(String input) {
        String[] paraArr = input.split(" ");
        if(paraArr.length > 0 && null != paraArr[0]){
            PayItem item = new PayItem();
            item.id = paraArr[1];
            item.name = paraArr[2];
            item.desc = paraArr[3];
            item.price = Integer.parseInt(paraArr[4]);
            item.num = Integer.parseInt(paraArr[5]);
            Activity activity = AppUtils.getCurActivity();
            Bitmap bmp = BitmapFactory.decodeResource(activity.getResources(), R.drawable.sample_yuanbao);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] appResData = baos.toByteArray();
            String ysdkExt = "ysdkExt";
            String midasExt = "midasExt";
            com.tencent.ysdk.api.YSDKApi.buyGoods(false, paraArr[0],item, PayModule.MIDAS_APPKEY,
                    appResData,midasExt,ysdkExt,YSDKDemoApi.sPayListener);
        }else{
            Log.e(YSDKDemoApi.TAG,"para is bad:"+ input);
        }
        return "";
    }



}
