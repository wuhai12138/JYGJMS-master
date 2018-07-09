package com.summ.model.response;

import com.summ.model.JShop;

import java.util.Date;
import java.util.List;

public class NannyInsuranceRes {
    private Integer insuranceId;

    /** 保险月份（每月一号） */
    private Date insuranceDate;

    /**  */
    private Integer nannyId;

    /**  */
    private Date createTime;

    /**  */
    private Integer isDel;

    private String nannyName;
    private String nannyTypeInfo = "白班服务师";
    private Integer nannyAge;
    private String nannyIdCard;
    private String health = "健康";

    private List<JShop> shopList;
    private String shopName;

    public List<JShop> getShopList() {
        return shopList;
    }

    public void setShopList(List<JShop> shopList) {
        this.shopList = shopList;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public Integer getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Integer insuranceId) {
        this.insuranceId = insuranceId;
    }

    public Date getInsuranceDate() {
        return insuranceDate;
    }

    public void setInsuranceDate(Date insuranceDate) {
        this.insuranceDate = insuranceDate;
    }

    public Integer getNannyId() {
        return nannyId;
    }

    public void setNannyId(Integer nannyId) {
        this.nannyId = nannyId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getNannyName() {
        return nannyName;
    }

    public void setNannyName(String nannyName) {
        this.nannyName = nannyName;
    }


    public String getNannyTypeInfo() {
        return nannyTypeInfo;
    }

    public void setNannyTypeInfo(String nannyTypeInfo) {
        this.nannyTypeInfo = nannyTypeInfo;
    }

    public Integer getNannyAge() {
        return nannyAge;
    }

    public void setNannyAge(Integer nannyAge) {
        this.nannyAge = nannyAge;
    }

    public String getNannyIdCard() {
        return nannyIdCard;
    }

    public void setNannyIdCard(String nannyIdCard) {
        this.nannyIdCard = nannyIdCard;
    }
}
