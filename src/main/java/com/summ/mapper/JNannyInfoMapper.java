package com.summ.mapper;

import com.summ.model.*;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.request.NannyInfoReq;
import com.summ.model.response.*;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.Na;

import java.util.List;
import java.util.Map;

/**
 *
 * JNannyInfo 表数据库控制层接口
 *
 * @author summ
 */
public interface JNannyInfoMapper extends BaseMapper<JNannyInfo> {

    /**
     * 获取条件下服务师总数
     * @param nannyInfoReq
     * @return
     */
    Integer getCount(@Param("nannyInfoReq")NannyInfoReq nannyInfoReq);

    /**
     * 获取条件下服务师列表
     * @param nannyInfoReq
     * @return
     */
    List<NannyListRes> getNannyInfoList(@Param("nannyInfoReq")NannyInfoReq nannyInfoReq);

    /**
     * 获取服务师基本信息
     * @param id
     * @return
     */
    NannyInfoRes getNannyBasic(int id);

    /**
     * 更新服务师基本信息
     * @param jNannyInfo
     * @return
     */
    Integer updateNannyBasic(@Param("jNannyInfo") JNannyInfo jNannyInfo);
    /**
     * 获取服务师其他信息
     * @param id
     * @return
     */
    NannyInfoRes getNannyOther(int id);

    /**
     * 获取服务师工作信息
     * @param id
     * @return
     */
    NannyInfoRes getJobData(int id);

    /**
     * 服务师门店
     * @param id
     * @return
     */
    List<NannyShopRes> getNannyShop(int id);

    /**
     * 服务师工种
     * @param id
     * @return
     */
    List<NannyJobLevelRes> getNannyJobLevel(int id);

    /**
     * 服务师宗教
     * @param id
     * @return
     */
    List<NannyReligionRes> getNannyReligion(int id);

    /**
     * 服务师证书
     * @param id
     * @return
     */
    List<NannyCertificateRes> getNannyCertificate(int id);

    /**
     * 服务师语言
     * @param id
     * @return
     */
    List<NannyLanguageRes> getNannyLanguage(int id);

    /**
     * 服务师性格
     * @param id
     * @return
     */
    List<NannyCharacterRes> getNannyCharacter(int id);

    /**
     * 服务师特长
     * @param id
     * @return
     */
    List<NannySkillRes> getNannySkill(int id);

    List<OrderTempNannyRes> getOrderTempNannyList(@Param("map") Map map);

}