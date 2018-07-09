package com.summ.controller.basic;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.summ.mapper.*;
import com.summ.model.*;
import com.summ.model.request.AddressReq;
import com.summ.model.response.ModelRes;
import com.summ.utils.JsonUtil;
import com.summ.utils.ResponseUtil;
import com.summ.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by summ on 17/11/27.
 * @author
 */
@Controller
@RequestMapping("/basic")
public class BasicController extends AutoMapperController{

    /**
     * 获取省市区街道列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/address")
    public Object getProvinceCityAndArea(@RequestBody AddressReq addressReq) {
        try {
            Map map = new HashMap();
            JStreet jStreet = new JStreet();
            JArea jArea = new JArea();
            JCity jCity = new JCity();
            if (addressReq.getAreaCode() != null && addressReq.getAreaCode().length() > 0) {
                //区code不为空则返回该code下的街道列表
                System.out.println("区code不为空则返回该code下的街道列表");
                jStreet.setAreaCode(addressReq.getAreaCode());
                EntityWrapper<JStreet> streetEntityWrapper = new EntityWrapper<JStreet>(jStreet);
                map.put("areaCode",addressReq.getAreaCode());
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", ResponseUtil.List2Map(jStreetMapper.selectByMap(map)));
            } else if (addressReq.getCityCode() != null && addressReq.getCityCode().length() > 0) {
                //区列表为空，市code不为空则返回该市下的区列表
                System.out.println("区列表为空，市code不为空则返回该市下的区列表");
                jArea.setCityCode(addressReq.getCityCode());
                EntityWrapper<JArea> areaEntityWrapper = new EntityWrapper<JArea>(jArea);
                map.put("cityCode",addressReq.getCityCode());
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", ResponseUtil.List2Map(jAreaMapper.selectByMap(map)));
            } else if (addressReq.getProvinceCode() != null && addressReq.getProvinceCode().length() > 0) {
                //省code不为空则返回该省下的市列表
                System.out.println("省code不为空则返回该省下的市列表");
                jCity.setProvinceCode(addressReq.getProvinceCode());
                EntityWrapper<JCity> cityEntityWrapper = new EntityWrapper<JCity>(jCity);
                map.put("provinceCode",addressReq.getProvinceCode());
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", ResponseUtil.List2Map(jCityMapper.selectByMap(map)));
            } else {
                //啥都不传则返回上海区列表
                System.out.println("啥都不传则返回上海区列表");
                map.put("cityCode","11010000");
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", ResponseUtil.List2Map(jAreaMapper.selectByMap(map)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 增加某个类型的字典信息
     * @param jDictInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/dict/insert")
    public Object insertDict(@RequestBody JDictInfo jDictInfo) {
        try {
            Map map = new HashMap();
            map.put("typeCode",jDictInfo.getTypecode());
            EntityWrapper<JDictInfo> entityWrapper = new EntityWrapper<JDictInfo>();
            jDictInfo.setDictcode(jDictInfoMapper.selectByMap(map).size()+1);
            return new ModelRes(ModelRes.Status.SUCCESS, "insert dict info success !", jDictInfoMapper.insert(jDictInfo));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
    /**
     * 获取指定类型有哪些信息
     * @param typeCode
     * @return
     */
    @ResponseBody
    @RequestMapping("/dict/find")
    public Object getDict(@RequestBody Map typeCode) {
        try {
            Map map = new HashMap();
            if (typeCode.get("info")!=null){
                map.put("list", jDictInfoMapper.getList((Integer) typeCode.get("typeCode"), (String) typeCode.get("info")));
            }else {
                map.put("list", jDictInfoMapper.getList((Integer) typeCode.get("typeCode"),""));
            }
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 获取数据字典类型
     * @param jAccess
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/dict/type")
    public Object getDictList(@RequestBody JAccess jAccess) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jDictTypeMapper.selectTypeList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 获取门店名称字典
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/shop/dict")
    public Object getShopDict(@RequestBody Map map, ServletRequest request) {
        try {
            JAdmin admin = (JAdmin) request.getAttribute("admin");
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", ResponseUtil.List2Map(jAdminShopMapper.getList(admin.getAdminId())));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 获取省籍贯字典
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/origin/dict")
    public Object getOriginDict(@RequestBody Map map) {
        try {
            EntityWrapper<JProvince> entityWrapper = new EntityWrapper<JProvince>();
            Map map1 = new HashMap();
            map1.put("list",jProvinceMapper.selectList(entityWrapper));
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", map1);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }


}
