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
@TableName("j_nanny_insurance")
public class JNannyInsurance implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 服务师保险记录表 */
	@TableId(type = IdType.AUTO)
	private Integer insuranceId;

	/** 保险月份（每月一号） */
	private Date insuranceDate;

	/**  */
	private Integer nannyId;

	/**  */
	private Date createTime=new Date();

	/**  */
	@TableLogic
	private Integer isDel=16;

	public JNannyInsurance(Date insuranceDate, Integer nannyId) {
		this.insuranceDate = insuranceDate;
		this.nannyId = nannyId;
	}

	public JNannyInsurance() {
	}

	public Integer getInsuranceId() {
		return this.insuranceId;
	}

	public void setInsuranceId(Integer insuranceId) {
		this.insuranceId = insuranceId;
	}

	public Date getInsuranceDate() {
		return this.insuranceDate;
	}

	public void setInsuranceDate(Date insuranceDate) {
		this.insuranceDate = insuranceDate;
	}

	public Integer getNannyId() {
		return this.nannyId;
	}

	public void setNannyId(Integer nannyId) {
		this.nannyId = nannyId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

}
