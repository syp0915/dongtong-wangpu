package com.dongtong.shop.service;

import com.alibaba.fastjson.JSON;
import com.dongtong.shop.JunitBaseTest;
import com.dongtong.shop.dto.*;
import com.dongtong.shop.manager.ShopManager;
import com.dongtong.shop.query.MapClerkQuery;
import com.dongtong.shop.query.MapClerkShopQuery;
import com.dongtong.shop.query.MapCustomerQuery;
import com.dongtong.shop.query.MapSummaryQuery;
import com.shfc.common.result.ResultDO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package com.dongtong.shop.service.ShopMapServiceTest
 * @Description: ShopMapService
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/8 18:21
 * version V1.0.0
 */
public class ShopMapServiceTest extends JunitBaseTest {
    @Autowired
    private ShopMapService shopMapService;
    @Autowired
    private ShopManager shopManager;

    @Test
    public void testQueryClerkRegionShopMap(){
        MapClerkQuery query = new MapClerkQuery();
        query.setType(1);
        //query.setDistrictId(310106L);
        query.setMaxLat("31.31353599973081");
        query.setMinLat("31.306170059650608");
        query.setMaxLon("121.50280003851093");
        query.setMinLon("121.4962064760677");
        ResultDO<List<ShopMapDTO>> resultDO = shopMapService.queryClerkRegionShopMap(query);

        Assert.assertTrue(resultDO.isSuccess());
    }

    @Test
    public void testQueryClerkShopMap(){
        MapClerkShopQuery query = new MapClerkShopQuery();
        query.setMaxLat("31.31353599973081");
        query.setMinLat("31.306170059650608");
        query.setMaxLon("121.50280003851093");
        query.setMinLon("121.4962064760677");
//        query.setBlockId(150L);
//        query.setQueryType(0);
//        query.setClerkId(1L);
        //query.setStatusList(Arrays.asList(new Integer[]{1,2}));
        ResultDO<List<ShopMapDetailDTO>> resultDO = shopMapService.queryClerkShopMap(query);

        Assert.assertTrue(resultDO.isSuccess());
    }

    @Test
    public void testFindShopSummaryById(){
        MapSummaryQuery query = new MapSummaryQuery();
        LatestShopDTO shopDTO = shopManager.getLatestShop();
        if(shopDTO != null){
            query.setShopId(shopDTO.getId());
            query.setLongitude("121.437817");
            query.setLatitude("31.107798");
            ResultDO<ShopMapSummaryDTO> resultDO = shopMapService.findShopSummaryById(query);
            Assert.assertTrue(resultDO.isSuccess());
        }
    }

    @Test
    public void testQueryCustomerRegionShopMap(){
        MapCustomerQuery query = new MapCustomerQuery();
        query.setType(1);
        query.setMaxLat("32.107798");
        query.setMinLat("30.107798");
        query.setMaxLon("122.407817");
        query.setMinLon("120.407817");
        List<Integer> rentList = new ArrayList<>();//租金
        rentList.add(1);rentList.add(2);rentList.add(3);rentList.add(4);rentList.add(5);
        query.setRentList(rentList);
        List<Integer> transferList = new ArrayList<>();//转让费集合
        transferList.add(1);transferList.add(2);transferList.add(3);
        query.setTransferList(rentList);
        List<Integer> areaList = new ArrayList<>();//面积集合
        areaList.add(null);areaList.add(null);
        query.setAreaList(areaList);
        Float width = 100f;//面宽
        query.setWidth(width);
        ResultDO<List<ShopMapDTO>> resultDO = shopMapService.queryCustomerRegionShopMap(query);
        if(resultDO.isSuccess()){
            System.out.println(JSON.toJSONString(resultDO.getData()));
        }else {
            System.out.println(resultDO.getErrMsg());
        }
    }
    @Test
    public void testQueryCustomerShopMap(){
        MapCustomerQuery query = new MapCustomerQuery();
        query.setBlockId(150L);
        List<Integer> rentList = new ArrayList<>();//租金
        rentList.add(1);rentList.add(2);rentList.add(3);rentList.add(4);rentList.add(5);
        query.setRentList(rentList);
        List<Integer> transferList = new ArrayList<>();//转让费集合
        transferList.add(1);transferList.add(2);transferList.add(3);
        query.setTransferList(rentList);
        List<Integer> areaList = new ArrayList<>();//面积集合
        areaList.add(null);areaList.add(null);
        query.setAreaList(areaList);
        Float width = 100f;//面宽
        query.setWidth(width);

        List<Long> featureTagList = new ArrayList<>();//特色
        featureTagList.add(1L);
        query.setFeatureTagList(featureTagList);
        List<Long> supportTagList = new ArrayList<>();//配套标签集合
        supportTagList.add(1L);
        query.setSupportTagList(supportTagList);
        ResultDO<List<ShopMapCustomerDetailDTO>> resultDO = shopMapService.queryCustomerShopMap(query);
        if(resultDO.isSuccess()){
            System.out.println(JSON.toJSONString(resultDO.getData()));
        }else {
            System.out.println(resultDO.getErrMsg());
        }

    }
    @Test
    public void testViewShopSummaryById(){
        MapSummaryQuery query = new MapSummaryQuery();
        query.setShopId(45L);
        ResultDO<ShopCustomerDTO> resultDO  = shopMapService.viewShopSummaryById(query);
        if(resultDO.isSuccess()){
            System.out.println(JSON.toJSONString(resultDO.getData()));
        }else {
            System.out.println(resultDO.getErrMsg());
        }
    }
}
