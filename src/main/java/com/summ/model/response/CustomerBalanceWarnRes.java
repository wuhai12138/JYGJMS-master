package com.summ.model.response;

import java.math.BigDecimal;
import java.util.BitSet;
import java.util.Date;

public class CustomerBalanceWarnRes {
    private Integer scheduleId=0;
    private Date scheduleDate;
    private BigDecimal totalPrice;
    private Integer customerId;
    private String customerName;
    private String customerPhone;
    private BigDecimal customerBalance;
    private Integer shopId;
    private String shopName;
    /**该段时间总充值金额*/
    private BigDecimal totalCharge;
    /**需充值金额*/
    private BigDecimal totalToCharge=new BigDecimal(0);
    /**临时用于计算充值日期的字段*/
    private BigDecimal tempMoney = new BigDecimal(0);
    /**最后充值日期*/
    private Date rechargeDate;


    public BigDecimal getTempMoney() {
        return tempMoney;
    }

    public void setTempMoney(BigDecimal tempMoney) {
        this.tempMoney = tempMoney;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Date getRechargeDate() {
        return rechargeDate;
    }

    public void setRechargeDate(Date rechargeDate) {
        this.rechargeDate = rechargeDate;
    }

    public BigDecimal getTotalToCharge() {
        return totalToCharge;
    }

    public void setTotalToCharge(BigDecimal totalToCharge) {
        this.totalToCharge = totalToCharge;
    }

    public BigDecimal getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(BigDecimal totalCharge) {
        this.totalCharge = totalCharge;
    }

    public BigDecimal getCustomerBalance() {
        return customerBalance;
    }

    public void setCustomerBalance(BigDecimal customerBalance) {
        this.customerBalance = customerBalance;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
