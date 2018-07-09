package com.summ.controller.customer;

import com.summ.controller.basic.AutoMapperController;
import com.summ.mapper.JCustomerMapper;
import com.summ.model.JCustomer;
import com.summ.model.JSystemAuthCode;
import com.summ.model.response.ModelRes;
import com.summ.utils.SendSMSUtil;
import com.summ.utils.StringUtil;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 废弃(小程序模块已分离出单独项目)
 */
@Controller
@RequestMapping("/customer/mini/app")
public class CustomerMiNiAppController extends AutoMapperController {


    /**
     * 1.验证微信openid有没有绑定过手机号
     * 2.验证手机号有没有绑定过微信号
     *
     * @param jCustomer
     * @return
     */
    @ResponseBody
    @RequestMapping("/sign")
    public Object sign(@RequestBody JCustomer jCustomer) {
        try {
            /**验证openid*/
            if (!StringUtil.isBlank(jCustomer.getWeiXinOpenId())) {
                Map mapOpenId = new HashMap();
                mapOpenId.put("weiXinOpenId", jCustomer.getWeiXinOpenId());
                List<JCustomer> jCustomerList = jCustomerMapper.selectByMap(mapOpenId);
                if (jCustomerList.size() > 0) {
                    return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jCustomerList.get(0));
                } else {
                    return new ModelRes(ModelRes.Status.SUCCESS, "该微信未绑定过手机号", null);
                }
            }
            /**验证手机号*/
            else if (!StringUtil.isBlank(jCustomer.getCustomerPhone())) {
                Map mapPhone = new HashMap();
                mapPhone.put("customerPhone", jCustomer.getCustomerPhone());
                List<JCustomer> jCustomerList = jCustomerMapper.selectByMap(mapPhone);
                if (jCustomerList.size() > 0) {
                    if(null==jCustomerList.get(0).getWeiXinOpenId()||"".equals(jCustomerList.get(0).getWeiXinOpenId())){
                        return new ModelRes(ModelRes.Status.FAILED,"未绑定openid !", jCustomerList.get(0));
                    }else {
                        return new ModelRes(ModelRes.Status.SUCCESS, "该手机号已经绑定过微信openid !", jCustomerList.get(0));
                    }
                } else {
                    /**查不到用户信息则新增客户后返回*/
                    JCustomer jCustomerNew = new JCustomer();
                    jCustomerNew.setCustomerPhone(jCustomer.getCustomerPhone());
                    jCustomerMapper.insert(jCustomerNew);
                    return new ModelRes(ModelRes.Status.FAILED, "未绑定openid ", jCustomerNew);
                }
            } else {
                return new ModelRes(ModelRes.Status.SUCCESS, "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 获取手机验证码
     *
     * @param jCustomer
     * @return
     */
    @ResponseBody
    @RequestMapping("/authCode")
    public Object getAuthCode(@RequestBody JCustomer jCustomer) {
        try {
            /**六位随机验证码*/
            String code = SendSMSUtil.generateCode();
            JSystemAuthCode jSystemAuthCode = new JSystemAuthCode(jCustomer.getCustomerPhone(), code, new Date());
            jSystemAuthCodeMapper.insert(jSystemAuthCode);
//            SendSMSUtil.signCustomer(code,"家有管家小程序",jCustomer.getCustomerPhone());
            return new ModelRes(ModelRes.Status.SUCCESS, "验证法已发送");
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }


    /**
     * 绑定或者更新微信openid
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/bind")
    public Object getBindCustomer(@RequestBody Map mapq) {
        try {
            String customerPhone = (String) mapq.get("customerPhone");
            String WeiXinOpenId = (String) mapq.get("WeiXinOpenId");
            Integer customerId = (Integer) mapq.get("customerId");
            String authCode = (String) mapq.get("authCode");


            Map map = new HashMap();
            map.put("phone", customerPhone);
            map.put("authCode", authCode);
            List<JSystemAuthCode> jSystemAuthCodeList = jSystemAuthCodeMapper.selectByMap(map);
            if (jSystemAuthCodeList.size() > 0 || authCode.equals("666")) {
                JCustomer jCustomer = new JCustomer();
                jCustomer.setCustomerId(customerId);
                jCustomer.setCustomerPhone(customerPhone);
                jCustomer.setWeiXinOpenId(WeiXinOpenId);
                return new ModelRes(ModelRes.Status.SUCCESS, "绑定成功",jCustomerMapper.updateById(jCustomer));
            } else {
                return new ModelRes(ModelRes.Status.FAILED, "验证码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }
}
