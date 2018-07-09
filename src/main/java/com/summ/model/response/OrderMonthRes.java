package com.summ.model.response;

import com.baomidou.mybatisplus.annotations.TableField;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by summ on 18/1/15.
 */
public class OrderMonthRes {
    private Integer orderId;
    @TableField("customerId")
    private Integer customerId;
    @TableField("shopId")
    private Integer shopId;
    @TableField("houseId")
    private Integer houseId;
    @TableField("serviceId")
    private Integer serviceId;
    /**
     * 默认预约中
     */
    @TableField("orderStatus")
    private Integer orderStatus;
    /**
     * 订单关闭状态（默认未关闭）
     */
    @TableField("orderCloseStatus")
    private Integer orderCloseStatus;
    @TableField("startDate")
    private Date startDate;
    @TableField("endDate")
    private Date endDate;
    /**
     * 客户每日单价
     */
    @TableField("customerUnitPrice")
    private BigDecimal customerUnitPrice;
    /**
     * 服务师每日工资
     */
    @TableField("nannyUnitPrice")
    private BigDecimal nannyUnitPrice;
    /**
     * 包年服务费支付状态
     */
    @TableField("payStatus")
    private Integer payStatus;
    @TableField("goodsId")
    private Integer goodsId;
    @TableField("supplierId")
    private Integer supplierId;
    @TableField("teacherId")
    private Integer teacherId;
    @TableField("createId")
    private Integer createId;
    @TableField("createTime")
    private Date createTime;
    @TableField("modifyId")
    private Integer modifyId;
    @TableField("modifyTime")
    private Date modifyTime;
    private String remark;
    @TableField("isDel")
    private Integer isDel;

    private String customerName;
    private String customerPhone;
    private BigDecimal customerBalance;
    private String houseAddress;
    private String orderStatusInfo;
    private String orderCloseStatusInfo;
    private String payStatusInfo;
    private String service;
    private String needString;
    private String serviceDetail;
    private String shopName;
    /**
     * 当月订单金额
     */
    @TableField("orderMoney")
    private BigDecimal orderMoney;
    /**
     * 服务师当月工资
     */
    @TableField("nannySalary")
    private BigDecimal nannySalary;


    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }

    public BigDecimal getNannySalary() {
        return nannySalary;
    }

    public void setNannySalary(BigDecimal nannySalary) {
        this.nannySalary = nannySalary;
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

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderCloseStatus() {
        return orderCloseStatus;
    }

    public void setOrderCloseStatus(Integer orderCloseStatus) {
        this.orderCloseStatus = orderCloseStatus;
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

    public BigDecimal getCustomerUnitPrice() {
        return customerUnitPrice;
    }

    public void setCustomerUnitPrice(BigDecimal customerUnitPrice) {
        this.customerUnitPrice = customerUnitPrice;
    }

    public BigDecimal getNannyUnitPrice() {
        return nannyUnitPrice;
    }

    public void setNannyUnitPrice(BigDecimal nannyUnitPrice) {
        this.nannyUnitPrice = nannyUnitPrice;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getModifyId() {
        return modifyId;
    }

    public void setModifyId(Integer modifyId) {
        this.modifyId = modifyId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
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

    public BigDecimal getCustomerBalance() {
        return customerBalance;
    }

    public void setCustomerBalance(BigDecimal customerBalance) {
        this.customerBalance = customerBalance;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getOrderStatusInfo() {
        return orderStatusInfo;
    }

    public void setOrderStatusInfo(String orderStatusInfo) {
        this.orderStatusInfo = orderStatusInfo;
    }

    public String getOrderCloseStatusInfo() {
        return orderCloseStatusInfo;
    }

    public void setOrderCloseStatusInfo(String orderCloseStatusInfo) {
        this.orderCloseStatusInfo = orderCloseStatusInfo;
    }

    public String getPayStatusInfo() {
        return payStatusInfo;
    }

    public void setPayStatusInfo(String payStatusInfo) {
        this.payStatusInfo = payStatusInfo;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getNeedString() {
        return needString;
    }

    public void setNeedString(String needString) {
        this.needString = needString;
    }

    public String getServiceDetail() {
        return serviceDetail;
    }

    public void setServiceDetail(String serviceDetail) {
        this.serviceDetail = serviceDetail;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
