package com.summ.model.response;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 *
 * 
 *
 */
public class CouponGoodsRes implements Serializable {


	/**  */
	private Integer couponGoodsId;

	/**  */
	private Integer couponId;

	/**  */
	private Integer goodsId;

	private String service;

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public Integer getCouponGoodsId() {
		return this.couponGoodsId;
	}

	public void setCouponGoodsId(Integer couponGoodsId) {
		this.couponGoodsId = couponGoodsId;
	}

	public Integer getCouponId() {
		return this.couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

}
