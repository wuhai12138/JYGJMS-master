package com.summ.controller.leaguer;

import com.summ.controller.basic.AutoMapperController;
import com.summ.model.JLeaguerShop;
import com.summ.model.response.ModelRes;
import com.summ.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 加盟商与门店绑定
 * @author summ
 */
@Controller
@RequestMapping("/leaguer/shop")
public class LeaguerShopController extends AutoMapperController{

    @ResponseBody
    @RequestMapping("/find")
    public Object findDetail(@RequestBody Map map) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", ResponseUtil.List2Map(jLeaguerShopMapper.getLeaguerShop((Integer) map.get("leaguerId"))));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object shopInsert(@RequestBody Map map, ServletRequest request) {
        try {

            List<Integer> shopIdList = (List<Integer>) map.get("shopId");
            Integer leaguerId = (Integer) map.get("leaguerId");

            List<JLeaguerShop> jLeaguerShopList = new ArrayList<JLeaguerShop>();
            Map map1 = new HashMap();
            map1.put("leaguerId", leaguerId);
            jLeaguerShopMapper.deleteByMap(map1);

            if (shopIdList.size()>0){
                for (Integer id : shopIdList) {
                    JLeaguerShop jLeaguerShop = new JLeaguerShop();
                    jLeaguerShop.setLeaguerId(leaguerId);
                    jLeaguerShop.setShopId(id);
                    jLeaguerShopList.add(jLeaguerShop);
                    jLeaguerShopMapper.insert(jLeaguerShop);
                }
            }
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", null);

        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
}
