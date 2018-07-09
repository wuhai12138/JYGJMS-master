package com.summ.controller.nanny;

import com.summ.controller.basic.AutoMapperController;
import com.summ.model.*;
import com.summ.model.response.*;
import com.summ.utils.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务师其他信息
 * @author summ
 * @date 17/12/18
 */
@Controller
@RequestMapping("/nanny/other")
public class NannyOtherController extends AutoMapperController {

    @ResponseBody
    @RequestMapping("/find")
    public Object findOtherData(@RequestBody JNannyInfo jNannyInfo) {
        try {
            int nannyId = jNannyInfo.getNannyId();
            NannyInfoRes nannyInfoRes = jNannyInfoMapper.getNannyOther(nannyId);
            //判断nannyInfoRes是否有数据，没有数据则该对象为空，需重新new。
            if (nannyInfoRes == null) {
                NannyInfoRes nannyInfoRes1 = new NannyInfoRes();
                //宗教信息
                List<NannyReligionRes> nannyReligionRes = jNannyInfoMapper.getNannyReligion(nannyId);
                if (nannyReligionRes.size() > 0) {
                    nannyInfoRes1.setNannyReligionRes(nannyReligionRes);
                }

                //语言信息
                List<NannyLanguageRes> nannyLanguageRes = jNannyInfoMapper.getNannyLanguage(nannyId);
                if (nannyLanguageRes.size() > 0) {
                    nannyInfoRes1.setNannyLanguageRes(nannyLanguageRes);
                }

                //特长信息
                List<NannySkillRes> nannySkillRes = jNannyInfoMapper.getNannySkill(nannyId);
                if (nannySkillRes.size() > 0) {
                    nannyInfoRes1.setNannySkillRes(nannySkillRes);
                }

                //性格信息
                List<NannyCharacterRes> nannyCharacterRes = jNannyInfoMapper.getNannyCharacter(nannyId);
                if (nannyCharacterRes.size() > 0) {
                    nannyInfoRes1.setNannyCharacterRes(nannyCharacterRes);
                }

                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", nannyInfoRes1);
            }
            //宗教信息
            List<NannyReligionRes> nannyReligionRes = jNannyInfoMapper.getNannyReligion(nannyId);
            if (nannyReligionRes.size() > 0) {
                nannyInfoRes.setNannyReligionRes(nannyReligionRes);
            }

            //语言信息
            List<NannyLanguageRes> nannyLanguageRes = jNannyInfoMapper.getNannyLanguage(nannyId);
            if (nannyLanguageRes.size() > 0) {
                nannyInfoRes.setNannyLanguageRes(nannyLanguageRes);
            }

            //特长信息
            List<NannySkillRes> nannySkillRes = jNannyInfoMapper.getNannySkill(nannyId);
            if (nannySkillRes.size() > 0) {
                nannyInfoRes.setNannySkillRes(nannySkillRes);
            }

            //性格信息
            List<NannyCharacterRes> nannyCharacterRes = jNannyInfoMapper.getNannyCharacter(nannyId);
            if (nannyCharacterRes.size() > 0) {
                nannyInfoRes.setNannyCharacterRes(nannyCharacterRes);
            }

            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", nannyInfoRes);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object updateJobData(@RequestBody JNannyInfo jNannyInfo) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyInfoMapper.updateById(jNannyInfo));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 宗教信息
     * CRUD for nanny religion
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/religion/insert")
    public Object insertReligion(@RequestBody Map map) {
        try {
            Integer languageId = (Integer) map.get("id");
            if (languageId == 0) {
                //新增数据字典
                JDictInfo jDictInfo = new JDictInfo();
                jDictInfo.setTypecode(18);
                jDictInfo.setInfo((String) map.get("info"));
                jDictInfoMapper.insert(jDictInfo);

                //插入服务师技能表
                JNannyReligion jNannyReligion = new JNannyReligion();
                jNannyReligion.setNannyId((Integer) map.get("nannyId"));
                jNannyReligion.setReligionId(jDictInfo.getId());
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyReligionMapper.insert(jNannyReligion));
            } else {
                JNannyReligion jNannyReligion = new JNannyReligion();
                jNannyReligion.setNannyId((Integer) map.get("nannyId"));
                jNannyReligion.setReligionId((Integer) map.get("id"));
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyReligionMapper.insert(jNannyReligion));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/religion/delete")
    public Object deleteReligion(@RequestBody Map map) {
        try {
            Map map1 = new HashMap();
            map1.put("nannyReligId", map.get("id"));
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyReligionMapper.deleteByMap(map1));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/religion/find/selected")
    public Object findReligion(@RequestBody JNannyReligion jNannyReligion) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyReligionMapper.getSelectedReligion(jNannyReligion.getNannyId()));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/religion/find/unselected")
    public Object religionDict(@RequestBody Map map) {
        try {
            Integer nannyId = (Integer) map.get("nannyId");
            String name = (String) map.get("name");

            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", JsonUtil.list2map(jNannyReligionMapper.getUnselectedReligion(nannyId, name)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 语言
     * CRUD for nanny language
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/language/insert")
    public Object insertLanguage(@RequestBody Map map) {
        try {
            Integer languageId = (Integer) map.get("id");
            if (languageId == 0) {
                //新增数据字典
                JDictInfo jDictInfo = new JDictInfo();
                jDictInfo.setTypecode(17);
                jDictInfo.setInfo((String) map.get("info"));
                jDictInfoMapper.insert(jDictInfo);

                //插入服务师技能表
                JNannyLanguage jNannyLanguage = new JNannyLanguage();
                jNannyLanguage.setNannyId((Integer) map.get("nannyId"));
                jNannyLanguage.setLanguageId(jDictInfo.getId());
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyLanguageMapper.insert(jNannyLanguage));
            } else {
                JNannyLanguage jNannyLanguage = new JNannyLanguage();
                jNannyLanguage.setNannyId((Integer) map.get("nannyId"));
                jNannyLanguage.setLanguageId((Integer) map.get("id"));
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyLanguageMapper.insert(jNannyLanguage));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/language/delete")
    public Object deleteLanguage(@RequestBody Map map) {
        try {
            Map map1 = new HashMap();
            map1.put("nannyLangId", map.get("id"));
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyLanguageMapper.deleteByMap(map1));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/language/find/selected")
    public Object findLanguage(@RequestBody JNannyLanguage jNannyLanguage) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyLanguageMapper.getSelectedLanguage(jNannyLanguage.getNannyId()));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/language/find/unselected")
    public Object languageDict(@RequestBody Map map) {
        try {
            Integer nannyId = (Integer) map.get("nannyId");
            String name = (String) map.get("name");
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", JsonUtil.list2map(jNannyLanguageMapper.getUnselectedLanguage(nannyId, name)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     *
     * 技能
     * CRUD for nanny skill
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/skill/insert")
    public Object insertSkill(@RequestBody Map map) {
        try {
            Integer skillId = (Integer) map.get("id");
            if (skillId == 0) {
                //新增数据字典
                JDictInfo jDictInfo = new JDictInfo();
                jDictInfo.setTypecode(19);
                if (map.get("info") == null) {
                    return new ModelRes(ModelRes.Status.FAILED, "info is null !", null);
                }
                jDictInfo.setInfo((String) map.get("info"));
                jDictInfoMapper.insert(jDictInfo);

                //插入服务师技能表
                JNannySkill jNannySkill = new JNannySkill();
                jNannySkill.setNannyId((Integer) map.get("nannyId"));
                jNannySkill.setSkillId(jDictInfo.getId());
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannySkillMapper.insert(jNannySkill));
            } else {
                JNannySkill jNannySkill = new JNannySkill();
                jNannySkill.setNannyId((Integer) map.get("nannyId"));
                jNannySkill.setSkillId((Integer) map.get("id"));
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannySkillMapper.insert(jNannySkill));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/skill/delete")
    public Object deleteSkill(@RequestBody Map map) {
        try {
            Map map1 = new HashMap();
            map1.put("nannySkillId", map.get("id"));
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannySkillMapper.deleteByMap(map1));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/skill/find/selected")
    public Object findSkill(@RequestBody JNannySkill jNannySkill) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannySkillMapper.getSelectedSkill(jNannySkill.getNannyId()));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/skill/find/unselected")
    public Object skillDict(@RequestBody Map map) {
        try {
            Integer nannyId = (Integer) map.get("nannyId");
            String name = (String) map.get("name");
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", JsonUtil.list2map(jNannySkillMapper.getUnselectedSkill(nannyId, name)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 性格
     * CRUD for nanny character
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/character/insert")
    public Object insertCharacter(@RequestBody Map map) {
        try {
            Integer languageId = (Integer) map.get("id");
            if (languageId == 0) {
                //新增数据字典
                JDictInfo jDictInfo = new JDictInfo();
                jDictInfo.setTypecode(20);
                jDictInfo.setInfo((String) map.get("info"));
                jDictInfoMapper.insert(jDictInfo);

                //插入服务师技能表
                JNannyCharacter jNannyCharacter = new JNannyCharacter();
                jNannyCharacter.setNannyId((Integer) map.get("nannyId"));
                jNannyCharacter.setCharacterId(jDictInfo.getId());
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyCharacterMapper.insert(jNannyCharacter));
            } else {
                JNannyCharacter jNannyCharacter = new JNannyCharacter();
                jNannyCharacter.setNannyId((Integer) map.get("nannyId"));
                jNannyCharacter.setCharacterId((Integer) map.get("id"));
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyCharacterMapper.insert(jNannyCharacter));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/character/delete")
    public Object deleteCharacter(@RequestBody Map map) {
        try {
            Map map1 = new HashMap();
            map1.put("nannyChaId", map.get("id"));
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyCharacterMapper.deleteByMap(map1));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/character/find/selected")
    public Object findCharacter(@RequestBody JNannyCharacter jNannyCharacter) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jNannyCharacterMapper.getSelectedCharacter(jNannyCharacter.getNannyId()));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/character/find/unselected")
    public Object characterDict(@RequestBody Map map) {
        try {
            Integer nannyId = (Integer) map.get("nannyId");
            String name = (String) map.get("name");
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", JsonUtil.list2map(jNannyCharacterMapper.getUnselectedCharacter(nannyId, name)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

}
