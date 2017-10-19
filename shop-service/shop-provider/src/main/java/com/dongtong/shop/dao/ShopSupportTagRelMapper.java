package com.dongtong.shop.dao;

import com.dongtong.shop.domain.ShopSupportTagRel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: ShopSupportTagRelMapper.java
 * @Description: 商铺配套标签
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 15:13
 * version v1.0.0
 */
@Repository
public interface ShopSupportTagRelMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 15:13
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zm
     * @Date 2017/05/02 15:13
     * @param record
     * @return int
     * @throws []
     */
    int insert(ShopSupportTagRel record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/02 15:13
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(ShopSupportTagRel record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 15:13
     * @param id
     * @return ShopSupportTagRel
     * @throws []
     */
    ShopSupportTagRel selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/02 15:13
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(ShopSupportTagRel record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 15:13
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(ShopSupportTagRel record);

    /**
     * 批量插入商铺配套
     * @param supportList
     */
    int insertList(@Param("list") List<Long> supportList, @Param("shopId") Long shopId);

    /**
     * 根据商铺ID删除配套设备
     * @param shopId
     * @return
     */
    int deleteByShopId(Long shopId);
}