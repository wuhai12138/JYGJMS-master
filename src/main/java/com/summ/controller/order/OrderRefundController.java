package com.summ.controller.order;

import com.summ.controller.basic.AutoMapperController;
import com.summ.model.*;
import com.summ.model.request.OrderRefundInsertReq;
import com.summ.model.request.OrderRefundReq;
import com.summ.model.response.ModelRes;
import com.summ.model.response.OrderRefundRes;
import com.summ.model.response.OrderScheduleRes;
import com.summ.model.response.TeacherRes;
import com.summ.utils.JsonUtil;
import com.summ.utils.OrderUtil;
import com.summ.utils.ResponseUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;

/**
 * 订单退款
 */
@Controller
@RequestMapping("/order/refund")
public class OrderRefundController extends AutoMapperController {

    /**
     * 合同订单日程退款申请
     *
     * @param orderRefundReq
     * @return
     */
    @ResponseBody
    @RequestMapping("/contract/insert")
    public Object contractInsert(@RequestBody OrderRefundInsertReq orderRefundReq, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");

            OrderScheduleRes orderScheduleRes = jOrderScheduleMapper.getScheduleDetail(orderRefundReq.getScheduleId());
            List<JNannyStatment> jNannyStatmentList = jNannyStatmentMapper.getNannyStatmentByScheduleId(orderRefundReq.getScheduleId());
            JOrderContract jOrderContract = jOrderContractMapper.selectById(Long.valueOf(orderScheduleRes.getOrderId()));

            if (orderScheduleRes.getPayStatus() == 157) {
                return new ModelRes(ModelRes.Status.FAILED, "日程未支付无法退款 !", null);
            }
            if (orderScheduleRes.getPayStatus() == 254) {
                return new ModelRes(ModelRes.Status.FAILED, "日程已退款 !", null);
            }
            JOrderRefund jOrderRefund = new JOrderRefund();
            jOrderRefund.setCreateId(jAdmin.getAdminId());
            jOrderRefund.setOrderType(163);
            if (jNannyStatmentList.size() > 0) {
                jOrderRefund.setOrderMoney(jNannyStatmentList.get(0).getStatmentMoney());
                jOrderRefund.setRefundMoney(jOrderRefund.getOrderMoney());
            }
            jOrderRefund.setOrderId(orderScheduleRes.getOrderId());
            jOrderRefund.setScheduleId(orderScheduleRes.getScheduleId());
            jOrderRefund.setReason(orderRefundReq.getReason());
            jOrderRefund.setRefundWay(251);
            jOrderRefund.setRemark(orderRefundReq.getRemark());
            jOrderRefund.setCustomerId(jOrderContract.getCustomerId());


            jOrderRefundMapper.insert(jOrderRefund);

            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 临时订单退款申请
     *
     * @param orderRefundReq
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/temp/insert")
    public Object insert(@RequestBody OrderRefundInsertReq orderRefundReq, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");

            JOrderTemp jOrderTemp = jOrderTempMapper.selectById(Long.valueOf(orderRefundReq.getOrderId()));
            List<OrderScheduleRes> jOrderScheduleList = jOrderScheduleMapper.getTempScheduleList(orderRefundReq.getOrderId());
            if (jOrderTemp.getPayStatus() == 157) {
                return new ModelRes(ModelRes.Status.FAILED, "订单未支付无法退款 !", jOrderTemp);
            }
            if (jOrderTemp.getPayStatus() == 254) {
                return new ModelRes(ModelRes.Status.FAILED, "订单已退款 !", jOrderTemp);
            }

            JOrderRefund jOrderRefund = new JOrderRefund();
            if (jOrderScheduleList.size() > 0) {
                jOrderRefund.setScheduleId(jOrderScheduleList.get(0).getScheduleId());
            }
            jOrderRefund.setCreateId(jAdmin.getAdminId());
            jOrderRefund.setOrderType(164);
            jOrderRefund.setOrderId(jOrderTemp.getOrderId());
            jOrderRefund.setReason(orderRefundReq.getReason());
            jOrderRefund.setRefundWay(251);
            jOrderRefund.setRemark(orderRefundReq.getRemark());
            jOrderRefund.setCustomerId(jOrderTemp.getCustomerId());
            jOrderRefund.setOrderMoney(jOrderTemp.getTotalPrice());
            jOrderRefund.setDiscount(jOrderTemp.getDiscount());
            jOrderRefund.setPayMoney(jOrderTemp.getPayMoney());
            jOrderRefund.setRefundMoney(jOrderTemp.getPayMoney());
            jOrderRefund.setCouponListId(jOrderTemp.getCouponListId());

            jOrderRefundMapper.insert(jOrderRefund);

            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }


    /**
     * 退款申请审核
     * @param jOrderRefund1
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/check")
    public Object check(@RequestBody JOrderRefund jOrderRefund1, ServletRequest request) {
        try {
            JAdmin jAdmin = (JAdmin) request.getAttribute("admin");

            /**审核通过*/
            if (jOrderRefund1.getCheckStatus()==233){
                JOrderRefund jOrderRefund = jOrderRefundMapper.selectById(Long.valueOf(jOrderRefund1.getRefundId()));
                JCustomer jCustomer = jCustomerMapper.selectById(Long.valueOf(jOrderRefund.getCustomerId()));
                /**修改客户余额*/
                jCustomer.setCustomerBalance(jCustomer.getCustomerBalance().add(jOrderRefund.getRefundMoney()));

                /**合同订单*/
                if (jOrderRefund.getOrderType() == 163) {
                    JOrderSchedule jOrderSchedule = jOrderScheduleMapper.selectById(Long.valueOf(jOrderRefund.getScheduleId()));
                    JOrderContract jOrderContract = jOrderContractMapper.selectById(Long.valueOf(jOrderRefund.getOrderId()));
                    /**修改订单状态为已退款*/
                    jOrderSchedule.setPayStatus(254);
                    /**服务时间*/
                    String serviceTime = jOrderSchedule.getStartTimeValue() + "-" + jOrderSchedule.getEndTimeValue();
                    Double serviceTimeLength = Double.valueOf((jOrderSchedule.getEndTime() - jOrderSchedule.getStartTime()) / 2f);

                    /***客户对账单*/
                    JCustomerStatment jCustomerStatment = new JCustomerStatment(OrderUtil.generateStamentNumber(jOrderRefund.getCustomerId()),
                            jOrderRefund.getCustomerId(), jOrderContract.getGoodsId(), jOrderContract.getHouseId(), jOrderContract.getOrderId(),
                            163, jOrderRefund.getScheduleId(), jOrderContract.getShopId(), serviceTime, serviceTimeLength, jOrderSchedule.getScheduleDate(),
                            new Date(), 42, jOrderRefund.getRefundMoney(), 0, jAdmin.getAdminId(), 49, 53, jCustomer.getCustomerBalance(), "", "");

                    jOrderScheduleMapper.updateById(jOrderSchedule);
                    jCustomerStatmentMapper.insert(jCustomerStatment);
                    jCustomerMapper.updateById(jCustomer);
                }
                /**临时订单*/
                if (jOrderRefund.getOrderType() == 164) {
                    JOrderTemp jOrderTemp = jOrderTempMapper.selectById(Long.valueOf(jOrderRefund.getOrderId()));
                    /**修改订单状态为已退款*/
                    jOrderTemp.setPayStatus(254);
                    /**关闭订单*/
                    jOrderTemp.setOrderCloseStatus(213);

                    /**服务时间*/
                    String serviceTime = jOrderTemp.getStartTimeValue() + "-" + jOrderTemp.getEndTimeValue();
                    Double serviceTimeLength = Double.valueOf((jOrderTemp.getEndTime() - jOrderTemp.getStartTime()) / 2f);

                    /**日程列表*/
                    Map map = new HashMap();
                    map.put("orderType", 164);
                    map.put("orderId", jOrderRefund.getOrderId());
                    List<JOrderSchedule> jOrderScheduleList = jOrderScheduleMapper.selectByMap(map);
                    if (jOrderScheduleList.size() > 0) {
                        for (JOrderSchedule jOrderSchedule : jOrderScheduleList) {
                            /**处理待完工的日程*/
                            if (jOrderSchedule.getScheduleStatus() == 152) {
                                jOrderSchedule.setScheduleStatus(155);
                                jOrderSchedule.setRemark("订单退款");
                            }
                            jOrderScheduleMapper.updateById(jOrderSchedule);
                        }
                    }

                    /***客户对账单*/
                    JCustomerStatment jCustomerStatment = new JCustomerStatment(OrderUtil.generateStamentNumber(jOrderRefund.getCustomerId()),
                            jOrderRefund.getCustomerId(), jOrderTemp.getGoodsId(), jOrderTemp.getHouseId(), jOrderTemp.getOrderId(),
                            163, jOrderRefund.getScheduleId(), jOrderTemp.getShopId(), serviceTime, serviceTimeLength, jOrderTemp.getOrderDate(),
                            new Date(), 42, jOrderRefund.getRefundMoney(), 0, jAdmin.getAdminId(), 49, 53, jCustomer.getCustomerBalance(), "", "");


                    jCustomerMapper.updateById(jCustomer);
                    jCustomerStatmentMapper.insert(jCustomerStatment);
                }

                jOrderRefund.setCheckDate(new Date());
                jOrderRefund.setCheckId(jAdmin.getAdminId());
                jOrderRefund.setCheckStatus(233);
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jOrderRefundMapper.updateById(jOrderRefund));
            }
            /**审核不通过*/
            else if (jOrderRefund1.getCheckStatus()==234){
                jOrderRefund1.setCheckStatus(234);
                jOrderRefund1.setCheckId(jAdmin.getAdminId());
                jOrderRefund1.setCheckDate(new Date());
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !",jOrderRefundMapper.updateById(jOrderRefund1));
            }else {
                return new ModelRes(ModelRes.Status.FAILED, "提交错误 !");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody JOrderRefund jOrderRefund, ServletRequest request) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jOrderRefundMapper.updateById(jOrderRefund));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody OrderRefundReq orderRefundReq, ServletRequest request) {
        try {
            orderRefundReq.setPage(orderRefundReq.getSize() * (orderRefundReq.getPage() - 1));
            Map map = JsonUtil.Obj2Map(orderRefundReq);
            map.put("startDate", new Date((Long) map.get("startDate")));
            map.put("endDate", new Date((Long) map.get("endDate")));
            List<OrderRefundRes> orderRefundResList = jOrderRefundMapper.getOrderRefundList(map);
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", ResponseUtil.List2Map(orderRefundResList, jOrderRefundMapper.getOrderRefundListCount(map)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/detail")
    public Object detail(@RequestBody Map map, ServletRequest request) {
        try {
            JOrderRefund jOrderRefund = jOrderRefundMapper.selectById(Long.valueOf((Integer) map.get("refundId")));
            /**合同订单**/
            if (jOrderRefund.getOrderType() == 163) {
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", ResponseUtil.List2Map(jOrderRefundMapper.getContractRefundDetail(jOrderRefund.getRefundId())));
            }
            /**临时订单**/
            if (jOrderRefund.getOrderType() == 164) {
                List<OrderRefundRes> orderRefundResList = jOrderRefundMapper.getTempRefundDetail(jOrderRefund.getOrderId());
                for (OrderRefundRes orderRefundRes : orderRefundResList) {
                    if (orderRefundRes.getSupplierId() != null || orderRefundRes.getSupplierId() != 0) {
                        OrderRefundRes orderRefundRes1 = jOrderRefundMapper.getScheduleNannyInfo(orderRefundRes.getSupplierId(), orderRefundRes.getScheduleId());
                        orderRefundRes.setNannyId(orderRefundRes1.getNannyId());
                        orderRefundRes.setNannyCost(orderRefundRes1.getNannyCost());
                        orderRefundRes.setNannyName(orderRefundRes1.getNannyName());
                        orderRefundRes.setNannyPhone(orderRefundRes1.getNannyPhone());
                    }
                }
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", ResponseUtil.List2Map(orderRefundResList));
            }

            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }


}
