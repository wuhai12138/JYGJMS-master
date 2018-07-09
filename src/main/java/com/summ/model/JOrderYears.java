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
@TableName("j_order_years")
public class JOrderYears implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Integer orderId;

	/**  */
	private Integer customerId;

	/**  */
	private Integer shopId;

	/**  */
	private Integer houseId;

	/**  */
	private Integer serviceId;

	/**  */
	private Integer orderStatus;

	/** 订单关闭状态（默认未关闭） */
	private Integer orderCloseStatus;

	/**  */
	private Date startDate;

	private Integer month;

	/**  */
	private Date endDate;

	/** 服务师工资，用于计算服务费和中介费 */
	private BigDecimal salary;

	/** 客户服务费 */
	private BigDecimal serviceFees;

	/** 包年服务费支付状态 */
	private Integer payStatus;

	/**中介费*/
	private BigDecimal agencyFees;


	/**  */
	private Integer goodsId;

	/**  */
	private Integer supplierId;

	/**  */
	private Integer teacherId;

	/**  */
	private Integer createId;

	/**  */
	private Date createTime;

	/**  */
	private Integer modifyId;

	/**  */
	private Date modifyTime;

	/**  */
	private String remark;

	/**  */
	@TableLogic
	private Integer isDel;

	public BigDecimal getAgencyFees() {
		return agencyFees;
	}

	public void setAgencyFees(BigDecimal agencyFees) {
		this.agencyFees = agencyFees;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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

	public Integer getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getOrderCloseStatus() {
		return this.orderCloseStatus;
	}

	public void setOrderCloseStatus(Integer orderCloseStatus) {
		this.orderCloseStatus = orderCloseStatus;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getSalary() {
		return this.salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public BigDecimal getServiceFees() {
		return this.serviceFees;
	}

	public void setServiceFees(BigDecimal serviceFees) {
		this.serviceFees = serviceFees;
	}

	public Integer getPayStatus() {
		return this.payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public Integer getCreateId() {
		return this.createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getModifyId() {
		return this.modifyId;
	}

	public void setModifyId(Integer modifyId) {
		this.modifyId = modifyId;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
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
