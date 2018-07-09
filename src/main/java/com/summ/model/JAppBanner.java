package com.summ.model;

import com.baomidou.mybatisplus.annotations.TableLogic;
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
 * @since 2018-06-22
 */
@TableName("j_app_banner")
public class JAppBanner implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户小程序首页轮播图
     */
    @TableId(value = "bannerId", type = IdType.AUTO)
    private Integer bannerId;
    /**
     * banner图
     */
    private String photo;
    /**
     * banner图活动页链接
     */
    private String url;
    /**
     * 活动说明
     */
    private String description;
    /**
     * 活动规则
     */
    private String rules;
    @TableField("createDate")
    private Date createDate;
    @TableField("startDate")
    private Date startDate;
    @TableField("endDate")
    private Date endDate;
    private String remark;
    @TableField("isDel")
    @TableLogic
    private Integer isDel;
    private Integer createId;


    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "JAppBanner{" +
        "bannerId=" + bannerId +
        ", photo=" + photo +
        ", url=" + url +
        ", description=" + description +
        ", rules=" + rules +
        ", createDate=" + createDate +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        ", remark=" + remark +
        ", isDel=" + isDel +
        "}";
    }
}
