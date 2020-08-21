package com.tencent.tmgp.yybtestsdk.api;

import androidx.annotation.IntDef;

/**
 * YSDKDemo里包含的各种APi的功能总览
 * <p>
 * create by stringwu 05/21/2019
 */
public interface IDemoApiType {

    int TYPE_USER = 1; //用户相关
    int TYPE_SHARE = 1 << 1; //分享相关
    int TYPE_PAY = 1 << 2;  //支付相关
    int TYPE_OTHERS = 1 << 3; //其他功能相关
    int TYPE_INNER_TEST = 1 << 4; //内部测试相关
    int TYPE_ICON = 1 << 5; //icon相关
    int TYPE_LAUNCH_GIFT = 1 << 6; //启动特权
    int TYPE_MODULE_INVOKE = 1 << 7; // 模块的调用相关


    @IntDef({TYPE_USER, TYPE_SHARE, TYPE_PAY, TYPE_OTHERS, TYPE_INNER_TEST, TYPE_ICON,
            TYPE_MODULE_INVOKE})
    @interface TYPE {

    }

    //登录，登出，读取票据，
    int USER_LOGIN_BY_QQ = 2; //QQ登录
    int USER_LOGIN_BY_WX = 2 << 1; //微信登录
    int USER_LOGIN_BY_GUEST = 2 << 2; // 游客登录
    int USER_LOGOUT = 2 << 3; //退出登录
    int USER_GET_LOGIN_RECORD = 2 << 4; //读取登录记录
    int USER_GET_WX_USER_INFO = 2 << 5; //获取微信的用户信息
    int USER_GET_QQ_USER_INFO = 2 << 6; //获取QQ的用户信息


    @IntDef({USER_LOGIN_BY_QQ, USER_LOGIN_BY_WX, USER_LOGIN_BY_GUEST,
            USER_LOGOUT, USER_GET_LOGIN_RECORD, USER_GET_WX_USER_INFO, USER_GET_QQ_USER_INFO})
    @interface SUBTYPE_USER {

    }

    int SHARE_TO_QQ = 3; //分享到QQ
    int SHARE_TO_WX = 3 << 1;//分享到微信
    int SHARE_TO_WX_TIME_LINE = 3 << 2; //分享到朋友圈
    int SHARE_TO_QZONE = 3 << 3; //分享到QQ空间
    int OPEN_SHARE_VIEW = 3 << 4; //打开通用分享面板

    @IntDef({SHARE_TO_QQ, SHARE_TO_WX, SHARE_TO_WX_TIME_LINE, SHARE_TO_QZONE, OPEN_SHARE_VIEW})
    @interface SUBTYPE_SHARE {

    }


    int PAY_OF_RECHARGE = 4; //充值
    int PAY_OF_BUYGOODS_FROM_SERVER = 4 << 1; //道具直购-服务器下单
    int PAY_OF_BUYGOODS_FROM_CLIENT = 4 << 2; //道具直购-客户端下单
    int PAY_GET_PF_PFKEY = 4 << 3; //获取PF & KEY;

    @IntDef({PAY_OF_RECHARGE, PAY_OF_BUYGOODS_FROM_SERVER, PAY_OF_BUYGOODS_FROM_CLIENT,
            PAY_GET_PF_PFKEY})
    @interface SUBTYPE_PAY {

    }

    //todo:应用是否安装，应用的安装版本
    int OTHERS_GET_INSTALL_CHANNEL = 5; //获取安装的渠道
    int OTHERS_GET_REGISTER_CHANNEL = 5 << 1;//获取注册的渠道
    int OTHERS_GET_SDK_VERSION = 5 << 2; //获取SDK的版本
    int OTHERS_IS_QQ_INSTALL = 5 << 3; //QQ是否有安装
    int OTHERS_IS_WX_INSTALL = 5 << 4;//微信是否有安装
    int OTHERS_GET_QQ_VERSION = 5 << 5; //获取QQ的版本
    int OTHERS_GET_WX_VERSION = 5 << 6; //获取QQ的版本
    int OTHERS_NATIVE_CRASH_TEST = 5 << 7; //native crash 测试
    int OTHERS_MATH_CRASH_TEST = 5 << 8;//算术 crash 测试
    int OTHERS_NPE_CRASH_TEST = 5 << 9;//npe  crash 测试


    @IntDef({OTHERS_GET_INSTALL_CHANNEL, OTHERS_GET_REGISTER_CHANNEL, OTHERS_GET_SDK_VERSION,
            OTHERS_IS_QQ_INSTALL, OTHERS_IS_WX_INSTALL, OTHERS_GET_QQ_VERSION, OTHERS_GET_WX_VERSION,
            OTHERS_NATIVE_CRASH_TEST, OTHERS_MATH_CRASH_TEST, OTHERS_NPE_CRASH_TEST})
    @interface SUBTYPE_OTHERS {

    }

    int INNER_SEND_TOKEN = 6; //发送用户票据,仅供开发测试使用
    int INNER_GET_CLOUD_SETTING = 6 << 1; // 拉取云端配置
    int INNER_GET_SO_MD5 = 6 << 2; // 获取配型So的MD5
    int INNER_ENC_DEC_TEST = 6 << 3;// 加解密测试
    int INNER_PRINT_CONFIG = 6 << 4; //打印所有的配置
    int INNER_QUERY_BALANCE = 6 << 5; // 查询余额
    int INNER_DEDUCT_GAME_COIN = 6 << 6; // 扣除游戏币
    int INNER_CANCEL_PAY = 6 << 7; //取消支付
    int INNER_BUY_GOODS = 6 << 8; //道具直购
    int INNER_BUY_GOODS_ALL_PARAMS = 6 << 9; //道具直购全参数
    int INNER_CALL_WEBVIEW = 6 << 10; //唤醒webview
    int INNER_SERVER_AUTH = 6 << 11; //ysdk后台鉴权登录态

    @IntDef({INNER_SEND_TOKEN, INNER_GET_CLOUD_SETTING, INNER_GET_SO_MD5, INNER_ENC_DEC_TEST,
            INNER_PRINT_CONFIG, INNER_QUERY_BALANCE, INNER_DEDUCT_GAME_COIN, INNER_CANCEL_PAY,
            INNER_BUY_GOODS, INNER_BUY_GOODS_ALL_PARAMS, INNER_CALL_WEBVIEW,INNER_SERVER_AUTH})
    @interface SUBTYPE_INNER_TEST {

    }

    int ICON_SHOW = 7; //展示图标
    int ICON_HIDE = 7 << 1; //隐藏图标
    int ICON_CHANGE_ORIENTATION = 7 << 2; //切换横竖屏
    int ICON_PERFORM_FEATURE_V = 7 << 3;//V+特权
    int ICON_PERFORM_FEATURE_BBS = 7 << 4;//社区
    int ICON_PERFORM_FEATURE_ACTION = 7 << 5; //活动

    @IntDef({ICON_SHOW, ICON_HIDE, ICON_CHANGE_ORIENTATION, ICON_PERFORM_FEATURE_V,
            ICON_PERFORM_FEATURE_BBS, ICON_PERFORM_FEATURE_ACTION})
    @interface SUBTYPE_ICON {

    }
    int LAUNCH_GIFT_SHOW_LAUNCH_GIFT_VIEW = 8; //拉起支付模块
    int LAUNCH_GIFT_AUTO_SHOW_LAUNCH_GIFT_VIEW = 8 << 1; //拉起图标模块

    @IntDef({LAUNCH_GIFT_SHOW_LAUNCH_GIFT_VIEW, LAUNCH_GIFT_AUTO_SHOW_LAUNCH_GIFT_VIEW})
    @interface SUBTYPE_LAUNCH_GIFT {

    }
    int MODULE_INVOKE_PAY = 9; //拉起支付模块
    int MODULE_INVOKE_ICON = 9 << 1; //拉起图标模块
    int MODULE_INVOKE_LAUNCH_GIFT = 9 << 2; //拉起启动特权模块

    @IntDef({MODULE_INVOKE_PAY, MODULE_INVOKE_ICON, MODULE_INVOKE_LAUNCH_GIFT})
    @interface SUBTYPE_MODULE_INVOKE {

    }


}
