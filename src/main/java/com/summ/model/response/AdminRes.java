package com.summ.model.response;

import java.util.Date;

/**
 * Created by summ on 17/11/28.
 */
public class AdminRes {
    /**  */
    private Integer adminId;

    /**  */
    private String adminName;

    /**
     * 密码
     */
    private String adminPassword;

    /**  */
    private String adminPhone;

    private int adminType;

    private String adminTypeInfo;

    private int adminBelong;

    private String adminBelongInfo;
    /**  */
    private Integer shopId;

    private String shopIdInfo;

    /**  */
    private Integer trainId;

    private String trainIdInfo;
    /**  */
    private Integer isDel = 16;

    private String isDelInfo;

    /**  */
    private Date createTime = new Date();

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public int getAdminType() {
        return adminType;
    }

    public void setAdminType(int adminType) {
        this.adminType = adminType;
    }


    public int getAdminBelong() {
        return adminBelong;
    }

    public void setAdminBelong(int adminBelong) {
        this.adminBelong = adminBelong;
    }


    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }


    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }


    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAdminTypeInfo() {
        return adminTypeInfo;
    }

    public void setAdminTypeInfo(String adminTypeInfo) {
        this.adminTypeInfo = adminTypeInfo;
    }

    public String getAdminBelongInfo() {
        return adminBelongInfo;
    }

    public void setAdminBelongInfo(String adminBelongInfo) {
        this.adminBelongInfo = adminBelongInfo;
    }

    public String getShopIdInfo() {
        return shopIdInfo;
    }

    public void setShopIdInfo(String shopIdInfo) {
        this.shopIdInfo = shopIdInfo;
    }

    public String getTrainIdInfo() {
        return trainIdInfo;
    }

    public void setTrainIdInfo(String trainIdInfo) {
        this.trainIdInfo = trainIdInfo;
    }

    public String getIsDelInfo() {
        return isDelInfo;
    }

    public void setIsDelInfo(String isDelInfo) {
        this.isDelInfo = isDelInfo;
    }
}
