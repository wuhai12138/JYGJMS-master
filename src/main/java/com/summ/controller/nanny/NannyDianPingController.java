package com.summ.controller.nanny;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.summ.Consts;
import com.summ.controller.basic.AutoMapperController;
import com.summ.model.JNannyDianPing;
import com.summ.model.request.NannyDianPingReq;
import com.summ.model.response.ModelRes;
import com.summ.utils.ResponseUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 服务师提交的第三方点评
 */
@RequestMapping("/nanny/dianping")
@RestController
public class NannyDianPingController extends AutoMapperController{

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public Object findDianPing(@RequestBody final NannyDianPingReq nannyDianPingReq) {
        try {
            nannyDianPingReq.setPage((nannyDianPingReq.getPage()-1)*nannyDianPingReq.getSize());
            List<JNannyDianPing> jNannyDianPingList = jNannyDianPingMapper.selectList(new Wrapper<JNannyDianPing>() {
                @Override
                public String getSqlSegment() {
                    return " and nannyId="+nannyDianPingReq.getNannyId()+" order by createDate desc limit "+nannyDianPingReq.getPage()+","+nannyDianPingReq.getSize();
                }
            });

            Integer count = jNannyDianPingMapper.selectCount(new Wrapper<JNannyDianPing>() {
                @Override
                public String getSqlSegment() {
                    return " and nannyId="+nannyDianPingReq.getNannyId()+" order by createDate desc limit "+nannyDianPingReq.getPage()+","+nannyDianPingReq.getSize();
                }
            });
            for (JNannyDianPing jNannyDianPing:jNannyDianPingList){
                jNannyDianPing.setPhote(Consts.nannyDianPingUrlRes+jNannyDianPing.getPhote());
            }
            return new ModelRes(ModelRes.Status.SUCCESS, "查询成功 !", ResponseUtil.List2Map(jNannyDianPingList,count));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object findDianPing(@RequestBody JNannyDianPing jNannyDianPing) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "查询成功 !",jNannyDianPingMapper.deleteById(jNannyDianPing.getDianPingId()));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
}
