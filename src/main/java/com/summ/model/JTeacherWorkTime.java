package com.summ.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 
 *
 */
@TableName("j_teacher_work_time")
public class JTeacherWorkTime implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Integer workTimeId;

	/**  */
	private Integer teacherId;

	/**  */
	private Long monday;

	/**  */
	private Long tuesday;

	/**  */
	private Long wednesday;

	/**  */
	private Long thursday;

	/**  */
	private Long friday;

	/**  */
	private Long saturday;

	/**  */
	private Long sunday;

	/**  */
	private String remark;

	/**  */
	private Integer isDel;

	public JTeacherWorkTime() {
	}

	public JTeacherWorkTime(Integer teacherId, Long monday, Long tuesday, Long wednesday, Long thursday, Long friday, Long saturday, Long sunday) {
		this.teacherId = teacherId;
		this.monday = monday;
		this.tuesday = tuesday;
		this.wednesday = wednesday;
		this.thursday = thursday;
		this.friday = friday;
		this.saturday = saturday;
		this.sunday = sunday;
	}

	public Integer getWorkTimeId() {
		return this.workTimeId;
	}

	public void setWorkTimeId(Integer workTimeId) {
		this.workTimeId = workTimeId;
	}

	public Integer getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
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
