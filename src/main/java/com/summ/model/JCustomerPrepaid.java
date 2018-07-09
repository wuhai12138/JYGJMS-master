package com.summ.model;

import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
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
 * @since 2018-06-11
 */
@TableName("j_customer_prepaid")
public class JCustomerPrepaid implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户预约表
     */
    @TableId(value = "prepaidId", type = IdType.AUTO)
    private Integer prepaidId;
    @TableField("customerId")
    private Integer customerId;
    @TableField("goodsId")
    private Integer goodsId;
    private String remark;
    @TableField("isDel")
    @TableLogic
    private Integer isDel;
    @TableField("createDate")
    private Date createDate;
    private Integer prepaidStatus;
    private Integer orderId;
    private Date modifyDate;
    private Integer modifyId;

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

    public Integer getPrepaidStatus() {
        return prepaidStatus;
    }

    public void setPrepaidStatus(Integer prepaidStatus) {
        this.prepaidStatus = prepaidStatus;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getPrepaidId() {
        return prepaidId;
    }

    public void setPrepaidId(Integer prepaidId) {
        this.prepaidId = prepaidId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "JCustomerPrepaid{" +
        "prepaidId=" + prepaidId +
        ", customerId=" + customerId +
        ", goodsId=" + goodsId +
        ", remark=" + remark +
        ", isDel=" + isDel +
        ", createDate=" + createDate +
        "}";
    }
}
