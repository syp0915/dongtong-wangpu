package com.dongtong.shop.dao;

import com.dongtong.shop.domain.Shop;
import com.dongtong.shop.dto.*;
import com.dongtong.shop.query.*;
import com.shfc.mybatis.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Package: com.dongtong.shop.dao.ShopMapper.java
 * @Description: 商铺
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2017
 * All right reserved.
 * Author lv bin
 * @date 2017/05/04 14:55
 * version v1.0.0
 */
@Repository
public interface ShopMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author lv bin
     * @Date 2017/05/04 14:55
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author lv bin
     * @Date 2017/05/04 14:55
     * @param record
     * @return int
     * @throws []
     */
    int insert(Shop record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author lv bin
     * @Date 2017/05/04 14:55
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(Shop record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author lv bin
     * @Date 2017/05/04 14:55
     * @param id
     * @return com.dongtong.shop.domain.Shop
     * @throws []
     */
    Shop selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author lv bin
     * @Date 2017/05/04 14:55
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(Shop record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author lv bin
     * @Date 2017/05/04 14:55
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(Shop record);

    /**
     * 客户端查询旺铺列表
     * @Author lv bin
     * @param page
     * @return
     */
    List<ShopCustomerDTO> queryShopByCustomer(Page<ShopCustomerDTO> page);

    /**
     * 业务端查询旺铺列表
     * @Author lv bin
     * @param page
     * @return
     */
    List<ShopClerkDTO> queryShopByClerk(Page<ShopClerkDTO> page);

    /**
     * 获取最近更新的一个旺铺
     * @Author lv bin
     * @return
     */
    LatestShopDTO getLatestShop();

    /**
     * 查询某个用户最近正在转让的一个旺铺
     * @Author lv bin
     * @return
     */
    LatestShopDTO getLatestShopByPhone(@Param("phone")String phone);

    /**
     * 查询本周我的房源浏览数量
     * @Author lv bin
     * @param phone
     * @return
     */
    int queryThisWeekScanCount(@Param("phone") String phone);

    /**
     * 本周被浏览百分比
     * @Author lv bin
     * @return
     */
    List<String> queryThisWeekScanRate();

    /**
     * 我发布的金铺列表接口
     * @Author lv bin
     * @param page
     * @return
     */
    List<ShopCustomerPublishDTO> queryMyPublishShop(Page<ShopCustomerPublishDTO> page);

    /**
     * 客户端统计数目接口
     * @Author zhoumin
     * @return
     */
    ShopCountDTO countShopFromClient();

    /**
     * 业务端商铺统计数目
     * @Author zhoumin
     * @param clerkId
     * @return
     */
    ShopCountDTO countShopFromService(Long clerkId);

    /**
     * 业务端商铺地图-区域板块层级店铺数量
     * @Author lv bin
     * @param query
     * @return
     */
    List<ShopMapDTO> queryClerkRegionShopMap(@Param("query") MapClerkQuery query);

    /**
     * 业务端商铺地图-店铺级别的地图
     * @Author lv bin
     * @param query
     * @return
     */
    List<ShopMapDetailDTO> queryClerkShopMap(@Param("query")MapClerkShopQuery query);

    /**
     * 业务端线索地图-区域板块层级线索数量
     * @Author xiehb
     * @param query
     * @return
     */
    List<ShopMapDTO> queryClerkRegionHintMap(@Param("query") MapClerkQuery query);


    /**
     * 业务端商铺地图-点击商铺查看概要信息
     * @Author lv bin
     * @param query
     * @return
     */
    ShopMapSummaryDTO findShopSummaryById(@Param("query")MapSummaryQuery query);

    /**
     * 查询商铺详情
     * @Author zhoumin
     * @param id
     * @return
     */
    ShopDetailDTO queryShopDetail(@Param("id") Long id);

    /**
     * 客户端
     * 收藏的商铺列表
     * @Author xiehb
     * @return
     */
    List<ShopCollectedCustomerDTO> shopCollectedList(Page page);
    /**
     * 客户端
     * 浏览的商铺列表
     * @Author xiehb
     * @return
     */
    List<ShopBrowseCustomerDTO> shopBrowseList(Page page);

    /**
     * 查询业务员信息
     * @Author zhoumin
     * @param id
     * @return
     */
    ClerkDTO queryClerkByShopId(Long id);

    /**
     * 查询商铺和业务员相关信息
     * @param shopId
     * @return
     * add by xiehb
     */
    ClerkAndShopInfoDTO queryClerkAndShopInfoByShopId(@Param("shopId") Long shopId);
    /**
     * 客户端商铺地图-区域板块层级店铺数量
     * 包含筛选条件
     * @Author xiehb
     * @param query
     * @return
     */
    List<ShopMapDTO> queryCustomerRegionShopMap(@Param("query") MapCustomerQuery query);

    /**
     * 用户端商铺地图-店铺级别的地图
     * 包含筛选条件
     * @Author xiehb
     * @param query
     * @return
     */
    List<ShopMapCustomerDetailDTO> queryCustomerShopMap(@Param("query")MapCustomerQuery query);
    /**
     * 用户端商铺地图-点击商铺查看概要信息
     * @Author xiehb
     * @param query
     * @return
     */
    ShopCustomerDTO viewShopSummaryById(@Param("query")MapSummaryQuery query);

    /**
     * 客户端
     * 约看的商铺列表
     * @return
     */
    List<ShopVisitCustomerDTO> shopVisitedList(Page page);
    /**
     * 客户端
     * 统计实时店铺数
     * @return
     */
    Integer countShop();

    /**
     * 查询二维码，状态为未使用
     * @param shopNum
     * @return 等于1条，说明编号存在，并且未被使用
     */
    Integer queryShopNumber(String shopNum);

    /**
     * 根据二维码查询商铺
     * @param shopNum
     * @return
     */
    Integer queryShopByShopNum(String shopNum);

    /**
     * 查询临铺信息
     * add by xiehb
     * @param shopId
     * @return
     */
    List<ShopNearDetailDTO> selectNearShop(@Param("id") Long shopId);

    /**
     * 根据二维码编号查找商铺id
     * @param shopCode
     * @return
     */
    Long getShopId(@Param("shopCode")String shopCode);

    /**
     * @description 旺铺导购，推荐旺铺查询
     * @package com.dongtong.shop.dao
     * @author chenxs
     * @date 2017/8/11 0011 16:22
     * @param shopRecomendQuery
     * @return ShopRecomendListDTO
     *
     * v1.2新增
     */
    ShopRecomendListDTO selectRecomendShopById(ShopRecomendQuery shopRecomendQuery);
    /**
     * @Description: 根据线索获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author lv bin
     * @Date 2017/05/04 14:55
     * @param hintId
     * @return com.dongtong.shop.domain.Shop
     * @throws []
     */
    Shop selectByHitId(Long hintId);

}