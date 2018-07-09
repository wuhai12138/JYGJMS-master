package com.summ.model.response;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by summ on 17/11/29.
 */
public class ReportCustomerRechargeRes {

    private Integer shopId;
    private String shopName;
    private BigDecimal alyPay;
    private BigDecimal WeiXin;
    private BigDecimal card;
    private BigDecimal cash;
    private BigDecimal totalCharge=new BigDecimal(0);
    private BigDecimal totalBalance;

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

    public BigDecimal getAlyPay() {
        return alyPay;
    }

    public void setAlyPay(BigDecimal alyPay) {
        this.alyPay = alyPay;
    }

    public BigDecimal getWeiXin() {
        return WeiXin;
    }

    public void setWeiXin(BigDecimal weiXin) {
        WeiXin = weiXin;
    }

    public BigDecimal getCard() {
        return card;
    }

    public void setCard(BigDecimal card) {
        this.card = card;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public BigDecimal getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(BigDecimal totalCharge) {
        this.totalCharge = totalCharge;
    }

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }
}
