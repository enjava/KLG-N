package com.miyin.klg.entity;

/**
 * Created by en on 2017/2/8.
 */

public class User {

    public int status;
    public DataBean data;
    public String msg;
    private  boolean isMyuserinfo=false;

    public static class DataBean {
        /**
         * userId : 1
         * userCode : UCiWU8nM
         * username : aaa
         * password : bbb
         * salt : tt
         * pwdPay : gg
         * salt1 : ee
         * mobile : 17051006723
         * headImg : http://wpt.zmdb8.com/kuma/images/advar.png
         * realName : ccc
         * cardNum : aaa
         * sheng : sss
         * city : ccc
         * area : bbb
         * address : 方法
         * recommend : 2
         * gender : 男
         * email : 驰骋
         * qrCode : http://wpt.zmdb8.com/kuma/QRCode/498f70e4-fddd-455c-bb67-833f101dd927.png
         * loginTime : 2017-02-08 17:06:39
         * loginIp : 122.233.230.28
         * isLogin : 1
         * times : 0
         * status : 2
         * createTime : 2017-02-08 12:01:14
         * updateTime : 2017-02-08 17:06:39
         * recommendName : 啊啊
         * recommendCode : 少室山
         * statusStr : 更改
         */

        public int userId;
        public String userCode;
        public String username;
        public String password;
        public String salt;
        public String pwdPay;
        public String salt1;
        public String mobile;
        public String headImg;
        public String realName;
        public String cardNum;
        public String sheng;
        public String city;
        public String area;
        public String address;
        public int recommend;
        public String gender;
        public String email;
        public String qrCode;
        public String loginTime;
        public String loginIp;
        public int isLogin;
        public int times;
        public int status;
        public String createTime;
        public String updateTime;
        public String recommendName;
        public String recommendCode;
        public String statusStr;

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
