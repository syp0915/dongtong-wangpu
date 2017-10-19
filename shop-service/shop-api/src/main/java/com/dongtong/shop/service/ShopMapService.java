package com.dongtong.shop.service;

import com.dongtong.shop.dto.*;
import com.dongtong.shop.query.MapClerkQuery;
import com.dongtong.shop.query.MapClerkShopQuery;
import com.dongtong.shop.query.MapCustomerQuery;
import com.dongtong.shop.query.MapSummaryQuery;
import com.shfc.common.result.ResultDO;

import java.util.List;

/**
 * @Package com.dongtong.shop.service.ShopMapService
 * @Description: 商铺地图service
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/8 18:19
 * version V1.0.0
 */
public interface ShopMapService {

    /**
     * 业务端商铺地图-区域板块层级店铺数量
     * @Author lv bin
     * @param query
     * @return
     */
    ResultDO<List<ShopMapDTO>> queryClerkRegionShopMap(MapClerkQuery query);

    /**
     * 业务端商铺地图-店铺级别的地图
     * @Author lv bin
     * @param query
     * @return
     */
    ResultDO<List<ShopMapDetailDTO>> queryClerkShopMap(MapClerkShopQuery query);

    /**
     * 业务端商铺地图-点击商铺查看概要信息
     * @Author lv bin
     * @param query
     * @return
     */
    ResultDO<ShopMapSummaryDTO> findShopSummaryById(MapSummaryQuery query);
    /**
     * 客户端商铺地图-区域板块层级店铺数量
     * 包含筛选条件
     * @Author xiehb
     * @param query
     * @return
     */
    ResultDO<List<ShopMapDTO>> queryCustomerRegionShopMap(MapCustomerQuery query);
    /**
     * 用户端商铺地图-店铺级别的地图
     * 包含筛选条件
     * @Author xiehb
     * @param query
     * @return
     */
    ResultDO<List<ShopMapCustomerDetailDTO>> queryCustomerShopMap(MapCustomerQuery query);

    /**
     * 用户端商铺地图-点击商铺查看概要信息
     * @Author xiehb
     * @param query
     * @return
     */
    ResultDO<ShopCustomerDTO> viewShopSummaryById(MapSummaryQuery query);

    /**
     * 业务端线索地图-区域板块层级线索数量
     * @Author xiehb
     */
    ResultDO<List<ShopMapDTO>> queryClerkRegionHintMap(MapClerkQuery query);
}
