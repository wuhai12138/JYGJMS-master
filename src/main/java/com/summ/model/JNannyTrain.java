package com.summ.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 *
 *
 */
@TableName("j_nanny_train")
public class JNannyTrain implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**  */
    @TableId(type = IdType.AUTO)
    private Integer nannyTrainId;

    /**  */
    private Integer nannyId;

    /**  */
    private Integer trainId;

    /**  */
    private Integer isDel = 16;


    public Integer getNannyTrainId() {
        return this.nannyTrainId;
    }

    public void setNannyTrainId(Integer nannyTrainId) {
        this.nannyTrainId = nannyTrainId;
    }

    public Integer getNannyId() {
        return this.nannyId;
    }

    public void setNannyId(Integer nannyId) {
        this.nannyId = nannyId;
    }

    public Integer getTrainId() {
        return this.trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public Integer getIsDel() {
        return this.isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

}
