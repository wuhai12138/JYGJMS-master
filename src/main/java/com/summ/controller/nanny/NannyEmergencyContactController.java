package com.summ.controller.nanny;

import com.summ.controller.basic.AutoMapperController;
import com.summ.model.JGoodsContract;
import com.summ.model.JNannyEmergencyContact;
import com.summ.model.request.GoodsReq;
import com.summ.model.response.ModelRes;
import com.summ.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/***
 * 服务师紧急联系人
 */
@Controller
@RequestMapping("nanny/emergency")
public class NannyEmergencyContactController extends AutoMapperController{

    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody JNannyEmergencyContact jNannyEmergencyContact) {
        try {

            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyEmergencyContactMapper.insert(jNannyEmergencyContact));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody JNannyEmergencyContact jNannyEmergencyContact) {
        try {

            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyEmergencyContactMapper.updateById(jNannyEmergencyContact));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody JNannyEmergencyContact jNannyEmergencyContact) {
        try {
            Map map = new HashMap();
            map.put("nannyId",jNannyEmergencyContact.getNannyId());
            return new ModelRes(ModelRes.Status.SUCCESS, "操作功 !", ResponseUtil.List2Map(jNannyEmergencyContactMapper.selectByMap(map)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

}
