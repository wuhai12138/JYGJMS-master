package com.summ.controller.appbanner;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.summ.Consts;
import com.summ.controller.basic.AutoMapperController;
import com.summ.model.JAdmin;
import com.summ.model.JAppBanner;
import com.summ.model.JAppBannerGoods;
import com.summ.model.JCouponGoods;
import com.summ.model.response.ModelRes;
import com.summ.utils.DateUtil;
import com.summ.utils.ResponseUtil;
import com.summ.utils.StringUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * app活动轮播图配置
 */
@RestController
@RequestMapping("/app/banner")
public class AppBannerController extends AutoMapperController {

    @RequestMapping("/insert")
    public Object insert(@RequestBody JAppBanner jAppBanner, ServletRequest request) {
        JAdmin jAdmin = (JAdmin) request.getAttribute("admin");
        jAppBanner.setCreateId(jAdmin.getAdminId());
        String fileName = "AB" + System.currentTimeMillis() + ".jpg";
        try {
            /**判断是否上传图片*/
            if (!"".equals(jAppBanner.getPhoto()) && null != jAppBanner.getPhoto()) {
                /**上传图片*/
                if (StringUtil.generateImage(jAppBanner.getPhoto(), Consts.appBannerUrl + fileName)) {
                    jAppBanner.setPhoto(fileName);
                    jAppBannerMapper.insert(jAppBanner);
                    jAppBanner.setPhoto(Consts.appBannerUrlRes + jAppBanner.getPhoto());
                    return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 ! ", jAppBanner);
                } else {
                    return new ModelRes(ModelRes.Status.FAILED, "图片上传失败 ! ", jAppBanner);
                }
            } else {
                jAppBannerMapper.insert(jAppBanner);
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 ! ", jAppBanner);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err ! ");
        }
    }
    @RequestMapping("/delete")
    public Object delete(@RequestBody JAppBanner jAppBanner, ServletRequest request) {
        try {
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 ! ", jAppBannerMapper.deleteById(jAppBanner.getBannerId()));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err ! ");
        }
    }
    @RequestMapping("/update")
    public Object update(@RequestBody JAppBanner jAppBanner, ServletRequest request) {
        try {
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 ! ", jAppBannerMapper.updateById(jAppBanner));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err ! ");
        }
    }

    @RequestMapping("/find")
    public Object find(@RequestBody final JAppBanner aa) {
        try {
            List<JAppBanner> jAppBannerList = jAppBannerMapper.selectList(new Wrapper<JAppBanner>() {
                @Override
                public String getSqlSegment() {
                    return "and startDate between '" + DateUtil.parseDateToString(aa.getStartDate(),DateUtil.PATTERN_yyyy_MM_dd_HHmmss)+"' and '"+DateUtil.parseDateToString(aa.getEndDate(),DateUtil.PATTERN_yyyy_MM_dd_HHmmss)+"'";
                }
            });
            /**循环补齐图片在服务器上的路径*/
            for (JAppBanner jAppBanner : jAppBannerList) {
                jAppBanner.setPhoto(Consts.appBannerUrlRes + jAppBanner.getPhoto());
            }
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 ! ", ResponseUtil.List2Map(jAppBannerList));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err ! ");
        }
    }

    /**
     *活动产品配置
     * @param map
     * @return
     */
    @RequestMapping("/goods/insert")
    public Object goodsInsert(@RequestBody Map map) {
        Integer bannerId = (Integer) map.get("bannerId");
        List<Integer> goodsIdList = (List<Integer>) map.get("goodsIdList");
        Map map1 = new HashMap();
        map1.put("bannerId",bannerId);
        jAppBannerGoodsMapper.deleteByMap(map1);
        if (goodsIdList.size()>0){
            for (Integer id : goodsIdList){
                JAppBannerGoods jAppBannerGoods = new JAppBannerGoods();
                jAppBannerGoods.setBannerId(bannerId);
                jAppBannerGoods.setGoodsId(id);
                jAppBannerGoodsMapper.insert(jAppBannerGoods);
            }
        }
        return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", null);
    }

    @RequestMapping("/goods/find")
    public Object goodsFind(@RequestBody JAppBanner jAppBanner) {
        return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", ResponseUtil.List2Map(jAppBannerGoodsMapper.getBannerGoods(jAppBanner.getBannerId())));
    }
}
