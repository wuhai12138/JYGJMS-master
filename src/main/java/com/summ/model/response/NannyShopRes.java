package com.summ.model.response;

/**
 * Created by summ on 17/12/25.
 */
public class NannyShopRes {
    private Integer nannyId;
    private Integer nannyShopId;
    private String shopName;
    private Integer shopId;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getNannyId() {
        return nannyId;
    }

    public void setNannyId(Integer nannyId) {
        this.nannyId = nannyId;
    }

    public Integer getNannyShopId() {
        return nannyShopId;
    }

    public void setNannyShopId(Integer nannyShopId) {
        this.nannyShopId = nannyShopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
