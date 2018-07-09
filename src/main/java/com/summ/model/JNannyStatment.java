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
@TableName("j_nanny_statment")
public class JNannyStatment implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Integer statmentId;

	private String statmentNanny;

	/**  */
	private Integer nannyId;

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
	private Integer statmentNannyType;
	/** 服务师当前实时星级84、85、86 */
	private Integer nannyCurrentJobLevel;
	/** 服务师当前小时薪资 */
	private BigDecimal nannyCurrentUnitPrice;
	/** 标记订单是否为自定义价格（即手动修改的价格,1表示手动修改） */
	private Integer orderPriceType;

	/** 金额 */
	private BigDecimal statmentMoney;

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

	private Date createDate = new Date();

	/**  */
	private String remark;

	/**奖励原因*/
	private Integer reason;

	private Integer adminId;
	@TableLogic
	private Integer isDel;

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}


	public JNannyStatment() {
	}

	public JNannyStatment(String statmentNanny, Integer nannyId, Integer scheduleId, Integer orderId, Integer shopId, Integer houseId, Integer customerId,
						  Integer statmentNannyType, BigDecimal statmentMoney, Integer orderType, Integer goodsId, String serviceTime, Double serviceTimeLength,
						  Date serviceDate, String remark,Integer adminId,Integer nannyCurrentJobLevel,BigDecimal nannyCurrentUnitPrice,Integer orderPriceType) {
		this.statmentNanny = statmentNanny;
		this.nannyId = nannyId;
		this.scheduleId = scheduleId;
		this.orderId = orderId;
		this.shopId = shopId;
		this.houseId = houseId;
		this.customerId = customerId;
		this.statmentNannyType = statmentNannyType;
		this.statmentMoney = statmentMoney;
		this.orderType = orderType;
		this.goodsId = goodsId;
		this.serviceTime = serviceTime;
		this.serviceTimeLength = serviceTimeLength;
		this.serviceDate = serviceDate;
		this.remark = remark;
		this.adminId=adminId;
		this.nannyCurrentJobLevel=nannyCurrentJobLevel;
		this.nannyCurrentUnitPrice=nannyCurrentUnitPrice;
		this.orderPriceType=orderPriceType;
	}



	public Integer getNannyCurrentJobLevel() {
		return nannyCurrentJobLevel;
	}

	public void setNannyCurrentJobLevel(Integer nannyCurrentJobLevel) {
		this.nannyCurrentJobLevel = nannyCurrentJobLevel;
	}

	public BigDecimal getNannyCurrentUnitPrice() {
		return nannyCurrentUnitPrice;
	}

	public void setNannyCurrentUnitPrice(BigDecimal nannyCurrentUnitPrice) {
		this.nannyCurrentUnitPrice = nannyCurrentUnitPrice;
	}

	public Integer getOrderPriceType() {
		return orderPriceType;
	}

	public void setOrderPriceType(Integer orderPriceType) {
		this.orderPriceType = orderPriceType;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public Integer getReason() {
		return reason;
	}

	public void setReason(Integer reason) {
		this.reason = reason;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getStatmentId() {
		return statmentId;
	}

	public void setStatmentId(Integer statmentId) {
		this.statmentId = statmentId;
	}

	public String getStatmentNanny() {
		return this.statmentNanny;
	}

	public void setStatmentNanny(String statmentNanny) {
		this.statmentNanny = statmentNanny;
	}

	public Integer getNannyId() {
		return this.nannyId;
	}

	public void setNannyId(Integer nannyId) {
		this.nannyId = nannyId;
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

	public Integer getStatmentNannyType() {
		return this.statmentNannyType;
	}

	public void setStatmentNannyType(Integer statmentNannyType) {
		this.statmentNannyType = statmentNannyType;
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
