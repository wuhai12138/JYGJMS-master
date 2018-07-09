package com.summ.controller.order;

import com.summ.controller.basic.AutoMapperController;
import com.summ.model.request.CommentReq;
import com.summ.model.response.ModelRes;
import com.summ.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;

/**
 * 日程评价
 */
@Controller
@RequestMapping("/schedule/comment")
public class ScheduleCommentController extends AutoMapperController{

    @ResponseBody
    @RequestMapping("/find")
    public Object findShopList(@RequestBody CommentReq commentReq, ServletRequest request) {
        try {
            commentReq.setPage((commentReq.getPage()-1)*commentReq.getSize());
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", ResponseUtil.List2Map(jScheduleNannyMapper.getScheduleComment(commentReq),jScheduleNannyMapper.getScheduleCommentCount(commentReq)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

}
