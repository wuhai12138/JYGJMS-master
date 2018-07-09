package com.summ.model.request;

import com.summ.utils.StringUtil;

import java.util.Date;
import java.util.List;

public class OrderRefundReq {
    /**退款原因*/
    private List<Integer> reason;
    /**订单类型*/
    private List<Integer> orderType;
    /**退款渠道*/
    private List<Integer> refundWay;
    /**审核状态*/
    private List<Integer> checkStatus;
    /**申请日期*/
    private Date startDate;
    private Date endDate;
    private Integer page;
    private Integer size;


    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Integer> getReason() {
        return reason;
    }

    public void setReason(List<Integer> reason) {
        this.reason = reason;
    }

    public List<Integer> getOrderType() {
        return orderType;
    }

    public void setOrderType(List<Integer> orderType) {
        this.orderType = orderType;
    }

    public List<Integer> getRefundWay() {
        return refundWay;
    }

    public void setRefundWay(List<Integer> refundWay) {
        this.refundWay = refundWay;
    }

    public List<Integer> getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(List<Integer> checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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
