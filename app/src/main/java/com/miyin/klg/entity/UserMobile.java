package com.miyin.klg.entity;

/**
 * Created by en on 2017/2/16.
 */

public class UserMobile {

    /**
     * status : 0
     * data : {"userId":1,"userCode":"YQWNVrDQ","username":"17096177660","mobile":"17096177660","headImg":"","realName":"","idNumber":null,"area":null,"recommend":null,"gender":"男","consumeLimit":3000,"email":null,"qrCode":" ","userType":0,"loginTime":"2016-12-16 18:05:26","loginIp":"0:0:0:0:0:0:0:1","isLogin":1,"isBlack":0,"createTime":"2016-12-15 18:43:24","updateTime":"2016-12-16 18:02:10","recommendName":"","recommendCode":""}
     * msg : 操作成功
     */

    public int status;
    public DataBean data;
    public String msg;



    public static class DataBean {
        /**
         * userId : 1
         * userCode : YQWNVrDQ
         * username : 17096177660
         * mobile : 17096177660
         * headImg :
         * realName :
         * idNumber : null
         * area : null
         * recommend : null
         * gender : 男
         * consumeLimit : 3000
         * email : null
         * qrCode :
         * userType : 0
         * loginTime : 2016-12-16 18:05:26
         * loginIp : 0:0:0:0:0:0:0:1
         * isLogin : 1
         * isBlack : 0
         * createTime : 2016-12-15 18:43:24
         * updateTime : 2016-12-16 18:02:10
         * recommendName :
         * recommendCode :
         */

        public int userId;
        public String userCode;
        public String username;
        public String mobile;
        public String headImg;
        public String realName;
        public Object idNumber;
        public Object area;
        public Object recommend;
        public String gender;
        public int consumeLimit;
        public Object email;
        public String qrCode;
        public int userType;
        public String loginTime;
        public String loginIp;
        public int isLogin;
        public int isBlack;
        public String createTime;
        public String updateTime;
        public String recommendName;
        public String recommendCode;
    }
}
