package com.summ.controller.coupon;

import com.summ.controller.basic.AutoMapperController;
import com.summ.model.JAdminType;
import com.summ.model.JCoupon;
import com.summ.model.JCouponGoods;
import com.summ.model.request.CouponReq;
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
 * 优惠券产品关系维护
 */
@Controller
@RequestMapping("/coupon/goods")
public class CouponGoodsController extends AutoMapperController{

    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody Map map) {
        try {
            Integer couponId = (Integer) map.get("couponId");
            List<Integer> goodsIdList = (List<Integer>) map.get("goodsIdList");
            List<JCouponGoods> jCouponGoodsList = new ArrayList<JCouponGoods>();
            Map map1 = new HashMap();
            map1.put("couponId",couponId);

            jCouponGoodsMapper.deleteByMap(map1);
            if (goodsIdList.size()>0){
                for (Integer id : goodsIdList){
                    JCouponGoods jCouponGoods = new JCouponGoods();
                    jCouponGoods.setCouponId(couponId);
                    jCouponGoods.setGoodsId(id);
                    jCouponGoodsList.add(jCouponGoods);
                    jCouponGoodsMapper.insert(jCouponGoods);
                }
            }
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody JCouponGoods jCouponGoods) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", ResponseUtil.List2Map(jCouponGoodsMapper.getCouponGoods(jCouponGoods.getCouponId())));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody JCouponGoods jCouponGoods) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jCouponGoodsMapper.updateById(jCouponGoods));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(@RequestBody JCouponGoods jCouponGoods) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jCouponGoodsMapper.deleteById(jCouponGoods));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }


}
