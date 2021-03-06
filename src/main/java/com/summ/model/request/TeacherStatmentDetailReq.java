package com.summ.model.request;

import java.util.Date;

/**
 * Created by summ on 18/1/23.
 */
public class TeacherStatmentDetailReq {
    private Integer teacherId;
    private Date startDate;
    private Date endDate;
    private Integer shopId;
    private Integer statmentTeacherType;
    private Integer page;
    private Integer size;
    private Integer adminId;
    private Integer reason=0;

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getStatmentTeacherType() {
        return statmentTeacherType;
    }

    public void setStatmentTeacherType(Integer statmentTeacherType) {
        this.statmentTeacherType = statmentTeacherType;
    }

    public Integer getReason() {
        return reason;
    }

    public void setReason(Integer reason) {
        this.reason = reason;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
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
