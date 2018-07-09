package com.summ.controller.nanny;

import com.summ.Consts;
import com.summ.controller.basic.AutoMapperController;
import com.summ.mapper.JNannyCertificateMapper;
import com.summ.model.JDictInfo;
import com.summ.model.JNannyCertificate;
import com.summ.model.JNannyInfo;
import com.summ.model.JNannyLanguage;
import com.summ.model.response.ModelRes;
import com.summ.model.response.NannyCertificateRes;
import com.summ.utils.ResponseUtil;
import com.summ.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务师证件
 * @author summ
 * @date 17/12/18
 */
@Controller
@RequestMapping("/nanny/certificate")
public class NannyCertificateController extends AutoMapperController {

    @ResponseBody
    @RequestMapping("/find")
    public Object findCertificateData(@RequestBody JNannyInfo jNannyInfo) {
        try {
            Map map = new HashMap();
            List<NannyCertificateRes> list = jNannyInfoMapper.getNannyCertificate(jNannyInfo.getNannyId());
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setPhoto(Consts.nannyCertUrlRes + list.get(i).getPhoto());
            }
            map.put("certificate", list);
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object deleteCertificateData(@RequestBody Map map) {
        try {
            JNannyCertificate jNannyCertificate = new JNannyCertificate();
            jNannyCertificate.setNannyCertId((Integer) map.get("id"));
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyCertificateMapper.deleteById(jNannyCertificate));
        } catch (Exception e) {

            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody JNannyCertificate map,@RequestBody Map map1) {
        try {
            Map map2 = new HashMap();
            map2.put("certificateNumber",map.getCertificateNumber());
            Integer certificateId =  map.getCertificateId();
            /**判断证件类型,如果为0则表示字典表无此证件类型,新增字典表*/
            if(certificateId==0){
                //新增数据字典
                JDictInfo jDictInfo = new JDictInfo();
                jDictInfo.setTypecode(21);
                jDictInfo.setInfo((String) map1.get("info"));
                jDictInfoMapper.insert(jDictInfo);

                //插入服务师技能表
                map.setCertificateId(jDictInfo.getId());

                return new ModelRes(ModelRes.Status.SUCCESS,"操作成功 !",jNannyCertificateMapper.insert(map));
            }else {
                map2.put("certificateId",map.getCertificateId());
                List<JNannyCertificate> jNannyCertificateList = jNannyCertificateMapper.selectByMap(map2);
                if (jNannyCertificateList.size()>0){
                    return new ModelRes(ModelRes.Status.FAILED,"证件重复 !", ResponseUtil.List2Map(jNannyCertificateList));
                }
                return new ModelRes(ModelRes.Status.SUCCESS,"操作成功 !",jNannyCertificateMapper.insert(map));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 上传证件照
     */
    @ResponseBody
    @RequestMapping("/upload")
    public Object upload(@RequestBody JNannyCertificate jNannyCertificate) {
        try {
            String fileName = "NC"+System.currentTimeMillis()+".jpg";
            if(StringUtil.generateImage(jNannyCertificate.getPhoto(),Consts.nannyCertUrl + fileName)){
                jNannyCertificate.setPhoto(fileName);
                return new ModelRes(ModelRes.Status.SUCCESS,"操作成功 !",jNannyCertificateMapper.updateById(jNannyCertificate));
            }
            return new ModelRes(ModelRes.Status.FAILED, "photo err !");
        } catch (Exception e)
        {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
}
