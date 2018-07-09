package com.summ.model.request;

import java.util.Date;
import java.util.List;

/**
 * Created by summ on 17/11/29.
 */
public class ReportCustomerRechargeDetailReq {
    private Integer customerId;
    private String customerName;
    private String customerPhone;
    private String serialNumber;
    private Date startDate;
    private Date endDate;
    private Integer shopId;
    private List<Integer> chargeWay;
    private List<Integer> terminal;
    private Integer page;
    private Integer size;
    private Integer adminId;

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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<Integer> getChargeWay() {
        return chargeWay;
    }

    public void setChargeWay(List<Integer> chargeWay) {
        this.chargeWay = chargeWay;
    }

    public List<Integer> getTerminal() {
        return terminal;
    }

    public void setTerminal(List<Integer> terminal) {
        this.terminal = terminal;
    }


    public void setPage(Integer page) {
        this.page = page;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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
