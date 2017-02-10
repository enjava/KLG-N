package com.miyin.klg.util;

/**
 * Created by en on 2017/2/9.
 */

public class ConstantsStoreURL {
    public static final String STORE_BASE_URL ="http://wpt.zmdb8.com/kuma/api/store/";
    /**
     *1 登录
     */
    public static final String STORE_LOGIN_URL=STORE_BASE_URL+"login";
    /**
     *2 修改登录密码
     */
    public static final String STORE_MODIFYPWD_URL=STORE_BASE_URL+"modifyPwd";
    /**
     *3修改支付密码
     */
    public static final String STORE_MODIFYPWDPAY_URL=STORE_BASE_URL+"modifyPwdPay";

    /**
     *4修改支付密码
     */
    public static final String STORE_BACKMESSAGE_URL=STORE_BASE_URL+"backMessage";
}
