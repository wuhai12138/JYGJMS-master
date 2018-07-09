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
@TableName("j_admin")
public class JAdmin implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Integer adminId;

	/**  */
	private String adminName;

	/** 密码 */
	private String adminPassword;

	/**  */
	private String adminPhone;

	/** 管理员类型1超级/2财务/3招聘/4培训中心/5门店 */
	private int adminType;

	/** 归属0超管/1培训中心/2门店 */
	private int adminBelong;

	/**  */
	private Integer shopId;

	/**  */
	@TableLogic
	private Integer isDel;

	/**  */
	private Date createTime;


	public Integer getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return this.adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPhone() {
		return this.adminPhone;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}

	public int getAdminType() {
		return this.adminType;
	}

	public void setAdminType(int adminType) {
		this.adminType = adminType;
	}

	public int getAdminBelong() {
		return this.adminBelong;
	}

	public void setAdminBelong(int adminBelong) {
		this.adminBelong = adminBelong;
	}

	public Integer getShopId() {
		return this.shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
