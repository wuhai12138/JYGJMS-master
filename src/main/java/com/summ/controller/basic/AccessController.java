package com.summ.controller.basic;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.summ.mapper.JAccessMapper;
import com.summ.model.JAccess;
import com.summ.model.JAdmin;
import com.summ.model.JDictType;
import com.summ.model.response.ModelRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *前端页面显示模块
 * @author summ
 * @date 17/11/22
 */
@Controller
@RequestMapping("/access")
public class AccessController extends AutoMapperController{

    /**
     * 添加页面侧边栏
     * @param jAccess
     * @return
     */
    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody JAccess jAccess){
        try {
            return new ModelRes(ModelRes.Status.SUCCESS,"操作成功  !",jAccessMapper.insert(jAccess));
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(@RequestBody JAccess jAccess){
        try {
            return new ModelRes(ModelRes.Status.SUCCESS,"操作成功  !",jAccessMapper.deleteById(Long.valueOf(jAccess.getAccessId())));
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody JAccess jAccess){
        try {
            return new ModelRes(ModelRes.Status.SUCCESS,"操作成功  !",jAccessMapper.updateById(jAccess));
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody JAccess jAccess){
        try {
            EntityWrapper<JAccess> entityWrapper = null;
            return new ModelRes(ModelRes.Status.SUCCESS,"操作成功  !",jAccessMapper.getListOrderBySort());
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }



}
