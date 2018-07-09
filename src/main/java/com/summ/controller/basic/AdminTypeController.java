package com.summ.controller.basic;

import com.summ.model.JAccessDict;
import com.summ.model.JAdminType;
import com.summ.model.JDictInfo;
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
 * 管理员类型 管理接口
 *
 * @author summ
 */
@Controller
@RequestMapping("/admin/type")
public class AdminTypeController extends AutoMapperController {

    /**
     * 添加管理员类型，即添加j_dict_info表
     *
     * @param jDictInfo
     * @return
     */
    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody JDictInfo jDictInfo) {
        try {
            Map map = new HashMap();
            map.put("typecode", 1);
            map.put("isDel", 16);
            List<JDictInfo> jDictInfoList = jDictInfoMapper.selectByMap(map);
            for (JDictInfo jDictInfo1 : jDictInfoList) {
                if (jDictInfo1.getInfo().equals(jDictInfo.getInfo())) {
                    return new ModelRes(ModelRes.Status.FAILED, "当前已经有重复的管理员类型", jDictInfoList);
                }
            }
            jDictInfo.setTypecode(1);
            jDictInfo.setDictcode(jDictInfoList.size() + 1);
            return new ModelRes(ModelRes.Status.SUCCESS, "", jDictInfoMapper.insert(jDictInfo));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 给某个管理员添加管理员类型
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/adminType/insert")
    public Object adminTypeInsert(@RequestBody Map map) {
        try {
            Integer adminId = (Integer) map.get("adminId");
            List<Integer> adminTypeList = (List<Integer>) map.get("adminTypeIdList");
            List<JAdminType> jAdminTypeList = new ArrayList<JAdminType>();
            Map map1 = new HashMap();
            map1.put("adminId", adminId);
            jAdminTypeMapper.deleteByMap(map1);

            if (adminTypeList.size() > 0) {

                for (Integer id : adminTypeList) {
                    JAdminType jAdminType = new JAdminType();
                    jAdminType.setAdminTypeId(id);
                    jAdminType.setAdminId(adminId);
                    jAdminTypeList.add(jAdminType);
                    jAdminTypeMapper.insert(jAdminType);
                }
            }

            return new ModelRes(ModelRes.Status.SUCCESS, "", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 查找某个管理员的管理员类型列表
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/adminType/find")
    public Object adminTypeFind(@RequestBody Map map) {
        try {
            Integer adminId = (Integer) map.get("adminId");
            return new ModelRes(ModelRes.Status.SUCCESS, "", ResponseUtil.List2Map(jAdminTypeMapper.getAdminTypeById(adminId)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(@RequestBody JDictInfo jDictInfo) {
        try {
            /**逻辑删除字典表*/
            jDictInfo.setIsDel(17);
            jDictInfoMapper.updateById(jDictInfo);
            /**删除管理员角色表中关于此管理员的记录*/
            Map map = new HashMap();
            map.put("adminTypeId", jDictInfo.getId());
            jAdminTypeMapper.deleteByMap(map);
            return new ModelRes(ModelRes.Status.SUCCESS, "", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 查找管理员类型
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody Map map) {
        try {
            map.put("typecode", 1);
            map.put("isDel", 16);
            List<JDictInfo> jDictInfoList = jDictInfoMapper.selectByMap(map);
            return new ModelRes(ModelRes.Status.SUCCESS, "", jDictInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 给某个管理员类型添加页面侧边栏
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/accessUrl/insert")
    public Object insertAccess(@RequestBody Map map) {
        try {
            Integer adminTypeId = (Integer) map.get("adminTypeId");
            List<Integer> accessIdList = (List<Integer>) map.get("urlList");
            List<JAccessDict> jAccessDictList = new ArrayList<JAccessDict>();
            Map map1 = new HashMap();
            map1.put("adminTypeId", adminTypeId);
            jAccessDictMapper.deleteByMap(map1);

            for (Integer id : accessIdList) {
                JAccessDict jAccessDict = new JAccessDict();
                jAccessDict.setAccessId(id);
                jAccessDict.setAdminTypeId(adminTypeId);
                jAccessDictMapper.insert(jAccessDict);
                jAccessDictList.add(jAccessDict);
            }

            return new ModelRes(ModelRes.Status.SUCCESS, "", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 查找某个管理员类型的侧边栏
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/accessUrl/find")
    public Object findAccess(@RequestBody Map map) {
        try {
            Integer adminTypeId = (Integer) map.get("adminTypeId");
            return new ModelRes(ModelRes.Status.SUCCESS, "", ResponseUtil.List2Map(jAccessDictMapper.getAccessUrlById(adminTypeId)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
}
