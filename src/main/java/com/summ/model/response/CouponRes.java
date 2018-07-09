package com.summ.model.response;

import java.math.BigDecimal;
import java.util.Date;

public class CouponRes {
    private Integer couponId;

    /**  */
    private String couponName;

    /**  */
    private String couponDetail;

    /**  */
    private BigDecimal couponPrice;
    private BigDecimal couponCost;

    private BigDecimal orderMiniPrice;

    /**  */
    private Date validTime;

    /**  */
    private Integer couponOrigin;
    private String couponOriginInfo;

    /**  */
    private Integer isDel = 16;

    /**  */
    private Date createTime;

    public BigDecimal getCouponCost() {
        return couponCost;
    }

    public void setCouponCost(BigDecimal couponCost) {
        this.couponCost = couponCost;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
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

    public BigDecimal getOrderMiniPrice() {
        return orderMiniPrice;
    }

    public void setOrderMiniPrice(BigDecimal orderMiniPrice) {
        this.orderMiniPrice = orderMiniPrice;
    }

    public Date getValidTime() {
        return validTime;
    }

    public void setValidTime(Date validTime) {
        this.validTime = validTime;
    }

    public Integer getCouponOrigin() {
        return couponOrigin;
    }

    public void setCouponOrigin(Integer couponOrigin) {
        this.couponOrigin = couponOrigin;
    }

    public String getCouponOriginInfo() {
        return couponOriginInfo;
    }

    public void setCouponOriginInfo(String couponOriginInfo) {
        this.couponOriginInfo = couponOriginInfo;
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
}
