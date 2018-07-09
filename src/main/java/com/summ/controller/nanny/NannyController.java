package com.summ.controller.nanny;

import com.summ.Consts;
import com.summ.controller.basic.AutoMapperController;
import com.summ.mapper.JNannyShopMapper;
import com.summ.model.JAdmin;
import com.summ.model.JNannyInfo;
import com.summ.model.JNannyShop;
import com.summ.model.request.NannyInfoReq;
import com.summ.model.response.*;
import com.summ.utils.*;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;

/**
 * 服务师模块
 * @author summ
 * @date 17/12/11
 */
@Controller
@RequestMapping("/nanny")
public class NannyController extends AutoMapperController {

    /**
     * CRUD for nanny
     *
     * @param jNannyInfo
     * @return
     */

    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody JNannyInfo jNannyInfo, @RequestBody Map map, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            jNannyInfo.setCreateTime(new Date());
            jNannyInfo.setCreateId(jAdmin.getAdminId());
            //根据身份证判断服务师信息是否已录入
            Map idMap = new HashMap(1);
            idMap.put("nannyIdCard", jNannyInfo.getNannyIdCard());
            List<JNannyInfo> idCardNannyInfo = jNannyInfoMapper.selectByMap(idMap);
            if (idCardNannyInfo.size() > 0) {
                Map map1 = new HashMap();
                map1.put("shopId",map.get("shopId"));
                map1.put("nannyId",idCardNannyInfo.get(0).getNannyId());
                if (jNannyShopMapper.selectByMap(map1).size()>0){
                    return new ModelRes(ModelRes.Status.FAILED, "该服务师不能重复添加同一门店 !",null );
                }
                //如果已经录入则返回服务师相关信息
                Map resMap = new HashMap(2);
                resMap.put("NannyInfoRes", idCardNannyInfo.get(0));
                resMap.put("NannyShopRes", jNannyInfoMapper.getNannyShop(idCardNannyInfo.get(0).getNannyId()));
                return new ModelRes(ModelRes.Status.BUILT, "服务师已存在", resMap);
            }
            String fileName = "NA" + System.currentTimeMillis() + ".jpg";
            //判断身份证是否合法以及照片上传是否成功
            if ("".equals(jNannyInfo.getNannyAvatar()) || null == jNannyInfo.getNannyAvatar()){

                if (IdCardUtil.isValidatedAllIdcard(jNannyInfo.getNannyIdCard())) {
                    //根据身份证生成年龄
                    jNannyInfo.setNannyAge(IdCardUtil.getAgeByIdCard(jNannyInfo.getNannyIdCard()));
                    jNannyInfo.setNannyAvatar(fileName);
                    jNannyInfo.setCreateTime(new Date());
                    //新增服务师
                    jNannyInfoMapper.insert(jNannyInfo);
                    //给服务师添加管理员所属门店
                    JNannyShop jNannyShop = new JNannyShop();
                    jNannyShop.setShopId((Integer) map.get("shopId"));
                    jNannyShop.setNannyId(jNannyInfo.getNannyId());
                    return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyShopMapper.insert(jNannyShop));
                } else {
                    return new ModelRes(ModelRes.Status.FAILED, " 身份证无效,请重新输入 !");
                }
            }else {
                if (IdCardUtil.isValidatedAllIdcard(jNannyInfo.getNannyIdCard()) && StringUtil.generateImage(jNannyInfo.getNannyAvatar(), Consts.nannyAvatarUrl + fileName)) {
                    //根据身份证生成年龄
                    jNannyInfo.setNannyAge(IdCardUtil.getAgeByIdCard(jNannyInfo.getNannyIdCard()));
                    jNannyInfo.setNannyAvatar(fileName);
                    jNannyInfo.setCreateTime(new Date());
                    //新增服务师
                    jNannyInfoMapper.insert(jNannyInfo);
                    //给服务师添加管理员所属门店
                    JNannyShop jNannyShop = new JNannyShop();
                    jNannyShop.setNannyId(jNannyInfo.getNannyId());
                    jNannyShop.setShopId((Integer) map.get("shopId"));
                    return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyShopMapper.insert(jNannyShop));
                } else {
                    return new ModelRes(ModelRes.Status.FAILED, "id card or avatar err !");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 更新服务师
     * @param jNannyInfo
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody JNannyInfo jNannyInfo,ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            jNannyInfo.setModifyId(jAdmin.getAdminId());
            jNannyInfo.setModifyTime(new Date());
            String fileName = "NA" + System.currentTimeMillis() + ".jpg";
            /**判断是否更新服务师图片*/
            if ("".equals(jNannyInfo.getNannyAvatar()) || null == jNannyInfo.getNannyAvatar()){
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功", jNannyInfoMapper.updateById(jNannyInfo));
            }else {
                if (StringUtil.generateImage(jNannyInfo.getNannyAvatar(), Consts.nannyAvatarUrl + fileName)) {
                    jNannyInfo.setNannyAvatar(fileName);
                    return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyInfoMapper.updateById(jNannyInfo));
                } else {
                    return new ModelRes(ModelRes.Status.FAILED, " avatar err !");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 更新服务师是否为常用服务师
     * @param jNannyInfo
     * @return
     */
    @ResponseBody
    @RequestMapping("/hot")
    public Object updateToHot(@RequestBody JNannyInfo jNannyInfo) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功", jNannyInfoMapper.updateById(jNannyInfo));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody NannyInfoReq nannyInfoReq, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            nannyInfoReq.setAdminId(jAdmin.getAdminId());
            nannyInfoReq.setPage(nannyInfoReq.getSize() * (nannyInfoReq.getPage() - 1));
            Map<String, Object> map = new HashedMap();
            map.put("list", jNannyInfoMapper.getNannyInfoList(nannyInfoReq));
            map.put("count", jNannyInfoMapper.getCount(nannyInfoReq));
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
}
