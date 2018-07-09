package com.summ.model.response;

import java.util.Date;

/**
 * Created by summ on 17/12/13.
 */
public class NannyListRes {
    private Integer nannyId;
    private String nannyName;
    private String nannyIdCard;
    private String nannyPhone;
    private Date createTime;
    private Integer nannyStatus;
    private String nannyStatusInfo;
    private Integer nannyAge;
    private Integer hot;
    private String hotInfo;
    private String remark;
    private String unitPrice;

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getNannyIdCard() {
        return nannyIdCard;
    }

    public void setNannyIdCard(String nannyIdCard) {
        this.nannyIdCard = nannyIdCard;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public String getHotInfo() {
        return hotInfo;
    }

    public void setHotInfo(String hotInfo) {
        this.hotInfo = hotInfo;
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

    public String getNannyPhone() {
        return nannyPhone;
    }

    public void setNannyPhone(String nannyPhone) {
        this.nannyPhone = nannyPhone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getNannyStatus() {
        return nannyStatus;
    }

    public void setNannyStatus(Integer nannyStatus) {
        this.nannyStatus = nannyStatus;
    }

    public String getNannyStatusInfo() {
        return nannyStatusInfo;
    }

    public void setNannyStatusInfo(String nannyStatusInfo) {
        this.nannyStatusInfo = nannyStatusInfo;
    }

    public Integer getNannyAge() {
        return nannyAge;
    }

    public void setNannyAge(Integer nannyAge) {
        this.nannyAge = nannyAge;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
