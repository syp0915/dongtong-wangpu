package com.dongtong.customer.dao;

import com.dongtong.customer.domain.CustomerVisitShop;
import com.dongtong.customer.dto.TenantAndLandlordDTO;
import com.dongtong.customer.dto.VisitDTO;
import com.dongtong.customer.dto.req.VisitShopReqDTO;
import com.dongtong.customer.dto.resp.VisitShopRespDTO;
import com.dongtong.customer.dto.resp.VisitedStatisticDTO;
import com.shfc.mybatis.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: com.dongtong.customer.dao.CustomerVisitShopMapper.java
 * @Description: 用户约看表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:47
 * version v1.0.0
 */
@Repository
public interface CustomerVisitShopMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:47
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zm
     * @Date 2017/05/02 14:47
     * @param record
     * @return int
     * @throws []
     */
    int insert(CustomerVisitShop record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/02 14:47
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(CustomerVisitShop record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:47
     * @param id
     * @return com.dongtong.customer.domain.CustomerVisitShop
     * @throws []
     */
    CustomerVisitShop selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/02 14:47
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(CustomerVisitShop record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:47
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(CustomerVisitShop record);

    /**
     * @Description: 分页获取全部数据库记录
     * @Title selectByPage
     * @Author lv bin
     * @Date 2017/05/10 13:39
     * @return null
     * @throws []
     */
    List<VisitShopRespDTO> selectByPage(@Param("visitShopReqDTO") VisitShopReqDTO visitShopReqDTO, Page page);

    VisitShopRespDTO selectById(Long id);

    /**
     * @Description: 店铺是否被约看
     * @Author zhoumin
     */
    Long queryShopVisitInfo(@Param("shopId") Long shopId , @Param("customerId") Long customerId);


    /**
     * 约看总数和排名统计接口
     * @return
     */
    List<VisitedStatisticDTO> myVisitStatistic();

    /**
     * 根据参数获取约看记录
     * @return
     */
    List<CustomerVisitShop> queryShopVisitById(@Param("shopId") Long shopId,@Param("customerId") Long customerId);

    /**
     * 根据参数获取约看记录
     * @return
     */
    CustomerVisitShop queryVisitByParams(@Param("customerId") Long customerId,@Param("shopId") Long shopId);


    int getDeadTimeNum(VisitShopReqDTO visitShopReqDTO);


    List<VisitShopRespDTO> selectNeedByPage(@Param("visitShopReqDTO") VisitShopReqDTO visitShopReqDTO, Page page);

    /**
     * 获取所有待办列表
     * @return
     */
    List<VisitShopRespDTO> pendingList();

    /**
     * 查看用户最近一次约看
     * @Author zhoumin
     * @param customerId
     * @return
     */
    VisitDTO queryLastVisit(@Param("customerId") Long customerId);

    /**
     * 用户删除约看
     * @param shopId
     * @param customerId
     * @return
     */
    int cannelShopVisitByShopId(@Param("shopId") Long shopId,@Param("customerId") Long customerId);

    TenantAndLandlordDTO getTenantAndLandlord(@Param("id") Long id);
}