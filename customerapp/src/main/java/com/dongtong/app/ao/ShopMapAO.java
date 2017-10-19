package com.dongtong.app.ao;

import com.dongtong.shop.dto.ShopCustomerDTO;
import com.dongtong.shop.dto.ShopMapCustomerDetailDTO;
import com.dongtong.shop.dto.ShopMapDTO;
import com.dongtong.shop.query.MapCustomerQuery;
import com.dongtong.shop.query.MapSummaryQuery;
import com.dongtong.shop.service.ShopMapService;
import com.shfc.common.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package com.dongtong.app.ao.ShopMapAO
 * @Description: 客户端商铺地图
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author xiehaibin
 * @date 2017/5/15 14:06
 * version V1.0.0
 */
@Service
public class ShopMapAO {
    @Autowired
    private ShopMapService shopMapService;

    /**
     * 客户端商铺地图-区域板块层级店铺数量
     * @param query
     * @return
     */
    public ResultDO<List<ShopMapDTO>> queryCustomerRegionShopMap(MapCustomerQuery query){
        return shopMapService.queryCustomerRegionShopMap(query);
    }

    /**
     * 用户端商铺地图-店铺级别的地图
     * @param query
     * @return
     */
    public ResultDO<List<ShopMapCustomerDetailDTO>> queryCustomerShopMap(MapCustomerQuery query){
        return shopMapService.queryCustomerShopMap(query);
    }

    /**
     * 用户端商铺地图-点击商铺查看概要信息
     * @param query
     * @return
     */
    public ResultDO<ShopCustomerDTO> viewShopSummaryById(MapSummaryQuery query){
        return shopMapService.viewShopSummaryById(query);
    }
}
