package com.summ.model.response;

import java.util.Date;

/**
 * Created by summ on 17/11/28.
 */
public class CustomerHouseRes {
    private Integer houseId;

    /**  */
    private Integer customerId;

    private Integer shopId;
    private String shopName;
    /**  */
    private Integer provinceId = 1;
    private String provinceIdInfo;

    /**  */
    private Integer cityId = 1;
    private String cityIdInfo;

    /**  */
    private Integer areaId;
    private String areaIdInfo;

    private Integer streetId;
    private String streetIdInfo;

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

    private Integer propertyId;
    private String propertyName;

    /**
     * 房产类型
     */
    private Integer houseType;

    /**  */
    private Integer createId;

    /**  */
    private Date createTime = new Date();

    /**  */
    private Integer isDel = 16;
    private String isDelInfo;

    /**服务计划书信息*/
    private Integer serviceId;
    private String ServiceDetail;

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getHouseType() {
        return houseType;
    }

    public void setHouseType(Integer houseType) {
        this.houseType = houseType;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceDetail() {
        return ServiceDetail;
    }

    public void setServiceDetail(String serviceDetail) {
        ServiceDetail = serviceDetail;
    }

    public Integer getStreetId() {
        return streetId;
    }

    public void setStreetId(Integer streetId) {
        this.streetId = streetId;
    }

    public String getStreetIdInfo() {
        return streetIdInfo;
    }

    public void setStreetIdInfo(String streetIdInfo) {
        this.streetIdInfo = streetIdInfo;
    }

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

    public String getProvinceIdInfo() {
        return provinceIdInfo;
    }

    public void setProvinceIdInfo(String provinceIdInfo) {
        this.provinceIdInfo = provinceIdInfo;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityIdInfo() {
        return cityIdInfo;
    }

    public void setCityIdInfo(String cityIdInfo) {
        this.cityIdInfo = cityIdInfo;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaIdInfo() {
        return areaIdInfo;
    }

    public void setAreaIdInfo(String areaIdInfo) {
        this.areaIdInfo = areaIdInfo;
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

    public String getIsDelInfo() {
        return isDelInfo;
    }

    public void setIsDelInfo(String isDelInfo) {
        this.isDelInfo = isDelInfo;
    }
}
