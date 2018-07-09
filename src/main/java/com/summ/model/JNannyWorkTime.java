package com.summ.model;

import java.io.Serializable;

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
@TableName("j_nanny_work_time")
public class JNannyWorkTime implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Integer workTimeId;

	/**  */
	private Integer nannyId;

	/**  */
	private Long monday=0L;

	/**  */
	private Long tuesday=0L;

	/**  */
	private Long wednesday=0L;

	/**  */
	private Long thursday=0L;

	/**  */
	private Long friday=0L;

	/**  */
	private Long saturday=0L;

	/**  */
	private Long sunday=0L;

	/**  */
	private String remark="";

	/**  */
	private Integer isDel=16;


	public Integer getWorkTimeId() {
		return this.workTimeId;
	}

	public void setWorkTimeId(Integer workTimeId) {
		this.workTimeId = workTimeId;
	}

	public Integer getNannyId() {
		return this.nannyId;
	}

	public void setNannyId(Integer nannyId) {
		this.nannyId = nannyId;
	}

	public Long getMonday() {
		return this.monday;
	}

	public void setMonday(Long monday) {
		this.monday = monday;
	}

	public Long getTuesday() {
		return this.tuesday;
	}

	public void setTuesday(Long tuesday) {
		this.tuesday = tuesday;
	}

	public Long getWednesday() {
		return this.wednesday;
	}

	public void setWednesday(Long wednesday) {
		this.wednesday = wednesday;
	}

	public Long getThursday() {
		return this.thursday;
	}

	public void setThursday(Long thursday) {
		this.thursday = thursday;
	}

	public Long getFriday() {
		return this.friday;
	}

	public void setFriday(Long friday) {
		this.friday = friday;
	}

	public Long getSaturday() {
		return this.saturday;
	}

	public void setSaturday(Long saturday) {
		this.saturday = saturday;
	}

	public Long getSunday() {
		return this.sunday;
	}

	public void setSunday(Long sunday) {
		this.sunday = sunday;
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
