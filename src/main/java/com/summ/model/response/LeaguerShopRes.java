package com.summ.model.response;

/**
 * Created by summ on 17/12/25.
 */
public class LeaguerShopRes {
    private Integer leaguerId;
    private Integer id;
    private String shopName;
    private Integer shopId;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getLeaguerId() {
        return leaguerId;
    }

    public void setLeaguerId(Integer leaguerId) {
        this.leaguerId = leaguerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
