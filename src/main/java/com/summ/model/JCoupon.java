package com.summ.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@TableName("j_coupon")
public class JCoupon implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Integer couponId;

	/**  */
	private String couponName;

	/**  */
	private String couponDetail;

	/**  */
	private BigDecimal couponPrice;
	private BigDecimal couponCost;

	private BigDecimal orderMiniPrice;

	/**  */
	private Date validTime;

	/**  */
	private Integer couponOrigin;

	/**  */
	@TableLogic
	private Integer isDel;

	/**  */
	private Date createTime;

	public BigDecimal getCouponCost() {
		return couponCost;
	}

	public void setCouponCost(BigDecimal couponCost) {
		this.couponCost = couponCost;
	}

	public BigDecimal getOrderMiniPrice() {
		return orderMiniPrice;
	}

	public void setOrderMiniPrice(BigDecimal orderMiniPrice) {
		this.orderMiniPrice = orderMiniPrice;
	}

	public Integer getCouponId() {
		return this.couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public String getCouponName() {
		return this.couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCouponDetail() {
		return this.couponDetail;
	}

	public void setCouponDetail(String couponDetail) {
		this.couponDetail = couponDetail;
	}

	public BigDecimal getCouponPrice() {
		return couponPrice;
	}

	public void setCouponPrice(BigDecimal couponPrice) {
		this.couponPrice = couponPrice;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getValidTime() {
		return this.validTime;
	}

	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}

	public Integer getCouponOrigin() {
		return couponOrigin;
	}

	public void setCouponOrigin(Integer couponOrigin) {
		this.couponOrigin = couponOrigin;
	}
}
