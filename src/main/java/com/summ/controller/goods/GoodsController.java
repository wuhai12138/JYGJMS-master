package com.summ.controller.goods;

import com.summ.Consts;
import com.summ.controller.basic.AutoMapperController;
import com.summ.model.JGoodsContract;
import com.summ.model.JGoodsCost;
import com.summ.model.request.GoodsReq;
import com.summ.model.response.GoodsContractRes;
import com.summ.model.response.ModelRes;
import com.summ.utils.ResponseUtil;
import com.summ.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 产品管理
 * @author summ
 */
@Controller
@RequestMapping("/goods")
public class GoodsController extends AutoMapperController{

    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(@RequestBody JGoodsContract jGoodsContract) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jGoodsContractMapper.insert(jGoodsContract));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/find")
    public Object find(@RequestBody GoodsReq map) {
        try {
            map.setPage(map.getSize() * (map.getPage() - 1));
            List<GoodsContractRes> goodsContractResList = jGoodsContractMapper.getGoodsList(map);
            /**产品banner图*/
            for (GoodsContractRes goodsContractRes:goodsContractResList){
                goodsContractRes.setBanner(Consts.goodsBannerUrlRes+goodsContractRes.getBanner());
            }
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", ResponseUtil.List2Map(goodsContractResList,jGoodsContractMapper.getGoodsListCount(map)));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 图片上传
     * @param jGoodsContract
     * @return
     */
    @ResponseBody
    @RequestMapping("/upload")
    public Object upload(@RequestBody JGoodsContract jGoodsContract,@RequestBody Map map) {
        try {
            Integer type = (Integer) map.get("type");
            /**type为1时表示上传banner图*/
            if (type==1){
                String fileName = "GB"+System.currentTimeMillis()+".png";
                if(StringUtil.generateImage(jGoodsContract.getBanner(), Consts.goodsBannerUrl + fileName)){
                    jGoodsContract.setBanner(fileName);
                    return new ModelRes(ModelRes.Status.SUCCESS,"操作成功 !",jGoodsContractMapper.updateById(jGoodsContract));
                }
            }
            /**否则表示上传详情页图*/
            else {
                String fileName = "good_"+jGoodsContract.getGoodsId()+".png";
                if(StringUtil.generateImage(jGoodsContract.getBanner(), Consts.goodsBannerUrl + fileName)){
                    jGoodsContract.setBanner(fileName);
                    return new ModelRes(ModelRes.Status.SUCCESS,"操作成功 !",null);
                }
            }
            return new ModelRes(ModelRes.Status.FAILED, "photo err !");
        } catch (Exception e)
        {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 更新产品信息，上下架产品
     * @param jGoodsContract
     * @return
     */
    @ResponseBody
    @RequestMapping("/putaway")
    public Object putaway(@RequestBody JGoodsContract jGoodsContract) {
        try {
            JGoodsContract jGoodsContract1 = jGoodsContractMapper.selectById(Long.valueOf(jGoodsContract.getGoodsId()));
            /**isDel字段表示上下架*/
            if (jGoodsContract1.getIsDel()==17){
                jGoodsContract.setIsDel(16);
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jGoodsContractMapper.updateById(jGoodsContract));
            }else {
                jGoodsContract.setIsDel(17);
                return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jGoodsContractMapper.updateById(jGoodsContract));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 更新产品信息
     * @param jGoodsContract
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestBody JGoodsContract jGoodsContract) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jGoodsContractMapper.updateById(jGoodsContract));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 获取产品成本列表
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/cost/find")
    public Object findCost(@RequestBody Map map) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", ResponseUtil.List2Map(jGoodsContractMapper.getGoodsCostList((Integer) map.get("goodsId"))));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 查询某个供应商的产品列表
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/supplier/find")
    public Object findByShop(@RequestBody Map map) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", ResponseUtil.List2Map(jGoodsContractMapper.getGoodsListBySupplier((Integer) map.get("supplierId"))));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    /**
     * 给某个供应商添加产品
     * @param jGoodsCost
     * @return
     */
    @ResponseBody
    @RequestMapping("/supplier/insert")
    public Object insertByShop(@RequestBody JGoodsCost jGoodsCost) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jGoodsCostMapper.insert(jGoodsCost));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/supplier/update")
    public Object updateByShop(@RequestBody JGoodsCost jGoodsCost) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jGoodsCostMapper.updateById(jGoodsCost));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }

    @ResponseBody
    @RequestMapping("/supplier/delete")
    public Object deleteByShop(@RequestBody JGoodsCost jGoodsCost) {
        try {
            return new ModelRes(ModelRes.Status.SUCCESS, "操作成功 !", jGoodsCostMapper.deleteById(jGoodsCost));
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelRes(ModelRes.Status.ERROR, "server err !");
        }
    }


}
