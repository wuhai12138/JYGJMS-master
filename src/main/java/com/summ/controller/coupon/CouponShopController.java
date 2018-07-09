package com.summ.controller.coupon;

import com.summ.controller.basic.AutoMapperController;
import com.summ.model.JAdminShop;
import com.summ.model.JCoupon;
import com.summ.model.JCouponGoods;
import com.summ.model.JCouponShop;
import com.summ.model.response.ModelRes;
import com.summ.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 优惠券门店维护
 */
@Controller
@RequestMapping("/coupon/shop")
public class CouponShopController extends AutoMapperController{

    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody Map map) {
        try {
            Integer couponId = (Integer) map.get("couponId");
            List<Integer> shopIdList = (List<Integer>) map.get("shopIdList");
            List<JCouponShop> jCouponShopList = new ArrayList<>();

            Map map1 = new HashMap();
            map1.put("couponId",couponId);
            jCouponShopMapper.deleteByMap(map1);

            if (shopIdList.size()>0){
                for (Integer id : shopIdList){
                    JCouponShop jCouponShop = new JCouponShop();
                    jCouponShop.setCouponId(couponId);
                    jCouponShop.setShopId(id);
                    jCouponShopMapper.insert(jCouponShop);
                }
            }
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !",null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody Map map) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", ResponseUtil.List2Map(jCouponShopMapper.getCouponShop((Integer) map.get("couponId"))));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
}
