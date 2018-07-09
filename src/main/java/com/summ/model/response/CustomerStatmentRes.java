package com.summ.model.response;

import com.summ.model.JCustomerStatment;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by summ on 17/11/29.
 */
public class CustomerStatmentRes extends JCustomerStatment{

    private String goodsName;

    private String nannyName;
    private Integer nannyId;
    private Integer supplierId;


    private String statusInfo;
    private String terminalInfo;
    private String adminName;
    private String chargeWayInfo;
    private String statmentCustomerTypeInfo;
    private String customerName;
    private String customerPhone;
    private String shopName;
    private String houseAddress;
    private String couponName;
    private BigDecimal couponPrice;
    private String checkingInfo;


    @Override
    public String toString() {
        return "CustomerStatmentRes{" +
                "goodsName='" + goodsName + '\'' +
                ", nannyName='" + nannyName + '\'' +
                ", nannyId=" + nannyId +
                ", supplierId=" + supplierId +
                ", statusInfo='" + statusInfo + '\'' +
                ", terminalInfo='" + terminalInfo + '\'' +
                ", adminName='" + adminName + '\'' +
                ", chargeWayInfo='" + chargeWayInfo + '\'' +
                ", statmentCustomerTypeInfo='" + statmentCustomerTypeInfo + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", shopName='" + shopName + '\'' +
                ", houseAddress='" + houseAddress + '\'' +
                ", couponName='" + couponName + '\'' +
                ", couponPrice=" + couponPrice +
                '}';
    }

    public String getCheckingInfo() {
        return checkingInfo;
    }

    public void setCheckingInfo(String checkingInfo) {
        this.checkingInfo = checkingInfo;
    }

    public Integer getNannyId() {
        return nannyId;
    }

    public void setNannyId(Integer nannyId) {
        this.nannyId = nannyId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getNannyName() {
        return nannyName;
    }

    public void setNannyName(String nannyName) {
        this.nannyName = nannyName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
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


    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public String getTerminalInfo() {
        return terminalInfo;
    }

    public void setTerminalInfo(String terminalInfo) {
        this.terminalInfo = terminalInfo;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getChargeWayInfo() {
        return chargeWayInfo;
    }

    public void setChargeWayInfo(String chargeWayInfo) {
        this.chargeWayInfo = chargeWayInfo;
    }

    public String getStatmentCustomerTypeInfo() {
        return statmentCustomerTypeInfo;
    }

    public void setStatmentCustomerTypeInfo(String statmentCustomerTypeInfo) {
        this.statmentCustomerTypeInfo = statmentCustomerTypeInfo;
    }
}
