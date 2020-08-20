package com.tencent.tmgp.yybtestsdk.appearance;

import android.text.TextUtils;

import com.tencent.tmgp.yybtestsdk.AppUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {
	
	/** 
     * 根据手机的分辨率从 dp 的单位 转成为 px 
     */  
    public static int dp(float dpValue) {  
        final float scale = AppUtils.getCurActivity().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);  
    }
    /** 
     * 根据手机的分辨率从 sp 的单位 转成为 px 
     */
    public static int sp(float spValue) { 
        final float fontScale = AppUtils.getCurActivity().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取md5
     * @param str
     * @return
     */
    public static String getMD5(String str){
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
