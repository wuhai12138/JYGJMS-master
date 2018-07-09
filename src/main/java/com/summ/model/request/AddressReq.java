package com.summ.model.request;

/**
 * Created by summ on 17/11/30.
 */
public class AddressReq {
    private String areaCode;
    private String cityCode;
    private String provinceCode;

    public AddressReq() {
    }

    public AddressReq(String areaCode, String cityCode, String provinceCode) {
        this.areaCode = areaCode;
        this.cityCode = cityCode;
        this.provinceCode = provinceCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
}
