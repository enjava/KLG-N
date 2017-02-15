package com.miyin.klg.entity;

/**
 * Created by en on 2017/2/9.
 */

public class Store {

    public int status;
    public DataBean data;
    public String msg;
    private  boolean isMyuserinfo=false;
/*

{ "status": 0,//请求状态。0：成功。1：失败。2：未登录 "data": {//用户信息 "userId": 1,//用户id "userCode": "Szf2zItc",//用户ID，页面显示用的
"password": null,//密码 "salt": null,//密码后缀 "pwdPay": null,//支付密码 "salt1": null,//支付密码后缀 "mobile": "17096177662",//负责人手机号。也是注册用的手机号
"headImg": "http://wpt.zmdb8.com/kuma/images/advar.png",//头像 "realName": "汤志光",//真实姓名 "userCard": "37112119910809003X",//身份证号
"cardPic": null,//身份证照片 "storeCardPic": null,//手持身份证照片 "storePaperPic": null,//承诺书。照片 "gender": "男",//性别 "sheng": null,//地址。省
"city": null,//地址。市 "area": null,//地址。区 "businessId": 40,//业务员id。即推荐人id "sellerId": 1,//业务商id "agentId": 1,//管理中心id "type": 0,//企业类型
 "email": null,//邮箱 "status": 2,//状态。0：冻结。1：激活。2：待完善 "companyName": "新东方",//公司名称 "storeName": "挖掘机",//店铺名称
 "address": null,//地址。详细地址 "business": null,//主营业务 "businessType1": null,//行业类别 "businessType2": null,//所在行业 "phone": null,//联系电话
 "licenceImg": null,//营业执照照片 "corporationPic": null,//法人身份证图片 "recommendPic": null,//推荐人身份证照片 "taxpayerNum": null,//纳税人标识号
 "licenceNum": null,//纳税人标识号 "taxType": null,//纳税人类别 "terminalNum": 4,//已有的终端数
 "qrCode": "http://wpt.zmdb8.com/kuma/QRCode/d2720d5f-0909-4fd9-805d-9d2535f86907.png",//用户的二维码图片 "loginTime": "2017-01-14 09:45:25",//最近登录时间
 "loginIp": "0:0:0:0:0:0:0:1",//最近登录ip "isLogin": 1,//登录状态。0：退出。1：登录 "createTime": null,//注册时间 "updateTime": "2017-01-14 09:42:01",//最近修改时间
 "list": null,//店铺照片 "busUserName": null,//所属业务员名称 "busUserCode": null,//所属业务员的用户ID "busSellerName": null,//所属业务商名称
 "busSellerUserCode": null,//所属业务商ID "agentName": null,//所属管理中心名称 "agentUserCode": null,//所属管理中心ID "statusStr": null//status对应的文字 },
 "msg": "登录成功" }
 */

    public Store() {
        this.isMyuserinfo = false;
    }

    public static class DataBean {
        /**
         * userId : 1
         * userCode : Szf2zItc
         * password : aa
         * salt : aa
         * pwdPay : bb
         * salt1 : cc
         * mobile : 17096177662
         * headImg : http://wpt.zmdb8.com/kuma/images/advar.png
         * realName : 汤志光
         * userCard : 37112119910809003X
         * cardPic : dd
         * storeCardPic : ee
         * storePaperPic : ff
         * gender : 男
         * sheng : ff
         * city : ff
         * area : ff
         * businessId : 40
         * sellerId : 1
         * agentId : 1
         * type : 0
         * email : ff
         * status : 2
         * companyName : 新东方
         * storeName : 挖掘机
         * address : ff
         * business : ff
         * businessType1 : ff
         * businessType2 : ff
         * phone : ff
         * licenceImg : ff
         * corporationPic : ff
         * recommendPic : ff
         * taxpayerNum : ff
         * licenceNum : ff
         * taxType : ff
         * terminalNum : 4
         * qrCode : http://wpt.zmdb8.com/kuma/QRCode/d2720d5f-0909-4fd9-805d-9d2535f86907.png
         * loginTime : 2017-01-14 09:45:25
         * loginIp : 0:0:0:0:0:0:0:1
         * isLogin : 1
         * createTime : ff
         * updateTime : 2017-01-14 09:42:01
         * list : ff
         * busUserName : ff
         * busUserCode : 2
         * busSellerName : ff
         * busSellerUserCode : ff
         * agentName : ff
         * agentUserCode : ff
         * statusStr : ff
         */

        public int userId;
        public String userCode;
        public String password;
        public String salt;
        public String pwdPay;
        public String salt1;
        public String mobile;
        public String headImg;
        public String realName;
        public String userCard;
        public String cardPic;
        public String storeCardPic;
        public String storePaperPic;
        public String gender;
        public String sheng;
        public String city;
        public String area;
        public int businessId;
        public int sellerId;
        public int agentId;
        public int type;
        public String email;
        public int status;
        public String companyName;
        public String storeName;
        public String address;
        public String business;
        public String businessType1;
        public String businessType2;
        public String phone;
        public String licenceImg;
        public String corporationPic;
        public String recommendPic;
        public String taxpayerNum;
        public String licenceNum;
        public String taxType;
        public int terminalNum;
        public String qrCode;
        public String loginTime;
        public String loginIp;
        public int isLogin;
        public String createTime;
        public String updateTime;
        public String list;
        public String busUserName;
        public String busUserCode;
        public String busSellerName;
        public String busSellerUserCode;
        public String agentName;
        public String agentUserCode;
        public String statusStr;
    }

    public boolean isMyuserinfo() {
        return isMyuserinfo;
    }

    public void setMyuserinfo(boolean myuserinfo) {
        isMyuserinfo = myuserinfo;
    }
}
