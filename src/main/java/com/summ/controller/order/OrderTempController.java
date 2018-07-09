package com.summ.controller.order;

import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.service.impl.AlipayTradeWithHBServiceImpl;
import com.summ.Consts;
import com.summ.controller.basic.AutoMapperController;
import com.summ.mapper.JOrderTempMapper;
import com.summ.model.*;
import com.summ.model.others.GetWXResult;
import com.summ.model.others.PayCallBackObj;
import com.summ.model.request.CustomerCouponReq;
import com.summ.model.request.OrderContractReq;
import com.summ.model.request.OrderTempChargeReq;
import com.summ.model.request.OrderTempReq;
import com.summ.model.response.*;
import com.summ.utils.*;
import com.sun.tools.internal.jxc.ap.Const;
import org.apache.commons.collections.map.HashedMap;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.Bidi;
import java.util.*;


@Controller
@RequestMapping("/order/temp")
public class OrderTempController extends AutoMapperController{

    /**
     * 新增临时订单
     * @param jOrderTemp
     * @return
     */
    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody JOrderTemp jOrderTemp,ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");

            JGoodsContract jGoodsContract = jGoodsContractMapper.selectById(Long.valueOf(jOrderTemp.getGoodsId()));
            jOrderTemp.setUnitPrice(jGoodsContract.getPrice());
            jOrderTemp.setTotalPrice(jGoodsContract.getPrice().multiply(new BigDecimal(jOrderTemp.getServiceAmount())));
            jOrderTemp.setCreateId(jAdmin.getAdminId());
            jOrderTemp.setCreateTime(new Date());
            jOrderTempMapper.insert(jOrderTemp);

            /**发送短信给门店手机号*/
            JShop jShop = jShopMapper.selectById(Long.valueOf(jOrderTemp.getShopId()));
            JCustomer jCustomer = jCustomerMapper.selectById(Long.valueOf(jOrderTemp.getCustomerId()));
            SendSMSUtil.notifyShop(jCustomer.getCustomerPhone(),jShop.getShopMobile(),jCustomer.getCustomerName());

            return new ModelRes(ModelRes.Status.SUCCESS,"操作成功  !", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody JOrderTemp jOrderTemp) {
        try {
            JOrderTemp jOrderTemp1 = jOrderTempMapper.selectById(jOrderTemp.getOrderId());
            jOrderTemp.setTotalPrice(jOrderTemp1.getUnitPrice().multiply(new BigDecimal(jOrderTemp.getServiceAmount())));
            jOrderTempMapper.updateById(jOrderTemp);
            return new ModelRes(ModelRes.Status.SUCCESS,"操作成功  !", jOrderTemp);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 根据订单返回支付信息（客户信息，订单金额，优惠券列表，支付方式）
     * @param orderTempChargeReq
     * @return
     */
    @ResponseBody
    @RequestMapping("/payData")
    public Object payData(@RequestBody OrderTempChargeReq orderTempChargeReq) {
        try {
            /**订单列表*/
            List<JOrderTemp> jOrderTempList = jOrderTempMapper.selectBatchIds(orderTempChargeReq.getOrderList());
            List<String> goodsList = new ArrayList<String>();

            BigDecimal cost = new BigDecimal(0);
            for (JOrderTemp jOrderTemp : jOrderTempList){
                goodsList.add(jOrderTemp.getGoodsId().toString());
                if (!jOrderTemp.getCustomerId().equals(orderTempChargeReq.getCustomerId())){
                    return new ModelRes(ModelRes.Status.FAILED, "有多个客户的订单，请重选 !");
                }else if (jOrderTemp.getPayStatus()==158){
                    return new ModelRes(ModelRes.Status.FAILED, "有订单已支付，请重选 !");
                }
                else {
                    cost = cost.add(jOrderTemp.getTotalPrice());
                }
            }

//            List<CustomerCouponListRes> customerCouponListResListOld = jCouponListMapper.getListById(new CustomerCouponReq(orderTempChargeReq.getCustomerId(),32));
//            List<CustomerCouponListRes> customerCouponListResList = new ArrayList<CustomerCouponListRes>();
//            for (CustomerCouponListRes customerCouponListRes: customerCouponListResListOld){
//                if (customerCouponListRes.getValidTime().after(new Date())){
//                    customerCouponListResList.add(customerCouponListRes);
//                }
//            }
            //不可使用优惠券列表
            List<CustomerCouponListRes> unUseCouponList = jCouponListMapper.getUnUseCouponList(orderTempChargeReq);
            //可使用优惠券列表
            List<CustomerCouponListRes> usedCouponList = jCouponListMapper.getUsedCouponList(orderTempChargeReq);

//            /**判断所有订单是不是同一产品*/
//            if (!StringUtil.isRepeat(goodsList)){
//                unUseCouponList.addAll(customerCouponListResList);
//            }else {
//
//                for (CustomerCouponListRes customerCouponListRes : customerCouponListResList) {
//                    /**判断优惠券是否能在订单的门店中使用*/
//                    Map couponShopMap = new HashMap();
//                    couponShopMap.put("couponId", customerCouponListRes.getCouponId());
//                    List<JCouponShop> jCouponShopList = jCouponShopMapper.selectByMap(couponShopMap);
//                    if (jCouponShopList.size() > 0) {
//                        List<String> shopList = new ArrayList<>();
//                        for (JCouponShop jCouponShop : jCouponShopList) {
//                            shopList.add(String.valueOf(jCouponShop.getShopId()));
//                        }
//                        for (JOrderTemp jOrderTemp : jOrderTempList) {
//                            shopList.add(String.valueOf(jOrderTemp.getShopId()));
//                            /**判断重复*/
//                            if (!StringUtil.isRepeat(shopList)) {
//                                usedCouponList.add(customerCouponListRes);
//                                continue;
//                            }
//                        }
//                    } else {
//                        unUseCouponList.add(customerCouponListRes);
//                        continue;
//                    }
//
//
//                    /**存放某个优惠券所适用的产品列表*/
//                    List<String> couponGoods = new ArrayList<String>();
//                    //优惠券产品列表
//                    Map map = new HashMap();
//                    map.put("couponId", customerCouponListRes.getCouponId());
//                    List<JCouponGoods> couponGoodsList = jCouponGoodsMapper.selectByMap(map);
//                    if (couponGoodsList.size() > 0) {
//                        for (JCouponGoods jCouponGoods : couponGoodsList) {
//                            couponGoods.add(jCouponGoods.getGoodsId().toString());
//                        }
//                        /**加入此次支付的产品*/
//                        couponGoods.add(goodsList.get(0));
//                        /**判断重复*/
//                        if (StringUtil.isRepeat(couponGoods)) {
//                            usedCouponList.add(customerCouponListRes);
//                        } else {
//                            unUseCouponList.add(customerCouponListRes);
//                        }
//                    } else {
//                        unUseCouponList.add(customerCouponListRes);
//                    }
//
//                }
//            }

            JCustomer jCustomer = jCustomerMapper.selectById(Long.valueOf(orderTempChargeReq.getCustomerId()));
            PayDataRes payDataRes = new PayDataRes(jCustomer.getCustomerId(),jCustomer.getCustomerName(),jCustomer.getCustomerPhone(),
                    jCustomer.getCustomerBalance(),cost,unUseCouponList,usedCouponList);

            return new ModelRes(ModelRes.Status.SUCCESS,"操作成功  !", payDataRes);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 临时订单多笔支付
     * @param orderTempChargeReq
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/pay")
    public Object pay(@RequestBody OrderTempChargeReq orderTempChargeReq,ServletRequest request) {
        try {
            //获取管理员id
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            Integer adminId = jAdmin.getAdminId();

            /**客户对账单列表*/
            List<JCustomerStatment> jCustomerStatmentList = new ArrayList<JCustomerStatment>();

            /**订单列表*/
            List<JOrderTemp> jOrderTempList = jOrderTempMapper.selectBatchIds(orderTempChargeReq.getOrderList());
            /**获取选择的优惠券信息*/
            JCoupon jCoupon = new JCoupon();
            if(orderTempChargeReq.getCouponListId()==0){
               jCoupon.setCouponPrice(new BigDecimal(0));
               jCoupon.setOrderMiniPrice(new BigDecimal(0));
            }else {
                jCoupon = jCouponMapper.getCouponByCouponListId(orderTempChargeReq.getCouponListId());
            }
            JCustomer jCustomer = jCustomerMapper.selectById(Long.valueOf(jOrderTempList.get(0).getCustomerId()));
            if (orderTempChargeReq.getCost().compareTo(jCoupon.getOrderMiniPrice()) <0){
                return new ModelRes(ModelRes.Status.FAILED, "订单总额不符合优惠券规范，请重选 !");
            }

            if (orderTempChargeReq.getCost().compareTo(jCoupon.getCouponPrice())!=1){
                if (orderTempChargeReq.getChargeType()==44||orderTempChargeReq.getChargeType()==45){
                    return new ModelRes(ModelRes.Status.FAILED, "订单金额0元时,请选择余额支付 !");
                }
            }
            if (orderTempChargeReq.getCost().subtract(jCoupon.getCouponPrice()).compareTo(jCustomer.getCustomerBalance())>0&&orderTempChargeReq.getChargeType()==48){
                    return new ModelRes(ModelRes.Status.FAILED, "余额不够，请重选支付方式 !");
            }

            String statmentCustomer = OrderUtil.generateStamentNumber(jCustomer.getCustomerId());
            /**判断支付方式是余额还是手机支付*/
            /**分别为余额，现金，pos机，银行转账*/
            if(orderTempChargeReq.getChargeType()==48||orderTempChargeReq.getChargeType()==127||orderTempChargeReq.getChargeType()==209||orderTempChargeReq.getChargeType()==243){
                /**针对每个订单，更新折扣金额，支付金额，支付状态，优惠券id，新增对账单*/
                for (JOrderTemp jOrderTemp : jOrderTempList){
                    if (jOrderTemp.getPayStatus()==158){
                        return new ModelRes(ModelRes.Status.FAILED, "有订单已支付 !",jOrderTemp);
                    }
                    Map map=new HashMap();
                    map.put("orderId",jOrderTemp.getOrderId());
                    map.put("orderType",164);
                    List<JOrderSchedule> jOrderScheduleList = jOrderScheduleMapper.selectByMap(map);
                    if (jOrderScheduleList.size()>0){
                        for (JOrderSchedule jOrderSchedule : jOrderScheduleList){
                            jOrderSchedule.setPayStatus(158);
                            jOrderScheduleMapper.updateById(jOrderSchedule);
                        }
                    }

                    /**更新订单信息*/
                    BigDecimal discount = jCoupon.getCouponPrice().multiply(jOrderTemp.getTotalPrice()).divide(orderTempChargeReq.getCost(),2,BigDecimal.ROUND_HALF_UP);
                    if (discount.compareTo(jOrderTemp.getTotalPrice())==1){
                        discount=jOrderTemp.getTotalPrice();
                    }
                    jOrderTemp.setDiscount(discount);
                    jOrderTemp.setPayMoney(jOrderTemp.getTotalPrice().subtract(jOrderTemp.getDiscount()));
                    jOrderTemp.setPayStatus(158);
                    jOrderTemp.setPayTime(new Date());
                    jOrderTemp.setCouponListId(orderTempChargeReq.getCouponListId());

                    String serviceTime = jOrderTemp.getStartTimeValue()+"-"+jOrderTemp.getEndTimeValue();
                    Double serviceTimeLength = Double.valueOf((jOrderTemp.getEndTime() - jOrderTemp.getStartTime()) / 2f);
                    JCustomerStatment jCustomerStatment = new JCustomerStatment(statmentCustomer,
                            jOrderTemp.getCustomerId(),jOrderTemp.getGoodsId(), jOrderTemp.getHouseId(),
                            jOrderTemp.getOrderId(),164,0,
                            jOrderTemp.getShopId(), serviceTime, serviceTimeLength,
                            jOrderTemp.getOrderDate(), new Date(), 177, jOrderTemp.getPayMoney(),
                            orderTempChargeReq.getChargeType(), adminId, 49, 53, jCustomer.getCustomerBalance().subtract(jOrderTemp.getPayMoney()),
                            orderTempChargeReq.getSerialNumber(), "",jOrderTemp.getCouponListId());
                    /**添加客户对账单*/
                    jCustomerStatmentList.add(jCustomerStatment);
                    jCustomerStatmentMapper.insert(jCustomerStatment);
                    if (orderTempChargeReq.getChargeType()==48){
                        /**更新客户余额*/
                        jCustomer.setCustomerBalance(jCustomer.getCustomerBalance().subtract(jOrderTemp.getPayMoney()));
                    }

                    jOrderTempMapper.updateById(jOrderTemp);
                }

                jCustomerMapper.updateById(jCustomer);
            }else {
                /**支付服务器所需信息*/
                PayCallBackObj payCallBackObj = new PayCallBackObj(jCustomer.getCustomerId(),jCustomer.getCustomerName(),jCustomer.getCustomerPhone(),jAdmin.getAdminId(),"",0);

                /**针对每个订单，更新折扣金额，支付金额，支付状态，优惠券id，新增对账单*/
                for (JOrderTemp jOrderTemp : jOrderTempList) {
                    /**更新订单信息*/
                    jOrderTemp.setDiscount(jCoupon.getCouponPrice().multiply(jOrderTemp.getTotalPrice()).divide(orderTempChargeReq.getCost(), 2, BigDecimal.ROUND_HALF_UP));
                    jOrderTemp.setPayMoney(jOrderTemp.getTotalPrice().subtract(jOrderTemp.getDiscount()));
                    jOrderTemp.setCouponListId(orderTempChargeReq.getCouponListId());

                    String serviceTime = jOrderTemp.getStartTimeValue() + "-" + jOrderTemp.getEndTimeValue();
                    Double serviceTimeLength = Double.valueOf((jOrderTemp.getEndTime() - jOrderTemp.getStartTime()) / 2f);
                    /**根据支付方式生成不同支付方式（chargeWay）的对账单*/
                    if (orderTempChargeReq.getChargeType()== Consts.zhifubao){
                        JCustomerStatment jCustomerStatment = new JCustomerStatment(statmentCustomer,
                                jOrderTemp.getCustomerId(), jOrderTemp.getGoodsId(), jOrderTemp.getHouseId(),
                                jOrderTemp.getOrderId(), 164, 0,
                                jOrderTemp.getShopId(), serviceTime, serviceTimeLength,
                                jOrderTemp.getOrderDate(), new Date(), 177, jOrderTemp.getPayMoney(),
                                44, adminId, 49, 54, jCustomer.getCustomerBalance(),
                                "", "");
                        /**添加客户对账单*/
                        jCustomerStatmentList.add(jCustomerStatment);
                        jCustomerStatmentMapper.insert(jCustomerStatment);
                    }else {
                        JCustomerStatment jCustomerStatment = new JCustomerStatment(statmentCustomer,
                                jOrderTemp.getCustomerId(), jOrderTemp.getGoodsId(), jOrderTemp.getHouseId(),
                                jOrderTemp.getOrderId(), 164, 0,
                                jOrderTemp.getShopId(), serviceTime, serviceTimeLength,
                                jOrderTemp.getOrderDate(), new Date(), 177, jOrderTemp.getPayMoney(),
                                45, adminId, 49, 54, jCustomer.getCustomerBalance(),
                                "", "");
                        /**添加客户对账单*/
                        jCustomerStatmentList.add(jCustomerStatment);
                        jCustomerStatmentMapper.insert(jCustomerStatment);
                    }

                }
                /**更新临时订单和新增对账单*/

                /**支付宝*/
                if (orderTempChargeReq.getChargeType()== Consts.zhifubao) {


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

                    AlipayF2FPrecreateResult result = AlipayUtil.test_trade_precreate("家有管家--扫码支付", statmentCustomer, orderTempChargeReq.getCost().subtract(jCoupon.getCouponPrice()).toString(), payCallBackObj, Consts.testServerUrl + "/order/temp/aliPay/callback");

                    switch (result.getTradeStatus()) {
                        case SUCCESS:
                            System.out.println("支付宝预下单成功: ");

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
                if (orderTempChargeReq.getChargeType()== Consts.weixin){
                    return WeixinUtil.weiXinQrcodePay(statmentCustomer,orderTempChargeReq.getCost().subtract(jCoupon.getCouponPrice()).toString(),Consts.testServerUrl+"/order/temp/WeiXin/callback",payCallBackObj,"家有管家--扫码支付");
                }
            }

            return new ModelRes(ModelRes.Status.SUCCESS, "支付成功！" ,null);
        }catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 支付宝回调
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
            map.put("statmentCustomer", aliPayResult.getOut_trade_no().get(0));
            List<JCustomerStatment> jCustomerStatmentList = jCustomerStatmentMapper.selectByMap(map);

            //修改对账单状态和订单支付状态
            for (JCustomerStatment jCustomerStatment : jCustomerStatmentList){
                jCustomerStatment.setStatus(53);
                jCustomerStatment.setSerialNumber(aliPayResult.getTrade_no().get(0));
                jCustomerStatment.setChargeDate(DateUtil.parseStringToDate(aliPayResult.getGmt_payment().get(0),DateUtil.PATTERN_yyyy_MM_dd_HHmmss));
                jCustomerStatment.setNotifyDate(new Date());

                JOrderTemp jOrderTemp = new JOrderTemp();
                jOrderTemp.setOrderId(jCustomerStatment.getOrderId());
                jOrderTemp.setPayStatus(158);
                jOrderTemp.setPayTime(new Date());

                if (0!=jCustomerStatment.getCouponListId()||null!=jCustomerStatment.getCouponListId()){
                    JCouponList jCouponList = new JCouponList();
                    jCouponList.setCouponListId(jCustomerStatment.getCouponListId());
                    jCouponList.setCouponStatus(34);
                    jCouponList.setUseTime(new Date());
                    jCouponListMapper.updateById(jCouponList);
                }

                jCustomerStatmentMapper.updateById(jCustomerStatment);
                jOrderTempMapper.updateById(jOrderTemp);
            }
            return "PayResult";
        } else {
            return "error";
        }
    }

    /**
     * 微信回调
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
            map.put("statmentCustomer",get.getOut_trade_no());
            List<JCustomerStatment> jCustomerStatmentList = jCustomerStatmentMapper.selectByMap(map);
            for (JCustomerStatment jCustomerStatment : jCustomerStatmentList){
                jCustomerStatment.setStatus(53);
                jCustomerStatment.setSerialNumber(get.getTransaction_id());
                jCustomerStatment.setChargeDate(DateUtil.parseStringToDate(get.getTime_end(),DateUtil.PATTERN_yyyyMMddHHmmssSSS));
                jCustomerStatment.setNotifyDate(new Date());

                JOrderTemp jOrderTemp = new JOrderTemp();
                jOrderTemp.setOrderId(jCustomerStatment.getOrderId());
                jOrderTemp.setPayStatus(158);
                jOrderTemp.setPayTime(new Date());

                if (0!=jCustomerStatment.getCouponListId()||null!=jCustomerStatment.getCouponListId()){
                    JCouponList jCouponList = new JCouponList();
                    jCouponList.setCouponListId(jCustomerStatment.getCouponListId());
                    jCouponList.setCouponStatus(34);
                    jCouponList.setUseTime(new Date());
                    jCouponListMapper.updateById(jCouponList);
                }

                jCustomerStatmentMapper.updateById(jCustomerStatment);
                jOrderTempMapper.updateById(jOrderTemp);
            }
            return "PayResult";
        }
        return "error";
    }

    /**
     * 获取临时订单服务列表
     * @param orderContractReq
     * @return
     */
    @ResponseBody
    @RequestMapping("/goods/find")
    public Object findGoods(@RequestBody OrderContractReq orderContractReq) {
        try {
            Map map = new HashMap();
            map.put("orderType",164);
            map.put("isDel",16);
            return new ModelRes(ModelRes.Status.SUCCESS,"操作成功  !", ResponseUtil.List2Map(jGoodsContractMapper.selectByMap(map)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }


    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody OrderTempReq orderTempReq, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            orderTempReq.setAdminId(jAdmin.getAdminId());
            orderTempReq.setPage(orderTempReq.getSize() * (orderTempReq.getPage() - 1));
            List<OrderTempRes> orderTempResList = jOrderTempMapper.getTempList(orderTempReq);
            Map map = ResponseUtil.List2Map(orderTempResList);
            map.put("count",jOrderTempMapper.getTempCount(orderTempReq));
            return new ModelRes(ModelRes.Status.SUCCESS,"操作成功  !",map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 订单关闭（如果订单已付款日程未签到，则提交退款申请至财务）
     * @param jOrderTemp
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/close")
    public Object close(@RequestBody JOrderTemp jOrderTemp,ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
            JOrderTemp jOrderTemp1 = jOrderTempMapper.selectById(Long.valueOf(jOrderTemp.getOrderId()));

            Map map = new HashMap();
            map.put("orderType",164);
            map.put("orderId",jOrderTemp.getOrderId());
            List<JOrderSchedule> jOrderScheduleList = jOrderScheduleMapper.selectByMap(map);
            if (jOrderScheduleList.size()>0){
                for (JOrderSchedule jOrderSchedule : jOrderScheduleList){
                    /**已签到日程不准许关闭*/
                    if (jOrderSchedule.getScheduleStatus()==153){
                        return new ModelRes(ModelRes.Status.SUCCESS,"该订单已完工，不予关闭  !", jOrderSchedule);
                    }
                    /**待完工日程*/
                    if (jOrderSchedule.getScheduleStatus()==152){
                        /**订单已支付则生成退款申请*/
                        if (jOrderTemp1.getPayStatus()==158){
                            /**获取支付信息*/
                            Map payMap = new HashMap();
                            payMap.put("orderType",164);
                            payMap.put("orderId",jOrderTemp.getOrderId());
                            payMap.put("status",53);
                            List<JCustomerStatment> jCustomerStatmentList = jCustomerStatmentMapper.selectByMap(payMap);
                            Integer payWay = 0;
                            if (jCustomerStatmentList.size()>0){
                                payWay=jCustomerStatmentList.get(0).getChargeWay();
                            }
                            JOrderRefund jOrderRefund = new JOrderRefund(jOrderTemp1.getCustomerId(),164,jOrderTemp1.getOrderId(),jOrderSchedule.getScheduleId(),payWay,251,230,jOrderTemp1.getTotalPrice(),jOrderTemp1.getDiscount(),jOrderTemp1.getPayMoney(),jOrderTemp1.getPayMoney(),jOrderTemp1.getCouponListId());
                            jOrderRefundMapper.insert(jOrderRefund);
                        }
                        jOrderSchedule.setCancelTime(new Date());
                        jOrderSchedule.setScheduleStatus(155);
                        jOrderSchedule.setCancelId(jAdmin.getAdminId());
                        jOrderSchedule.setRemark(StringUtil.isBlank(jOrderSchedule.getRemark())?" 关闭原因："+jOrderTemp.getRemark():jOrderSchedule.getRemark()+",关闭原因："+jOrderTemp.getRemark());

                    }
                    jOrderScheduleMapper.updateById(jOrderSchedule);
                }


            }

            jOrderTemp1.setOrderCloseStatus(213);
            jOrderTemp1.setModifyId(jAdmin.getAdminId());
            jOrderTemp1.setModifyTime(new Date());
            jOrderTemp1.setRemark(StringUtil.isBlank(jOrderTemp1.getRemark())?"关闭原因："+jOrderTemp.getRemark():jOrderTemp1.getRemark()+",关闭原因："+jOrderTemp.getRemark());
            jOrderTempMapper.updateById(jOrderTemp1);
            return new ModelRes(ModelRes.Status.SUCCESS,"操作成功  !", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

}
