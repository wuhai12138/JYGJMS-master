package com.summ.model.response;

import java.math.BigDecimal;

public class ReportCashPledgeRes {
    private Integer shopId;
    private String shopName;
    private BigDecimal payMoney;
    private BigDecimal refundMoney;
    private BigDecimal punishmentMoney;
    private Integer punishmentTimes;

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

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public BigDecimal getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(BigDecimal refundMoney) {
        this.refundMoney = refundMoney;
    }

    public BigDecimal getPunishmentMoney() {
        return punishmentMoney;
    }

    public void setPunishmentMoney(BigDecimal punishmentMoney) {
        this.punishmentMoney = punishmentMoney;
    }

    public Integer getPunishmentTimes() {
        return punishmentTimes;
    }

    public void setPunishmentTimes(Integer punishmentTimes) {
        this.punishmentTimes = punishmentTimes;
    }
}
