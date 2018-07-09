package com.summ.model.response;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * 
 *
 */
public class OrderRefundRes implements Serializable {

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
	private Integer isDel;

	private String orderTypeInfo;
	private String payWayInfo;
	private String refundWayInfo;
	private String checkStatusInfo;
	private String reasonInfo;
	private String createName;
	private String checkName;

	private String customerName;
	private String customerPhone;
	private BigDecimal couponPrice;
    private BigDecimal couponCost;
    private String couponName;

    private Integer scheduleStatus;
    private String scheduleStatusInfo;
    private Date scheduleDate;
    private String startTimeValue;
    private String endTimeValue;
    private BigDecimal nannyCost;
    private Integer shopId;
    private String shopName;
    private Integer nannyId;
    private Integer supplierId;
    private String nannyName;
    private String nannyPhone;


	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getScheduleStatusInfo() {
		return scheduleStatusInfo;
	}

	public void setScheduleStatusInfo(String scheduleStatusInfo) {
		this.scheduleStatusInfo = scheduleStatusInfo;
	}

	public BigDecimal getNannyCost() {
		return nannyCost;
	}

	public void setNannyCost(BigDecimal nannyCost) {
		this.nannyCost = nannyCost;
	}

	public String getStartTimeValue() {
		return startTimeValue;
	}

	public void setStartTimeValue(String startTimeValue) {
		this.startTimeValue = startTimeValue;
	}

	public String getEndTimeValue() {
		return endTimeValue;
	}

	public void setEndTimeValue(String endTimeValue) {
		this.endTimeValue = endTimeValue;
	}

	public Integer getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(Integer scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public String getOrderTypeInfo() {
		return orderTypeInfo;
	}

	public void setOrderTypeInfo(String orderTypeInfo) {
		this.orderTypeInfo = orderTypeInfo;
	}

	public String getPayWayInfo() {
		return payWayInfo;
	}

	public void setPayWayInfo(String payWayInfo) {
		this.payWayInfo = payWayInfo;
	}


	public String getCheckStatusInfo() {
		return checkStatusInfo;
	}

	public void setCheckStatusInfo(String checkStatusInfo) {
		this.checkStatusInfo = checkStatusInfo;
	}

	public String getReasonInfo() {
		return reasonInfo;
	}

	public void setReasonInfo(String reasonInfo) {
		this.reasonInfo = reasonInfo;
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

	public BigDecimal getCouponPrice() {
		return couponPrice;
	}

	public void setCouponPrice(BigDecimal couponPrice) {
		this.couponPrice = couponPrice;
	}

	public BigDecimal getCouponCost() {
		return couponCost;
	}

	public void setCouponCost(BigDecimal couponCost) {
		this.couponCost = couponCost;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
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

	public Integer getRefundWay() {
		return refundWay;
	}

	public void setRefundWay(Integer refundWay) {
		this.refundWay = refundWay;
	}

	public String getRefundWayInfo() {
		return refundWayInfo;
	}

	public void setRefundWayInfo(String refundWayInfo) {
		this.refundWayInfo = refundWayInfo;
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
