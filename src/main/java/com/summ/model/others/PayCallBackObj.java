package com.summ.model.others;

public class PayCallBackObj {
    private Integer customerId=0;
    private String customerName="";
    private String customerPhone="";
    private Integer adminId=0;
    private String shopName="";
    private Integer shopId=0;

    public PayCallBackObj() {
    }

    public PayCallBackObj(Integer customerId, String customerName, String customerPhone, Integer adminId, String shopName, Integer shopId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.adminId = adminId;
        this.shopName = shopName;
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return customerId +
                "-" + customerName +
                "-" + customerPhone +
                "-" + adminId +
                "-" + shopName +
                "-" + shopId +
                '-';
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
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

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
