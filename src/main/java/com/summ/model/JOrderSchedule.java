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
@TableName("j_order_schedule")
public class JOrderSchedule implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Integer scheduleId;

	private Integer customerId;
	/**  */
	private Integer orderId;

	/**  */
	private Integer goodsId;
	/**  */
	private Integer shopId;

	/**  */
	private Integer houseId;

	private Integer serviceId;

	private Integer orderType;

	/** 对账单 */
	private Integer statmentId;

	private Date scheduleDate;

	/** 单价 */
	private BigDecimal unitPrice=new BigDecimal(0);

	/** 总价 */
	private BigDecimal totalPrice=new BigDecimal(0);

	/** 成本（取决于服务师） */
	private BigDecimal cost=new BigDecimal(0);

	/** 日程状态 */
	private Integer scheduleStatus=152;

	/** 日程类型 */
	private Integer scheduleType;

	/** 支付状态 */
	private Integer payStatus=157;

	/** 开始时间Id */
	private Integer startTime;

	/** 结束时间Id */
	private Integer endTime;

	private String startTimeValue;

	private String endTimeValue;

	/** 时间值 */
	private Long timeValue;

	/** 签到人id */
	private Integer clockId=0;

	/** 签到时间 */
	private Date clockTime;

	private Double clockLatitude;
	private Double clockLongitude;

	/** 完工操作人id */
	private Integer completedId=0;

	/** 完工时间 */
	private Date completedTime;

	/** 暂停时间 */
	private Date suspendTime;

	/** 取消操作人id */
	private Integer cancelId=0;

	/** 取消时间  */
	private Date cancelTime;

	private String weekday;

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

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getHouseId() {
		return houseId;
	}

	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Double getClockLatitude() {
		return clockLatitude;
	}

	public void setClockLatitude(Double clockLatitude) {
		this.clockLatitude = clockLatitude;
	}

	public Double getClockLongitude() {
		return clockLongitude;
	}

	public void setClockLongitude(Double clockLongitude) {
		this.clockLongitude = clockLongitude;
	}

	public Integer getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(Integer scheduleType) {
		this.scheduleType = scheduleType;
	}

	public Integer getClockId() {
		return clockId;
	}

	public void setClockId(Integer clockId) {
		this.clockId = clockId;
	}

	public Integer getCompletedId() {
		return completedId;
	}

	public void setCompletedId(Integer completedId) {
		this.completedId = completedId;
	}

	public Integer getCancelId() {
		return cancelId;
	}

	public void setCancelId(Integer cancelId) {
		this.cancelId = cancelId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
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

	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public Long getTimeValue() {
		return timeValue;
	}

	public void setTimeValue(Long timeValue) {
		this.timeValue = timeValue;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
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

	public Integer getStatmentId() {
		return this.statmentId;
	}

	public void setStatmentId(Integer statmentId) {
		this.statmentId = statmentId;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getCost() {
		return this.cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public Integer getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(Integer scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	public Integer getPayStatus() {
		return this.payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	public Date getClockTime() {
		return this.clockTime;
	}

	public void setClockTime(Date clockTime) {
		this.clockTime = clockTime;
	}

	public Date getCompletedTime() {
		return this.completedTime;
	}

	public void setCompletedTime(Date completedTime) {
		this.completedTime = completedTime;
	}

	public Date getSuspendTime() {
		return this.suspendTime;
	}

	public void setSuspendTime(Date suspendTime) {
		this.suspendTime = suspendTime;
	}

	public Date getCancelTime() {
		return this.cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

}
