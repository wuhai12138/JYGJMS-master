package com.summ.controller.nanny;

import com.summ.controller.basic.AutoMapperController;
import com.summ.mapper.JNannyInsuranceMapper;
import com.summ.model.JNannyCase;
import com.summ.model.JNannyInfo;
import com.summ.model.JNannyInsurance;
import com.summ.model.response.ModelRes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 服务师保险模块
 * @author summ
 */
@Controller
@RequestMapping("/nanny/insurance")
public class NannyInsuranceController extends AutoMapperController{

    /**
     * 给服务师购买制定月份保险
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody Map map) {
        try {
            Date insuranceDate = new Date((Long) map.get("insuranceDate"));
            List<Integer> nannyIdList = (List<Integer>) map.get("list");
            for (Integer ii : nannyIdList){
                JNannyInsurance jNannyInsurance = new JNannyInsurance(insuranceDate,ii);
                jNannyInsuranceMapper.insert(jNannyInsurance);
            }
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

}
