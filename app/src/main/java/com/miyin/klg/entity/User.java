package com.miyin.klg.entity;

/**
 * Created by en on 2017/2/8.
 */

public class User {

    public DataBean data;
    public String msg;
    private  boolean isMyuserinfo=false;

    public static class DataBean {

        public int userId;
        public String userCode;
        public String username;

        public String realName;
        public String cardNum;
        public String sheng;
        public String city;
        public String area;
        public String address;

        public String recommendName;
        public String recommendCode;

    }


    /**
     { "status": 0,//状态。0：成功。1：失败。2：未登录 "data": { "userId": 5,//用户id，表中的唯一标识 "userCode": "UABXzbm2",//用户ID，页面上显示用的
     "username": "aaaaa",//昵称 "password": null,//密码（加密后的） "salt": null,//密码后缀，拼接用 "pwdPay": null,//支付密码 "salt1": null,//支付密码后缀
     "mobile": "17096177662",//手机号 "headImg": "http://wpt.zmdb8.com/kuma/images/advar.png",//头像 "realName": null,//真实姓名 "cardNum": null,//身份证号
     "sheng": "浙江",//地区（省） "city": "杭州",//地区（市） "area": "下城区",//地区（区） "address": "石桥",//地址（详细地址） "recommend": null,//推荐人id
     "gender": "男",//性别 "email": null,//邮箱 "qrCode": "http://wpt.zmdb8.com/kuma/QRCode/9ccab6b0-7edb-4d80-a7dc-bed1ab4a2e54.png",//二维码。推荐别人的时候用
     "loginTime": "2017-01-07 10:00:16",//最近登录时间 "loginIp": "0:0:0:0:0:0:0:1",//最近登录ip "isLogin": 1,//登录状态。0：退出。1：登录 "status": 2,//状态。0："冻结":1："激活":2："待完善":3"审核中"
     "createTime": "2016-12-24 18:22:49", "updateTime": "2017-01-07 09:56:54", "recommendName": null,//推荐人昵称 "recommendCode": null,//推荐人用户ID "statusStr": null//状态。status对应的文字 },
    msg": "操作成功"//文字说明 }
     */


    public User() {
        this.isMyuserinfo = false;
    }

    public boolean isMyuserinfo() {
        return isMyuserinfo;
    }

    public void setMyuserinfo(boolean myuserinfo) {
        isMyuserinfo = myuserinfo;
    }
}
