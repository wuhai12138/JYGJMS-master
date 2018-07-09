package com.summ.model;

import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author summ
 * @since 2018-05-29
 */
@TableName("j_order_month_prepaid")
public class JOrderMonthPrepaid implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 包月订单预支付id
     */
    @TableId(value = "prepaidId", type = IdType.AUTO)
    private Integer prepaidId;
    /**
     * 包月订单id
     */
    @TableField("orderId")
    private Integer orderId;
    /**区分是试工还是非试工*/
    private Integer prepaidType;
    @TableField("customerId")
    private Integer customerId;
    private Integer nannyId;
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
    @TableField("createId")
    private Integer createId;
    @TableField("createDate")
    private Date createDate;
    @TableField("modifyId")
    private Integer modifyId;
    @TableField("modifyDate")
    private Date modifyDate;
    @TableField("isDel")
    @TableLogic
    private Integer isDel;

    public Integer getPrepaidType() {
        return prepaidType;
    }

    public void setPrepaidType(Integer prepaidType) {
        this.prepaidType = prepaidType;
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
