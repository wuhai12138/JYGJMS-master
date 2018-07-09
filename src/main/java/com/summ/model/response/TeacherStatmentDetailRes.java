package com.summ.model.response;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by summ on 18/1/23.
 */
public class TeacherStatmentDetailRes {
    private Integer statmentId;

    private String statmentNanny;

    /**  */
    private Integer teacherId;
    private String teacherName;
    private String teacherPhone;

    /**  */
    private Integer scheduleId;

    /**  */
    private Integer orderId;

    /**  */
    private Integer shopId;
    private String shopName;

    /**  */
    private Integer houseId;
    private String houseAddress;

    /**  */
    private Integer customerId;
    private String customerName;
    private String customerPhone;

    /** 服务师对账单类型 */
    private Integer statmentTeacherType;
    private String statmentTeacherTypeInfo;

    /** 服务师当前实时星级84、85、86 */
    private Integer teacherCurrentJobLevel;
    /** 服务师当前小时薪资 */
    private Integer teacherCurrentUnitPrice;
    /** 标记订单是否为自定义价格（即手动修改的价格,1表示手动修改） */
    private Integer orderPriceType;

    /** 金额 */
    private BigDecimal statmentMoney;

    /** 订单类型 */
    private Integer orderType;


    /** 产品Id */
    private Integer goodsId;
    private String service;

    /** 服务时间（几点到几点） */
    private String serviceTime;

    /** 服务时长 */
    private Double serviceTimeLength;

    /** 服务日期 */
    private Date serviceDate;

    private Date createDate = new Date();

    /**  */
    private String remark;

    private Integer reason;
    private String reasonInfo;

    private Integer adminId;
    private String adminName;


    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public Integer getStatmentTeacherType() {
        return statmentTeacherType;
    }

    public void setStatmentTeacherType(Integer statmentTeacherType) {
        this.statmentTeacherType = statmentTeacherType;
    }

    public String getStatmentTeacherTypeInfo() {
        return statmentTeacherTypeInfo;
    }

    public void setStatmentTeacherTypeInfo(String statmentTeacherTypeInfo) {
        this.statmentTeacherTypeInfo = statmentTeacherTypeInfo;
    }

    public Integer getTeacherCurrentJobLevel() {
        return teacherCurrentJobLevel;
    }

    public void setTeacherCurrentJobLevel(Integer teacherCurrentJobLevel) {
        this.teacherCurrentJobLevel = teacherCurrentJobLevel;
    }

    public Integer getTeacherCurrentUnitPrice() {
        return teacherCurrentUnitPrice;
    }

    public void setTeacherCurrentUnitPrice(Integer teacherCurrentUnitPrice) {
        this.teacherCurrentUnitPrice = teacherCurrentUnitPrice;
    }

    public Integer getOrderPriceType() {
        return orderPriceType;
    }

    public void setOrderPriceType(Integer orderPriceType) {
        this.orderPriceType = orderPriceType;
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

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Integer getReason() {
        return reason;
    }

    public void setReason(Integer reason) {
        this.reason = reason;
    }

    public String getReasonInfo() {
        return reasonInfo;
    }

    public void setReasonInfo(String reasonInfo) {
        this.reasonInfo = reasonInfo;
    }


    public Integer getStatmentId() {
        return statmentId;
    }

    public void setStatmentId(Integer statmentId) {
        this.statmentId = statmentId;
    }

    public String getStatmentNanny() {
        return statmentNanny;
    }

    public void setStatmentNanny(String statmentNanny) {
        this.statmentNanny = statmentNanny;
    }


    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
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

    public BigDecimal getStatmentMoney() {
        return statmentMoney;
    }

    public void setStatmentMoney(BigDecimal statmentMoney) {
        this.statmentMoney = statmentMoney;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public Double getServiceTimeLength() {
        return serviceTimeLength;
    }

    public void setServiceTimeLength(Double serviceTimeLength) {
        this.serviceTimeLength = serviceTimeLength;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
