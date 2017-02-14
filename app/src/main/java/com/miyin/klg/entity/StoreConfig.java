package com.miyin.klg.entity;

import java.io.Serializable;

/**
 * Created by en on 2017/2/14.
 */

public class StoreConfig implements Serializable {


    private int type;//是	企业类型。0：企业。1：个人
    private String storeName;//是	店铺名称
    private String storePhone;//是	店铺电话
    private String sheng;//是	省份
    private String city;//是	市
    private String area;//是	区
    private String address;//是	详细地址（个人则是网店地址）
    private String longitude;// 否	经度（企业必填。个人可以不填）
    private String latitude;//否	纬度（企业必填。个人可以不填）
    private String companyName;//否	公司名称（企业必填。个人可以不填）
    private String licenceNum;//	否	营业执照号
    private String businessType2;//是	所在行业。
    private String shopHours;//是	营业时间
    private String storeInfo;//是	店铺介绍
    private String realName;//是	负责人姓名
    private String userCard;//是	负责人身份证号
    private String phone;//是	负责人联系电话
    private boolean isWZ=false;//网站截图 企业为 门店图
    private boolean isHandID=false;//手持身份证
    private boolean isChengnuo=false;//承诺书
    private boolean isLicence=false;//营业执照

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public String getSheng() {
        return sheng;
    }

    public void setSheng(String sheng) {
        this.sheng = sheng;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLicenceNum() {
        return licenceNum;
    }

    public void setLicenceNum(String licenceNum) {
        this.licenceNum = licenceNum;
    }

    public String getBusinessType2() {
        return businessType2;
    }

    public void setBusinessType2(String businessType2) {
        this.businessType2 = businessType2;
    }

    public String getShopHours() {
        return shopHours;
    }

    public void setShopHours(String shopHours) {
        this.shopHours = shopHours;
    }

    public String getStoreInfo() {
        return storeInfo;
    }

    public void setStoreInfo(String storeInfo) {
        this.storeInfo = storeInfo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserCard() {
        return userCard;
    }

    public void setUserCard(String userCard) {
        this.userCard = userCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isWZ() {
        return isWZ;
    }

    public void setWZ(boolean WZ) {
        isWZ = WZ;
    }

    public boolean isHandID() {
        return isHandID;
    }

    public void setHandID(boolean handID) {
        isHandID = handID;
    }

    public boolean isChengnuo() {
        return isChengnuo;
    }

    public void setChengnuo(boolean chengnuo) {
        isChengnuo = chengnuo;
    }

    public boolean isLicence() {
        return isLicence;
    }

    public void setLicence(boolean licence) {
        isLicence = licence;
    }
}
