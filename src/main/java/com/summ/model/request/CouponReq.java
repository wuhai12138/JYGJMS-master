package com.summ.model.request;

import java.util.Date;

public class CouponReq {
    private String couponName;
    private Integer couponOrigin;
    private Integer page=0;
    private Integer size=50;

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Integer getCouponOrigin() {
        return couponOrigin;
    }

    public void setCouponOrigin(Integer couponOrigin) {
        this.couponOrigin = couponOrigin;
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
