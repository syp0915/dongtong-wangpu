package com.dongtong.shop.dao;

import com.dongtong.shop.domain.ShopStationRel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: ShopStationRelMapper.java
 * @Description: 商铺地铁站关系表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 15:12
 * version v1.0.0
 */
@Repository
public interface ShopStationRelMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 15:12
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zm
     * @Date 2017/05/02 15:12
     * @param record
     * @return int
     * @throws []
     */
    int insert(ShopStationRel record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/02 15:12
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(ShopStationRel record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 15:12
     * @param id
     * @return ShopStationRel
     * @throws []
     */
    ShopStationRel selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/02 15:12
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(ShopStationRel record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 15:12
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(ShopStationRel record);

    /**
     * @Description: 批量新增
     * @Title batchInsert
     * @Author jon lv
     * @Date 2017/05/02 15:12
     * @param list
     * @return int
     * @throws []
     */
    int batchInsert(java.util.List<ShopStationRel> list);

    /**
     * 商铺地铁站关系跑批
     * @param longitude
     * @param latitude
     * @return
     */
    List<ShopStationRel> shopStationRelJob(@Param("longitude")String longitude,
                                           @Param("latitude")String latitude);

    /**
     * 根据商铺id和地铁站id查询记录
     * @param shopId
     * @param stationId
     * @return
     */
    ShopStationRel findRelByShopIdAndStationId(@Param("shopId")Long shopId,
                                               @Param("stationId")Long stationId);

    /**
     * 根据商铺id删除地铁站关系
     * @param shopId
     * @return
     */
    int deleteByShopId(@Param("shopId")Long shopId);
}