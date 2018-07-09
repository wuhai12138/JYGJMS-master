package com.summ.controller.customer;

import com.summ.controller.basic.AutoMapperController;
import com.summ.model.JCouponList;
import com.summ.model.request.CouponReq;
import com.summ.model.request.CustomerCouponReq;
import com.summ.model.response.CustomerCouponListRes;
import com.summ.model.response.ModelRes;
import com.summ.utils.ResponseUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 客户优惠券
 * @author summ
 * @date 17/11/28
 */
@Controller
@RequestMapping("/customer/coupon")
public class CustomerCouponController extends AutoMapperController{

    /**
     * CRUD for customer coupon
     * @param
     * @return
     */

    @ResponseBody
    @RequestMapping("/list")
    public Object insert(@RequestBody CouponReq couponReq){
        try {
            couponReq.setPage(couponReq.getSize()*(couponReq.getPage()-1));
            return new ModelRes(ModelRes.Status.SUCCESS,"操作成功 !",ResponseUtil.List2Map(jCouponMapper.getUsefulCouponList(couponReq)));
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody JCouponList jCouponList){
        try {
            System.out.println(">>>>>>>jCouponList>>>>>>>>>"+jCouponList.getCouponStatus());

            return new ModelRes(ModelRes.Status.SUCCESS,"操作成功 !",jCouponListMapper.insert(jCouponList));
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(@RequestBody Map<String,Integer> map){
        try {
            return new ModelRes(ModelRes.Status.SUCCESS,"操作成功 !",jCouponListMapper.deleteCoupon(map.get("couponListId")));
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody JCouponList jCouponList){
        try {
            JCouponList jCouponList1 = jCouponListMapper.selectById(Long.valueOf(jCouponList.getCouponListId()));
            System.out.println("<<<<<CouponStatus<<<<<<<"+jCouponList1.getCouponStatus());
            jCouponList.setCouponStatus(jCouponList1.getCouponStatus());
            return new ModelRes(ModelRes.Status.SUCCESS,"操作成功 !",jCouponListMapper.updateById(jCouponList));
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody CustomerCouponReq customerCouponReq){
        try {
            customerCouponReq.setPage((customerCouponReq.getPage()-1)*customerCouponReq.getSize());
            List<CustomerCouponListRes> customerCouponListResList = jCouponListMapper.getListById(customerCouponReq);
            for (CustomerCouponListRes customerCouponListRes : customerCouponListResList){
                /**判断优惠券是否为未使用*/
                if (customerCouponListRes.getCouponStatus()==32){
                    /**判断优惠券是否过期*/
                    if (customerCouponListRes.getValidTime().before(new Date())){
                        customerCouponListRes.setCouponStatus(33);
                        customerCouponListRes.setCouponStatusInfo("已过期");
                    }
                }
            }
            return new ModelRes(ModelRes.Status.SUCCESS,"操作成功 !", ResponseUtil.List2Map(customerCouponListResList,jCouponListMapper.getCount(customerCouponReq)));
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

}
