package com.summ.controller.nanny;

import com.summ.Consts;
import com.summ.controller.basic.AutoMapperController;
import com.summ.model.JNannyInfo;
import com.summ.model.response.ModelRes;
import com.summ.model.response.NannyInfoRes;
import com.summ.utils.IdCardUtil;
import com.summ.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 服务师基本信息
 * @author summ
 * @date 17/12/18
 */
@Controller
@RequestMapping("/nanny/basic")
public class NannyBasicController extends AutoMapperController {

    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody JNannyInfo jNannyInfo) {
        try {
            JNannyInfo jNannyInfo1 = jNannyInfoMapper.selectById(Long.valueOf(jNannyInfo.getNannyId()));
            if (!jNannyInfo.getNannyIdCard().equals(jNannyInfo1.getNannyIdCard())){
                Map map = new HashMap();
                map.put("nannyIdCard",jNannyInfo.getNannyIdCard());
                if (jNannyInfoMapper.selectByMap(map).size()>0){
                    return new ModelRes(ModelRes.Status.SUCCESS, "身份证重复 !");
                }
            }
            if (!jNannyInfo.getNannyPhone().equals(jNannyInfo1.getNannyPhone())){
                Map map = new HashMap();
                map.put("nannyPhone",jNannyInfo.getNannyPhone());
                if (jNannyInfoMapper.selectByMap(map).size()>0){
                    return new ModelRes(ModelRes.Status.SUCCESS, "手机号重复 !");
                }
            }

            //判断身份证是否有效
            if(IdCardUtil.isValidatedAllIdcard(jNannyInfo.getNannyIdCard())){
                if (!"".equals(jNannyInfo.getNannyAvatar())) {
                    File file = new File(Consts.nannyAvatarUrl + jNannyInfoMapper.getNannyBasic(jNannyInfo.getNannyId()).getNannyAvatar());
                    if (file.exists() && file.isFile()) {
                        file.delete();
                        System.out.println(">>>>>>>>>file delete.");
                    }
                    String fileName = System.currentTimeMillis() + ".jpg";
                    //判断身份证是否合法以及照片上传是否成功
                    if (!StringUtil.generateImage(jNannyInfo.getNannyAvatar(), Consts.nannyAvatarUrl + fileName)) {
                        return new ModelRes(ModelRes.Status.FAILED, "头像上传错误 !", "");
                    }
                    jNannyInfo.setNannyAvatar(fileName);
                    return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyInfoMapper.updateById(jNannyInfo));
                }else {
                    jNannyInfo.setNannyAvatar(jNannyInfoMapper.getNannyBasic(jNannyInfo.getNannyId()).getNannyAvatar());
                    return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyInfoMapper.updateById(jNannyInfo));
                }
            }else {
                return new ModelRes(ModelRes.Status.FAILED, "身份证无效 !", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }


    @ResponseBody
    @RequestMapping("/find")
    public Object findBasicData(@RequestBody JNannyInfo jNannyInfo) {
        try {
            NannyInfoRes nannyInfoRes = jNannyInfoMapper.getNannyBasic(jNannyInfo.getNannyId());
            nannyInfoRes.setNannyAvatar(Consts.nannyAvatarUrlRes + nannyInfoRes.getNannyAvatar());
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", nannyInfoRes);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
}
