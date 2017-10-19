package com.dongtong.shop.dao;

import com.dongtong.shop.domain.ShopFollowTag;
import org.springframework.stereotype.Repository;

/**
 * @Package: com.dongtong.shop.dao.ShopFollowTagMapper.java
 * @Description: 商铺跟进标签表
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author xiehb
 * @date 2017/08/03 18:32
 * version v1.2.0
 */
@Repository
public interface ShopFollowTagMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author xiehb
     * @Date 2017/08/03 18:32
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author xiehb
     * @Date 2017/08/03 18:32
     * @param record
     * @return int
     * @throws []
     */
    int insert(ShopFollowTag record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author xiehb
     * @Date 2017/08/03 18:32
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(ShopFollowTag record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author xiehb
     * @Date 2017/08/03 18:32
     * @param id
     * @return com.dongtong.shop.domain.ShopFollowTag
     * @throws []
     */
    ShopFollowTag selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author xiehb
     * @Date 2017/08/03 18:32
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(ShopFollowTag record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author xiehb
     * @Date 2017/08/03 18:32
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(ShopFollowTag record);

    int batchInsert(java.util.List<ShopFollowTag> list);
}