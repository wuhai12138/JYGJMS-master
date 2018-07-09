package com.summ.model;

import java.io.Serializable;
import java.util.Date;

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
@TableName("j_teacher_re_visit")
public class JTeacherReVisit implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 回访表 */
	@TableId(type = IdType.AUTO)
	private Integer reVisitId;

	/**  */
	private Integer customerId;

	/**  */
	private Integer orderId;

	/**  */
	private Integer houseId;

	/**  */
	private Integer shopId;

	/**  */
	private Integer teacherId;

	/** 计划回访时间 */
	private Date planVisitDate;

	/** 实际回访时间 */
	private Date realVisitDate;

	/**  */
	private String remark;

	/** 回访状态 */
	private Integer reVisitStatus;

	/**  */
	@TableLogic
	private Integer isDel;

	/**  */
	private Integer createId;

	/**  */
	private Date createDate;


	public Integer getReVisitId() {
		return this.reVisitId;
	}

	public void setReVisitId(Integer reVisitId) {
		this.reVisitId = reVisitId;
	}

	public Integer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getHouseId() {
		return this.houseId;
	}

	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}

	public Integer getShopId() {
		return this.shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public Date getPlanVisitDate() {
		return this.planVisitDate;
	}

	public void setPlanVisitDate(Date planVisitDate) {
		this.planVisitDate = planVisitDate;
	}

	public Date getRealVisitDate() {
		return this.realVisitDate;
	}

	public void setRealVisitDate(Date realVisitDate) {
		this.realVisitDate = realVisitDate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getReVisitStatus() {
		return this.reVisitStatus;
	}

	public void setReVisitStatus(Integer reVisitStatus) {
		this.reVisitStatus = reVisitStatus;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
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

}
