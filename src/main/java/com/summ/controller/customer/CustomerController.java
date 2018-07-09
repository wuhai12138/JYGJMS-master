package com.summ.controller.customer;

import com.alibaba.druid.support.json.JSONUtils;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.service.impl.AlipayTradeWithHBServiceImpl;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.summ.Consts;
import com.summ.controller.basic.AutoMapperController;
import com.summ.model.*;
import com.summ.model.others.GetWXResult;
import com.summ.model.others.PayCallBackObj;
import com.summ.model.others.WeiXinSign;
import com.summ.model.request.CustomerPagReq;
import com.summ.model.request.CustomerReq;
import com.summ.model.response.ALIPayResult;
import com.summ.model.response.ModelRes;
import com.summ.model.response.ShopRes;
import com.summ.utils.*;
import com.summ.utils.mongodb.MongoDBUtil;
import com.summ.utils.mongodb.model.MongoConfig;
import com.sun.imageio.plugins.jpeg.JPEG;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 客户基本信息
 * @author summ
 * @date 17/11/20
 */
@Controller
@RequestMapping("/customer")
public class CustomerController extends AutoMapperController {
    /**
     * CRUD for customer
     *
     * @param customerReq
     * @return
     */

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody CustomerReq customerReq, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");

            /***根据地址获取经纬度*/
            Map<String, Double> tencentMap = TencentMapUtil.getLngAndLat("上海市" + customerReq.getHouseAddress());
            if (tencentMap == null) {
                return new ModelRes(ModelRes.Status.FAILED, "获取不到该地址，请重新输入 !");
            }
            Map map = new HashMap();
            map.put("customerPhone",customerReq.getCustomerPhone());
            List<JCustomer> jCustomerList=jCustomerMapper.selectByMap(map);
            if (jCustomerList.size()>0){
                return new ModelRes(ModelRes.Status.FAILED, "重复手机号 !",ResponseUtil.List2Map(jCustomerList));
            }

            /**客户基本信息*/
            JCustomer jCustomer = new JCustomer();
            jCustomer.setCustomerName(customerReq.getCustomerName());
            jCustomer.setCustomerSex(customerReq.getCustomerSex());
            jCustomer.setCustomerPhone(customerReq.getCustomerPhone());
            jCustomer.setRemark(customerReq.getRemark());
            jCustomer.setCreateId(jAdmin.getAdminId());
            jCustomer.setCreateTime(new Date());
            jCustomer.setMemberOrigin(customerReq.getMemberOrigin());
            jCustomerMapper.insert(jCustomer);


            /**客户房产信息*/
            JCustomerHouse jCustomerHouse = new JCustomerHouse();
            jCustomerHouse.setPropertyId(customerReq.getPropertyId());
            jCustomerHouse.setShopId(customerReq.getShopId());
            jCustomerHouse.setCustomerId(jCustomer.getCustomerId());
            jCustomerHouse.setHouseType(customerReq.getHouseType());
            jCustomerHouse.setBedRoom(customerReq.getBedRoom());
            jCustomerHouse.setLivingRoom(customerReq.getLivingRoom());
            jCustomerHouse.setRestRoom(customerReq.getRestRoom());
            jCustomerHouse.setHouseArea(customerReq.getHouseArea());
            jCustomerHouse.setAreaId(customerReq.getAreaId());
            jCustomerHouse.setHouseAddress(customerReq.getHouseAddress());
            jCustomerHouse.setLatitude(tencentMap.get("lat"));
            jCustomerHouse.setLongitude(tencentMap.get("lng"));
            jCustomerHouse.setServiceId(0);
            jCustomerHouse.setCreateId(jAdmin.getAdminId());
            jCustomerHouse.setCreateTime(new Date());
            jCustomerHouseMapper.insert(jCustomerHouse);

            return new ModelRes(ModelRes.Status.SUCCESS,"操作成功  !", jCustomer);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(@RequestBody Map<String, List> jCustomer) {
        try {
            List list = jCustomer.get("customerId");
            System.out.println(">>>>>>>>>>>list>>>>>>>>>>>>" + list);
            return new ModelRes(ModelRes.Status.SUCCESS, "delete customer success !", jCustomerMapper.deleteList(list));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody JCustomer jCustomer) {
        try {
            JCustomer jCustomer1 = jCustomerMapper.selectById(Long.valueOf(jCustomer.getCustomerId()));
            /**如果更新了手机号则判断手机号是否重复*/
            if (!jCustomer1.getCustomerPhone().equals(jCustomer.getCustomerPhone())){
                Map map = new HashMap();
                map.put("customerPhone",jCustomer.getCustomerPhone());
                List<JCustomer> jCustomerList=jCustomerMapper.selectByMap(map);
                if (jCustomerList.size()>0){
                    return new ModelRes(ModelRes.Status.FAILED, "重复手机号 !",ResponseUtil.List2Map(jCustomerList));
                }
            }
            return new ModelRes(ModelRes.Status.SUCCESS, "update customer success !", jCustomerMapper.updateById(jCustomer));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody CustomerPagReq customerPagReq, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            customerPagReq.setAdminId(jAdmin.getAdminId());
            customerPagReq.setPage(customerPagReq.getSize() * (customerPagReq.getPage() - 1));
            Map<String, Object> map = new HashedMap();
            map.put("count", jCustomerMapper.getCount(customerPagReq));
            map.put("list", jCustomerMapper.getCustomerList(customerPagReq));
            return new ModelRes(ModelRes.Status.SUCCESS, "search customer success !", map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 查询无门店的客户
     * @param customerPagReq
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/find/noShop")
    public Object findNoShop(@RequestBody CustomerPagReq customerPagReq, ServletRequest request) {
        try {
            customerPagReq.setPage(customerPagReq.getSize() * (customerPagReq.getPage() - 1));
            Map<String, Object> map = new HashedMap();
            map.put("count", jCustomerMapper.getCountNoShop(customerPagReq));
            map.put("list", jCustomerMapper.getCustomerListNoShop(customerPagReq));
            return new ModelRes(ModelRes.Status.SUCCESS, "search customer success !", map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 客户充值接口
     *
     * @param map
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/recharge")
    public Object recharge(@RequestBody Map map, ServletRequest request) {
        try {
            String rechargeMoney = (String) map.get("money");
            Integer customerId = (Integer) map.get("customerId");
            /**充值方式*/
            Integer chargeWay = (Integer) map.get("chargeWay");
            /**管理员信息*/
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            /**流水号*/
            String serialNumber = (String) map.get("serialNumber");
            JCustomer jCustomer = jCustomerMapper.selectById(Long.valueOf(customerId));

            /**客户对账单*/
            JCustomerStatment jCustomerStatment = new JCustomerStatment();

            jCustomerStatment.setStatmentCustomer(OrderUtil.generateStamentNumber(customerId));
            jCustomerStatment.setCustomerId(customerId);
            jCustomerStatment.setShopId(0);
            //38表示充值
            jCustomerStatment.setStatmentCustomerType(38);
            jCustomerStatment.setChargeDate(new Date());
            jCustomerStatment.setCreateDate(new Date());
            jCustomerStatment.setNotifyDate(new Date());
            jCustomerStatment.setBalance(jCustomer.getCustomerBalance());
            jCustomerStatment.setTerminal(49);
            jCustomerStatment.setChargeMoney(new BigDecimal(rechargeMoney));
            jCustomerStatment.setChargeWay(chargeWay);
            jCustomerStatment.setAdminId(jAdmin.getAdminId());
            //54表示支付未成功，等待支付宝回调
            jCustomerStatment.setStatus(54);

            /**支付服务器所需信息*/
            PayCallBackObj payCallBackObj = new PayCallBackObj(jCustomer.getCustomerId(), jCustomer.getCustomerName(), jCustomer.getCustomerPhone(), jAdmin.getAdminId(), "", 0);

            /**支付宝支付*/
            if (chargeWay == Consts.zhifubao) {

                /**先添加对账单记录*/
                jCustomerStatmentMapper.insert(jCustomerStatment);


                /** 一定要在创建AlipayTradeService之前调用Configs.init()设置默认参数
                 *  Configs会读取classpath下的zfbinfo.properties文件配置信息，如果找不到该文件则确认该文件是否在classpath目录
                 */
                Configs.init("zfbinfo.properties");

                /** 使用Configs提供的默认参数
                 *  AlipayTradeService可以使用单例或者为静态成员对象，不需要反复new
                 */
                AlipayUtil.tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();

                // 支付宝当面付2.0服务（集成了交易保障接口逻辑）
                AlipayUtil.tradeWithHBService = new AlipayTradeWithHBServiceImpl.ClientBuilder().build();

                /** 如果需要在程序中覆盖Configs提供的默认参数, 可以使用ClientBuilder类的setXXX方法修改默认参数 否则使用代码中的默认设置 */
                /**monitorService = new AlipayMonitorServiceImpl.ClientBuilder().setGatewayUrl("http://mcloudmonitor.com/gateway.do").setCharset("GBK").setFormat("json").build();*/

                AlipayF2FPrecreateResult result = AlipayUtil.test_trade_precreate("家有管家--扫码充值", jCustomerStatment.getStatmentCustomer(), rechargeMoney, payCallBackObj, Consts.testServerUrl + "/customer/aliPay/callback");

                switch (result.getTradeStatus()) {
                    case SUCCESS:
                        System.out.println("支付宝预下单成功: ");

                        AlipayTradePrecreateResponse response = result.getResponse();

                        return new ModelRes(ModelRes.Status.SUCCESS, "success !", response.getQrCode());

                    case FAILED:
                        System.out.println("支付宝预下单失败!!!");
                        return new ModelRes(ModelRes.Status.FAILED, "支付宝预下单失败 !", null);

                    case UNKNOWN:
                        System.out.println("系统异常，预下单状态未知!!");
                        return new ModelRes(ModelRes.Status.FAILED, " 系统异常，预下单状态未知!", null);

                    default:
                        System.out.println("不支持的交易状态，交易返回异常!!");
                        return new ModelRes(ModelRes.Status.FAILED, "不支持的交易状态，交易返回异常 !", null);
                }

            }
            /**微信支付*/
            else if (chargeWay == Consts.weixin) {
                /**先添加对账单记录*/
                jCustomerStatmentMapper.insert(jCustomerStatment);

                return WeixinUtil.weiXinQrcodePay(jCustomerStatment.getStatmentCustomer(), rechargeMoney, Consts.testServerUrl + "/customer/WeiXin/callback", payCallBackObj, "家有官家--扫码充值");
            }
            /**现金或者刷卡支付*/
            else if (chargeWay == Consts.card || chargeWay == Consts.cash||chargeWay==Consts.bank) {
                jCustomerStatment.setStatus(53);
                jCustomerStatment.setSerialNumber(serialNumber);
                jCustomerStatment.setBalance(jCustomerStatment.getBalance().add(jCustomerStatment.getChargeMoney()));

                jCustomer.setCustomerBalance(jCustomerStatment.getBalance());
                jCustomerStatmentMapper.insert(jCustomerStatment);
                jCustomerMapper.updateById(jCustomer);
                return new ModelRes(ModelRes.Status.SUCCESS, "充值成功 !", null);
            } else {
                return new ModelRes(ModelRes.Status.FAILED, "不支持当前支付方式 !", null);
            }
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

            Map map = new HashMap();
            map.put("statmentCustomer", aliPayResult.getOut_trade_no().get(0));
            List<JCustomerStatment> jCustomerStatmentList = jCustomerStatmentMapper.selectByMap(map);
            for (JCustomerStatment jCustomerStatment : jCustomerStatmentList) {
                /**修改客户对账单*/
                jCustomerStatment.setBalance(jCustomerStatment.getBalance().add(new BigDecimal(Double.valueOf(aliPayResult.getBuyer_pay_amount().get(0)))));
                jCustomerStatment.setSerialNumber(aliPayResult.getTrade_no().get(0));
                jCustomerStatment.setChargeDate(DateUtil.parseStringToDate(aliPayResult.getGmt_payment().get(0),DateUtil.PATTERN_yyyy_MM_dd_HHmmss));
                jCustomerStatment.setNotifyDate(new Date());
                jCustomerStatment.setStatus(53);
                /**修改客户*/
                JCustomer jCustomer = jCustomerMapper.selectById(Long.valueOf(jCustomerStatment.getCustomerId()));
                jCustomer.setCustomerBalance(jCustomer.getCustomerBalance().add(jCustomerStatment.getChargeMoney()));

                jCustomerStatmentMapper.updateById(jCustomerStatment);
                jCustomerMapper.updateById(jCustomer);
            }
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
            Map map = new HashMap();
            map.put("statmentCustomer", get.getOut_trade_no());
            List<JCustomerStatment> jCustomerStatmentList = jCustomerStatmentMapper.selectByMap(map);
            for (JCustomerStatment jCustomerStatment : jCustomerStatmentList) {
                jCustomerStatment.setBalance(jCustomerStatment.getBalance().add(new BigDecimal(Double.valueOf(get.getTotal_fee()) / 100)));
                jCustomerStatment.setSerialNumber(get.getTransaction_id());
                jCustomerStatment.setStatus(53);
                jCustomerStatment.setChargeDate(DateUtil.parseStringToDate(get.getTime_end(),DateUtil.PATTERN_yyyyMMddHHmmss));
                jCustomerStatment.setNotifyDate(new Date());

                JCustomer jCustomer = jCustomerMapper.selectById(Long.valueOf(jCustomerStatment.getCustomerId()));
                jCustomer.setCustomerBalance(jCustomer.getCustomerBalance().add(jCustomerStatment.getChargeMoney()));
                jCustomerStatmentMapper.updateById(jCustomerStatment);
                jCustomerMapper.updateById(jCustomer);
            }
            return "PayResult";
        }
        return "error";
    }


}
