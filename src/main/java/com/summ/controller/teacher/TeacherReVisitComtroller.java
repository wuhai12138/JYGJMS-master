package com.summ.controller.teacher;

import com.summ.controller.basic.AutoMapperController;
import com.summ.model.JAdmin;
import com.summ.model.JTeacherReVisit;
import com.summ.model.request.TeacherStatmentDetailReq;
import com.summ.model.response.ModelRes;
import com.summ.utils.ResponseUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.jar.JarEntry;

@Controller
@RequestMapping("/teacher/revisit")
public class TeacherReVisitComtroller extends AutoMapperController{

    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody JTeacherReVisit jTeacherReVisit, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            jTeacherReVisit.setCreateDate(new Date());
            jTeacherReVisit.setCreateId(jAdmin.getAdminId());
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jTeacherReVisitMapper.insert(jTeacherReVisit));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 根据回访地址查找带教列表
     * @param jTeacherReVisit
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/find/teacher")
    public Object findTeacher(@RequestBody JTeacherReVisit jTeacherReVisit, ServletRequest request) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", ResponseUtil.List2Map(jTeacherReVisitMapper.getRevisitTeacherByCustomer(jTeacherReVisit.getHouseId())));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

}
