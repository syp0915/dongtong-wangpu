package com.dongtong.shop.dao;

import com.dongtong.shop.domain.ShopImg;
import com.dongtong.shop.dto.ShopImgDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: ShopImgMapper.java
 * @Description: 商铺图片
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 15:10
 * version v1.0.0
 */
@Repository
public interface ShopImgMapper {
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
    int insert(ShopImg record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/02 15:10
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(ShopImg record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 15:10
     * @param id
     * @return ShopImg
     * @throws []
     */
    ShopImg selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/02 15:10
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(ShopImg record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 15:10
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(ShopImg record);

    /**
     * 批量插入商铺图片
     * @param shopList
     */
    int insertList(@Param("list") List<ShopImgDTO> shopList,@Param("shopId") Long shopId);

    /**
     * 根据商铺ID删除店铺图片
     * @param shopId
     * @return
     */
    int deleteByShopId(Long shopId);

    /**
     * 铺位照片查询
     * @param shopId
     * @return
     */
    List<ShopImgDTO> queryShopImg(Long shopId);

}