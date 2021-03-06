package com.miyin.klg.util;

/**
 * Created by en on 2017/2/9.
 */

public class ConstantsStoreURL {


    public static final String STORE_BASE_URL ="http://wpt.zmdb8.com/kuma/api/store/";
    public static final String STORE_BASEA_URL ="http://wpt.zmdb8.com/kuma/store/";
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
    /**
     *5绑定银行卡
     */
    public static final String STORE_BINDBANK_URL = STORE_BASE_URL+"bindBank";

    /**
     *6上传承诺书 storePaperPic	file
     */
    public static final String STORE_MODIFYSTOREPAPERPIC_URL = STORE_BASEA_URL+"modifyStorePaperPic";


    /**
     *7上传店铺照片 upStorePhoto 	file
     */
    public static final String STORE_UPSTOREPHOTO_URL = STORE_BASEA_URL+"upStorePhoto";

    /**
     *8上传手持身份证照片 storeCardPic	file
     */
    public static final String STORE_MODIFYSTORECARDPIC_URL = STORE_BASEA_URL+"modifyStoreCardPic";
//
//    /**
//     *9 身份证照片 cardPic	file
//     */
//    public static final String STORE_MODIFYCARDPIC_URL = STORE_BASEA_URL+"modifyCardPic";
    /**
     *10 营业执照 licenceImg	file
     */
    public static final String STORE_MODIFYLICENCEIMG_URL = STORE_BASEA_URL+"modifyLicenceImg";
    /**
     *11 商家入驻，即修改商家个人信息 licenceImg	file
     */
    public static final String STORE_MODIFYSTOREINFO_URL = STORE_BASEA_URL+"modifyStoreInfo";

    /**
     *12 获取个人信息 licenceImg	file addEntryOrder
     */
    public static final String STORE_MYUSERINFO_URL = STORE_BASE_URL+"storeInfo";
    /**
     *13 实体店消费登记
     */
    public static final String STORE_ADDENTRYORDER_URL = STORE_BASE_URL+"addEntryOrder";
    /**
     *14 合伙人查看实体店消费记录表
     */
    public static final String STORE_ENTRYORDERLIST_URL = STORE_BASE_URL+"entryOrderList";
    //	http://wpt.zmdb8.com/kuma/api/store/entryOrderList

    //http://wpt.zmdb8.com/kuma/api/store/

    /**
     *15 合伙人查看实体店消费记录表
     */
    public static final String STORE_ORDERLIST_URL = STORE_BASE_URL+"orderList";

}
