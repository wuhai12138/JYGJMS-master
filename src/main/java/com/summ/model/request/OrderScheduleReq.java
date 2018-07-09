package com.summ.model.request;

import java.util.Date;

/**
 * Created by summ on 18/1/15.
 */
public class OrderScheduleReq {
    private Integer orderId;
    private Integer orderType;
    private Date scheduleDateBefore;
    private Date scheduleDateAfter;
    private Integer scheduleStatus;
    private Integer payStatus;
    private Integer page;
    private Integer size;

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getScheduleDateBefore() {
        return scheduleDateBefore;
    }

    public void setScheduleDateBefore(Date scheduleDateBefore) {
        this.scheduleDateBefore = scheduleDateBefore;
    }

    public Date getScheduleDateAfter() {
        return scheduleDateAfter;
    }

    public void setScheduleDateAfter(Date scheduleDateAfter) {
        this.scheduleDateAfter = scheduleDateAfter;
    }

    public Integer getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(Integer scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
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
