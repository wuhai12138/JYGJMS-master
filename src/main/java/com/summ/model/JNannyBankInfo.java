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
@TableName("j_nanny_bank_info")
public class JNannyBankInfo implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Integer nannyBankId;

	/**  */
	private Integer nannyId;

	/**  */
	private String nannyName;

	/**  */
	private String nannyIdCard;

	/** 身份证 */
	private String nannyPhone;

	/** 卡号 */
	private String salaryCard;

	/**  */
	private String area;

	/** 银行电话 */
	private String tel;

	/** 银行卡产品名称 */
	private String brand;

	/** 银行名称 */
	private String bankName;

	/** 卡种 */
	private String cardType;

	/** 银行官网 */
	private String url;

	/** 回执卡号 */
	private String cardNum;

	/** 验证返回代码0表示成功，其他表示失败 */
	private Integer code;

	/** 代码对应消息 */
	private String codeMsg;

	/** 验证结果json字符串 */
	private String resultJson;

	public JNannyBankInfo() {
	}

	public JNannyBankInfo(Integer nannyId, String nannyName, String nannyIdCard, String nannyPhone, String salaryCard, String area, String tel, String brand, String bankName, String cardType, String url, String cardNum, String resultJson) {
		this.nannyId = nannyId;
		this.nannyName = nannyName;
		this.nannyIdCard = nannyIdCard;
		this.nannyPhone = nannyPhone;
		this.salaryCard = salaryCard;
		this.area = area;
		this.tel = tel;
		this.brand = brand;
		this.bankName = bankName;
		this.cardType = cardType;
		this.url = url;
		this.cardNum = cardNum;
		this.resultJson = resultJson;
	}

	public Integer getNannyBankId() {
		return this.nannyBankId;
	}

	public void setNannyBankId(Integer nannyBankId) {
		this.nannyBankId = nannyBankId;
	}

	public Integer getNannyId() {
		return this.nannyId;
	}

	public void setNannyId(Integer nannyId) {
		this.nannyId = nannyId;
	}

	public String getNannyName() {
		return this.nannyName;
	}

	public void setNannyName(String nannyName) {
		this.nannyName = nannyName;
	}

	public String getNannyIdCard() {
		return this.nannyIdCard;
	}

	public void setNannyIdCard(String nannyIdCard) {
		this.nannyIdCard = nannyIdCard;
	}

	public String getNannyPhone() {
		return this.nannyPhone;
	}

	public void setNannyPhone(String nannyPhone) {
		this.nannyPhone = nannyPhone;
	}

	public String getSalaryCard() {
		return this.salaryCard;
	}

	public void setSalaryCard(String salaryCard) {
		this.salaryCard = salaryCard;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCardNum() {
		return this.cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getCodeMsg() {
		return this.codeMsg;
	}

	public void setCodeMsg(String codeMsg) {
		this.codeMsg = codeMsg;
	}

	public String getResultJson() {
		return this.resultJson;
	}

	public void setResultJson(String resultJson) {
		this.resultJson = resultJson;
	}

}
