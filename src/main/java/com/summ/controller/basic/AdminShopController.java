package com.summ.controller.basic;

import com.summ.model.JAdmin;
import com.summ.model.JAdminShop;
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
 * 管理员门店维护
 */
@Controller
@RequestMapping("/admin/shop")
public class AdminShopController extends AutoMapperController{

    @ResponseBody
    @RequestMapping("/find")
    public Object findShop(@RequestBody JAdmin jAdmin) {
        try {
            Map map = new HashMap();
            map.put("adminId",jAdmin.getAdminId());
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", ResponseUtil.List2Map(jAdminShopMapper.getList(jAdmin.getAdminId())));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/insert")
    public Object insertShop(@RequestBody Map map) {
        try {
            Integer adminId = (Integer) map.get("adminId");
            List<Integer> shopIdList = (List<Integer>) map.get("shopIdList");
            List<JAdminShop> jAdminShopList = new ArrayList<JAdminShop>();
            Map map1 = new HashMap();
            map1.put("adminId",adminId);
            jAdminShopMapper.deleteByMap(map1);
            if (shopIdList.size()>0){
                for (Integer id : shopIdList){
                    JAdminShop jAdminShop = new JAdminShop();
                    jAdminShop.setShopId(id);
                    jAdminShop.setAdminId(adminId);
                    jAdminShopList.add(jAdminShop);
                    jAdminShopMapper.insert(jAdminShop);
                }
            }


            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
}
