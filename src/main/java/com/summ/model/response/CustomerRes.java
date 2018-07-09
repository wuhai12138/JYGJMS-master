package com.summ.model.response;

import com.summ.model.JCustomer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by summ on 17/11/28.
 */
public class CustomerRes{
    private Integer customerId;

    /**  */
    private String customerName;

    /**  */
    private String customerPhone;

    /** 用户类型1注册2会员3僵尸用户 */
    private Integer customerType ;
    private String customerTypeInfo;

    /** 1男2女 */
    private Integer customerSex;
    private String customerSexInfo;

    /**  */
    private BigDecimal customerBalance;

    /** 1警告2不警告 */
    private Integer warnType;
    private String warnTypeInfo ;

    /**  */
    private String remark;

    /**  */

    /** 是否删除（1为已删除） */
    private Integer isDel;
    private String isDelInfo;

    /** 1门店2电话咨询3ios4android */
    private Integer memberOrigin;

    /**  */
    private Integer memberLevel;

    /**  */
    private Integer memberType;

    /** 微信小程序openId */
    private String WeiXinOpenId;

    /** 支付宝小程序openId */
    private String AlyOpenId;

    /**  */
    private Integer modifyId;

    /**  */
    private Date modifyTime;

    /**  */
    private Integer createId;

    /**  */
    private Date createTime;

    public String getWeiXinOpenId() {
        return WeiXinOpenId;
    }

    public void setWeiXinOpenId(String weiXinOpenId) {
        WeiXinOpenId = weiXinOpenId;
    }

    public String getAlyOpenId() {
        return AlyOpenId;
    }

    public void setAlyOpenId(String alyOpenId) {
        AlyOpenId = alyOpenId;
    }

    public Integer getModifyId() {
        return modifyId;
    }

    public void setModifyId(Integer modifyId) {
        this.modifyId = modifyId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public String getCustomerTypeInfo() {
        return customerTypeInfo;
    }

    public void setCustomerTypeInfo(String customerTypeInfo) {
        this.customerTypeInfo = customerTypeInfo;
    }

    public Integer getCustomerSex() {
        return customerSex;
    }

    public void setCustomerSex(Integer customerSex) {
        this.customerSex = customerSex;
    }

    public String getCustomerSexInfo() {
        return customerSexInfo;
    }

    public void setCustomerSexInfo(String customerSexInfo) {
        this.customerSexInfo = customerSexInfo;
    }

    public BigDecimal getCustomerBalance() {
        return customerBalance;
    }

    public void setCustomerBalance(BigDecimal customerBalance) {
        this.customerBalance = customerBalance;
    }

    public Integer getWarnType() {
        return warnType;
    }

    public void setWarnType(Integer warnType) {
        this.warnType = warnType;
    }

    public String getWarnTypeInfo() {
        return warnTypeInfo;
    }

    public void setWarnTypeInfo(String warnTypeInfo) {
        this.warnTypeInfo = warnTypeInfo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getIsDelInfo() {
        return isDelInfo;
    }

    public void setIsDelInfo(String isDelInfo) {
        this.isDelInfo = isDelInfo;
    }

    public Integer getMemberOrigin() {
        return memberOrigin;
    }

    public void setMemberOrigin(Integer memberOrigin) {
        this.memberOrigin = memberOrigin;
    }

    public Integer getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(Integer memberLevel) {
        this.memberLevel = memberLevel;
    }

    public Integer getMemberType() {
        return memberType;
    }

    public void setMemberType(Integer memberType) {
        this.memberType = memberType;
    }
}
