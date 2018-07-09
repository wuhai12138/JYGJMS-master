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
@TableName("j_supplier_statment")
public class JSupplierStatment implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Integer statmentId;

	/**  */
	private String statmentSupplier;

	/**  */
	private Integer supplierId;

	/**  */
	private Integer scheduleId;

	/**  */
	private Integer orderId;

	/**  */
	private Integer shopId;

	/**  */
	private Integer houseId;

	/**  */
	private Integer customerId;

	/** 服务师对账单类型 */
	private Integer statmentSupplierType;

	/** 平台成本价 */
	private BigDecimal statmentMoney;

	/** 供应商成本价 */
	private BigDecimal supplierCost;

	/** 订单类型 */
	private Integer orderType;

	/** 产品Id */
	private Integer goodsId;

	/** 服务时间（几点到几点） */
	private String serviceTime;

	/** 服务时长 */
	private Double serviceTimeLength;

	/** 服务日期 */
	private Date serviceDate;

	/**  */
	private Date createDate;

	/**  */
	private String remark;
	@TableLogic
	private Integer isDel;

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public JSupplierStatment() {
	}

	public JSupplierStatment(String statmentSupplier, Integer supplierId, Integer scheduleId, Integer orderId, Integer shopId,
							 Integer houseId, Integer customerId, Integer statmentSupplierType, BigDecimal statmentMoney,BigDecimal supplierCost, Integer orderType,
							 Integer goodsId, String serviceTime, Double serviceTimeLength, Date serviceDate, Date createDate, String remark) {
		this.statmentSupplier = statmentSupplier;
		this.supplierId = supplierId;
		this.scheduleId = scheduleId;
		this.orderId = orderId;
		this.shopId = shopId;
		this.houseId = houseId;
		this.customerId = customerId;
		this.statmentSupplierType = statmentSupplierType;
		this.statmentMoney = statmentMoney;
		this.supplierCost = supplierCost;
		this.orderType = orderType;
		this.goodsId = goodsId;
		this.serviceTime = serviceTime;
		this.serviceTimeLength = serviceTimeLength;
		this.serviceDate = serviceDate;
		this.createDate = createDate;
		this.remark = remark;
	}

	public BigDecimal getSupplierCost() {
		return supplierCost;
	}

	public void setSupplierCost(BigDecimal supplierCost) {
		this.supplierCost = supplierCost;
	}
	public Integer getStatmentId() {
		return this.statmentId;
	}

	public void setStatmentId(Integer statmentId) {
		this.statmentId = statmentId;
	}

	public String getStatmentSupplier() {
		return this.statmentSupplier;
	}

	public void setStatmentSupplier(String statmentSupplier) {
		this.statmentSupplier = statmentSupplier;
	}

	public Integer getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getScheduleId() {
		return this.scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getShopId() {
		return this.shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getHouseId() {
		return this.houseId;
	}

	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}

	public Integer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getStatmentSupplierType() {
		return this.statmentSupplierType;
	}

	public void setStatmentSupplierType(Integer statmentSupplierType) {
		this.statmentSupplierType = statmentSupplierType;
	}

	public BigDecimal getStatmentMoney() {
		return this.statmentMoney;
	}

	public void setStatmentMoney(BigDecimal statmentMoney) {
		this.statmentMoney = statmentMoney;
	}

	public Integer getOrderType() {
		return this.orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
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

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
