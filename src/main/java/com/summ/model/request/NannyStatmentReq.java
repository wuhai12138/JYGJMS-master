package com.summ.model.request;

import java.util.Date;

/**
 * Created by summ on 18/1/18.
 */
public class NannyStatmentReq {
    private Date startDate;
    private Date endDate;
    private Integer statmentNannyType;
    private Integer nannyId;
    private String nannyName;
    private String nannyPhone;
    private Integer shopId;
    private Integer page;
    private Integer size;
    private Integer adminId;

    public Integer getStatmentNannyType() {
        return statmentNannyType;
    }

    public void setStatmentNannyType(Integer statmentNannyType) {
        this.statmentNannyType = statmentNannyType;
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

    public Integer getNannyId() {
        return nannyId;
    }

    public void setNannyId(Integer nannyId) {
        this.nannyId = nannyId;
    }

    public String getNannyName() {
        return nannyName;
    }

    public void setNannyName(String nannyName) {
        this.nannyName = nannyName;
    }

    public String getNannyPhone() {
        return nannyPhone;
    }

    public void setNannyPhone(String nannyPhone) {
        this.nannyPhone = nannyPhone;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
}
