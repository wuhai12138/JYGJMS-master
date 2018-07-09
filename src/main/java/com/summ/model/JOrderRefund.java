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
@TableName("j_order_refund")
public class JOrderRefund implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 订单退款表 */
	@TableId(type = IdType.AUTO)
	private Integer refundId;
	private Integer customerId;
	private Integer orderType;
	private Integer orderId;
	private Integer scheduleId;
	/** 支付方式 */
	private Integer payWay;
	/** 退款方式 */
	private Integer refundWay;
	/** 审核状态 */
	private Integer checkStatus;
	/** 退款原因 */
	private Integer reason;
	/** 订单金额 */
	private BigDecimal orderMoney;
	/** 折扣金额 */
	private BigDecimal discount;
	/** 支付金额 */
	private BigDecimal payMoney;
	/** 退款金额 */
	private BigDecimal refundMoney;
	/** 优惠券id */
	private Integer couponListId;
	private String remark;
	private Integer createId;
	private Date createDate;
	private Integer checkId;
	private Date checkDate;
	@TableLogic
	private Integer isDel;

	public JOrderRefund() {
	}

	public JOrderRefund(Integer customerId, Integer orderType, Integer orderId, Integer scheduleId, Integer payWay, Integer refundWay, Integer reason, BigDecimal orderMoney, BigDecimal discount, BigDecimal payMoney, BigDecimal refundMoney, Integer couponListId) {
		this.customerId = customerId;
		this.orderType = orderType;
		this.orderId = orderId;
		this.scheduleId = scheduleId;
		this.payWay = payWay;
		this.refundWay = refundWay;
		this.reason = reason;
		this.orderMoney = orderMoney;
		this.discount = discount;
		this.payMoney = payMoney;
		this.refundMoney = refundMoney;
		this.couponListId = couponListId;
	}

	public Integer getRefundWay() {
		return refundWay;
	}

	public void setRefundWay(Integer refundWay) {
		this.refundWay = refundWay;
	}

	public Integer getRefundId() {
		return this.refundId;
	}

	public void setRefundId(Integer refundId) {
		this.refundId = refundId;
	}

	public Integer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getOrderType() {
		return this.orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getScheduleId() {
		return this.scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Integer getPayWay() {
		return this.payWay;
	}

	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}

	public Integer getCheckStatus() {
		return this.checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Integer getReason() {
		return this.reason;
	}

	public void setReason(Integer reason) {
		this.reason = reason;
	}

	public BigDecimal getOrderMoney() {
		return this.orderMoney;
	}

	public void setOrderMoney(BigDecimal orderMoney) {
		this.orderMoney = orderMoney;
	}

	public BigDecimal getDiscount() {
		return this.discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getPayMoney() {
		return this.payMoney;
	}

	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}

	public BigDecimal getRefundMoney() {
		return this.refundMoney;
	}

	public void setRefundMoney(BigDecimal refundMoney) {
		this.refundMoney = refundMoney;
	}

	public Integer getCouponListId() {
		return this.couponListId;
	}

	public void setCouponListId(Integer couponListId) {
		this.couponListId = couponListId;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCreateId() {
		return this.createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getCheckId() {
		return this.checkId;
	}

	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}

	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

}
