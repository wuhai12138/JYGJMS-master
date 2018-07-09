package com.summ.model.response;

import java.util.Date;

/**
 * Created by summ on 18/1/5.
 */
public class CustomerServiceRes {
    private Integer serviceId;

    /**  */
    private Integer customerId;

    /**  */
    private Integer productId;

    /**  */
    private Integer houseId;

    /**  */
    private Integer teacherId;

    /**  */
    private Integer adminId;

    /**  */
    private Integer shopId;

    /**  */
    private Integer adminConfirm;
    private String adminConfirmInfo;

    /**  */
    private Integer customerConfirm;
    private String customerConfirmInfo;

    private String serviceDetail;

    private Date createTime;

    private String serviceName;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getAdminConfirm() {
        return adminConfirm;
    }

    public void setAdminConfirm(Integer adminConfirm) {
        this.adminConfirm = adminConfirm;
    }

    public String getAdminConfirmInfo() {
        return adminConfirmInfo;
    }

    public void setAdminConfirmInfo(String adminConfirmInfo) {
        this.adminConfirmInfo = adminConfirmInfo;
    }

    public Integer getCustomerConfirm() {
        return customerConfirm;
    }

    public void setCustomerConfirm(Integer customerConfirm) {
        this.customerConfirm = customerConfirm;
    }

    public String getCustomerConfirmInfo() {
        return customerConfirmInfo;
    }

    public void setCustomerConfirmInfo(String customerConfirmInfo) {
        this.customerConfirmInfo = customerConfirmInfo;
    }

    public String getServiceDetail() {
        return serviceDetail;
    }

    public void setServiceDetail(String serviceDetail) {
        this.serviceDetail = serviceDetail;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
