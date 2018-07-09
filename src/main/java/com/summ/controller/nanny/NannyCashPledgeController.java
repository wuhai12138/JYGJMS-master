package com.summ.controller.nanny;

import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.service.impl.AlipayTradeWithHBServiceImpl;
import com.summ.Consts;
import com.summ.controller.basic.AutoMapperController;
import com.summ.model.JAdmin;
import com.summ.model.JNannyCashPledgeRecord;
import com.summ.model.JNannyInfo;
import com.summ.model.JShop;
import com.summ.model.others.GetWXResult;
import com.summ.model.others.PayCallBackObj;
import com.summ.model.response.ALIPayResult;
import com.summ.model.response.AdminShopRes;
import com.summ.model.response.ModelRes;
import com.summ.model.response.NannyShopRes;
import com.summ.utils.*;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 服务师保证金
 *
 * @author summ
 */
@Controller
@RequestMapping("/cash/pledge")
public class NannyCashPledgeController extends AutoMapperController {

    /**
     * 获取保证金支付门店（服务师所属门店与管理员旗下门店的交集）
     *
     * @param jNannyInfo
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/shop")
    public Object shop(@RequestBody JNannyInfo jNannyInfo, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            List<JShop> shopList = new ArrayList<JShop>();
            List<AdminShopRes> adminShopResList = jAdminShopMapper.getList(jAdmin.getAdminId());
            List<NannyShopRes> nannyShopResList = jNannyInfoMapper.getNannyShop(jNannyInfo.getNannyId());
            for (AdminShopRes adminShopRes : adminShopResList) {
                for (NannyShopRes nannyShopRes : nannyShopResList) {
                    if (adminShopRes.getShopId().equals(nannyShopRes.getShopId())) {
                        JShop jShop = new JShop(adminShopRes.getShopId(), adminShopRes.getShopName());
                        shopList.add(jShop);
                    }
                }
            }
            if (shopList.size() == 0) {
                return new ModelRes(ModelRes.Status.FAILED, "无门店！");
            }
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功", ResponseUtil.List2Map(shopList));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 添加保证金记录
     *
     * @param jNannyCashPledgeRecord
     * @return
     */
    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody JNannyCashPledgeRecord jNannyCashPledgeRecord) {
        try {
            JNannyInfo jNannyInfo = jNannyInfoMapper.selectById(Long.valueOf(jNannyCashPledgeRecord.getNannyId()));
            jNannyCashPledgeRecord.setCashPledgeBalance(jNannyInfo.getNannyCashPledge().subtract(jNannyCashPledgeRecord.getMoney()));
            jNannyInfo.setNannyCashPledge(jNannyInfo.getNannyCashPledge().subtract(jNannyCashPledgeRecord.getMoney()));
            jNannyCashPledgeRecord.setPaymentType(198);

            jNannyInfoMapper.updateById(jNannyInfo);
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功", jNannyCashPledgeRecordMapper.insert(jNannyCashPledgeRecord));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 缴纳保证金
     *
     * @param jNannyCashPledgeRecord
     * @return
     */
    @ResponseBody
    @RequestMapping("/pay")
    public Object pay(@RequestBody JNannyCashPledgeRecord jNannyCashPledgeRecord, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            JNannyInfo jNannyInfo = jNannyInfoMapper.selectById(Long.valueOf(jNannyCashPledgeRecord.getNannyId()));
            JShop jShop = jShopMapper.selectById(Long.valueOf(jNannyCashPledgeRecord.getShopId()));
            /**现金支付和刷卡支付*/
            if (jNannyCashPledgeRecord.getPaymentType() == 194 || jNannyCashPledgeRecord.getPaymentType() == 197) {

                jNannyInfo.setNannyCashPledge(jNannyInfo.getNannyCashPledge().add(jNannyCashPledgeRecord.getMoney()));
                jNannyInfoMapper.updateById(jNannyInfo);

                jNannyCashPledgeRecord.setCashPledgeBalance(jNannyInfo.getNannyCashPledge());
                jNannyCashPledgeRecord.setStatus(53);
                /**操作方式为缴纳保证金*/
                jNannyCashPledgeRecord.setOperateType(199);
                jNannyCashPledgeRecord.setSystemSerialNumber(OrderUtil.generateStamentNumber(jNannyCashPledgeRecord.getNannyId()));
                jNannyCashPledgeRecordMapper.insert(jNannyCashPledgeRecord);
            } else {
                /**支付服务器所需信息*/
                PayCallBackObj payCallBackObj = new PayCallBackObj(jNannyInfo.getNannyId(), jNannyInfo.getNannyName(), jNannyInfo.getNannyPhone(), jAdmin.getAdminId(), jShop.getShopName(), jShop.getShopId());

                /**添加保证金缴纳记录，状态为待支付，等待回调*/
                /**状态为待支付，等待支付宝回调*/
                jNannyCashPledgeRecord.setStatus(54);
                /**操作方式为缴纳保证金*/
                jNannyCashPledgeRecord.setOperateType(199);
                jNannyCashPledgeRecord.setSystemSerialNumber(OrderUtil.generateStamentNumber(jNannyCashPledgeRecord.getNannyId()));
                jNannyCashPledgeRecordMapper.insert(jNannyCashPledgeRecord);

                /**支付宝*/
                if (jNannyCashPledgeRecord.getPaymentType() == 195) {

                    Configs.init("zfbinfo.properties");
                    AlipayUtil.tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();
                    AlipayUtil.tradeWithHBService = new AlipayTradeWithHBServiceImpl.ClientBuilder().build();
                    AlipayF2FPrecreateResult result = AlipayUtil.test_trade_precreate("家有管家--扫码充值", jNannyCashPledgeRecord.getSystemSerialNumber(), jNannyCashPledgeRecord.getMoney().toString(), payCallBackObj, Consts.testServerUrl + "/cash/pledge/aliPay/callback");
                    switch (result.getTradeStatus()) {
                        case SUCCESS:
                            System.out.println("支付宝预下单成功! ");
                            AlipayTradePrecreateResponse response = result.getResponse();

                            return new ModelRes(ModelRes.Status.SUCCESS, "success !", response.getQrCode());

                        case FAILED:
                            System.out.println("支付宝预下单失败!!!");
                            return new ModelRes(ModelRes.Status.FAILED, "支付宝预下单失败 !", null);

                        case UNKNOWN:
                            System.out.println("系统异常，预下单状态未知!!!");
                            return new ModelRes(ModelRes.Status.FAILED, " 系统异常，预下单状态未知!", null);

                        default:
                            System.out.println("不支持的交易状态，交易返回异常!!!");
                            return new ModelRes(ModelRes.Status.FAILED, "不支持的交易状态，交易返回异常 !", null);
                    }

                }
                /**微信*/
                if (jNannyCashPledgeRecord.getPaymentType() == 196) {
                    return WeixinUtil.weiXinQrcodePay(jNannyCashPledgeRecord.getSystemSerialNumber(), jNannyCashPledgeRecord.getMoney().toString(), Consts.testServerUrl + "/cash/pledge/WeiXin/callback", payCallBackObj, "家有官家--扫码充值");
                }
            }

            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody Map map) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功", ResponseUtil.List2Map(jNannyCashPledgeRecordMapper.getNannyCashPledgeList((Integer) map.get("nannyId"))));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 支付宝充值回调
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/aliPay/callback", method = RequestMethod.POST)
    public String AliPayResult(HttpServletRequest request) throws Exception {
        Map requestParams = request.getParameterMap();
        System.out.println("Aly Callback data Map:" + JsonUtil.toJson(requestParams));

        ALIPayResult aliPayResult = JsonUtil.json2Bean(JsonUtil.toJson(requestParams), ALIPayResult.class);

        if (request.getParameter("trade_status") != null && request.getParameter("trade_status").equals("TRADE_SUCCESS")) {

            //通过系统订单号找到该对账单
            Map map = new HashedMap();
            map.put("systemSerialNumber", aliPayResult.getOut_trade_no().get(0));
            JNannyCashPledgeRecord jNannyCashPledgeRecord = (JNannyCashPledgeRecord) jNannyCashPledgeRecordMapper.selectByMap(map).get(0);

            JNannyInfo jNannyInfo = jNannyInfoMapper.selectById(Long.valueOf(jNannyCashPledgeRecord.getNannyId()));
            jNannyInfo.setNannyCashPledge(jNannyInfo.getNannyCashPledge().add(new BigDecimal(aliPayResult.getBuyer_pay_amount().get(0))));
            jNannyInfoMapper.updateById(jNannyInfo);

            jNannyCashPledgeRecord.setCashPledgeBalance(jNannyInfo.getNannyCashPledge());
            jNannyCashPledgeRecord.setStatus(53);
            jNannyCashPledgeRecordMapper.updateById(jNannyCashPledgeRecord);
            return "PayResult";
        } else {
            return "error";
        }
    }

    /**
     * 微信充值回调
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/WeiXin/callback", method = RequestMethod.POST)
    public String WeiXinResult(HttpServletRequest request) throws Exception {

        String inputLine;
        StringBuffer notifyXml = new StringBuffer();
        try {
            while ((inputLine = request.getReader().readLine()) != null) {
                notifyXml.append(inputLine);
                System.out.println(" ");
            }
            request.getReader().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("notifyXml:" + notifyXml.toString());
        GetWXResult get = (GetWXResult) JsonUtil.getObjectFromXML(notifyXml.toString(), GetWXResult.class);
        System.out.println("notifyObj:" + get.toString());
        if (get.getResult_code().equals("SUCCESS")) {

            //通过系统订单号找到该对账单
            Map map = new HashedMap();
            map.put("systemSerialNumber", get.getOut_trade_no());
            JNannyCashPledgeRecord jNannyCashPledgeRecord = (JNannyCashPledgeRecord) jNannyCashPledgeRecordMapper.selectByMap(map).get(0);

            JNannyInfo jNannyInfo = jNannyInfoMapper.selectById(Long.valueOf(jNannyCashPledgeRecord.getNannyId()));
            jNannyInfo.setNannyCashPledge(jNannyInfo.getNannyCashPledge().add(new BigDecimal(Double.valueOf(get.getTotal_fee()) / 100)));
            jNannyInfoMapper.updateById(jNannyInfo);

            jNannyCashPledgeRecord.setCashPledgeBalance(jNannyInfo.getNannyCashPledge());
            jNannyCashPledgeRecord.setStatus(53);
            jNannyCashPledgeRecordMapper.updateById(jNannyCashPledgeRecord);
            return "PayResult";

        } else {
            return "error";
        }
    }

}
