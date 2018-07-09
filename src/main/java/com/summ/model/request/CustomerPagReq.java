package com.summ.model.request;

/**
 * Created by summ on 17/11/20.
 */
public class CustomerPagReq {
    private Integer adminId;
    private Integer customerId;
    private Integer shopId;
    private String customerPhone;
    private String customerName;
    private Integer customerType;
    private Integer page;
    private Integer size;

    @Override
    public String toString() {
        return "CustomerPagReq{" +
                "adminId=" + adminId +
                ", customerId=" + customerId +
                ", shopId=" + shopId +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerType=" + customerType +
                ", page=" + page +
                ", size=" + size +
                '}';
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
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
