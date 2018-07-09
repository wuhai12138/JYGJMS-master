package com.summ.model.response;

import java.util.Date;

/**
 * Created by summ on 18/1/5.
 */
public class serviceListRes {
    private Integer houseId;

    /**  */
    private Integer customerId;

    /**  */
    private Integer provinceId = 1;

    /**  */
    private Integer cityId = 1;

    /**  */
    private Integer areaId;

    private Integer streetId;

    /** 房产地址 */
    private String houseAddress;

    /**  */
    private Integer bedRoom;

    /**  */
    private Integer livingRoom;

    /**  */
    private Integer restRoom;

    /** 面积 */
    private String houseArea;

    /**  */
    private Date createTime;

    /**  */
    private Integer isDel = 16;

    /**  */
    private Integer adminConfirm;
    private String adminConfirmInfo;

    /**  */
    private Integer customerConfirm;
    private String customerConfirmInfo;

    private Integer serviceId;

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getStreetId() {
        return streetId;
    }

    public void setStreetId(Integer streetId) {
        this.streetId = streetId;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public Integer getBedRoom() {
        return bedRoom;
    }

    public void setBedRoom(Integer bedRoom) {
        this.bedRoom = bedRoom;
    }

    public Integer getLivingRoom() {
        return livingRoom;
    }

    public void setLivingRoom(Integer livingRoom) {
        this.livingRoom = livingRoom;
    }

    public Integer getRestRoom() {
        return restRoom;
    }

    public void setRestRoom(Integer restRoom) {
        this.restRoom = restRoom;
    }

    public String getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(String houseArea) {
        this.houseArea = houseArea;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
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

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
}
