package com.summ.model;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
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
@TableName("j_app_banner_goods")
public class JAppBannerGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * app活动banner与产品中间表
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("bannerId")
    private Integer bannerId;
    @TableField("goodsId")
    private Integer goodsId;
    @TableField("isDel")
    private Integer isDel;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "JAppBannerGoods{" +
        "id=" + id +
        ", bannerId=" + bannerId +
        ", goodsId=" + goodsId +
        ", isDel=" + isDel +
        "}";
    }
}
