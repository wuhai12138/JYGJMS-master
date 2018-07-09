package com.summ.model;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.summ.utils.StringUtil;

/**
 *
 * 
 *
 */
@TableName("j_customer_aly_statment")
public class JCustomerAlyStatment implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Integer tradeId;

	/** 支付宝交易号 */
	private String tradeNo;

	/**  */
	private String outTradeNo;

	/** 业务类型 */
	private String tradeType;

	/** 商品名称 */
	private String goodsName;

	/**  */
	private Date createTime;

	/**  */
	private Date paymentTime;

	/** 门店编号 */
	private String storeNo;

	/** 门店id */
	private Integer storeId;

	/** 操作人 */
	private String creater;

	/** 终端号 */
	private String terminal;

	/** 客户账户 */
	private String customerAmount;

	/** 订单金额 */
	private BigDecimal orderAmount;

	/** 实收金额 */
	private BigDecimal receiptAmount;

	/** 支付宝红包 */
	private BigDecimal redPacketsMoney;

	/** 集分宝金额 */
	private BigDecimal pointAmount;

	/** 支付宝优惠金额 */
	private BigDecimal alyDiscount;

	/** 商家优惠 */
	private BigDecimal merchantsDiscount;

	/** 券核销金额 */
	private BigDecimal vouchersDiscount;

	/** 券名称 */
	private String vouchersName;

	/** 商家红包消费金额 */
	private BigDecimal merchantsResPacketConsume;

	/** 卡消费金额 */
	private BigDecimal cardConsume;

	/** 退款批次号/请求号 */
	private String refundNo;

	/** 服务费 */
	private BigDecimal serviceFee;

	/** 分润 */
	private BigDecimal profits;

	/** 备注 */
	private String alyRemark;

	/** 是否对账 */
	private Integer checking;
	@TableField(exist = false)
	private String checkingInfo;

	/** 对账日期 */
	private Date checkingDate;

	/**  */
	private String remark;

	/**  */
	@TableLogic
	private Integer isDel=16;

	public JCustomerAlyStatment() {
	}


	@Override
	public String toString() {
		return "JCustomerAlyStatment{" +
				"tradeId=" + tradeId +
				", tradeNo='" + tradeNo + '\'' +
				", outTradeNo='" + outTradeNo + '\'' +
				", tradeType='" + tradeType + '\'' +
				", goodsName='" + goodsName + '\'' +
				", createTime=" + createTime +
				", paymentTime=" + paymentTime +
				", storeNo='" + storeNo + '\'' +
				", storeId=" + storeId +
				", creater='" + creater + '\'' +
				", terminal='" + terminal + '\'' +
				", customerAmount='" + customerAmount + '\'' +
				", orderAmount=" + orderAmount +
				", receiptAmount=" + receiptAmount +
				", redPacketsMoney=" + redPacketsMoney +
				", pointAmount=" + pointAmount +
				", alyDiscount=" + alyDiscount +
				", merchantsDiscount=" + merchantsDiscount +
				", vouchersDiscount=" + vouchersDiscount +
				", vouchersName='" + vouchersName + '\'' +
				", merchantsResPacketConsume=" + merchantsResPacketConsume +
				", cardConsume=" + cardConsume +
				", refundNo='" + refundNo + '\'' +
				", serviceFee=" + serviceFee +
				", profits=" + profits +
				", alyRemark='" + alyRemark + '\'' +
				", checking=" + checking +
				", checkingDate=" + checkingDate +
				", remark='" + remark + '\'' +
				", isDel=" + isDel +
				'}';
	}

	public JCustomerAlyStatment(Integer tradeId, Integer isDel) {
		this.tradeId = tradeId;
		this.isDel = isDel;
	}

	public JCustomerAlyStatment(String tradeNo, String outTradeNo, String tradeType, String goodsName, Date createTime, Date paymentTime, String storeNo,
								Integer storeId, String creater, String terminal, String customerAmount, BigDecimal orderAmount, BigDecimal receiptAmount,
								BigDecimal redPacketsMoney, BigDecimal pointAmount, BigDecimal alyDiscount, BigDecimal merchantsDiscount, BigDecimal vouchersDiscount,
								String vouchersName, BigDecimal merchantsResPacketConsume, BigDecimal cardConsume, String refundNo, BigDecimal serviceFee, BigDecimal profits,
								String alyRemark) {
		this.tradeNo = tradeNo;
		this.outTradeNo = outTradeNo;
		this.tradeType = tradeType;
		this.goodsName = goodsName;
		this.createTime = createTime;
		this.paymentTime = paymentTime;
		this.storeNo = storeNo;
		this.storeId = storeId;
		this.creater = creater;
		this.terminal = terminal;
		this.customerAmount = customerAmount;
		this.orderAmount = orderAmount;
		this.receiptAmount = receiptAmount;
		this.redPacketsMoney = redPacketsMoney;
		this.pointAmount = pointAmount;
		this.alyDiscount = alyDiscount;
		this.merchantsDiscount = merchantsDiscount;
		this.vouchersDiscount = vouchersDiscount;
		this.vouchersName = vouchersName;
		this.merchantsResPacketConsume = merchantsResPacketConsume;
		this.cardConsume = cardConsume;
		this.refundNo = refundNo;
		this.serviceFee = serviceFee;
		this.profits = profits;
		this.alyRemark = alyRemark;
	}

	public JCustomerAlyStatment(String tradeNo, String outTradeNo, String tradeType, String goodsName, Date createTime, Date paymentTime, String storeNo,
								Integer storeId, String creater, String terminal, String customerAmount, BigDecimal orderAmount, BigDecimal receiptAmount,
								BigDecimal redPacketsMoney, BigDecimal pointAmount, BigDecimal alyDiscount, BigDecimal merchantsDiscount, BigDecimal vouchersDiscount,
								String vouchersName, BigDecimal merchantsResPacketConsume, BigDecimal cardConsume, String refundNo, BigDecimal serviceFee, BigDecimal profits,
								String alyRemark, String checkingInfo) {
		this.tradeNo = tradeNo;
		this.outTradeNo = outTradeNo;
		this.tradeType = tradeType;
		this.goodsName = goodsName;
		this.createTime = createTime;
		this.paymentTime = paymentTime;
		this.storeNo = storeNo;
		this.storeId = storeId;
		this.creater = creater;
		this.terminal = terminal;
		this.customerAmount = customerAmount;
		this.orderAmount = orderAmount;
		this.receiptAmount = receiptAmount;
		this.redPacketsMoney = redPacketsMoney;
		this.pointAmount = pointAmount;
		this.alyDiscount = alyDiscount;
		this.merchantsDiscount = merchantsDiscount;
		this.vouchersDiscount = vouchersDiscount;
		this.vouchersName = vouchersName;
		this.merchantsResPacketConsume = merchantsResPacketConsume;
		this.cardConsume = cardConsume;
		this.refundNo = refundNo;
		this.serviceFee = serviceFee;
		this.profits = profits;
		this.alyRemark = alyRemark;
		this.checkingInfo = checkingInfo;
	}

    public String getCheckingInfo() {
        return checkingInfo;
    }

    public void setCheckingInfo(String checkingInfo) {
        this.checkingInfo = checkingInfo;
    }

    public Integer getTradeId() {
		return this.tradeId;
	}

	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}

	public String getTradeNo() {
		return this.tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getOutTradeNo() {
		return this.outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTradeType() {
		return this.tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getPaymentTime() {
		return this.paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	public String getStoreNo() {
		return this.storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}

	public Integer getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getCreater() {
		return this.creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getTerminal() {
		return this.terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getCustomerAmount() {
		return this.customerAmount;
	}

	public void setCustomerAmount(String customerAmount) {
		this.customerAmount = customerAmount;
	}

	public BigDecimal getOrderAmount() {
		return this.orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public BigDecimal getReceiptAmount() {
		return this.receiptAmount;
	}

	public void setReceiptAmount(BigDecimal receiptAmount) {
		this.receiptAmount = receiptAmount;
	}

	public BigDecimal getRedPacketsMoney() {
		return this.redPacketsMoney;
	}

	public void setRedPacketsMoney(BigDecimal redPacketsMoney) {
		this.redPacketsMoney = redPacketsMoney;
	}

	public BigDecimal getPointAmount() {
		return this.pointAmount;
	}

	public void setPointAmount(BigDecimal pointAmount) {
		this.pointAmount = pointAmount;
	}

	public BigDecimal getAlyDiscount() {
		return this.alyDiscount;
	}

	public void setAlyDiscount(BigDecimal alyDiscount) {
		this.alyDiscount = alyDiscount;
	}

	public BigDecimal getMerchantsDiscount() {
		return this.merchantsDiscount;
	}

	public void setMerchantsDiscount(BigDecimal merchantsDiscount) {
		this.merchantsDiscount = merchantsDiscount;
	}

	public BigDecimal getVouchersDiscount() {
		return this.vouchersDiscount;
	}

	public void setVouchersDiscount(BigDecimal vouchersDiscount) {
		this.vouchersDiscount = vouchersDiscount;
	}

	public String getVouchersName() {
		return this.vouchersName;
	}

	public void setVouchersName(String vouchersName) {
		this.vouchersName = vouchersName;
	}

	public BigDecimal getMerchantsResPacketConsume() {
		return this.merchantsResPacketConsume;
	}

	public void setMerchantsResPacketConsume(BigDecimal merchantsResPacketConsume) {
		this.merchantsResPacketConsume = merchantsResPacketConsume;
	}

	public BigDecimal getCardConsume() {
		return this.cardConsume;
	}

	public void setCardConsume(BigDecimal cardConsume) {
		this.cardConsume = cardConsume;
	}

	public String getRefundNo() {
		return this.refundNo;
	}

	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}

	public BigDecimal getServiceFee() {
		return this.serviceFee;
	}

	public void setServiceFee(BigDecimal serviceFee) {
		this.serviceFee = serviceFee;
	}

	public BigDecimal getProfits() {
		return this.profits;
	}

	public void setProfits(BigDecimal profits) {
		this.profits = profits;
	}

	public String getAlyRemark() {
		return this.alyRemark;
	}

	public void setAlyRemark(String alyRemark) {
		this.alyRemark = alyRemark;
	}

	public Integer getChecking() {
		return this.checking;
	}

	public void setChecking(Integer checking) {
		this.checking = checking;
	}

	public Date getCheckingDate() {
		return this.checkingDate;
	}

	public void setCheckingDate(Date checkingDate) {
		this.checkingDate = checkingDate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

}
