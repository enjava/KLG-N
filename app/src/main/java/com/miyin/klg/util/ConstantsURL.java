package com.miyin.klg.util;

public class ConstantsURL {
	/**
	 * 基础URL
	 */
	public static final String USER_BASE_URl ="http://wpt.zmdb8.com/kuma/api/user/";
	/**
	 *1 用户注册接口
	 */
	public static final String USER_REGISTER_URL =USER_BASE_URl+"register";
	/**
	 *2  用户登录接口
	 */
	public static final String USER_LOGIN_URL =USER_BASE_URl+"login";
	/**
	 * 3 发送验证码接口
	 */
	public static final String USER_SENDEXAMINE_URL =USER_BASE_URl+"sendExamine";
	/**
	 *4 校验验证码
	 */
	public static final String USER_checkExamine_URL =USER_BASE_URl+"checkExamine";
	/**
	 *5 判断手机号是否存在
	 */
	public static final String USER_MOBILEEXISTED_URL =USER_BASE_URl+"mobileExisted";
	/**
	 *6 退出登录接口
	 */
	public static final String USER_LOGOUT_URL =USER_BASE_URl+"logout";
	/**
	 *7 忘记密码。重新设置登录密码
	 */
	public static final String USER_FORGETPWD_URL =USER_BASE_URl+"forgetPwd";
	/**
	 *8 查看用户自己的信息
	 */
	public static final String USER_MYUSERINFO_URL =USER_BASE_URl+"myUserInfo";
	/**
	 *9 获取大转盘。（每个区域显示的文字）
	 */
	public static final String USER_GETTURNTABLE_URL =USER_BASE_URl+"getTurnTable";
	/**
	 *10 查看某个用户的个人信息
	 */
	public static final String USER_USERINFO_URL =USER_BASE_URl+"userInfo";
	/**
	 *11  修改昵称
	 */
	public static final String USER_MODIFYUSERNAME_URL =USER_BASE_URl+"modifyUsername";
	/**
	 *12 修改头像
	 */
	public static final String USER_MODIFYHEADIMG_URL =USER_BASE_URl+"modifyHeadImg";
	/**
	 *13 修改登录密码
	 */
	public static final String USER_MODIFYPWD_URL =USER_BASE_URl+"modifyPwd";
	/**
	 *14 修改地址
	 */
	public static final String USER_MODIFYADDRESS_URL =USER_BASE_URl+"modifyAddress";
	/**
	 *15 修改支付密码（二级密码）
	 */
	public static final String USER_MODIFYPWDPAY_URL =USER_BASE_URl+"modifyPwdPay";
	/**
	 *16 找回支付密码
	 */
	public static final String USER_FORGETPWDPAY_URL =USER_BASE_URl+"forgetPwdPay";
	/**
	 *17 修改身份证号
	 */
	public static final String USER_MODIFYIDNUMBER_URL =USER_BASE_URl+"modifyIdNumber";
	/**
	 *18 修改真实姓名
	 */
	public static final String USER_MODIFYREALNAME_URL =USER_BASE_URl+"modifyRealName";
	/**
	 *19 修改绑定的手机号
	 */
	public static final String USER_MODIFYMOBILE_URL =USER_BASE_URl+"modifyMobile" ;
	/**
	 *20 查看终端里的商品
	 */
	public static final String USER_TERMINALID_URL =USER_BASE_URl+"terminalId" ;
	/**
	 *22 发送反馈消息
	 */
	public static final String USER_BACKMESSAGE_URL =USER_BASE_URl+"backMessage" ;
	/**
	 *23 获取用户钱包信息
	 */
	public static final String USER_GETWALLETINFO_URL =USER_BASE_URl+"getWalletInfo" ;
	/**
	 *24 获取消息列表
	 */
	public static final String USER_MESSAGELIST_URL =USER_BASE_URl+"messageList" ;
	/**
	 *25 获取订单列表
	 */
	public static final String USER_orderInfo_URL =USER_BASE_URl+"orderInfo" ;

	/**
	 *26 获取版本号
	 */
	public static final String USER_VERSION_URL =USER_BASE_URl+"getNewVarsion";
}
