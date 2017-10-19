package com.dongtong.shop.dao;

import com.dongtong.shop.domain.ShopFeatureTagRel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: ShopFeatureTagRelMapper.java
 * @Description: 商铺特色标签
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 15:09
 * version v1.0.0
 */
@Repository
public interface ShopFeatureTagRelMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 15:09
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zm
     * @Date 2017/05/02 15:09
     * @param record
     * @return int
     * @throws []
     */
    int insert(ShopFeatureTagRel record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/02 15:09
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(ShopFeatureTagRel record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 15:09
     * @param id
     * @return ShopFeatureTagRel
     * @throws []
     */
    ShopFeatureTagRel selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/02 15:09
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(ShopFeatureTagRel record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 15:09
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(ShopFeatureTagRel record);
    /**
     * 批量插入商铺特色
     * @param featureList
     */
    int insertList(@Param("list") List<Long> featureList, @Param("shopId") Long shopId);

    /**
     * 根据商铺ID删除特色记录
     * @param shopId
     * @return
     */
    int deleteByShopId(Long shopId);
}