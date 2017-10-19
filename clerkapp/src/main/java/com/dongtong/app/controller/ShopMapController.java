package com.dongtong.app.controller;

import com.dongtong.app.ao.ShopMapAO;
import com.dongtong.shop.dto.ShopMapDTO;
import com.dongtong.shop.dto.ShopMapDetailDTO;
import com.dongtong.shop.dto.ShopMapSummaryDTO;
import com.dongtong.shop.query.MapClerkQuery;
import com.dongtong.shop.query.MapClerkShopQuery;
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
 * @Description: 商铺接口
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/4 17:00
 * version V1.0.0
 */
@Api(tags = "ShopMapController", description = "商铺地图接口")
@RestController
@RequestMapping(value = "/api/map",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ShopMapController {

    @Autowired
    private ShopMapAO shopMapAO;

    @ApiOperation(value = "业务端商铺地图-区域板块层级店铺数量", notes = "业务端商铺地图-区域板块层级店铺数量")
    @RequestMapping(value = "/regionShop/{version:.+}", method = RequestMethod.POST)
    public ResultDO<List<ShopMapDTO>> regionShop(
            @ApiParam(name="query",value="请求参数", required=true)@RequestBody MapClerkQuery query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){

        return shopMapAO.queryClerkRegionShopMap(query);
    }

    @ApiOperation(value = "业务端商铺地图-店铺级别的地图", notes = "业务端商铺地图-店铺级别的地图")
    @RequestMapping(value = "/shop/{version:.+}", method = RequestMethod.POST)
    public ResultDO<List<ShopMapDetailDTO>> shop(
            @ApiParam(name="query",value="请求参数", required=true)@RequestBody MapClerkShopQuery query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){

        return shopMapAO.queryClerkShopMap(query);
    }

    @ApiOperation(value = "业务端商铺地图-点击商铺查看概要信息", notes = "业务端商铺地图-点击商铺查看概要信息")
    @RequestMapping(value = "/summary/{version:.+}", method = RequestMethod.POST)
    public ResultDO<ShopMapSummaryDTO> summary(
            @ApiParam(name="query",value="请求参数", required=true)@RequestBody MapSummaryQuery query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){

        return shopMapAO.findShopSummaryById(query);
    }

    @ApiOperation(value = "业务端线索地图-区域板块层级线索数量", notes = "业务端线索地图-区域板块层级线索数量")
    @RequestMapping(value = "/regionHint/{version:.+}", method = RequestMethod.POST)
    public ResultDO<List<ShopMapDTO>> regionHint(
            @ApiParam(name="query",value="请求参数", required=true)@RequestBody MapClerkQuery query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){

        return shopMapAO.queryClerkRegionHintMap(query);
    }
}
