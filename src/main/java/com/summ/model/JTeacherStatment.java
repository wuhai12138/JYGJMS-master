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
@TableName("j_teacher_statment")
public class JTeacherStatment implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Integer statmentId;

	/**  */
	private String statmentNanny;

	/**  */
	private Integer teacherId;

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

	/** 带教对账单类型 */
	private Integer statmentTeacherType;

	/** 带教当前实时星级216 */
	private Integer teacherCurrentJobLevel;
	/** 带教当前小时薪资 */
	private BigDecimal teacherCurrentUnitPrice;
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

	/** 奖励原因 */
	private Integer reason;

	/**  */
	private Date createDate;

	/**  */
	private String remark;

	/**  */
	private Integer adminId;

	@TableLogic
	private Integer isDel;

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public JTeacherStatment() {
	}

	public JTeacherStatment(String statmentNanny, Integer teacherId, Integer scheduleId, Integer orderId,
							Integer shopId, Integer houseId, Integer customerId, Integer statmentTeacherType, BigDecimal statmentMoney,
							Integer orderType, Integer goodsId, String serviceTime, Double serviceTimeLength, Date serviceDate,String remark,
							Integer adminId,Integer teacherCurrentJobLevel,BigDecimal teacherCurrentUnitPrice,Integer orderPriceType) {
		this.statmentNanny = statmentNanny;
		this.teacherId = teacherId;
		this.scheduleId = scheduleId;
		this.orderId = orderId;
		this.shopId = shopId;
		this.houseId = houseId;
		this.customerId = customerId;
		this.statmentTeacherType = statmentTeacherType;
		this.statmentMoney = statmentMoney;
		this.orderType = orderType;
		this.goodsId = goodsId;
		this.serviceTime = serviceTime;
		this.serviceTimeLength = serviceTimeLength;
		this.serviceDate = serviceDate;
		this.remark = remark;
		this.adminId = adminId;
		this.teacherCurrentJobLevel = teacherCurrentJobLevel;
		this.teacherCurrentUnitPrice = teacherCurrentUnitPrice;
		this.orderPriceType = orderPriceType;
	}

	public Integer getTeacherCurrentJobLevel() {
		return teacherCurrentJobLevel;
	}

	public void setTeacherCurrentJobLevel(Integer teacherCurrentJobLevel) {
		this.teacherCurrentJobLevel = teacherCurrentJobLevel;
	}

	public BigDecimal getTeacherCurrentUnitPrice() {
		return teacherCurrentUnitPrice;
	}

	public void setTeacherCurrentUnitPrice(BigDecimal teacherCurrentUnitPrice) {
		this.teacherCurrentUnitPrice = teacherCurrentUnitPrice;
	}

	public Integer getOrderPriceType() {
		return orderPriceType;
	}

	public void setOrderPriceType(Integer orderPriceType) {
		this.orderPriceType = orderPriceType;
	}

	public Integer getStatmentId() {
		return this.statmentId;
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

	public Integer getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
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

	public Integer getStatmentTeacherType() {
		return this.statmentTeacherType;
	}

	public void setStatmentTeacherType(Integer statmentTeacherType) {
		this.statmentTeacherType = statmentTeacherType;
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

	public Integer getReason() {
		return this.reason;
	}

	public void setReason(Integer reason) {
		this.reason = reason;
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

	public Integer getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

}
