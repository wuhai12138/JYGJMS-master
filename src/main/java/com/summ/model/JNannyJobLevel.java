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
@TableName("j_nanny_job_level")
public class JNannyJobLevel implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 工种表 */
	@TableId(type = IdType.AUTO)
	private Integer nannyJobLevelId;

	/**  */
	private Integer nannyId;

	/**  */
	private Integer jobLevelId;

	/**  */
	private Integer isDel;


	public Integer getNannyJobLevelId() {
		return this.nannyJobLevelId;
	}

	public void setNannyJobLevelId(Integer nannyJobLevelId) {
		this.nannyJobLevelId = nannyJobLevelId;
	}

	public Integer getNannyId() {
		return this.nannyId;
	}

	public void setNannyId(Integer nannyId) {
		this.nannyId = nannyId;
	}

	public Integer getJobLevelId() {
		return jobLevelId;
	}

	public void setJobLevelId(Integer jobLevelId) {
		this.jobLevelId = jobLevelId;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

}
