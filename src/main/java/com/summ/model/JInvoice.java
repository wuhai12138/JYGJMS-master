package com.summ.model;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

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
@TableName("j_invoice")
public class JInvoice implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 开票记录表 */
	@TableId(type = IdType.AUTO)
	private Integer invoiceId;

	/** 抬头 */
	private String invoiceTitle;
	/** 抬头类型1表示供应商2表示加盟商 */
	private Integer titleType;

	/** 税号 */
	private String taxNumber;

	/** 发票种类 */
	private Integer invoiceType;

	/** 消费类型 */
	private Integer expenseType;

	/**  */
	private BigDecimal invoiceMoney;

	/** 状态 */
	private Integer invoiceStatus;

	/** 开票人 */
	private Integer invoicingId;


	/**  */
	private Date invoiceDate;

	/**  */
	private Integer createId;

	/**  */
	private Date createDate;

	/**  */
	@TableLogic
	private Integer isDel;


	public Integer getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getInvoiceTitle() {
		return this.invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public String getTaxNumber() {
		return this.taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	public Integer getInvoiceType() {
		return this.invoiceType;
	}

	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}

	public Integer getExpenseType() {
		return this.expenseType;
	}

	public void setExpenseType(Integer expenseType) {
		this.expenseType = expenseType;
	}

	public BigDecimal getInvoiceMoney() {
		return this.invoiceMoney;
	}

	public void setInvoiceMoney(BigDecimal invoiceMoney) {
		this.invoiceMoney = invoiceMoney;
	}

	public Integer getInvoiceStatus() {
		return this.invoiceStatus;
	}

	public void setInvoiceStatus(Integer invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public Integer getInvoicingId() {
		return this.invoicingId;
	}

	public void setInvoicingId(Integer invoicingId) {
		this.invoicingId = invoicingId;
	}

	public Integer getTitleType() {
		return titleType;
	}

	public void setTitleType(Integer titleType) {
		this.titleType = titleType;
	}

	public Date getInvoiceDate() {
		return this.invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Integer getCreateId() {
		return this.createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

}
