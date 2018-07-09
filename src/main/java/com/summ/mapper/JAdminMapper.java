package com.summ.mapper;

import com.summ.model.JAdmin;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.summ.model.request.PaginateReq;
import com.summ.model.response.AccessRes;
import com.summ.model.response.AdminRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * JAdmin 表数据库控制层接口
 * @author summ
 */
public interface JAdminMapper extends BaseMapper<JAdmin> {



    /**
     * 根据id查找
     * @param id
     * @return
     */
    JAdmin getAdminById(Integer id);

    /**
     * 获取列表
     * @param paginateReq
     * @return
     */
    List<AdminRes> getAdminList(@Param("paginateReq") PaginateReq paginateReq);

    /**
     * 获取该管理员权限页面列表
     * @param id
     * @return
     */
    List<AccessRes> getAccess(int id);

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    int deleteAdmin(int id);

    /**
     * 获取总数
     * @return
     */
    int getCount(@Param("paginateReq") PaginateReq paginateReq);

}