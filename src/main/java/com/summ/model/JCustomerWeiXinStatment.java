package com.summ.model;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 *
 *
 */
@TableName("j_customer_wei_xin_statment")
public class JCustomerWeiXinStatment implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**  */
    @TableId(type = IdType.AUTO)
    private Integer tradeId;

    /**
     * 交易时间
     */
    private Date tradeTime;

    /**  */
    private String appId;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 子商户号
     */
    private String childMchId;

    /**
     * 设备号
     */
    private String deviceNumber;

    /**
     * 微信订单号
     */
    private String transactionId;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 用户标识
     */
    private String customerId;

    /**
     * 交易类型
     */
    private String transactionType;

    /**
     * 交易状态
     */
    private String transactionStatus;

    /**
     * 付款银行
     */
    private String bank;

    /**
     * 货币种类
     */
    private String currencyType;

    /**
     * 总金额
     */
    private BigDecimal totalMoney;

    /**
     * 企业红包金额
     */
    private String enterpriseRedPacketsMoney;

    /**
     * 企业红包退款金额
     */
    private String enterpriseRedPacketsRefundMoney;

    /**
     * 微信退款单号
     */
    private String refundTradeNumber;

    /**
     * 商户退款单号
     */
    private String refundNumber;

    /**
     * 退款金额
     */
    private BigDecimal refundMoney;

    /**
     * 退款类型
     */
    private String refundType;

    /**
     * 退款类型
     */
    private String refundStatus;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品数据包
     */
    private String goodsData;

    /**
     * 手续费
     */
    private BigDecimal poundage;

    /**
     * 费率
     */
    private String rate;

    @TableLogic
    private Integer isDel=16;
    /**
     * 对账状态
     */
    private Integer checking;
    private Date checkingDate;
    private String remark;
    @TableField(exist = false)
    private String checkingInfo;

    public JCustomerWeiXinStatment() {
    }

    public JCustomerWeiXinStatment(Integer tradeId, Integer isDel) {
        this.tradeId = tradeId;
        this.isDel = isDel;
    }

    public JCustomerWeiXinStatment(Date tradeTime, String appId, String mchId, String childMchId, String deviceNumber, String transactionId, String outTradeNo, String customerId,
                                   String transactionType, String transactionStatus, String bank, String currencyType, BigDecimal totalMoney, String enterpriseRedPacketsMoney, String enterpriseRedPacketsRefundMoney,
                                   String refundTradeNumber, String refundNumber, BigDecimal refundMoney, String refundType, String refundStatus, String goodsName,
                                   String goodsData, BigDecimal poundage, String rate,String checkingInfo) {
        this.tradeTime = tradeTime;
        this.appId = appId;
        this.mchId = mchId;
        this.childMchId = childMchId;
        this.deviceNumber = deviceNumber;
        this.transactionId = transactionId;
        this.outTradeNo = outTradeNo;
        this.customerId = customerId;
        this.transactionType = transactionType;
        this.transactionStatus = transactionStatus;
        this.bank = bank;
        this.currencyType = currencyType;
        this.totalMoney = totalMoney;
        this.enterpriseRedPacketsMoney = enterpriseRedPacketsMoney;
        this.enterpriseRedPacketsRefundMoney = enterpriseRedPacketsRefundMoney;
        this.refundTradeNumber = refundTradeNumber;
        this.refundNumber = refundNumber;
        this.refundMoney = refundMoney;
        this.refundType = refundType;
        this.refundStatus = refundStatus;
        this.goodsName = goodsName;
        this.goodsData = goodsData;
        this.poundage = poundage;
        this.rate = rate;
        this.checkingInfo=checkingInfo;
    }
    public JCustomerWeiXinStatment(Date tradeTime, String appId, String mchId, String childMchId, String deviceNumber, String transactionId, String outTradeNo, String customerId,
                                   String transactionType, String transactionStatus, String bank, String currencyType, BigDecimal totalMoney, String enterpriseRedPacketsMoney, String enterpriseRedPacketsRefundMoney,
                                   String refundTradeNumber, String refundNumber, BigDecimal refundMoney, String refundType, String refundStatus, String goodsName,
                                   String goodsData, BigDecimal poundage, String rate) {
        this.tradeTime = tradeTime;
        this.appId = appId;
        this.mchId = mchId;
        this.childMchId = childMchId;
        this.deviceNumber = deviceNumber;
        this.transactionId = transactionId;
        this.outTradeNo = outTradeNo;
        this.customerId = customerId;
        this.transactionType = transactionType;
        this.transactionStatus = transactionStatus;
        this.bank = bank;
        this.currencyType = currencyType;
        this.totalMoney = totalMoney;
        this.enterpriseRedPacketsMoney = enterpriseRedPacketsMoney;
        this.enterpriseRedPacketsRefundMoney = enterpriseRedPacketsRefundMoney;
        this.refundTradeNumber = refundTradeNumber;
        this.refundNumber = refundNumber;
        this.refundMoney = refundMoney;
        this.refundType = refundType;
        this.refundStatus = refundStatus;
        this.goodsName = goodsName;
        this.goodsData = goodsData;
        this.poundage = poundage;
        this.rate = rate;
    }

    public String getCheckingInfo() {
        return checkingInfo;
    }

    public void setCheckingInfo(String checkingInfo) {
        this.checkingInfo = checkingInfo;
    }

    public Date getCheckingDate() {
        return checkingDate;
    }

    public void setCheckingDate(Date checkingDate) {
        this.checkingDate = checkingDate;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getChecking() {
        return checking;
    }

    public void setChecking(Integer checking) {
        this.checking = checking;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public Integer getTradeId() {
        return this.tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public Date getTradeTime() {
        return this.tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return this.mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getChildMchId() {
        return this.childMchId;
    }

    public void setChildMchId(String childMchId) {
        this.childMchId = childMchId;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return this.outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTransactionType() {
        return this.transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionStatus() {
        return this.transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getBank() {
        return this.bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCurrencyType() {
        return this.currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public BigDecimal getTotalMoney() {
        return this.totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getEnterpriseRedPacketsMoney() {
        return this.enterpriseRedPacketsMoney;
    }

    public void setEnterpriseRedPacketsMoney(String enterpriseRedPacketsMoney) {
        this.enterpriseRedPacketsMoney = enterpriseRedPacketsMoney;
    }

    public String getEnterpriseRedPacketsRefundMoney() {
        return this.enterpriseRedPacketsRefundMoney;
    }

    public void setEnterpriseRedPacketsRefundMoney(String enterpriseRedPacketsRefundMoney) {
        this.enterpriseRedPacketsRefundMoney = enterpriseRedPacketsRefundMoney;
    }

    public String getRefundTradeNumber() {
        return refundTradeNumber;
    }

    public void setRefundTradeNumber(String refundTradeNumber) {
        this.refundTradeNumber = refundTradeNumber;
    }

    public String getRefundNumber() {
        return refundNumber;
    }

    public void setRefundNumber(String refundNumber) {
        this.refundNumber = refundNumber;
    }

    public BigDecimal getRefundMoney() {
        return this.refundMoney;
    }

    public void setRefundMoney(BigDecimal refundMoney) {
        this.refundMoney = refundMoney;
    }

    public String getRefundType() {
        return this.refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }

    public String getRefundStatus() {
        return this.refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getGoodsName() {
        return this.goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsData() {
        return this.goodsData;
    }

    public void setGoodsData(String goodsData) {
        this.goodsData = goodsData;
    }

    public BigDecimal getPoundage() {
        return this.poundage;
    }

    public void setPoundage(BigDecimal poundage) {
        this.poundage = poundage;
    }

    public String getRate() {
        return this.rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

}
