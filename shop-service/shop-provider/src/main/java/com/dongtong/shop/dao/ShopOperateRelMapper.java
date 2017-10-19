package com.dongtong.shop.dao;

import com.dongtong.shop.domain.ShopOperateRel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: ShopOperateRelMapper.java
 * @Description: 商铺经营范围关系
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 15:11
 * version v1.0.0
 */
@Repository
public interface ShopOperateRelMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 15:11
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author zm
     * @Date 2017/05/02 15:11
     * @param record
     * @return int
     * @throws []
     */
    int insert(ShopOperateRel record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/02 15:11
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(ShopOperateRel record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 15:11
     * @param id
     * @return ShopOperateRel
     * @throws []
     */
    ShopOperateRel selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/02 15:11
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(ShopOperateRel record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 15:11
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(ShopOperateRel record);

    /**
     * 批量插入商铺经营范围
     * @param operateRelList
     * @param shopId
     * @return
     */
    int insertList(@Param("list") List<Long> operateRelList, @Param("shopId") Long shopId);
    /**
     * 根据商铺ID删除店铺可经营记录
     * @param shopId
     * @return
     */
    int deleteByShopId(Long shopId);
}