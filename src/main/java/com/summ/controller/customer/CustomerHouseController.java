package com.summ.controller.customer;

import com.summ.controller.basic.AutoMapperController;
import com.summ.model.JAdmin;
import com.summ.model.JCustomerHouse;
import com.summ.model.response.CustomerHouseRes;
import com.summ.model.response.ModelRes;
import com.summ.utils.ResponseUtil;
import com.summ.utils.TencentMapUtil;
import com.summ.utils.mongodb.MongoDBUtil;
import com.summ.utils.mongodb.model.MongoConfig;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 客户房产
 * @author summ
 * @date 17/11/28
 */
@Controller
@RequestMapping("/customer/house")
public class CustomerHouseController extends AutoMapperController{

    /**
     * CRUD for customer house
     * @param jCustomerHouse
     * @return
     */

    @ResponseBody
    @RequestMapping("/find")
    public Object findHouse(@RequestBody JCustomerHouse jCustomerHouse){
        try {
            /**初始化mongodb*/
            mongoDBUtil = MongoDBUtil.getInstance(mongoConfig);
            List<CustomerHouseRes> customerHouseResList = jCustomerHouseMapper.getList(jCustomerHouse.getCustomerId());
            if (customerHouseResList.size()>0){
                /**查找mongo中的服务计划书(服务计划书与房产一对一绑定)*/
                for (CustomerHouseRes customerHouseRes : customerHouseResList){
                    Map map = new HashedMap();
                    map.put("serviceId",customerHouseRes.getServiceId());
                    customerHouseRes.setServiceDetail(mongoDBUtil.query("customer_service",map));
                }
            }
            return new ModelRes(ModelRes.Status.SUCCESS,"操作成功 !", ResponseUtil.List2Map(customerHouseResList));
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/insert")
    public Object insertHouse(@RequestBody JCustomerHouse jCustomerHouse, ServletRequest request){
        try {
            /***根据地址获取经纬度*/
            Map<String, Double> tencentMap = TencentMapUtil.getLngAndLat("上海市" + jCustomerHouse.getHouseAddress());
            if (tencentMap == null) {
                return new ModelRes(ModelRes.Status.FAILED, "获取不到该地址，请重新输入 !");
            }
            jCustomerHouse.setLatitude(tencentMap.get("lat"));
            jCustomerHouse.setLongitude(tencentMap.get("lng"));
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            jCustomerHouse.setCreateId(jAdmin.getAdminId());
            return new ModelRes(ModelRes.Status.SUCCESS,"操作成功 !",jCustomerHouseMapper.insert(jCustomerHouse));
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object deleteHouse(@RequestBody Map<String,List> map){
        try {
            return new ModelRes(ModelRes.Status.SUCCESS,"操作成功 !",jCustomerHouseMapper.deleteList(map.get("houseId")));
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
    @ResponseBody
    @RequestMapping("/deleteOne")
    public Object deleteOne(@RequestBody JCustomerHouse jCustomerHouse){
        try {
            return new ModelRes(ModelRes.Status.SUCCESS,"操作成功 !",jCustomerHouseMapper.deleteById(jCustomerHouse));
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object updateHouse(@RequestBody JCustomerHouse jCustomerHouse){
        try {
            return new ModelRes(ModelRes.Status.SUCCESS,"操作成功 !",jCustomerHouseMapper.updateById(jCustomerHouse));
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

}
