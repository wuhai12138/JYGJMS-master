package com.summ.controller.nanny;

import com.summ.controller.basic.AutoMapperController;
import com.summ.model.JNannyBankInfo;
import com.summ.model.JNannyInfo;
import com.summ.model.others.ShowApiRes;
import com.summ.model.response.ModelRes;
import com.summ.utils.AliUtil;
import com.summ.utils.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务师工资卡信息验证
 * @author summ
 */
@Controller
@RequestMapping("/nanny")
public class NannyBankSignInfoController extends AutoMapperController{

    /**
     * 四联验证/手机号 身份证 姓名 银行卡
     * @param jNannyInfo
     * @return
     */
    @ResponseBody
    @RequestMapping("/sign")
    public Object find(@RequestBody JNannyInfo jNannyInfo) {
        try {
            /**判断四要素是否已经验证过*/
            Map map = new HashMap();
            map.put("nannyPhone",jNannyInfo.getNannyPhone());
            map.put("nannyIdCard",jNannyInfo.getNannyIdCard());
            map.put("nannyName",jNannyInfo.getNannyName());
            map.put("salaryCard",jNannyInfo.getSalaryCard());
            /**先查找数据库中是否有历史验证记录*/
            List<JNannyBankInfo> list = jNannyBankInfoMapper.selectByMap(map);
            if (list.size()>0){
                /**判断验证状态 0表示验证通过*/
                if (list.get(0).getCode()==0){
                    System.out.println(list.get(0).getCodeMsg());
                    jNannyInfo.setDataSignStatus(255);
                    jNannyInfoMapper.updateById(jNannyInfo);
                    return new ModelRes(ModelRes.Status.SUCCESS, list.get(0).getCodeMsg());
                }else {
                    /**其他表示验证未通过,返回错误信息*/
                    System.out.println(list.get(0).getCodeMsg());
                    return new ModelRes(ModelRes.Status.FAILED, list.get(0).getCodeMsg());
                }
            }

            /**四联验证*/
            String string = AliUtil.signFourPoint(jNannyInfo.getNannyName().toString(),jNannyInfo.getSalaryCard().toString(),jNannyInfo.getNannyIdCard().toString(),jNannyInfo.getNannyPhone().toString());
//            String string = "{\"showapi_res_code\":0,\"showapi_res_error\":\"\",\"showapi_res_body\":{\"ret_code\":0,\"belong\":{\"area\":\"江西省 - 南昌\",\"tel\":\"95533\",\"brand\":\"龙卡储蓄卡(银联卡)\",\"bankName\":\"建设银行\",\"cardType\":\"借记卡\",\"url\":\"www.ccb.com\",\"cardNum\":\"6227002022071179506\"},\"code\":5,\"msg\":\"认证不通过\"}}";
            ShowApiRes showApiRes = JsonUtil.json2Bean(string,ShowApiRes.class);

            ShowApiRes.ShowapiResBodyBean.BelongBean belongBean = showApiRes.getShowapi_res_body().getBelong();
            JNannyBankInfo jNannyBankInfo = new JNannyBankInfo(jNannyInfo.getNannyId(),jNannyInfo.getNannyName(),jNannyInfo.getNannyIdCard(),jNannyInfo.getNannyPhone(),jNannyInfo.getSalaryCard(),
                    belongBean.getArea(),belongBean.getTel(),belongBean.getBrand(),
                    belongBean.getBankName(),belongBean.getCardType(),belongBean.getUrl(),
                    belongBean.getCardNum(),string);
            /**判断认证状态*/
            switch (showApiRes.getShowapi_res_body().getCode()){
                case 0:
                    jNannyBankInfo.setCode(0);
                    jNannyBankInfo.setCodeMsg("认证成功");
                    jNannyInfo.setDataSignStatus(255);
                    jNannyInfoMapper.updateById(jNannyInfo);
                    return new ModelRes(ModelRes.Status.SUCCESS, "认证成功", jNannyBankInfoMapper.insert(jNannyBankInfo));
                case 4:
                    jNannyBankInfo.setCode(4);
                    jNannyBankInfo.setCodeMsg("此卡被没收,请于发卡方联系");
                    return new ModelRes(ModelRes.Status.FAILED, "此卡被没收,请于发卡方联系",  jNannyBankInfoMapper.insert(jNannyBankInfo));
                case 5:
                    jNannyBankInfo.setCode(5);
                    jNannyBankInfo.setCodeMsg("持卡人认证失败");
                    return new ModelRes(ModelRes.Status.FAILED, "持卡人认证失败", jNannyBankInfoMapper.insert(jNannyBankInfo));
                case 14:
                    jNannyBankInfo.setCode(14);
                    jNannyBankInfo.setCodeMsg("无效卡号");
                    return new ModelRes(ModelRes.Status.FAILED, "无效卡号", jNannyBankInfoMapper.insert(jNannyBankInfo));
                case 15:
                    jNannyBankInfo.setCode(15);
                    jNannyBankInfo.setCodeMsg("此卡无对应发卡方");
                    return new ModelRes(ModelRes.Status.FAILED, "此卡无对应发卡方", jNannyBankInfoMapper.insert(jNannyBankInfo));
                case 21:
                    jNannyBankInfo.setCode(21);
                    jNannyBankInfo.setCodeMsg("该卡未初始化或睡眠卡");
                    return new ModelRes(ModelRes.Status.FAILED, "该卡未初始化或睡眠卡", jNannyBankInfoMapper.insert(jNannyBankInfo));
                case 34:
                    jNannyBankInfo.setCode(34);
                    jNannyBankInfo.setCodeMsg("作弊卡,吞卡");
                    return new ModelRes(ModelRes.Status.FAILED, "作弊卡,吞卡", jNannyBankInfoMapper.insert(jNannyBankInfo));
                case 40:
                    jNannyBankInfo.setCode(40);
                    jNannyBankInfo.setCodeMsg("发卡方不支持的交易");
                    return new ModelRes(ModelRes.Status.FAILED, "发卡方不支持的交易", jNannyBankInfoMapper.insert(jNannyBankInfo));
                case 41:
                    jNannyBankInfo.setCode(41);
                    jNannyBankInfo.setCodeMsg("此卡已经挂失");
                    return new ModelRes(ModelRes.Status.FAILED, "此卡已经挂失", jNannyBankInfoMapper.insert(jNannyBankInfo));
                case 43:
                    jNannyBankInfo.setCode(43);
                    jNannyBankInfo.setCodeMsg("此卡被没收");
                    return new ModelRes(ModelRes.Status.FAILED, "此卡被没收", jNannyBankInfoMapper.insert(jNannyBankInfo));
                case 54:
                    jNannyBankInfo.setCode(54);
                    jNannyBankInfo.setCodeMsg("该卡已过期");
                    return new ModelRes(ModelRes.Status.FAILED, "该卡已过期", jNannyBankInfoMapper.insert(jNannyBankInfo));
                case 57:
                    jNannyBankInfo.setCode(57);
                    jNannyBankInfo.setCodeMsg("发卡方不允许此交易");
                    return new ModelRes(ModelRes.Status.FAILED, "发卡方不允许此交易 ", jNannyBankInfoMapper.insert(jNannyBankInfo));
                case 62:
                    jNannyBankInfo.setCode(62);
                    jNannyBankInfo.setCodeMsg("受限制的卡");
                    return new ModelRes(ModelRes.Status.FAILED, "受限制的卡", jNannyBankInfoMapper.insert(jNannyBankInfo));
                case 75:
                    jNannyBankInfo.setCode(75);
                    jNannyBankInfo.setCodeMsg("密码错误次数超限");
                    return new ModelRes(ModelRes.Status.FAILED, "密码错误次数超限", jNannyBankInfoMapper.insert(jNannyBankInfo));
                case 83:
                    jNannyBankInfo.setCode(83);
                    jNannyBankInfo.setCodeMsg("银行卡号码有误");
                    return new ModelRes(ModelRes.Status.FAILED, "银行卡号码有误", jNannyBankInfoMapper.insert(jNannyBankInfo));
                case 84:
                    jNannyBankInfo.setCode(84);
                    jNannyBankInfo.setCodeMsg("手机号码不合法");
                    return new ModelRes(ModelRes.Status.FAILED, "手机号码不合法", jNannyBankInfoMapper.insert(jNannyBankInfo));
                case 86:
                    jNannyBankInfo.setCode(86);
                    jNannyBankInfo.setCodeMsg("持卡人信息有误");
                    return new ModelRes(ModelRes.Status.FAILED, "持卡人信息有误", jNannyBankInfoMapper.insert(jNannyBankInfo));
                case 87:
                    jNannyBankInfo.setCode(87);
                    jNannyBankInfo.setCodeMsg("未开通无卡支付");
                    return new ModelRes(ModelRes.Status.FAILED, "未开通无卡支付", jNannyBankInfoMapper.insert(jNannyBankInfo));
                default:
                    jNannyBankInfo.setCode(100);
                    jNannyBankInfo.setCodeMsg("交易失败请重试");
                    return new ModelRes(ModelRes.Status.FAILED, "交易失败请重试", jNannyBankInfoMapper.insert(jNannyBankInfo));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
}
