package com.summ.model.response;

import java.io.Serializable;

/**
 *
 * 
 *
 */
public class BannerGoodsRes implements Serializable {


	/**  */
	private Integer id;

	/**  */
	private Integer bannerId;

	/**  */
	private Integer goodsId;

	private String service;

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBannerId() {
		return bannerId;
	}

	public void setBannerId(Integer bannerId) {
		this.bannerId = bannerId;
	}

	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

}
