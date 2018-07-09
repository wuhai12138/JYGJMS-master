package com.summ.model.response;

import com.sun.tools.internal.xjc.reader.dtd.bindinfo.BIAttribute;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by summ on 18/1/18.
 */
public class NannyStatmentRes {
    private Integer nannyId;
    private String nannyName;
    private String nannyPhone;
    private String shopName;
    private Integer shopId;
    private String NannyUnitPrice;
    private BigDecimal  orderContractMoney;
    private Double orderContractTimes;
    private BigDecimal  orderTempMoney;
    private Double orderTempTimes;
    private BigDecimal  orderGrouponMoney;
    private Double orderGrouponTimes;
    /**合计工资*/
    private BigDecimal totalSalary;
    /**合计工时*/
    private Double totalTimes;
    /** 个人所得税 */
    private BigDecimal IndividualIncomeTax;
    /** 实际发放工资 */
    private BigDecimal realSalary;
    private BigDecimal reward;
    private BigDecimal punishment;
    private BigDecimal orderRefund;

    public BigDecimal getRealSalary() {
        return realSalary;
    }

    public void setRealSalary(BigDecimal realSalary) {
        this.realSalary = realSalary;
    }

    public BigDecimal getIndividualIncomeTax() {
        return IndividualIncomeTax;
    }

    public void setIndividualIncomeTax(BigDecimal individualIncomeTax) {
        IndividualIncomeTax = individualIncomeTax;
    }

    public BigDecimal getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(BigDecimal totalSalary) {
        this.totalSalary = totalSalary;
    }

    public Double getTotalTimes() {
        return totalTimes;
    }

    public void setTotalTimes(Double totalTimes) {
        this.totalTimes = totalTimes;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getNannyId() {
        return nannyId;
    }

    public void setNannyId(Integer nannyId) {
        this.nannyId = nannyId;
    }

    public String getNannyName() {
        return nannyName;
    }

    public void setNannyName(String nannyName) {
        this.nannyName = nannyName;
    }

    public String getNannyPhone() {
        return nannyPhone;
    }

    public void setNannyPhone(String nannyPhone) {
        this.nannyPhone = nannyPhone;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getNannyUnitPrice() {
        return NannyUnitPrice;
    }

    public void setNannyUnitPrice(String nannyUnitPrice) {
        NannyUnitPrice = nannyUnitPrice;
    }

    public BigDecimal getOrderContractMoney() {
        return orderContractMoney;
    }

    public void setOrderContractMoney(BigDecimal orderContractMoney) {
        this.orderContractMoney = orderContractMoney;
    }

    public Double getOrderContractTimes() {
        return orderContractTimes;
    }

    public void setOrderContractTimes(Double orderContractTimes) {
        this.orderContractTimes = orderContractTimes;
    }

    public BigDecimal getOrderTempMoney() {
        return orderTempMoney;
    }

    public void setOrderTempMoney(BigDecimal orderTempMoney) {
        this.orderTempMoney = orderTempMoney;
    }

    public Double getOrderTempTimes() {
        return orderTempTimes;
    }

    public void setOrderTempTimes(Double orderTempTimes) {
        this.orderTempTimes = orderTempTimes;
    }

    public BigDecimal getOrderGrouponMoney() {
        return orderGrouponMoney;
    }

    public void setOrderGrouponMoney(BigDecimal orderGrouponMoney) {
        this.orderGrouponMoney = orderGrouponMoney;
    }

    public Double getOrderGrouponTimes() {
        return orderGrouponTimes;
    }

    public void setOrderGrouponTimes(Double orderGrouponTimes) {
        this.orderGrouponTimes = orderGrouponTimes;
    }

    public BigDecimal getReward() {
        return reward;
    }

    public void setReward(BigDecimal reward) {
        this.reward = reward;
    }

    public BigDecimal getPunishment() {
        return punishment;
    }

    public void setPunishment(BigDecimal punishment) {
        this.punishment = punishment;
    }

    public BigDecimal getOrderRefund() {
        return orderRefund;
    }

    public void setOrderRefund(BigDecimal orderRefund) {
        this.orderRefund = orderRefund;
    }
}
