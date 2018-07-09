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
@TableName("j_nanny_religion")
public class JNannyReligion implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 宗教 */
	@TableId(type = IdType.AUTO)
	private Integer nannyReligId;

	/** 宗教Id */
	private Integer religionId;

	/**  */
	private Integer nannyId;

	/**  */
	private Integer isDel;


	public Integer getNannyReligId() {
		return this.nannyReligId;
	}

	public void setNannyReligId(Integer nannyReligId) {
		this.nannyReligId = nannyReligId;
	}

	public Integer getReligionId() {
		return this.religionId;
	}

	public void setReligionId(Integer religionId) {
		this.religionId = religionId;
	}

	public Integer getNannyId() {
		return this.nannyId;
	}

	public void setNannyId(Integer nannyId) {
		this.nannyId = nannyId;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

}
