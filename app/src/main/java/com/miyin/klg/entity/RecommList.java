package com.miyin.klg.entity;

import java.util.List;

/**
 * Created by en on 2017/2/13.
 */

public class RecommList {


    /**
     * data : {"key":"","list":[{"address":"","area":"","cardNum":"","city":"","createTime":"","email":"","gender":"","headImg":"images/advar.png","loginIp":"","loginTime":"","mobile":"1321006726","password":"","pwdPay":"","qrCode":"","realName":"","recommend":"","recommendCode":"","recommendName":"","salt":"","salt1":"","sheng":"","status":2,"statusStr":"待完善","times":"","updateTime":"","userCode":"U0Lu2e2Y","userId":17,"username":""}],"offset":0,"page":1,"pageSize":10,"time1":"","time2":"","totalPage":1,"totalRow":10}
     * msg : 操作成功
     * status : 0
     */

    public DataBean data;
    public String msg;
    public int status;

    public static class DataBean {

        public String key;
        public int offset;
        public int page;
        public int pageSize;
        public String time1;
        public String time2;
        public int totalPage;
        public int totalRow;
        public List<ListBean> list;


        public static class ListBean {

            public String address;
            public String area;
            public String cardNum;
            public String city;
            public String createTime;
            public String email;
            public String gender;
            public String headImg;
            public String loginIp;
            public String loginTime;
            public String mobile;
            public String password;
            public String pwdPay;
            public String qrCode;
            public String realName;
            public String recommend;
            public String recommendCode;
            public String recommendName;
            public String salt;
            public String salt1;
            public String sheng;
            public int status;
            public String statusStr;
            public String times;
            public String updateTime;
            public String userCode;
            public int userId;
            public String username;

        }
    }
}
