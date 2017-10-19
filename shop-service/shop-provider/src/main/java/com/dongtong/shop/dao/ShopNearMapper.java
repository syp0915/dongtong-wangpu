package com.dongtong.shop.dao;

import com.dongtong.shop.domain.ShopNear;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Package: ShopNearMapper.java
 * @Description: 邻铺
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 15:10
 * version v1.0.0
 */
@Repository
public interface ShopNearMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 15:10
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zm
     * @Date 2017/05/02 15:10
     * @param record
     * @return int
     * @throws []
     */
    int insert(ShopNear record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/02 15:10
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(ShopNear record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 15:10
     * @param id
     * @return ShopNear
     * @throws []
     */
    ShopNear selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/02 15:10
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(ShopNear record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 15:10
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(ShopNear record);

    /**
     * 查询临铺
     * @param id
     * @param shopId
     * @return
     */
    ShopNear selectNearByIdAndShopId(@Param("id") Long id,@Param("shopId") Long shopId);

    /**
     * 查询临铺位置信息
     * @param shopId
     * @param nearSeat
     * @return
     */
    ShopNear selectNearByShopIdAndNearSeat(@Param("shopId") Long shopId,@Param("nearSeat") int nearSeat);
}