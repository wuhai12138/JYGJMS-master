package com.summ.model.response;

/**
 * Created by summ on 18/1/29.
 */
public class AdminTypeRes {
    private Integer id;
    private Integer adminId;
    private Integer adminTypeId;
    private String adminTypeIdInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getAdminTypeId() {
        return adminTypeId;
    }

    public void setAdminTypeId(Integer adminTypeId) {
        this.adminTypeId = adminTypeId;
    }

    public String getAdminTypeIdInfo() {
        return adminTypeIdInfo;
    }

    public void setAdminTypeIdInfo(String adminTypeIdInfo) {
        this.adminTypeIdInfo = adminTypeIdInfo;
    }
}
