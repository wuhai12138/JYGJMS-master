package com.summ.controller.shop;


import com.summ.controller.basic.AutoMapperController;
import com.summ.model.JAdmin;
import com.summ.model.JCouponGoods;
import com.summ.model.JGoodsShop;
import com.summ.model.JShop;
import com.summ.model.request.ShopReq;
import com.summ.model.response.ModelRes;
import com.summ.utils.ResponseUtil;
import com.summ.utils.TencentMapUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 门店与产品关系表维护
 * @author summ
 */
@Controller
@RequestMapping("/shop/goods")
public class ShopGoodsController extends AutoMapperController {

    @ResponseBody
    @RequestMapping("/insert")
    public Object insertShop(@RequestBody Map map) {
        try {
            Integer shopId = (Integer) map.get("shopId");
            List<Integer> goodsIdList = (List<Integer>) map.get("goodsIdList");
            Map map1 = new HashMap();
            map1.put("shopId",shopId);
            jGoodsShopMapper.deleteByMap(map1);

            if (goodsIdList.size()>0){
                for (Integer id : goodsIdList){
                    JGoodsShop jGoodsShop = new JGoodsShop();
                    jGoodsShop.setShopId(shopId);
                    jGoodsShop.setGoodsId(id);
                    jGoodsShopMapper.insert(jGoodsShop);
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
    public Object find(@RequestBody JGoodsShop jGoodsShop) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", ResponseUtil.List2Map(jGoodsShopMapper.getGoodsByShop(jGoodsShop.getShopId())));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
}
