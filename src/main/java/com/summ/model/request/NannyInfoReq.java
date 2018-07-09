package com.summ.model.request;


/**
 * Created by summ on 17/12/13.
 */
public class NannyInfoReq {
    private Integer adminId;
    private Integer nannyId;
    private String nannyName;
    private String nannyPhone;
    private Integer nannyStatus;
    private Integer shopId;
    private Integer nannyLevel;
    private Integer hot;
    private int page;
    private int size;

    @Override
    public String toString() {
        return "NannyInfoReq{" +
                "nannyId=" + nannyId +
                ", nannyName='" + nannyName + '\'' +
                ", nannyPhone='" + nannyPhone + '\'' +
                ", nannyStatus=" + nannyStatus +
                ", shopId=" + shopId +
                ", nannyLevel=" + nannyLevel +
                ", page=" + page +
                ", size=" + size +
                '}';
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
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

    public Integer getNannyStatus() {
        return nannyStatus;
    }

    public void setNannyStatus(Integer nannyStatus) {
        this.nannyStatus = nannyStatus;
    }

    public Integer getNannyLevel() {
        return nannyLevel;
    }

    public void setNannyLevel(Integer nannyLevel) {
        this.nannyLevel = nannyLevel;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
