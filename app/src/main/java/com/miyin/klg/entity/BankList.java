package com.miyin.klg.entity;

import java.util.List;

/**
 * Created by en on 2017/2/13.
 */

public class BankList {

    /**
     * status : 0
     * data : [{"bankId":1,"bankName":"农业银行","ico":"bankPic/614fb581-12d0-4339-88f5-0cc7c3404420.png","isDel":0,"createTime":null,"updateTime":"2017-01-05 12:57:05"}]
     * msg : 操作成功
     */

    public int status;
    public String msg;
    public List<DataBean> data;


    public static class DataBean {
        /**
         * bankId : 1
         * bankName : 农业银行
         * ico : bankPic/614fb581-12d0-4339-88f5-0cc7c3404420.png
         * isDel : 0
         * createTime : null
         * updateTime : 2017-01-05 12:57:05
         */

        public int bankId;
        public String bankName;
        public String ico;
        public int isDel;
        public Object createTime;
        public String updateTime;

    }
}
