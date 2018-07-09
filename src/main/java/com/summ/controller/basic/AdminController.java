package com.summ.controller.basic;

import com.summ.model.JAdmin;
import com.summ.model.JAdminType;
import com.summ.model.request.PaginateReq;
import com.summ.model.response.ModelRes;
import com.summ.utils.ResponseUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员模块
 *
 * @author summ
 * @date 17/11/20
 */

@Controller
@RequestMapping("/admin")
public class AdminController extends AutoMapperController {

    /**
     * 登录验证用户名密码并返回管理员拥有的权限列表
     *
     * @param admin
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public Object login(@RequestBody JAdmin admin) {
        try {
            Map map1 = new HashMap();
            map1.put("adminName", admin.getAdminName());
            map1.put("isDel",16);
            List<JAdmin> jAdminList = jAdminMapper.selectByMap(map1);
            if (jAdminList.size() < 1) {
                return new ModelRes(ModelRes.Status.FAILED, "无此用户，请重新输入 !", null);
            }
            JAdmin jAdmin = jAdminList.get(0);
            if (jAdmin.getAdminPassword().equals(admin.getAdminPassword())) {
                Map map = new HashMap();
                map.put("adminTypeList", jAdminTypeMapper.getAdminTypeById(jAdmin.getAdminId()));
                map.put("admin", jAdmin);
                return new ModelRes(ModelRes.Status.SUCCESS, "登陆成功 !", map);
            } else {
                return new ModelRes(ModelRes.Status.FAILED, "密码错误 !");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err ! ");
        }
    }

    /**
     * 根据管理员类型返回页面列表
     * @param jAdminType
     * @return
     */
    @ResponseBody
    @RequestMapping("/accessPage")
    public Object getAccessPage(@RequestBody JAdminType jAdminType) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "登陆成功 !", ResponseUtil.List2Map(jAdminMapper.getAccess(jAdminType.getId())));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err ! ");
        }
    }

    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody JAdmin jAdmin) {
        try {
            Map map = new HashMap();
            map.put("adminName", jAdmin.getAdminName());
            map.put("isDel",16);
            if (jAdminMapper.selectByMap(map).size() > 0) {
                return new ModelRes(ModelRes.Status.FAILED, "管理员姓名重复 !");
            } else {
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功  !", jAdminMapper.insert(jAdmin));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody JAdmin jAdmin) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "update administrator success !", jAdminMapper.updateById(jAdmin));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody PaginateReq paginateReq, ServletRequest request) {
        try {
            JAdmin admin = (JAdmin) request.getAttribute("admin");
            paginateReq.setAdminId(admin.getAdminId());
            paginateReq.setPage(paginateReq.getSize() * (paginateReq.getPage() - 1));
            Map<String, Object> map = new HashedMap();
            map.put("count", jAdminMapper.getCount(paginateReq));
            map.put("list", jAdminMapper.getAdminList(paginateReq));
            return new ModelRes(ModelRes.Status.SUCCESS, "search administrator success !", map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(@RequestBody JAdmin jAdmin) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jAdminMapper.deleteAdmin(jAdmin.getAdminId()));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

}
