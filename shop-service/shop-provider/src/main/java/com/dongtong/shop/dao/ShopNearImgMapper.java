package com.dongtong.shop.dao;

import com.dongtong.shop.domain.ShopNearImg;
import com.dongtong.shop.dto.ShopImgDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: ShopNearImgMapper.java
 * @Description: 邻铺图片
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017 
 * All right reserved.
 * Author zm
 * @date 2017/05/02 15:10
 * version v1.0.0
 */
@Repository
public interface ShopNearImgMapper {
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
    int insert(ShopNearImg record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author zm
     * @Date 2017/05/02 15:10
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(ShopNearImg record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 15:10
     * @param id
     * @return ShopNearImg
     * @throws []
     */
    ShopNearImg selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author zm
     * @Date 2017/05/02 15:10
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(ShopNearImg record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author zm
     * @Date 2017/05/02 15:10
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(ShopNearImg record);

    /**
     * 批量插入临铺图片
     * @param shopList
     */
    int insertList(@Param("list") List<ShopImgDTO> shopList, @Param("nearId") Long nearId);

    /**
     * 根据临铺ID删除临铺图片
     * @param nearId
     * @return
     */
    int deleteByNearId(Long nearId);

    /**
     * 查询临铺图片
     * @param nearId
     * @return
     */
    List<ShopImgDTO> selectNearImg(Long nearId);
}