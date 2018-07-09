package com.summ.controller.coupon;

import com.summ.controller.basic.AutoMapperController;
import com.summ.model.JCoupon;
import com.summ.model.request.CouponReq;
import com.summ.model.response.ModelRes;
import com.summ.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 优惠券信息维护
 */
@Controller
@RequestMapping("/coupon")
public class CouponController extends AutoMapperController{

    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody JCoupon jCoupon) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jCouponMapper.insert(jCoupon));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody CouponReq couponReq) {
        try {
            couponReq.setPage((couponReq.getPage()-1)*couponReq.getSize());
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", ResponseUtil.List2Map(jCouponMapper.getCouponList(couponReq)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody JCoupon jCoupon) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jCouponMapper.updateById(jCoupon));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(@RequestBody JCoupon jCoupon) {
        try {
            jCoupon.setIsDel(17);
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jCouponMapper.updateById(jCoupon));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }


}
