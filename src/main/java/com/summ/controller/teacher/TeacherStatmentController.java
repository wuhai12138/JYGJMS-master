package com.summ.controller.teacher;

import com.summ.controller.basic.AutoMapperController;
import com.summ.model.JAdmin;
import com.summ.model.JNannyStatment;
import com.summ.model.request.NannyStatmentDetailReq;
import com.summ.model.request.NannyStatmentReq;
import com.summ.model.request.NannyStatmentRewardsAndPunishmentsReq;
import com.summ.model.request.TeacherStatmentDetailReq;
import com.summ.model.response.ModelRes;
import com.summ.model.response.NannyStatmentRes;
import com.summ.utils.JsonUtil;
import com.summ.utils.OrderUtil;
import com.summ.utils.ResponseUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by summ on 18/1/18.
 */
@Controller
@RequestMapping("/teacher/statment")
public class TeacherStatmentController extends AutoMapperController{

    /**
     * 带教对账单明细
     * @param teacherStatmentDetailReq
     * @return
     */
    @ResponseBody
    @RequestMapping("/find/detail")
    public Object findDetail(@RequestBody TeacherStatmentDetailReq teacherStatmentDetailReq, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            teacherStatmentDetailReq.setAdminId(jAdmin.getAdminId());
            teacherStatmentDetailReq.setPage(teacherStatmentDetailReq.getSize() * (teacherStatmentDetailReq.getPage()-1));
            Map<String, Object> map = new HashedMap();
            map.put("count", jTeacherStatmentMapper.getTeacherStatmentDetailCount(teacherStatmentDetailReq));
            map.put("list", jTeacherStatmentMapper.getTeacherStatmentDetail(teacherStatmentDetailReq));
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
}
