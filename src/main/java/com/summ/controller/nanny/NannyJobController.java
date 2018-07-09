package com.summ.controller.nanny;

import com.summ.controller.basic.AutoMapperController;
import com.summ.model.JDictInfo;
import com.summ.model.JNannyInfo;
import com.summ.model.JNannyJobLevel;
import com.summ.model.JNannyShop;
import com.summ.model.response.*;
import com.summ.utils.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.util.*;

/**
 * 服务师工作信息
 * @author summ
 * @date 17/12/18
 */
@Controller
@RequestMapping("/nanny/job")
public class NannyJobController extends AutoMapperController {

    @ResponseBody
    @RequestMapping("/find")
    public Object findJobData(@RequestBody JNannyInfo jNannyInfo) {
        try {
            NannyInfoRes nannyInfoRes = jNannyInfoMapper.getJobData(jNannyInfo.getNannyId());
            //判断nannyInfoRes是否有数据，没有数据则该对象为空，需重新new。
            if (nannyInfoRes == null) {
                NannyInfoRes nannyInfoRes1 = new NannyInfoRes();
                List<NannyShopRes> nannyShopResList = jNannyInfoMapper.getNannyShop(jNannyInfo.getNannyId());
                if (nannyShopResList != null) {
                    nannyInfoRes1.setNannyShopRes(nannyShopResList);
                }
                List<NannyJobLevelRes> jobLevelRes = jNannyInfoMapper.getNannyJobLevel(jNannyInfo.getNannyId());
                if (jobLevelRes != null) {
                    nannyInfoRes1.setNannyJobLevelRes(jobLevelRes);
                }
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", nannyInfoRes1);
            }

            List<NannyShopRes> nannyShopResList = jNannyInfoMapper.getNannyShop(jNannyInfo.getNannyId());
            if (nannyShopResList != null) {
                nannyInfoRes.setNannyShopRes(nannyShopResList);
            }
            List<NannyJobLevelRes> jobLevelRes = jNannyInfoMapper.getNannyJobLevel(jNannyInfo.getNannyId());
            if (jobLevelRes != null) {
                nannyInfoRes.setNannyJobLevelRes(jobLevelRes);
            }
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", nannyInfoRes);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object updateJobData(@RequestBody final JNannyInfo jNannyInfo) {
        try {

            //根据身份证判断服务师信息是否已录入
            if (null != jNannyInfo.getNannyIdCard()) {
                Map idMap = new HashMap(1);
                idMap.put("nannyIdCard", jNannyInfo.getNannyIdCard());
                List<JNannyInfo> idCardNannyInfo = jNannyInfoMapper.selectByMap(idMap);
                if (idCardNannyInfo.size() > 0) {
                    if (!idCardNannyInfo.get(0).getNannyId().equals(jNannyInfo.getNannyId())) {
                        //如果已经录入则返回服务师相关信息
                        Map resMap = new HashMap(2);
                        resMap.put("NannyInfoRes", idCardNannyInfo.get(0));
                        resMap.put("NannyShopRes", jNannyInfoMapper.getNannyShop(idCardNannyInfo.get(0).getNannyId()));
                        return new ModelRes(ModelRes.Status.BUILT, "身份证已存在", resMap);
                    }
                }
            }


            /**如果要更新服务师为培训成功，则判断该服务师的健康证照片、身份证正反面照片是否上传。*/
            if (jNannyInfo.getNannyStatus() == 56) {
                List<NannyCertificateRes> list = jNannyInfoMapper.getNannyCertificate(jNannyInfo.getNannyId());
                for (NannyCertificateRes nannyCertificateRes : list) {
                    if (nannyCertificateRes.getCertificateId() == 109 || nannyCertificateRes.getCertificateId() == 189 || nannyCertificateRes.getCertificateId() == 190) {
                        if (nannyCertificateRes.getPhoto() == null || "".equals(nannyCertificateRes.getPhoto())) {
                            return new ModelRes(ModelRes.Status.FAILED, "服务师" + nannyCertificateRes.getCertificateIdInfo() + "未上传");
                        }
                    } else {
                        return new ModelRes(ModelRes.Status.FAILED, "服务师健康证、身份证未上传");
                    }
                }
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyInfoMapper.updateById(jNannyInfo));
            } else {
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyInfoMapper.updateById(jNannyInfo));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 服务师离职
     *
     * @param
     * @param jNannyInfo
     * @return
     */
    @ResponseBody
    @RequestMapping("/dimission")
    public Object dimission(@RequestBody JNannyInfo jNannyInfo) {
        try {
            jNannyInfo.setNannyStatus(57);
            jNannyInfo.setDimissionTime(new Date());
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyInfoMapper.updateById(jNannyInfo));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 服务师升星级
     *
     * @param jNannyInfo
     * @return
     */
    @ResponseBody
    @RequestMapping("/levelUp")
    public Object levelUp(@RequestBody JNannyInfo jNannyInfo) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyInfoMapper.updateById(jNannyInfo));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 给服务师绑定门店
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
            Integer nannyId = (Integer) map.get("nannyId");

            List<JNannyShop> jNannyShopList = new ArrayList<JNannyShop>();
            Map map1 = new HashMap();
            map1.put("nannyId", nannyId);
            jNannyShopMapper.deleteByMap(map1);
            for (Integer id : shopIdList) {
                JNannyShop jNannyShop = new JNannyShop();
                jNannyShop.setNannyId(nannyId);
                jNannyShop.setShopId(id);
                jNannyShopList.add(jNannyShop);
                jNannyShopMapper.insert(jNannyShop);
            }
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", null);

        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 服务师工种增删改查
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/level/insert")
    public Object insertLevel(@RequestBody Map map) {
        try {
            Integer languageId = (Integer) map.get("id");
            if (languageId == 0) {
                //新增数据字典
                JDictInfo jDictInfo = new JDictInfo();
                jDictInfo.setTypecode(16);
                jDictInfo.setInfo((String) map.get("info"));
                jDictInfoMapper.insert(jDictInfo);

                //插入服务师技能表
                JNannyJobLevel jNannyJobLevel = new JNannyJobLevel();
                jNannyJobLevel.setNannyId((Integer) map.get("nannyId"));
                jNannyJobLevel.setJobLevelId(jDictInfo.getId());
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyJobLevelMapper.insert(jNannyJobLevel));
            } else {
                JNannyJobLevel jNannyJobLevel = new JNannyJobLevel();
                jNannyJobLevel.setNannyId((Integer) map.get("nannyId"));
                jNannyJobLevel.setJobLevelId((Integer) map.get("id"));
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyJobLevelMapper.insert(jNannyJobLevel));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/level/delete")
    public Object deleteLevel(@RequestBody Map map) {
        try {
            Map map1 = new HashMap();
            map1.put("nannyJobLevelId", map.get("id"));
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyJobLevelMapper.deleteByMap(map1));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 服务师已选工种
     *
     * @param jNannyJobLevel
     * @return
     */
    @ResponseBody
    @RequestMapping("/level/find/selected")
    public Object findLevel(@RequestBody JNannyJobLevel jNannyJobLevel) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyJobLevelMapper.getSelectedLevelList(jNannyJobLevel.getNannyId()));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 服务师未选工种
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/level/find/unselected")
    public Object LevelDict(@RequestBody Map map) {
        try {
            Integer nannyId = (Integer) map.get("nannyId");
            String name = (String) map.get("name");
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", JsonUtil.list2map(jNannyJobLevelMapper.getUnselectedLevelList(nannyId, name)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }


}
