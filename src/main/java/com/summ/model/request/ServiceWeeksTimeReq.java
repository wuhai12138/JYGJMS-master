package com.summ.model.request;

import java.util.Date;
import java.util.List;

/**
 * Created by summ on 18/1/12.
 */
public class ServiceWeeksTimeReq {

    /**验证服务师工时用的时间值列表*/
    private List<Long> longList;
    /***验证日程所用的时间值列表*/
    private List<Long> scheduleLongList;
    private List<String> weekList;
    private String weekday="";
    private Integer nannyId;
    private String name;
    private Date startDate;
    private Date endDate;
    private Integer startId;
    private Integer endId;
    private Integer goodsId;
    private Integer shopId;
    private Integer scheduleId;

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getStartId() {
        return startId;
    }

    public void setStartId(Integer startId) {
        this.startId = startId;
    }

    public Integer getEndId() {
        return endId;
    }

    public void setEndId(Integer endId) {
        this.endId = endId;
    }

    public List<Long> getScheduleLongList() {
        return scheduleLongList;
    }

    public void setScheduleLongList(List<Long> scheduleLongList) {
        this.scheduleLongList = scheduleLongList;
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

    public List<String> getWeekList() {
        return weekList;
    }

    public void setWeekList(List<String> weekList) {
        this.weekList = weekList;
    }

    public Integer getNannyId() {
        return nannyId;
    }

    public void setNannyId(Integer nannyId) {
        this.nannyId = nannyId;
    }

    public List<Long> getLongList() {
        return longList;
    }

    public void setLongList(List<Long> longList) {
        this.longList = longList;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }
}
