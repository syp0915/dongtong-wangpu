package com.dongtong.app.ao;

import com.dongtong.app.utils.HttpSessionUtils;
import com.dongtong.shop.dto.ShopMapDTO;
import com.dongtong.shop.dto.ShopMapDetailDTO;
import com.dongtong.shop.dto.ShopMapSummaryDTO;
import com.dongtong.shop.query.MapClerkQuery;
import com.dongtong.shop.query.MapClerkShopQuery;
import com.dongtong.shop.query.MapSummaryQuery;
import com.dongtong.shop.service.ShopMapService;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package com.dongtong.app.ao.ShopMapAO
 * @Description: ShopMapAO
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/10 15:45
 * version V1.0.0
 */
@Service
public class ShopMapAO {
    @Autowired
    private ShopMapService shopMapService;

    /**
     * 业务端商铺地图-区域板块层级店铺数量
     * @Author lv bin
     * @param query
     * @return
     */
    public ResultDO<List<ShopMapDTO>> queryClerkRegionShopMap(MapClerkQuery query){

        return shopMapService.queryClerkRegionShopMap(query);
    }

    /**
     * 业务端商铺地图-店铺级别的地图
     * @Author lv bin
     * @param query
     * @return
     */
    public ResultDO<List<ShopMapDetailDTO>> queryClerkShopMap(MapClerkShopQuery query){

        query.setClerkId(HttpSessionUtils.getCurrentAppUserId());

        return shopMapService.queryClerkShopMap(query);
    }

    /**
     * 业务端商铺地图-点击商铺查看概要信息
     * @Author lv bin
     * @param query
     * @return
     */
    public ResultDO<ShopMapSummaryDTO> findShopSummaryById(MapSummaryQuery query){

        return shopMapService.findShopSummaryById(query);
    }
    /**
     * 业务端线索地图-区域板块层级线索数量
     * @Author xiehb
     * @param query
     * @return
     */
    public ResultDO<List<ShopMapDTO>> queryClerkRegionHintMap(MapClerkQuery query){

        return shopMapService.queryClerkRegionHintMap(query);
    }
}
