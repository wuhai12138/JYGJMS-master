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
@TableName("j_leaguer")
public class JLeaguer implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 加盟商表 */
	@TableId(type = IdType.AUTO)
	private Integer leaguerId;
	private Integer companyId;

	/**  */
	private String address;

	/** 手机号 */
	private String phone;

	/**  */
	private Integer leaguerStatus;

	/**  */
	private String contacter;

	/**  */
	private Integer provinceId;

	/**  */
	private Integer cityId;

	/**  */
	private Integer areaId;

	/**  */
	private String remark="";


	/**  */
	private Date createTime;

	/**  */
	@TableLogic
	private Integer isDel;

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getLeaguerId() {
		return this.leaguerId;
	}

	public void setLeaguerId(Integer leaguerId) {
		this.leaguerId = leaguerId;
	}


	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getLeaguerStatus() {
		return leaguerStatus;
	}

	public void setLeaguerStatus(Integer leaguerStatus) {
		this.leaguerStatus = leaguerStatus;
	}

	public String getContacter() {
		return this.contacter;
	}

	public void setContacter(String contacter) {
		this.contacter = contacter;
	}

	public Integer getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
