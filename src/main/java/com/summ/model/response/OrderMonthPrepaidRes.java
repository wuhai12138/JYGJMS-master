package com.summ.model.response;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author summ
 * @since 2018-05-29
 */
public class OrderMonthPrepaidRes {


    /**
     * 包月订单预支付id
     */
    private Integer prepaidId;
    /**
     * 包月订单id
     */
    @TableField("orderId")
    private Integer orderId;
    /**区分是试工还是非试工*/
    private Integer prepaidType;
    private String prepaidTypeInfo;
    @TableField("customerId")
    private Integer customerId;
    private Integer nannyId;
    private String nannyName;
    private String nannyPhone;
    /**
     * 预支付日期
     */
    @TableField("prepaidDate")
    private Date prepaidDate;
    /**
     * 当月订单金额
     */
    @TableField("orderMoney")
    private BigDecimal orderMoney;
    /**
     * 服务师当月工资
     */
    @TableField("nannySalary")
    private BigDecimal nannySalary;
    /**
     * 默认待支付
     */
    @TableField("payStatus")
    private Integer payStatus;
    private String payStatusInfo;
    @TableField("createId")
    private Integer createId;
    @TableField("createDate")
    private Date createDate;
    @TableField("modifyId")
    private Integer modifyId;
    @TableField("modifyDate")
    private Date modifyDate;
    @TableField("isDel")
    private Integer isDel;

    public Integer getPrepaidType() {
        return prepaidType;
    }

    public void setPrepaidType(Integer prepaidType) {
        this.prepaidType = prepaidType;
    }

    public String getPrepaidTypeInfo() {
        return prepaidTypeInfo;
    }

    public void setPrepaidTypeInfo(String prepaidTypeInfo) {
        this.prepaidTypeInfo = prepaidTypeInfo;
    }

    public String getNannyName() {
        return nannyName;
    }

    public void setNannyName(String nannyName) {
        this.nannyName = nannyName;
    }

    public String getNannyPhone() {
        return nannyPhone;
    }

    public void setNannyPhone(String nannyPhone) {
        this.nannyPhone = nannyPhone;
    }

    public String getPayStatusInfo() {
        return payStatusInfo;
    }

    public void setPayStatusInfo(String payStatusInfo) {
        this.payStatusInfo = payStatusInfo;
    }

    public Integer getNannyId() {
        return nannyId;
    }

    public void setNannyId(Integer nannyId) {
        this.nannyId = nannyId;
    }

    public Integer getPrepaidId() {
        return prepaidId;
    }

    public void setPrepaidId(Integer prepaidId) {
        this.prepaidId = prepaidId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getPrepaidDate() {
        return prepaidDate;
    }

    public void setPrepaidDate(Date prepaidDate) {
        this.prepaidDate = prepaidDate;
    }

    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }

    public BigDecimal getNannySalary() {
        return nannySalary;
    }

    public void setNannySalary(BigDecimal nannySalary) {
        this.nannySalary = nannySalary;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getCreateId() {
        return createId;
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

    public Integer getModifyId() {
        return modifyId;
    }

    public void setModifyId(Integer modifyId) {
        this.modifyId = modifyId;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "JOrderMonthPrepaid{" +
        "prepaidId=" + prepaidId +
        ", orderId=" + orderId +
        ", customerId=" + customerId +
        ", prepaidDate=" + prepaidDate +
        ", orderMoney=" + orderMoney +
        ", nannySalary=" + nannySalary +
        ", payStatus=" + payStatus +
        ", createId=" + createId +
        ", createDate=" + createDate +
        ", modifyId=" + modifyId +
        ", modifyDate=" + modifyDate +
        ", isDel=" + isDel +
        "}";
    }
}
