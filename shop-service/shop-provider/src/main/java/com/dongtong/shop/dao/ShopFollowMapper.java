package com.dongtong.shop.dao;

import com.dongtong.shop.domain.ShopFollow;
import com.dongtong.shop.dto.ShopFollowDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: ShopFollowMapper.java
 * @Description: 商铺跟进信息
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 15:09
 * version v1.0.0
 */
@Repository
public interface ShopFollowMapper {
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
    int insert(ShopFollow record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/02 15:09
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(ShopFollow record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 15:09
     * @param id
     * @return ShopFollow
     * @throws []
     */
    ShopFollow selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/02 15:09
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(ShopFollow record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 15:09
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(ShopFollow record);

    /**
     * 根据商铺id查询跟进列表
     * @param shopId
     * @return
     */
    List<ShopFollowDTO> getFollowListByShopId(@Param("shopId") Long shopId);

    /**
     * 查询7天未跟进的所有商铺
     * @return
     */
    List<ShopFollow> getFollowByTimeOut();
}