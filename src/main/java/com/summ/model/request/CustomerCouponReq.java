package com.summ.model.request;

/**
 * Created by summ on 17/12/12.
 */
public class CustomerCouponReq {
    private int customerId;
    private int couponStatus;
    private int page=0;
    private int size=50;

    @Override
    public String toString() {
        return "CustomerCouponReq{" +
                "customerId=" + customerId +
                ", couponStatus=" + couponStatus +
                ", page=" + page +
                ", size=" + size +
                '}';
    }

    public CustomerCouponReq() {
    }

    public CustomerCouponReq(int customerId, int couponStatus) {
        this.customerId = customerId;
        this.couponStatus = couponStatus;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(int couponStatus) {
        this.couponStatus = couponStatus;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
