package com.summ.model;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author summ
 * @since 2018-05-29
 */
@TableName("j_nanny_emergency_contact")
public class JNannyEmergencyContact implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 紧急联系人id
     */
    @TableId(value = "emergencyContactId", type = IdType.AUTO)
    private Integer emergencyContactId;
    @TableField("nannyId")
    private Integer nannyId;
    private String name;
    private String phone;
    @TableField("createId")
    private Integer createId;
    @TableField("createDate")
    private Date createDate;
    @TableField("modifyId")
    private Integer modifyId;
    @TableField("modifyDate")
    private Date modifyDate;
    @TableField("isDel")
    private Integer isDel;
    private String relationship;


    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public Integer getEmergencyContactId() {
        return emergencyContactId;
    }

    public void setEmergencyContactId(Integer emergencyContactId) {
        this.emergencyContactId = emergencyContactId;
    }

    public Integer getNannyId() {
        return nannyId;
    }

    public void setNannyId(Integer nannyId) {
        this.nannyId = nannyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getModifyId() {
        return modifyId;
    }

    public void setModifyId(Integer modifyId) {
        this.modifyId = modifyId;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "JNannyEmergencyContact{" +
        "emergencyContactId=" + emergencyContactId +
        ", nannyId=" + nannyId +
        ", name=" + name +
        ", phone=" + phone +
        ", createId=" + createId +
        ", createDate=" + createDate +
        ", modifyId=" + modifyId +
        ", modifyDate=" + modifyDate +
        ", isDel=" + isDel +
        "}";
    }
}
