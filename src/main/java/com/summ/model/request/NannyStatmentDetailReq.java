package com.summ.model.request;

import java.util.Date;

/**
 * Created by summ on 18/1/23.
 */
public class NannyStatmentDetailReq {
    private Integer nannyId;
    private Date startDate;
    private Date endDate;
    private Integer shopId;
    private Integer statmentNannyType;
    private Integer page;
    private Integer size;
    private Integer adminId;
    private Integer reason;

    public Integer getReason() {
        return reason;
    }

    public void setReason(Integer reason) {
        this.reason = reason;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getStatmentNannyType() {
        return statmentNannyType;
    }

    public void setStatmentNannyType(Integer statmentNannyType) {
        this.statmentNannyType = statmentNannyType;
    }

    public Integer getNannyId() {
        return nannyId;
    }

    public void setNannyId(Integer nannyId) {
        this.nannyId = nannyId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
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
