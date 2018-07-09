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
@TableName("j_nanny_certificate")
public class JNannyCertificate implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 证件 */
	@TableId(type = IdType.AUTO)
	private Integer nannyCertId;

	/**  */
	private Integer nannyId;

	/**  */
	private String photo;

	/**  */
	private Integer certificateId;

	/** 证件编号 */
	private String certificateNumber;

	/** 证件有效期 */
	private Date validDate;

	/**  */
	private Integer createId;

	/**  */
	private Date createTime;

	/**  */
	@TableLogic
	private Integer isDel;


	public String getCertificateNumber() {
		return certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getNannyCertId() {
		return this.nannyCertId;
	}

	public void setNannyCertId(Integer nannyCertId) {
		this.nannyCertId = nannyCertId;
	}

	public Integer getNannyId() {
		return this.nannyId;
	}

	public void setNannyId(Integer nannyId) {
		this.nannyId = nannyId;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getCertificateId() {
		return this.certificateId;
	}

	public void setCertificateId(Integer certificateId) {
		this.certificateId = certificateId;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

}
