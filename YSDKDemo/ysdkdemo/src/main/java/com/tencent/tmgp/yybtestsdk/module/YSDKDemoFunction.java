package com.tencent.tmgp.yybtestsdk.module;

import java.util.ArrayList;

public class YSDKDemoFunction {
    public int type; //功能类型
    public int subType; //功能子类型
    public String rawApiName = ""; // 调用的SDK的原始Api接口名字
    public String displayName = ""; // 用于显示的名字
    public String desc = ""; // 描述一下接口的用途
    public String resultDesc = ""; // 存储结果
    public String inputName = ""; // 需要输入的项的名称
    public String defaultValue = ""; // 需要输入的项的名称

    public ArrayList<YSDKDemoFunction> apiSet = null;


    public YSDKDemoFunction(int type, int subType) {
        this(type, subType, "", "", "");

    }

    public YSDKDemoFunction(int type, int subType, String rawApiName, String displayName, String desc) {
        this(type, subType, rawApiName, displayName, desc, "");
    }

    public YSDKDemoFunction(int type, int subType, String rawApiName, String displayName,
                            String desc, String inputName) {
        this(type, subType, rawApiName, displayName, desc, inputName, "");

    }

    public YSDKDemoFunction(int type, int subType, String rawApiName, String displayName,
                            String desc, String inputName, String defaultValue) {
        this(type, subType, rawApiName, displayName, desc, "", inputName, defaultValue, null);

    }

    public YSDKDemoFunction(int type, int subType, String displayName, ArrayList<YSDKDemoFunction> apiSet) {
        this(type, subType, "", displayName, "", "", "", "", apiSet);
    }


    public YSDKDemoFunction(int type,
                            int subType,
                            String rawApiName,
                            String displayName,
                            String desc,
                            String resultDesc,
                            String inputName,
                            String defaultValue,
                            ArrayList<YSDKDemoFunction> apiSet) {
        this.type = type;
        this.subType = subType;
        this.rawApiName = rawApiName;
        this.displayName = displayName;

        this.desc = desc;
        this.resultDesc = resultDesc;
        this.inputName = inputName;
        this.defaultValue = defaultValue;
        this.apiSet = apiSet;
    }

}
