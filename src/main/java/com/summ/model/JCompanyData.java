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
@TableName("j_company_data")
public class JCompanyData implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Integer companyId;

	/**  */
	private String companyName="";

	/** 公司注册地址 */
	private String registerAddress;

	/** 统一社会信用代码 */
	private String creditCode;

	/** 经营范围 */
	private String businessScope;

	/**  */
	private Date businessStartDate;

	/**  */
	private Date businessEndDate;

	/** 法人 */
	private String legalPerson;

	/**  */
	private String idCard;

	/** 身份证有效期 */
	private Date idValidDate;

	/** 开户行 */
	private String depositBank;

	/** 银行账号 */
	private String bankAccount;

	/** 营业执照 图片 */
	private String businessLicense;

	/** 身份证正面照 */
	private String idCardBefore;

	/** 身份证反面照 */
	private String idCardAfter;

	@TableLogic
	private Integer isDel;

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Integer getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRegisterAddress() {
		return this.registerAddress;
	}

	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}

	public String getCreditCode() {
		return this.creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public String getBusinessScope() {
		return this.businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public Date getBusinessStartDate() {
		return this.businessStartDate;
	}

	public void setBusinessStartDate(Date businessStartDate) {
		this.businessStartDate = businessStartDate;
	}

	public Date getBusinessEndDate() {
		return this.businessEndDate;
	}

	public void setBusinessEndDate(Date businessEndDate) {
		this.businessEndDate = businessEndDate;
	}

	public String getLegalPerson() {
		return this.legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Date getIdValidDate() {
		return this.idValidDate;
	}

	public void setIdValidDate(Date idValidDate) {
		this.idValidDate = idValidDate;
	}

	public String getDepositBank() {
		return this.depositBank;
	}

	public void setDepositBank(String depositBank) {
		this.depositBank = depositBank;
	}

	public String getBankAccount() {
		return this.bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBusinessLicense() {
		return this.businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getIdCardBefore() {
		return this.idCardBefore;
	}

	public void setIdCardBefore(String idCardBefore) {
		this.idCardBefore = idCardBefore;
	}

	public String getIdCardAfter() {
		return this.idCardAfter;
	}

	public void setIdCardAfter(String idCardAfter) {
		this.idCardAfter = idCardAfter;
	}

}
