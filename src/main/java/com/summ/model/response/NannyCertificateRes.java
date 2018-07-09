package com.summ.model.response;

import java.util.Date;

/**
 * Created by summ on 17/12/14.
 */
public class NannyCertificateRes {

    /**
     * certificateId : 2
     * certificateIdInfo :
     */

    private int nannyCertId;
    private String certificateIdInfo;
    private String photo;
    private Integer certificateId;
    /** 证件编号 */
    private String certificateNumber;

    /** 证件有效期 */
    private Date validDate;

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public Integer getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Integer certificateId) {
        this.certificateId = certificateId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getNannyCertId() {
        return nannyCertId;
    }

    public void setNannyCertId(int nannyCertId) {
        this.nannyCertId = nannyCertId;
    }

    public String getCertificateIdInfo() {
        return certificateIdInfo;
    }

    public void setCertificateIdInfo(String certificateIdInfo) {
        this.certificateIdInfo = certificateIdInfo;
    }
}
