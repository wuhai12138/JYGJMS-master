package com.summ.model.request;

import java.util.Date;

public class OrderMonthPrepaidReq {
    private Integer orderId;
    private Integer customerId;
    private String customerName;
    private String customerPhone;
    private Integer nannyId;
    private String nannyName;
    private Date startPrepaidDate;
    private Date endPrepaidDate;
    private Integer payStatus;
    private Integer page;
    private Integer size;

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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
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

    public Date getStartPrepaidDate() {
        return startPrepaidDate;
    }

    public void setStartPrepaidDate(Date startPrepaidDate) {
        this.startPrepaidDate = startPrepaidDate;
    }

    public Date getEndPrepaidDate() {
        return endPrepaidDate;
    }

    public void setEndPrepaidDate(Date endPrepaidDate) {
        this.endPrepaidDate = endPrepaidDate;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }
}
