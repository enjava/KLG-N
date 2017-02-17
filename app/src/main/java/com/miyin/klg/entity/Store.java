package com.miyin.klg.entity;

/**
 * Created by en on 2017/2/9.
 */

public class Store {


    public  boolean isMyuserinfo=false;
    /**
     * data : {"address":"eerggvvmmm","agentId":2,"agentName":"","agentUserCode":"","area":"余杭区","busSellerName":"","busSellerUserCode":"","busUserCode":"","busUserName":"","business":"","businessId":3,"businessType1":"","businessType2":"2588","cardPic":"store/cardPic/cbd1e385-9885-43bb-ad78-9a7f3bebcef4.png","city":"杭州市","companyName":"EmmyNomutual","corporationPic":"","createTime":"","email":"","gender":"男","headImg":"images/advar.png","isLogin":1,"latitude":"","licenceImg":"store/licenceImg/d0df943f-3ffb-4f22-ab51-94acae8bde47.png","licenceNum":"91330110MA27XB9Q7U","list":[],"loginIp":"122.233.224.119","loginTime":"2017-02-16 15:27:05","longitude":"-1","mobile":"17051006723","password":"b8616bdfc95284f9573beffc886b61a4","phone":"17051006723","pwdPay":"415e2498005097c512d057fdd354c61a","qrCode":"QRCode/19de3666-2eb3-4ae2-97f0-f614943418b2.png","realName":"张洪恩","recommendPic":"","salt":"gMqrHt","salt1":"QlC08Z","sellerId":2,"sheng":"浙江省","shopHours":"2586","status":2,"statusStr":"待完善","storeCardPic":"store/storeCardPic/be8c42ba-ecf8-4838-a6fb-6597208f0cf9.png","storeInfo":"rrrfcccvghh","storeName":"HHNNN","storePaperPic":"store/storePaperPic/236bf79b-e7d3-4c33-bfa9-3f225dca38c6.png","storePhone":"17051006723","taxType":"","taxpayerNum":"","terminalNum":0,"times":9,"type":1,"updateTime":"2017-02-16 15:27:05","userCard":"412324198202035036","userCode":"SRReDn2M","userId":5}
     * msg : 操作成功
     * status : 0
     */

    public DataBean data;
    public String msg;
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


    public boolean isMyuserinfo() {
        return isMyuserinfo;
    }

    public void setMyuserinfo(boolean myuserinfo) {
        isMyuserinfo = myuserinfo;
    }

   
    public static class DataBean {
        /**
         * address : eerggvvmmm
         * agentId : 2
         * agentName :
         * agentUserCode :
         * area : 余杭区
         * busSellerName :
         * busSellerUserCode :
         * busUserCode :
         * busUserName :
         * business :
         * businessId : 3
         * businessType1 :
         * businessType2 : 2588
         * cardPic : store/cardPic/cbd1e385-9885-43bb-ad78-9a7f3bebcef4.png
         * city : 杭州市
         * companyName : EmmyNomutual
         * corporationPic :
         * createTime :
         * email :
         * gender : 男
         * headImg : images/advar.png
         * isLogin : 1
         * latitude :
         * licenceImg : store/licenceImg/d0df943f-3ffb-4f22-ab51-94acae8bde47.png
         * licenceNum : 91330110MA27XB9Q7U
         * list : []
         * loginIp : 122.233.224.119
         * loginTime : 2017-02-16 15:27:05
         * longitude : -1
         * mobile : 17051006723
         * password : b8616bdfc95284f9573beffc886b61a4
         * phone : 17051006723
         * pwdPay : 415e2498005097c512d057fdd354c61a
         * qrCode : QRCode/19de3666-2eb3-4ae2-97f0-f614943418b2.png
         * realName : 张洪恩
         * recommendPic :
         * salt : gMqrHt
         * salt1 : QlC08Z
         * sellerId : 2
         * sheng : 浙江省
         * shopHours : 2586
         * status : 2
         * statusStr : 待完善
         * storeCardPic : store/storeCardPic/be8c42ba-ecf8-4838-a6fb-6597208f0cf9.png
         * storeInfo : rrrfcccvghh
         * storeName : HHNNN
         * storePaperPic : store/storePaperPic/236bf79b-e7d3-4c33-bfa9-3f225dca38c6.png
         * storePhone : 17051006723
         * taxType :
         * taxpayerNum :
         * terminalNum : 0
         * times : 9
         * type : 1
         * updateTime : 2017-02-16 15:27:05
         * userCard : 412324198202035036
         * userCode : SRReDn2M
         * userId : 5
         */

        public String address;

        public String area;

        public String busUserCode;
        public String busUserName;

        public String city;

        public String realName;

        public String sheng;

        public int type;
        public String userCard;
        public String userCode;
        public int userId;

    }
}
