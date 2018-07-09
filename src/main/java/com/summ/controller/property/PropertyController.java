package com.summ.controller.property;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.summ.controller.basic.AutoMapperController;
import com.summ.model.JAdmin;
import com.summ.model.JCustomerHouseProperty;
import com.summ.model.JShop;
import com.summ.model.response.ModelRes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;

/**
 * 物业
 */
@Controller
@RequestMapping("property")
public class PropertyController extends AutoMapperController{

    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody JCustomerHouseProperty jCustomerHouseProperty, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            jCustomerHouseProperty.setCreateId(jAdmin.getAdminId());
            jCustomerHousePropertyMapper.insert(jCustomerHouseProperty);
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody JCustomerHouseProperty jCustomerHouseProperty, ServletRequest request) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jCustomerHousePropertyMapper.selectList(new EntityWrapper<JCustomerHouseProperty>()));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody JCustomerHouseProperty jCustomerHouseProperty, ServletRequest request) {
        try {
            jCustomerHousePropertyMapper.updateById(jCustomerHouseProperty);
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
}
