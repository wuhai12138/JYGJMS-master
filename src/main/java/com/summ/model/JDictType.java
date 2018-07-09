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
@TableName("j_dict_type")
public class JDictType implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	private Integer typecode;

	/**  */
	private String typename;


	public Integer getTypecode() {
		return this.typecode;
	}

	public void setTypecode(Integer typecode) {
		this.typecode = typecode;
	}

	public String getTypename() {
		return this.typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

}
