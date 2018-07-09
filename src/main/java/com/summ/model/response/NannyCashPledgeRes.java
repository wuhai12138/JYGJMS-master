package com.summ.model.response;

import java.math.BigDecimal;
import java.util.Date;

public class NannyCashPledgeRes {
    private Integer id;
    private Integer nannyId;
    private Integer scheduleId;
    private Integer orderId;
    private Integer shopId;
    private String shopName;
    private BigDecimal money;
    private Date createDate;
    private BigDecimal cashPledgeBalance;
    private Integer isDel;
    private Integer status;
    private String statusInfo;
    private String serialNumber;
    private Integer reason;
    private String reasonInfo;
    private Integer paymentType;
    private String paymentTypeInfo;
    private Integer operateType;
    private String operateTypeInfo;

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentTypeInfo() {
        return paymentTypeInfo;
    }

    public void setPaymentTypeInfo(String paymentTypeInfo) {
        this.paymentTypeInfo = paymentTypeInfo;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public String getOperateTypeInfo() {
        return operateTypeInfo;
    }

    public void setOperateTypeInfo(String operateTypeInfo) {
        this.operateTypeInfo = operateTypeInfo;
    }

    public Integer getReason() {
        return reason;
    }

    public void setReason(Integer reason) {
        this.reason = reason;
    }

    public String getReasonInfo() {
        return reasonInfo;
    }

    public void setReasonInfo(String reasonInfo) {
        this.reasonInfo = reasonInfo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNannyId() {
        return nannyId;
    }

    public void setNannyId(Integer nannyId) {
        this.nannyId = nannyId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getCashPledgeBalance() {
        return cashPledgeBalance;
    }

    public void setCashPledgeBalance(BigDecimal cashPledgeBalance) {
        this.cashPledgeBalance = cashPledgeBalance;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
