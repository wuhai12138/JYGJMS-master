package com.summ.model.request;

import java.util.Date;

public class NannyInsuranceReq {
    private Date insuranceDate;
    private Date lastInsuranceDate;
    private Integer shopId;
    private Integer adminId;
    private Integer page;
    private Integer size;

    public Date getLastInsuranceDate() {
        return lastInsuranceDate;
    }

    public void setLastInsuranceDate(Date lastInsuranceDate) {
        this.lastInsuranceDate = lastInsuranceDate;
    }

    public Date getInsuranceDate() {
        return insuranceDate;
    }

    public void setInsuranceDate(Date insuranceDate) {
        this.insuranceDate = insuranceDate;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
