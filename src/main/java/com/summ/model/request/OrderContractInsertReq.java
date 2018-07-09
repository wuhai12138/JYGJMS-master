package com.summ.model.request;

import java.util.Date;

public class OrderContractInsertReq {
    private Integer orderId;

    /**  */
    private Integer customerId;

    /**  */
    private Integer shopId;

    /**  */
    private Integer houseId;

    private Integer serviceId;

    /**  */
    private Integer orderStatus;

    /**  */
    private Date startDate;

    /**  */
    private Date endDate;

    /**  */
    private Integer goodsId;

    /**供应商Id */
    private Integer supplierId;

    /**  */
    private Integer teacherId;

    /**  */
    private Date createTime=new Date();

    /**  */
    private String remark;

    /**  */
    private Integer isDel=16;
}
