package com.summ.model.request;

import com.summ.utils.DateUtil;

import java.util.Date;

public class CommentReq {
    private Integer staring;
    private Date startDate;
    private Date endDate;
    private Integer page;
    private Integer size;

    public Integer getStaring() {
        return staring;
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

    public void setStaring(Integer staring) {
        this.staring = staring;
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
}
