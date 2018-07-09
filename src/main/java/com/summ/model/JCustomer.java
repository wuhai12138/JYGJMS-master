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
@TableName("j_customer")
public class JCustomer implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Integer customerId;

	/**  */
	private String customerName;

	/**  */
	private String customerPhone;

	/**  */
//	private Integer shopId;

	/** 用户类型11注册12会员13僵尸用户 */
	private Integer customerType;

	/** 1男2女 */
	private Integer customerSex;

	/**  */
	private BigDecimal customerBalance;

	/** 14警告15不警告 */
	private Integer warnType;

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

	/**  */
	private String remark;

	/** 是否删除（1为已删除） */
	@TableLogic
	private Integer isDel;

	/**积分*/
	private Integer customerCredit;
	@Override
	public String toString() {
		return "JCustomer{" +
				"customerId=" + customerId +
				", customerName='" + customerName + '\'' +
				", customerPhone='" + customerPhone + '\'' +
				", customerType=" + customerType +
				", customerSex=" + customerSex +
				", customerBalance=" + customerBalance +
				", warnType=" + warnType +
				", remark='" + remark + '\'' +
				", createTime=" + createTime +
				", isDel=" + isDel +
				", memberOrigin='" + memberOrigin + '\'' +
				", memberLevel=" + memberLevel +
				", memberType=" + memberType +
				'}';
	}

	public JCustomer() {
	}

	public JCustomer(Integer customerId, Integer shopId, String customerName, String customerPhone, Integer customerType, Integer customerSex, BigDecimal customerBalance, Integer warnType, String remark, Date createTime, Integer isDel, Integer memberOrigin, Integer memberLevel, Integer memberType) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.customerType = customerType;
		this.customerSex = customerSex;
		this.customerBalance = customerBalance;
		this.warnType = warnType;
		this.remark = remark;
		this.createTime = createTime;
		this.isDel = isDel;
		this.memberOrigin = memberOrigin;
		this.memberLevel = memberLevel;
		this.memberType = memberType;

	}

	public JCustomer(Integer customerId, BigDecimal customerBalance) {
		this.customerId = customerId;
		this.customerBalance = customerBalance;
	}

	public Integer getCustomerCredit() {
		return customerCredit;
	}

	public void setCustomerCredit(Integer customerCredit) {
		this.customerCredit = customerCredit;
	}

	public Integer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return this.customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

//	public Integer getShopId() {
//		return this.shopId;
//	}
//
//	public void setShopId(Integer shopId) {
//		this.shopId = shopId;
//	}

	public Integer getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}

	public Integer getCustomerSex() {
		return this.customerSex;
	}

	public void setCustomerSex(Integer customerSex) {
		this.customerSex = customerSex;
	}

	public BigDecimal getCustomerBalance() {
		return this.customerBalance;
	}

	public void setCustomerBalance(BigDecimal customerBalance) {
		this.customerBalance = customerBalance;
	}

	public Integer getWarnType() {
		return this.warnType;
	}

	public void setWarnType(Integer warnType) {
		this.warnType = warnType;
	}

	public Integer getMemberOrigin() {
		return memberOrigin;
	}

	public void setMemberOrigin(Integer memberOrigin) {
		this.memberOrigin = memberOrigin;
	}

	public Integer getMemberLevel() {
		return this.memberLevel;
	}

	public void setMemberLevel(Integer memberLevel) {
		this.memberLevel = memberLevel;
	}

	public Integer getMemberType() {
		return this.memberType;
	}

	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}

	public String getWeiXinOpenId() {
		return this.WeiXinOpenId;
	}

	public void setWeiXinOpenId(String WeiXinOpenId) {
		this.WeiXinOpenId = WeiXinOpenId;
	}

	public String getAlyOpenId() {
		return this.AlyOpenId;
	}

	public void setAlyOpenId(String AlyOpenId) {
		this.AlyOpenId = AlyOpenId;
	}

	public Integer getModifyId() {
		return this.modifyId;
	}

	public void setModifyId(Integer modifyId) {
		this.modifyId = modifyId;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getCreateId() {
		return this.createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

}
