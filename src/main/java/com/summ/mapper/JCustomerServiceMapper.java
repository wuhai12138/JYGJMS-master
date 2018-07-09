package com.summ.mapper;

import com.summ.model.JCustomerHouse;
import com.summ.model.JCustomerService;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.response.CustomerServiceRes;

import java.util.List;

/**
 *
 * JCustomerService 表数据库控制层接口
 *
 */
public interface JCustomerServiceMapper extends BaseMapper<JCustomerService> {

    CustomerServiceRes selectServiceById(int id);
    List<CustomerServiceRes> findServiceList(int customerId);
    List<JCustomerHouse> findServiceUnusedHouse(int customerId);
}