package com.summ.model.response;

/**
 * Created by summ on 17/12/25.
 */
public class TeacherShopRes {
    private Integer teacherId;
    private Integer teacherShopId;
    private String shopName;
    private Integer shopId;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getTeacherShopId() {
        return teacherShopId;
    }

    public void setTeacherShopId(Integer teacherShopId) {
        this.teacherShopId = teacherShopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
