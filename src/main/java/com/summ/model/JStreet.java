package com.summ.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.summ.utils.StringUtil;

/**
 *
 * 
 *
 */
@TableName("j_street")
public class JStreet implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Integer streetId;

	/**  */
	private String streetCode;

	/**  */
	private String streetName;

	/**  */
	private String areaCode;

	public JStreet() {
	}

	public JStreet(Integer streetId, String streetCode, String streetName, String areaCode) {
		this.streetId = streetId;
		this.streetCode = streetCode;
		this.streetName = streetName;
		this.areaCode = areaCode;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getStreetId() {
		return streetId;
	}

	public void setStreetId(Integer streetId) {
		this.streetId = streetId;
	}


	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetCode() {
		return streetCode;
	}

	public void setStreetCode(String streetCode) {
		this.streetCode = streetCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
}
