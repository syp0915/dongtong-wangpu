package com.dongtong.app.controller;


import com.dongtong.app.annotation.UnLoginLimit;
import com.dongtong.app.ao.ShopMapAO;
import com.dongtong.shop.dto.ShopCustomerDTO;
import com.dongtong.shop.dto.ShopMapCustomerDetailDTO;
import com.dongtong.shop.dto.ShopMapDTO;
import com.dongtong.shop.query.MapCustomerQuery;
import com.dongtong.shop.query.MapSummaryQuery;
import com.shfc.common.result.ResultDO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Package com.dongtong.app.controller.ShopMapController
 * @Description: 客户端商铺地图
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author xiehaibin
 * @date 2017/5/15 14:04
 * version V1.0.0
 */
@Api(tags = "ShopMapController", description = "客户端商铺地图接口")
@RestController
@RequestMapping(value = "/api/map",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ShopMapController {
    @Autowired
    private ShopMapAO shopMapAO;

    @UnLoginLimit
    @ApiOperation(value = "客户端商铺地图-区域板块层级店铺数量", notes = "客户端商铺地图-区域板块层级店铺数量")
    @RequestMapping(value = "/regionShop/{version:.+}", method = RequestMethod.POST)
    public ResultDO<List<ShopMapDTO>> regionShop(
            @ApiParam(name="query",value="请求参数", required=true)@RequestBody MapCustomerQuery query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){

        return shopMapAO.queryCustomerRegionShopMap(query);
    }

    @UnLoginLimit
    @ApiOperation(value = "客户端商铺地图-店铺级别的地图", notes = "客户端商铺地图-店铺级别的地图")
    @RequestMapping(value = "/shop/{version:.+}", method = RequestMethod.POST)
    public ResultDO<List<ShopMapCustomerDetailDTO>> shop(
            @ApiParam(name="query",value="请求参数", required=false)@RequestBody(required = false) MapCustomerQuery query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){

        return shopMapAO.queryCustomerShopMap(query);
    }

    @UnLoginLimit
    @ApiOperation(value = "客户端商铺地图-点击商铺查看概要信息", notes = "客户端商铺地图-点击商铺查看概要信息")
    @RequestMapping(value = "/summary/{version:.+}", method = RequestMethod.POST)
    public ResultDO<ShopCustomerDTO> summary(
            @ApiParam(name="query",value="请求参数", required=true)@RequestBody MapSummaryQuery query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){

        return shopMapAO.viewShopSummaryById(query);
    }
}
