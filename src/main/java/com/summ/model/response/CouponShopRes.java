package com.summ.model.response;

import java.io.Serializable;

/**
 *
 * 
 *
 */
public class CouponShopRes implements Serializable {


	/**  */
	private Integer couponShopId;

	/**  */
	private Integer couponId;

	/**  */
	private Integer shopId;

	private String shopName;

	public Integer getCouponShopId() {
		return couponShopId;
	}

	public void setCouponShopId(Integer couponShopId) {
		this.couponShopId = couponShopId;
	}

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
}
