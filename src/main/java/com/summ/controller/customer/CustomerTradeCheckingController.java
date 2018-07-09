package com.summ.controller.customer;

import com.summ.Consts;
import com.summ.controller.basic.AutoMapperController;
import com.summ.mapper.JCustomerAlyStatmentMapper;
import com.summ.model.JCustomerAlyStatment;
import com.summ.model.JCustomerStatment;
import com.summ.model.JCustomerWeiXinStatment;
import com.summ.model.JGoodsCost;
import com.summ.model.request.CustomerStatmentReq;
import com.summ.model.response.CustomerStatmentRes;
import com.summ.model.response.ModelRes;
import com.summ.utils.AlipayUtil;
import com.summ.utils.DateUtil;
import com.summ.utils.ResponseUtil;
import com.summ.utils.WeixinUtil;
import com.sun.tools.internal.jxc.ap.Const;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 客户对账(系统对账单和第三方支付账单)
 */
@Controller
@RequestMapping("/customer/trade")
public class CustomerTradeCheckingController extends AutoMapperController {

    /**
     * 查找账单
     * @param customerStatmentReq
     * @return
     */
    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody CustomerStatmentReq customerStatmentReq) {
        try {
            Map map=new HashMap();
            /**微信账单*/
            if (Consts.weixin == customerStatmentReq.getChargeWay()) {
                /**如果数据库中已经存储过支付公司对账单,直接查询*/
                List<JCustomerWeiXinStatment> tempList =  jCustomerWeiXinStatmentMapper.getWeiXinStatmentListByDate(customerStatmentReq);
                if (tempList.size()==0){
                    Map<String,List<JCustomerWeiXinStatment>> utilMap = WeixinUtil.weiXinOrderQuery(DateUtil.parseDateToString(customerStatmentReq.getStartDate(), DateUtil.PATTERN_yyyyMMdd));
                    map.put("jCustomerPayStatmentList",utilMap.get("jCustomerWeiXinStatmentList"));
                }else {
                    map.put("jCustomerPayStatmentList",tempList);
                }
            }
            /**支付宝账单*/
            else if (Consts.zhifubao==customerStatmentReq.getChargeWay()){
                List<JCustomerAlyStatment> tempList = jCustomerAlyStatmentMapper.getAlyStatmentListByDate(customerStatmentReq);
                if (tempList.size()==0){
                    Map<String,List<JCustomerAlyStatment>> utilMap = AlipayUtil.getTradeByDate(DateUtil.parseDateToString(customerStatmentReq.getStartDate(), DateUtil.PATTERN_yyyy_MM_dd));
                    map.put("jCustomerPayStatmentList",utilMap.get("jCustomerAlyStatmentList"));
                }else {
                    map.put("jCustomerPayStatmentList",tempList);
                }
            }
            List<CustomerStatmentRes> jCustomerStatmentList = jCustomerStatmentMapper.getStatmentListByDate(customerStatmentReq);
            map.put("jCustomerStatmentList",jCustomerStatmentList);

            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 对账
     * @param customerStatmentReq
     * @return
     */
    @ResponseBody
    @RequestMapping("/check")
    public Object check(@RequestBody CustomerStatmentReq customerStatmentReq) {
        try {

            /**客户对账单*/
            List<CustomerStatmentRes> jCustomerStatmentList = jCustomerStatmentMapper.getStatmentListByDate(customerStatmentReq);
            /**微信对账*/
            if (Consts.weixin == customerStatmentReq.getChargeWay()) {
                /**如果数据库中已经存储过支付公司对账单,则先逻辑删除对账单,重新从api拉取,重新对账*/
                List<JCustomerWeiXinStatment> tempList =  jCustomerWeiXinStatmentMapper.getWeiXinStatmentListByDate(customerStatmentReq);
                if (tempList.size()>0){
                    for (JCustomerWeiXinStatment jc:tempList){
                        JCustomerWeiXinStatment jCustomerWeiXinStatment = new JCustomerWeiXinStatment(jc.getTradeId(),17);
                        jCustomerWeiXinStatmentMapper.updateById(jCustomerWeiXinStatment);
                    }
                }

                Map<String,List<JCustomerWeiXinStatment>> utilMap = WeixinUtil.weiXinOrderQuery(DateUtil.parseDateToString(customerStatmentReq.getStartDate(), DateUtil.PATTERN_yyyyMMdd));
                List<JCustomerWeiXinStatment> jCustomerWeiXinStatmentList = utilMap.get("jCustomerWeiXinStatmentListForInsert");
                if (jCustomerWeiXinStatmentList.size()>0){
                    for (JCustomerWeiXinStatment jCustomerWeiXinStatment : jCustomerWeiXinStatmentList){
                        /**判断是否有客户对账单*/
                        if (jCustomerStatmentList.size()==0){
                            /**没有则直接返回当前微信对账单对账失败*/
                            jCustomerWeiXinStatment.setChecking(272);
                        }else {
                            /**标记此条微信对账单能否匹配到客户对账单，默认为不能*/
                            int temp=0;
                            /**临时存储客户对账单角标*/
                            List<Integer> indexList = new ArrayList<>();
                            for(int i=0;i<jCustomerStatmentList.size();i++){
                                /**找出相应的系统对账单*/
                                if (jCustomerStatmentList.get(i).getSerialNumber().equals(jCustomerWeiXinStatment.getTransactionId())){
                                    temp++;
                                    indexList.add(i);
                                }
                            }

                            /**此处表示没匹配到客户对账单*/
                            if (temp==0){
                                jCustomerWeiXinStatment.setChecking(272);
                                jCustomerWeiXinStatment.setCheckingDate(new Date());
                            }
                            /**此处表示匹配到多条客户对账单*/
                            if (temp>2){
                                jCustomerWeiXinStatment.setChecking(273);
                                jCustomerWeiXinStatment.setCheckingDate(new Date());
                                for (Integer index : indexList){
                                    JCustomerStatment jCustomerStatment = new JCustomerStatment();
                                    jCustomerStatment.setStatmentId(jCustomerStatmentList.get(index).getStatmentId());
                                    jCustomerStatment.setChecking(273);
                                    jCustomerStatment.setCheckingDate(jCustomerWeiXinStatment.getCheckingDate());
                                    jCustomerStatmentMapper.updateById(jCustomerStatment);
                                }
                            }
                            /**此处表示只匹配到一条客户对账单，且角标为index**/
                            if (temp==1){
                                /**金额不对*/
                                if (jCustomerStatmentList.get(indexList.get(0)).getChargeMoney().compareTo(jCustomerWeiXinStatment.getTotalMoney())!=1){
                                    jCustomerWeiXinStatment.setChecking(275);
                                    jCustomerWeiXinStatment.setCheckingDate(new Date());
                                    jCustomerStatmentList.get(indexList.get(0)).setChecking(275);
                                    JCustomerStatment jCustomerStatment = new JCustomerStatment();
                                    jCustomerStatment.setStatmentId(jCustomerStatmentList.get(indexList.get(0)).getStatmentId());
                                    jCustomerStatment.setChecking(275);
                                    jCustomerStatment.setCheckingDate(jCustomerWeiXinStatment.getCheckingDate());
                                    jCustomerStatmentMapper.updateById(jCustomerStatment);
                                }
                                /**日期不对*/
                                else if (!DateUtil.parseDateToString(jCustomerStatmentList.get(indexList.get(0)).getChargeDate(),DateUtil.PATTERN_yyyyMMdd).equals(DateUtil.parseDateToString(jCustomerWeiXinStatment.getTradeTime(),DateUtil.PATTERN_yyyyMMdd))){
                                    jCustomerWeiXinStatment.setChecking(276);
                                    jCustomerWeiXinStatment.setCheckingDate(new Date());
                                    jCustomerStatmentList.get(indexList.get(0)).setChecking(276);
                                    JCustomerStatment jCustomerStatment = new JCustomerStatment();
                                    jCustomerStatment.setStatmentId(jCustomerStatmentList.get(indexList.get(0)).getStatmentId());
                                    jCustomerStatment.setChecking(276);
                                    jCustomerStatment.setCheckingDate(jCustomerWeiXinStatment.getCheckingDate());
                                    jCustomerStatmentMapper.updateById(jCustomerStatment);
                                }
                                /**对账成功*/
                                else {
                                    jCustomerWeiXinStatment.setChecking(271);
                                    jCustomerWeiXinStatment.setCheckingDate(new Date());

                                    JCustomerStatment jCustomerStatment = new JCustomerStatment();
                                    jCustomerStatment.setStatmentId(jCustomerStatmentList.get(indexList.get(0)).getStatmentId());
                                    jCustomerStatment.setChecking(271);
                                    jCustomerStatment.setCheckingDate(jCustomerWeiXinStatment.getCheckingDate());
                                    jCustomerStatmentMapper.updateById(jCustomerStatment);
                                }

                            }
                        }
                        /**添加微信对账记录*/
                        jCustomerWeiXinStatmentMapper.insert(jCustomerWeiXinStatment);
                    }
                }
            }

            /**支付宝对账*/
            if (Consts.zhifubao == customerStatmentReq.getChargeWay()) {
                /**如果数据库中已经存储过支付公司对账单,则先逻辑删除对账单,重新从api拉取,重新对账*/
                List<JCustomerAlyStatment> tempList =  jCustomerAlyStatmentMapper.getAlyStatmentListByDate(customerStatmentReq);
                if (tempList.size()>0){
                    for (JCustomerAlyStatment jc:tempList){
                        JCustomerAlyStatment jCustomerAlyStatment = new JCustomerAlyStatment(jc.getTradeId(),17);
                        jCustomerAlyStatmentMapper.updateById(jCustomerAlyStatment);
                    }
                }

                Map<String,List<JCustomerAlyStatment>> utilMap = AlipayUtil.getTradeByDate(DateUtil.parseDateToString(customerStatmentReq.getStartDate(), DateUtil.PATTERN_yyyy_MM_dd));
                List<JCustomerAlyStatment> jCustomerAlyStatmentList = utilMap.get("jCustomerAlyStatmentListForInsert");
                if (jCustomerAlyStatmentList.size()>0){
                    for (JCustomerAlyStatment jCustomerAlyStatment : jCustomerAlyStatmentList){
                        /**判断是否有客户对账单*/
                        if (jCustomerStatmentList.size()==0){
                            /**没有则直接返回当前微信对账单对账失败*/
                            jCustomerAlyStatment.setChecking(272);
                        }else {
                            /**标记此条微信对账单能否匹配到客户对账单，默认为不能*/
                            int temp=0;
                            /**临时存储客户对账单角标*/
                            List<Integer> indexList = new ArrayList<>();
                            for(int i=0;i<jCustomerStatmentList.size();i++){
                                /**找出相应的系统对账单*/
                                if (jCustomerStatmentList.get(i).getSerialNumber().equals(jCustomerAlyStatment.getTradeNo())){
                                    temp++;
                                    indexList.add(i);
                                }
                            }

                            /**此处表示没匹配到客户对账单*/
                            if (temp==0){
                                jCustomerAlyStatment.setChecking(272);
                                jCustomerAlyStatment.setCheckingDate(new Date());
                            }
                            /**此处表示匹配到多条客户对账单*/
                            if (temp>2){
                                jCustomerAlyStatment.setChecking(273);
                                jCustomerAlyStatment.setCheckingDate(new Date());
                                for (Integer index : indexList){
                                    JCustomerStatment jCustomerStatment = new JCustomerStatment();
                                    jCustomerStatment.setChecking(273);
                                    jCustomerStatment.setStatmentId(jCustomerStatmentList.get(index).getStatmentId());
                                    jCustomerStatment.setCheckingDate(jCustomerAlyStatment.getCheckingDate());
                                    jCustomerStatmentMapper.updateById(jCustomerStatment);
                                }
                            }
                            /**此处表示只匹配到一条客户对账单，且角标为index**/
                            if (temp==1){
                                /**金额不对*/
                                if (jCustomerStatmentList.get(indexList.get(0)).getChargeMoney().compareTo(jCustomerAlyStatment.getReceiptAmount())!=1){
                                    jCustomerAlyStatment.setChecking(275);
                                    jCustomerAlyStatment.setCheckingDate(new Date());
                                    jCustomerStatmentList.get(indexList.get(0)).setChecking(275);
                                    JCustomerStatment jCustomerStatment = new JCustomerStatment();
                                    jCustomerStatment.setChecking(275);
                                    jCustomerStatment.setStatmentId(jCustomerStatmentList.get(indexList.get(0)).getStatmentId());
                                    jCustomerStatment.setCheckingDate(jCustomerAlyStatment.getCheckingDate());
                                    jCustomerStatmentMapper.updateById(jCustomerStatment);
                                }
                                /**日期不对*/
                                else if (!DateUtil.parseDateToString(jCustomerStatmentList.get(indexList.get(0)).getChargeDate(),DateUtil.PATTERN_yyyyMMdd).equals(DateUtil.parseDateToString(jCustomerAlyStatment.getPaymentTime(),DateUtil.PATTERN_yyyyMMdd))){
                                    jCustomerAlyStatment.setCheckingDate(new Date());
                                    jCustomerAlyStatment.setChecking(276);
                                    jCustomerStatmentList.get(indexList.get(0)).setChecking(276);
                                    JCustomerStatment jCustomerStatment = new JCustomerStatment();
                                    jCustomerStatment.setStatmentId(jCustomerStatmentList.get(indexList.get(0)).getStatmentId());
                                    jCustomerStatment.setChecking(276);
                                    jCustomerStatment.setCheckingDate(jCustomerAlyStatment.getCheckingDate());
                                    jCustomerStatmentMapper.updateById(jCustomerStatment);
                                }
                                /**对账成功*/
                                else {
                                    jCustomerAlyStatment.setChecking(271);
                                    jCustomerAlyStatment.setCheckingDate(new Date());

                                    JCustomerStatment jCustomerStatment = new JCustomerStatment();
                                    jCustomerStatment.setStatmentId(jCustomerStatmentList.get(indexList.get(0)).getStatmentId());
                                    jCustomerStatment.setCheckingDate(jCustomerAlyStatment.getCheckingDate());
                                    jCustomerStatment.setChecking(271);
                                    jCustomerStatmentMapper.updateById(jCustomerStatment);
                                }

                            }
                        }
                        /**添加支付宝对账记录*/
                        jCustomerAlyStatmentMapper.insert(jCustomerAlyStatment);
                    }
                }
            }

            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", null);
        }catch (Exception e){
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }


}
