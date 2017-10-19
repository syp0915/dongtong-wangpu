package com.dongtong.customer.dao;

import com.dongtong.customer.domain.CustomerCollectedShop;
import com.dongtong.customer.dto.resp.CollectStatisticDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: com.dongtong.customer.dao.CustomerCollectedShopMapper.java
 * @Description: 用户收藏的商铺
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 14:42
 * version v1.0.0
 */
@Repository
public interface CustomerCollectedShopMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:42
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zm
     * @Date 2017/05/02 14:42
     * @param record
     * @return int
     * @throws []
     */
    int insert(CustomerCollectedShop record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/02 14:42
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(CustomerCollectedShop record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:42
     * @param id
     * @return com.dongtong.customer.domain.CustomerCollectedShop
     * @throws []
     */
    CustomerCollectedShop selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/02 14:42
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(CustomerCollectedShop record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 14:42
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(CustomerCollectedShop record);

    /**
     * 收藏总数和排名统计接口
     * @return
     */
    List<CollectStatisticDTO> myCollectStatistic();

    /**
     * @Description: 店铺是否被收藏
     * @Author zhoumin
     */
    Long queryShopCollectedInfo(@Param("shopId") Long shopId , @Param("customerId") Long customerId);

    /**
     * 根据商铺Id删除当前用户的收藏记录
     * @param shopId
     * @param customerId
     * @Author xiehb
     */
    int deleteByShopId(@Param("shopId") Long shopId,@Param("customerId")  Long customerId);
}