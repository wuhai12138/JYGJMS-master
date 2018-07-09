package com.summ.controller.teacher;

import com.summ.Consts;
import com.summ.controller.basic.AutoMapperController;
import com.summ.model.*;
import com.summ.model.request.NannyInfoReq;
import com.summ.model.request.TeacherReq;
import com.summ.model.response.ModelRes;
import com.summ.model.response.TeacherRes;
import com.summ.utils.IdCardUtil;
import com.summ.utils.NannyWorkTimeUtil;
import com.summ.utils.ResponseUtil;
import com.summ.utils.StringUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.util.*;

/**
 * @author summ
 * @date 17/12/11
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController extends AutoMapperController {

    /**
     * CRUD for nanny
     *
     * @param jTeacher
     * @return
     */

    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody JTeacher jTeacher, @RequestBody Map map, ServletRequest request) {
        try {
            //根据身份证判断带教信息是否已录入
            Map idMap = new HashMap(1);
            idMap.put("teacherIdCard", jTeacher.getTeacherIdCard());
            List<JTeacher> idCardteacherInfo = jTeacherMapper.selectByMap(idMap);
            if (idCardteacherInfo.size() > 0) {
                Map map1 = new HashMap();
                map1.put("shopId",map.get("shopId"));
                map1.put("teacherId",idCardteacherInfo.get(0).getTeacherId());
                if (jTeacherShopMapper.selectByMap(map1).size()>0){
                    return new ModelRes(ModelRes.Status.FAILED, "该带教不能重复添加同一门店 !",null );
                }

                //如果已经录入则返回带教相关信息
                Map resMap = new HashMap(2);
                resMap.put("TeacherInfoRes", idCardteacherInfo.get(0));
                resMap.put("TeacherShopRes", jTeacherMapper.getTeacherShop(idCardteacherInfo.get(0).getTeacherId()));
                return new ModelRes(ModelRes.Status.BUILT, "带教已存在", resMap);
            }
            String fileName = "TE" + System.currentTimeMillis() + ".jpg";
//            if (IdCardUtil.isValidatedAllIdcard(jNannyInfo.getNannyIdCard()) && StringUtil.generateImage(jNannyInfo.getNannyAvatar(),"C:/Users/summ/Desktop/upload/" + fileName)){
            //判断身份证是否合法以及照片上传是否成功
            if ("".equals(jTeacher.getTeacherAvatar()) || null == jTeacher.getTeacherAvatar()){

                if (IdCardUtil.isValidatedAllIdcard(jTeacher.getTeacherIdCard())) {
                    //根据身份证生成年龄
                    jTeacher.setTeacherAge(IdCardUtil.getAgeByIdCard(jTeacher.getTeacherIdCard()));
                    jTeacher.setTeacherAvatar(fileName);
                    jTeacher.setCreateTime(new Date());
                    //新增带教
                    jTeacherMapper.insert(jTeacher);
                    //给带教添加门店
                    JTeacherShop jTeacherShop = new JTeacherShop();
                    jTeacherShop.setShopId((Integer) map.get("shopId"));
                    jTeacherShop.setTeacherId(jTeacher.getTeacherId());
                    jTeacherShopMapper.insert(jTeacherShop);
                    //给带教添加工时
                    Long timeValue = NannyWorkTimeUtil.getTimeListValue(10,44);
                    JTeacherWorkTime jTeacherWorkTime = new JTeacherWorkTime(jTeacher.getTeacherId(),timeValue,timeValue,timeValue,timeValue,timeValue,timeValue,timeValue);
                    jTeacherWorkTimeMapper.insert(jTeacherWorkTime);

                    return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", null);
                } else {
                    return new ModelRes(ModelRes.Status.FAILED, " 身份证错误 !");
                }
            }else {
                if (IdCardUtil.isValidatedAllIdcard(jTeacher.getTeacherIdCard()) && StringUtil.generateImage(jTeacher.getTeacherAvatar(), Consts.nannyAvatarUrl + fileName)) {
                    //根据身份证生成年龄
                    jTeacher.setTeacherAge(IdCardUtil.getAgeByIdCard(jTeacher.getTeacherIdCard()));
                    jTeacher.setTeacherAvatar(fileName);
                    jTeacher.setCreateTime(new Date());
                    //新增服务师
                    jTeacherMapper.insert(jTeacher);
                    //给带教添加门店
                    JTeacherShop jTeacherShop = new JTeacherShop();
                    jTeacherShop.setTeacherId(jTeacher.getTeacherId());
                    jTeacherShop.setShopId((Integer) map.get("shopId"));
                    jTeacherShopMapper.insert(jTeacherShop);

                    //给带教添加工时
                    Long timeValue = NannyWorkTimeUtil.getTimeListValue(10,44);
                    JTeacherWorkTime jTeacherWorkTime = new JTeacherWorkTime(jTeacher.getTeacherId(),timeValue,timeValue,timeValue,timeValue,timeValue,timeValue,timeValue);
                    jTeacherWorkTimeMapper.insert(jTeacherWorkTime);

                    return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", null);
                } else {
                    return new ModelRes(ModelRes.Status.FAILED, "id card or avatar err !");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody TeacherReq teacherReq, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            teacherReq.setAdminId(jAdmin.getAdminId());
            teacherReq.setPage(teacherReq.getSize() * (teacherReq.getPage() - 1));
            Map<String, Object> map = new HashedMap();
            map.put("list", jTeacherMapper.getTeacherList(teacherReq));
            map.put("count", jTeacherMapper.getCount(teacherReq));
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody JTeacher jTeacher, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");

            JTeacher jTeacher1 = jTeacherMapper.selectById(Long.valueOf(jTeacher.getTeacherId()));
            if (null!=jTeacher.getTeacherIdCard()){
                if (!jTeacher.getTeacherIdCard().equals(jTeacher1.getTeacherIdCard())){
                    Map idMap = new HashMap();
                    idMap.put("teacherIdCard",jTeacher.getTeacherIdCard());
                    List<JTeacher> idTeacherList = jTeacherMapper.selectByMap(idMap);
                    if (idTeacherList.size()>0){
                        return new ModelRes(ModelRes.Status.FAILED, "身份证已占用 !", ResponseUtil.List2Map(idTeacherList));
                    }
                }
            }
            if (null!=jTeacher.getTeacherPhone()){
                if (!jTeacher.getTeacherPhone().equals(jTeacher1.getTeacherPhone())){
                    Map phoneMap = new HashMap();
                    phoneMap.put("teacherPhone",jTeacher.getTeacherPhone());
                    List<JTeacher> phoneTeacherList = jTeacherMapper.selectByMap(phoneMap);
                    if (phoneTeacherList.size()>0){
                        return new ModelRes(ModelRes.Status.FAILED, "手机号已占用 !", ResponseUtil.List2Map(phoneTeacherList));
                    }
                }
            }
            jTeacher.setModifyTime(new Date());
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jTeacherMapper.updateById(jTeacher));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 带教离职
     * @param
     * @param jTeacher
     * @return
     */
    @ResponseBody
    @RequestMapping("/dimission")
    public Object dimission(@RequestBody JTeacher jTeacher) {
        try {
            jTeacher.setTeacherStatus(57);
            jTeacher.setDimissionTime(new Date());
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jTeacherMapper.updateById(jTeacher));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/detail/find")
    public Object findDetail(@RequestBody JTeacher jTeacher, ServletRequest request) {
        try {
            TeacherRes teacherRes = jTeacherMapper.getTeacherById(jTeacher.getTeacherId());
            teacherRes.setTeacherAvatar(Consts.nannyAvatarUrlRes + teacherRes.getTeacherAvatar());
            teacherRes.setShopResList(jTeacherMapper.getTeacherShop(jTeacher.getTeacherId()));
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", teacherRes);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 给带教绑定门店
     *
     * @param map
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/shop/insert")
    public Object shopInsert(@RequestBody Map map, ServletRequest request) {
        try {
            List<Integer> shopIdList = (List<Integer>) map.get("shopId");
            Integer teacherId = (Integer) map.get("teacherId");

            List<JTeacherShop> jTeacherShopList = new ArrayList<JTeacherShop>();
            Map map1 = new HashMap();
            map1.put("teacherId",teacherId);
            jTeacherShopMapper.deleteByMap(map1);
            for (Integer id : shopIdList){
                JTeacherShop jTeacherShop = new JTeacherShop();
                jTeacherShop.setTeacherId(teacherId);
                jTeacherShop.setShopId(id);
                jTeacherShopList.add(jTeacherShop);
                jTeacherShopMapper.insert(jTeacherShop);
            }

            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !",null );

        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

}
