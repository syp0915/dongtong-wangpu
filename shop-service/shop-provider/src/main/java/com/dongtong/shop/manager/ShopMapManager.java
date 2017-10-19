package com.dongtong.shop.manager;

import com.dongtong.shop.dao.ShopMapper;
import com.dongtong.shop.dto.*;
import com.dongtong.shop.query.MapClerkQuery;
import com.dongtong.shop.query.MapClerkShopQuery;
import com.dongtong.shop.query.MapCustomerQuery;
import com.dongtong.shop.query.MapSummaryQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package com.dongtong.shop.manager.ShopMapManager
 * @Description: 商铺地图
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/8 18:22
 * version V1.0.0
 */
@Service
public class ShopMapManager {

    @Autowired
    private ShopMapper shopMapper;

    public List<ShopMapDTO> queryClerkRegionShopMap(MapClerkQuery query){

        return shopMapper.queryClerkRegionShopMap(query);
    }

    public List<ShopMapDetailDTO> queryClerkShopMap(MapClerkShopQuery query){
        return shopMapper.queryClerkShopMap(query);
    }

    public ShopMapSummaryDTO findShopSummaryById(MapSummaryQuery query){
        return shopMapper.findShopSummaryById(query);
    }

    public List<ShopMapDTO> queryCustomerRegionShopMap(MapCustomerQuery query){
        return shopMapper.queryCustomerRegionShopMap(query);
    }

    public List<ShopMapCustomerDetailDTO> queryCustomerShopMap(MapCustomerQuery query){
        return shopMapper.queryCustomerShopMap(query);
    }

    public ShopCustomerDTO viewShopSummaryById(MapSummaryQuery query){
        return shopMapper.viewShopSummaryById(query);
    }

    /**
     * 业务端线索地图-区域板块层级线索数量
     * @Author xiehb
     */
    public List<ShopMapDTO> queryClerkRegionHintMap(MapClerkQuery query){
        return shopMapper.queryClerkRegionHintMap(query);
    }
}
