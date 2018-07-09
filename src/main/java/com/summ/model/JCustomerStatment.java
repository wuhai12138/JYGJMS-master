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
@TableName("j_customer_statment")
public class JCustomerStatment implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Integer statmentId;

	/** 系统订单号*/
	private String statmentCustomer;

	/**  */
	private Integer customerId;

	/**  */
	private Integer goodsId;

	/**  */
	private Integer houseId;

	/**  */
	private Integer orderId;

	/** 订单类型 */
	private Integer orderType;

	private Integer couponListId;

	/** 日程Id */
	private Integer scheduleId;

	/**  */
	private Integer shopId;

	/** 服务时间（几点到几点） */
	private String serviceTime;

	/** 服务时长 */
	private Double serviceTimeLength;

	/** 服务日期 */
	private Date serviceDate;

	/** 支付日期 */
	private Date chargeDate;
	/** 创建日期 */
	private Date createDate;
	/** 支付回调日期 */
	private Date notifyDate;

	/** 客户对账单类型 */
	private Integer statmentCustomerType;

	/** 支付金额 */
	private BigDecimal chargeMoney;

	/** 充值方式 */
	private Integer chargeWay;

	/**  */
	private Integer adminId;

	/** 终端 */
	private Integer terminal;

	/** 状态 */
	private Integer status;

	/** 余额 */
	private BigDecimal balance;

	/** 流水号 */
	private String serialNumber;

	/**  */
	private String remark;

	/**  */
	private String withdrawlCard;

	/**  */
	private String withdrawlBank;

	/**对账状态*/
	private Integer checking;

	private Date checkingDate;

	@TableLogic
	private Integer isDel;

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public JCustomerStatment() {
	}

	public JCustomerStatment(String statmentCustomer, Integer customerId, Integer goodsId, Integer houseId, Integer orderId, Integer orderType, Integer scheduleId, Integer shopId, String serviceTime, Double serviceTimeLength, Date serviceDate, Date chargeDate, Integer statmentCustomerType, BigDecimal chargeMoney, Integer chargeWay, Integer adminId, Integer terminal, Integer status, BigDecimal balance, String serialNumber, String remark) {
		this.statmentCustomer = statmentCustomer;
		this.customerId = customerId;
		this.goodsId = goodsId;
		this.houseId = houseId;
		this.orderId = orderId;
		this.orderType = orderType;
		this.scheduleId = scheduleId;
		this.shopId = shopId;
		this.serviceTime = serviceTime;
		this.serviceTimeLength = serviceTimeLength;
		this.serviceDate = serviceDate;
		this.chargeDate = chargeDate;
		this.statmentCustomerType = statmentCustomerType;
		this.chargeMoney = chargeMoney;
		this.chargeWay = chargeWay;
		this.adminId = adminId;
		this.terminal = terminal;
		this.status = status;
		this.balance = balance;
		this.serialNumber = serialNumber;
		this.remark = remark;
	}
	public JCustomerStatment(String statmentCustomer, Integer customerId, Integer goodsId, Integer houseId,
							 Integer orderId, Integer orderType, Integer scheduleId, Integer shopId, String serviceTime,
							 Double serviceTimeLength, Date serviceDate, Date chargeDate, Integer statmentCustomerType,
							 BigDecimal chargeMoney, Integer chargeWay, Integer adminId, Integer terminal, Integer status,
							 BigDecimal balance, String serialNumber, String remark,Integer couponListId) {
		this.statmentCustomer = statmentCustomer;
		this.customerId = customerId;
		this.goodsId = goodsId;
		this.houseId = houseId;
		this.orderId = orderId;
		this.orderType = orderType;
		this.scheduleId = scheduleId;
		this.shopId = shopId;
		this.serviceTime = serviceTime;
		this.serviceTimeLength = serviceTimeLength;
		this.serviceDate = serviceDate;
		this.chargeDate = chargeDate;
		this.statmentCustomerType = statmentCustomerType;
		this.chargeMoney = chargeMoney;
		this.chargeWay = chargeWay;
		this.adminId = adminId;
		this.terminal = terminal;
		this.status = status;
		this.balance = balance;
		this.serialNumber = serialNumber;
		this.remark = remark;
		this.couponListId = couponListId;
	}

	public Date getCheckingDate() {
		return checkingDate;
	}

	public void setCheckingDate(Date checkingDate) {
		this.checkingDate = checkingDate;
	}

	public Integer getChecking() {
		return checking;
	}

	public void setChecking(Integer checking) {
		this.checking = checking;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getNotifyDate() {
		return notifyDate;
	}

	public void setNotifyDate(Date notifyDate) {
		this.notifyDate = notifyDate;
	}

	public Integer getCouponListId() {
		return couponListId;
	}

	public void setCouponListId(Integer couponListId) {
		this.couponListId = couponListId;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getStatmentId() {
		return statmentId;
	}

	public void setStatmentId(Integer statmentId) {
		this.statmentId = statmentId;
	}

	public String getStatmentCustomer() {
		return this.statmentCustomer;
	}

	public void setStatmentCustomer(String statmentCustomer) {
		this.statmentCustomer = statmentCustomer;
	}

	public Integer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getHouseId() {
		return this.houseId;
	}

	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
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

	public Integer getShopId() {
		return this.shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getServiceTime() {
		return this.serviceTime;
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

	public Double getServiceTimeLength() {
		return this.serviceTimeLength;
	}

	public void setServiceTimeLength(Double serviceTimeLength) {
		this.serviceTimeLength = serviceTimeLength;
	}

	public Date getServiceDate() {
		return this.serviceDate;
	}

	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}

	public Date getChargeDate() {
		return this.chargeDate;
	}

	public void setChargeDate(Date chargeDate) {
		this.chargeDate = chargeDate;
	}

	public Integer getStatmentCustomerType() {
		return this.statmentCustomerType;
	}

	public void setStatmentCustomerType(Integer statmentCustomerType) {
		this.statmentCustomerType = statmentCustomerType;
	}

	public BigDecimal getChargeMoney() {
		return this.chargeMoney;
	}

	public void setChargeMoney(BigDecimal chargeMoney) {
		this.chargeMoney = chargeMoney;
	}

	public Integer getChargeWay() {
		return this.chargeWay;
	}

	public void setChargeWay(Integer chargeWay) {
		this.chargeWay = chargeWay;
	}

	public Integer getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Integer getTerminal() {
		return this.terminal;
	}

	public void setTerminal(Integer terminal) {
		this.terminal = terminal;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getBalance() {
		return this.balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getWithdrawlCard() {
		return this.withdrawlCard;
	}

	public void setWithdrawlCard(String withdrawlCard) {
		this.withdrawlCard = withdrawlCard;
	}

	public String getWithdrawlBank() {
		return this.withdrawlBank;
	}

	public void setWithdrawlBank(String withdrawlBank) {
		this.withdrawlBank = withdrawlBank;
	}

}
