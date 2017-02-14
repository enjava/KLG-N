package com.miyin.klg.util;

import java.io.Serializable;
import java.util.Map;

/**
 * 序列化map供Bundle传递map使用
 * Created  on 13-12-9.
 */
public class SerializableMap  implements Serializable{

    private Map<String,String> map;
    private String [] mBankNameDatas;
    private String [] mBankIdDatas;
    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public String[] getmBankNameDatas() {
        return mBankNameDatas;
    }

    public void setmBankNameDatas(String[] mBankNameDatas) {
        this.mBankNameDatas = mBankNameDatas;
    }

    public String[] getmBankIdDatas() {
        return mBankIdDatas;
    }

    public void setmBankIdDatas(String[] mBankIdDatas) {
        this.mBankIdDatas = mBankIdDatas;
    }
}