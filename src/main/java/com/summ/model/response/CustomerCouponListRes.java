package com.summ.model.response;

import com.sun.tools.internal.xjc.reader.dtd.bindinfo.BIAttribute;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by summ on 17/11/28.
 */
public class CustomerCouponListRes {

    private Integer couponListId;

    /**  */
    private String couponCode = "";

    /**  */
    private Integer couponId;

    /**  */
    private Integer shopId;
    private String shopIdInfo;

    /**  */
    private Integer customerId;

    private Integer couponStatus;
    private String couponStatusInfo;


    /**  */
    private Integer missionId;

    /**  */
    private Date createTime;
    private Date useTime;

    /**  */
    private Integer isDel;
    private String isDelInfo;

    private String remark;

    /**  */
    private String couponName;

    /**  */
    private String couponDetail;

    /**  */
    private BigDecimal couponPrice;
    private BigDecimal couponCost;

    /**  */
    private Date validTime;

    /**  */
    private String couponOrigin;
    private String couponOriginInfo;


    public BigDecimal getCouponCost() {
        return couponCost;
    }

    public void setCouponCost(BigDecimal couponCost) {
        this.couponCost = couponCost;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public Integer getCouponListId() {
        return couponListId;
    }

    public void setCouponListId(Integer couponListId) {
        this.couponListId = couponListId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopIdInfo() {
        return shopIdInfo;
    }

    public void setShopIdInfo(String shopIdInfo) {
        this.shopIdInfo = shopIdInfo;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(Integer couponStatus) {
        this.couponStatus = couponStatus;
    }

    public String getCouponStatusInfo() {
        return couponStatusInfo;
    }

    public void setCouponStatusInfo(String couponStatusInfo) {
        this.couponStatusInfo = couponStatusInfo;
    }

    public Integer getMissionId() {
        return missionId;
    }

    public void setMissionId(Integer missionId) {
        this.missionId = missionId;
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

    public String getIsDelInfo() {
        return isDelInfo;
    }

    public void setIsDelInfo(String isDelInfo) {
        this.isDelInfo = isDelInfo;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getCouponDetail() {
        return couponDetail;
    }

    public void setCouponDetail(String couponDetail) {
        this.couponDetail = couponDetail;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    public Date getValidTime() {
        return validTime;
    }

    public void setValidTime(Date validTime) {
        this.validTime = validTime;
    }

    public String getCouponOrigin() {
        return couponOrigin;
    }

    public void setCouponOrigin(String couponOrigin) {
        this.couponOrigin = couponOrigin;
    }

    public String getCouponOriginInfo() {
        return couponOriginInfo;
    }

    public void setCouponOriginInfo(String couponOriginInfo) {
        this.couponOriginInfo = couponOriginInfo;
    }
}
