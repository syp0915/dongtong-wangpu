package com.dongtong.shop.dao;

import com.dongtong.shop.domain.ShopStatusFlow;
import org.springframework.stereotype.Repository;

/**
 * @Package: ShopStatusFlowMapper.java
 * @Description: 店铺状态流转记录
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 15:12
 * version v1.0.0
 */
@Repository
public interface ShopStatusFlowMapper {
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
    int insert(ShopStatusFlow record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/02 15:12
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(ShopStatusFlow record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 15:12
     * @param id
     * @return ShopStatusFlow
     * @throws []
     */
    ShopStatusFlow selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/02 15:12
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(ShopStatusFlow record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 15:12
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(ShopStatusFlow record);
}