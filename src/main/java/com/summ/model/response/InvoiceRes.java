package com.summ.model.response;

import java.math.BigDecimal;
import java.util.Date;

public class InvoiceRes {
    private Integer invoiceId;

    /** 抬头 */
    private String invoiceTitle;
    /** 抬头类型1表示供应商2表示加盟商 */
    private Integer titleType;
    private String titleTypeInfo;

    /** 税号 */
    private String taxNumber;

    /** 发票种类 */
    private Integer invoiceType;
    private String invoiceTypeInfo;

    /** 消费类型 */
    private Integer expenseType;
    private String expenseTypeInfo;

    /**  */
    private BigDecimal invoiceMoney;

    /** 状态 */
    private Integer invoiceStatus;
    private String invoiceStatusInfo;

    /** 开票人 */
    private Integer invoicingId;
    private String invoicingName;



    /**  */
    private Date invoiceDate;

    /**  */
    private Integer createId;
    private String createName;

    /**  */
    private Date createDate;

    /**  */
    private Integer isDel;

    public String getInvoicingName() {
        return invoicingName;
    }

    public void setInvoicingName(String invoicingName) {
        this.invoicingName = invoicingName;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceTypeInfo() {
        return invoiceTypeInfo;
    }

    public void setInvoiceTypeInfo(String invoiceTypeInfo) {
        this.invoiceTypeInfo = invoiceTypeInfo;
    }

    public Integer getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(Integer expenseType) {
        this.expenseType = expenseType;
    }

    public String getExpenseTypeInfo() {
        return expenseTypeInfo;
    }

    public void setExpenseTypeInfo(String expenseTypeInfo) {
        this.expenseTypeInfo = expenseTypeInfo;
    }

    public BigDecimal getInvoiceMoney() {
        return invoiceMoney;
    }

    public void setInvoiceMoney(BigDecimal invoiceMoney) {
        this.invoiceMoney = invoiceMoney;
    }

    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getInvoiceStatusInfo() {
        return invoiceStatusInfo;
    }

    public void setInvoiceStatusInfo(String invoiceStatusInfo) {
        this.invoiceStatusInfo = invoiceStatusInfo;
    }

    public Integer getInvoicingId() {
        return invoicingId;
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

    public String getTitleTypeInfo() {
        return titleTypeInfo;
    }

    public void setTitleTypeInfo(String titleTypeInfo) {
        this.titleTypeInfo = titleTypeInfo;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}
