package com.summ.mapper;

import com.summ.model.JCustomerInvoice;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.omg.PortableInterceptor.INACTIVE;

import java.math.BigDecimal;

/**
 *
 * JCustomerInvoice 表数据库控制层接口
 *
 */
public interface JCustomerInvoiceMapper extends BaseMapper<JCustomerInvoice> {
    /**
     * 获取客户可开票最大金额（订单支付总金额-已开票金额）
     * @param customerId
     * @return
     */
    BigDecimal getCustomerMaxInvoiceMoney(Integer customerId);

    /**
     * 如没有开票记录则直接查询消费总金额
     * @param customerId
     * @return
     */
    BigDecimal getCustomerPayMoney(Integer customerId);


}