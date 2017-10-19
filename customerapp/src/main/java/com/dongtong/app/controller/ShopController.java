package com.dongtong.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.dongtong.app.annotation.UnLoginLimit;
import com.dongtong.app.ao.ShopAO;
import com.dongtong.customer.domain.CustomerCollectedShop;
import com.dongtong.customer.domain.CustomerLiaison;
import com.dongtong.customer.dto.CustomerCorrectDTO;
import com.dongtong.customer.dto.resp.StatisticDTO;
import com.dongtong.shop.dto.*;
import com.dongtong.shop.query.BaseQuery;
import com.dongtong.shop.query.ShopCollectedQuery;
import com.dongtong.shop.query.ShopCustomerQuery;
import com.shfc.common.result.ResultDO;
import com.shfc.mybatis.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Package com.dongtong.app.controller.ShopController
 * @Description: 商铺接口
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/4 17:00
 * version V1.0.0
 */
@Api(tags = "ShopController", description = "商铺接口")
@RestController
@RequestMapping(value = "/api/shop",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ShopController {
    @Autowired
    private ShopAO shopAO;

    @UnLoginLimit
    @ApiOperation(value = "商铺筛选", notes = "商铺筛选")
    @RequestMapping(value = "/queryShop/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Page<ShopCustomerDTO>> queryShop(
            @ApiParam(name="query",value="筛选参数", required=true)@RequestBody ShopCustomerQuery query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){

        return shopAO.queryShopByCustomer(query);
    }

    @ApiOperation(value = "旺铺被看总数和排名统计", notes = "旺铺被看总数和排名统计")
    @RequestMapping(value = "/queryMyShopScanCount/{version:.+}", method = RequestMethod.POST)
    public ResultDO<ShopScanCountDTO> queryMyShopScanCount(
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){

        return shopAO.queryMyShopScanCount();
    }

    @ApiOperation(value = "我发布的金铺列表接", notes = "我发布的金铺列表")
    @RequestMapping(value = "/queryMyPublishShop/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Page<ShopCustomerPublishDTO>> queryMyPublishShop(
            @ApiParam(name="query",value="请求参数", required=false)@RequestBody(required = false) BaseQuery query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){

        return shopAO.queryMyPublishShop(query);
    }

    @ApiOperation(value = "商铺下架", notes = "商铺下架")
    @RequestMapping(value = "/unDoShop/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Boolean> unDoShop(
            @ApiParam(name="dto",value="请求参数", required=true)@RequestBody ShopUndoDTO dto,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){

        return shopAO.unDoShop(dto);
    }


    @ApiOperation(value = "我收藏的商铺-排名统计", notes = "我收藏的商铺-排名统计")
    @RequestMapping(value = "/myCollectStatistic/{version:.+}", method = RequestMethod.POST)
    public ResultDO<StatisticDTO> myCollectStatistic(
            @ApiParam(name="dto",value="请求参数", required=false)@RequestBody(required = false) JSONObject object,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return shopAO.myCollectStatistic();
    }

    @ApiOperation(value = "我收藏的商铺-商铺列表", notes = "我收藏的商铺-商铺列表")
    @RequestMapping(value = "/shopCollectedList/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Page<ShopCollectedCustomerDTO>> shopCollectedList(
            @ApiParam(name="dto",value="请求参数", required=false)@RequestBody(required = false) ShopCollectedQuery query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return shopAO.shopCollectedList(query);
    }
    @ApiOperation(value = "我收藏的商铺-取消收藏", notes = "我收藏的商铺-取消收藏")
    @RequestMapping(value = "/cancelShopCollected/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Boolean> cancelShopCollected(
            @ApiParam(name="dto",value="请求参数", required=true)@RequestBody JSONObject jsonObject,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return shopAO.cancelShopCollected(jsonObject.getLong("shopId"));
    }

    @ApiOperation(value = "我浏览的商铺-排名统计", notes = "我浏览的商铺-排名统计")
    @RequestMapping(value = "/myBrowseStatistic/{version:.+}", method = RequestMethod.POST)
    public ResultDO<StatisticDTO> myBrowseStatistic(
            @ApiParam(name="dto",value="请求参数", required=false)@RequestBody(required = false) JSONObject object,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return shopAO.myBrowseStatistic();
    }

    @ApiOperation(value = "我浏览的商铺-商铺列表", notes = "我浏览的商铺-商铺列表")
    @RequestMapping(value = "/shopBrowseList/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Page<ShopBrowseCustomerDTO>> shopBrowseList(
            @ApiParam(name="dto",value="请求参数", required=false)@RequestBody(required = false) ShopCollectedQuery query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return shopAO.shopBrowseList(query);
    }
    @ApiOperation(value = "我浏览的商铺-删除浏览", notes = "我浏览的商铺-deleteShopBrowse")
    @RequestMapping(value = "/deleteShopBrowse/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Boolean> deleteShopBrowse(
            @ApiParam(name="dto",value="请求参数", required=true)@RequestBody JSONObject jsonObject,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return shopAO.deleteShopBrowse(jsonObject.getLong("shopId"));
    }


    @UnLoginLimit
    @ApiOperation(value = "商铺数目统计" , notes = "商铺数目统计")
    @RequestMapping(value = "/countShopFromClient/{version:.+}",method = RequestMethod.POST)
    public ResultDO<ShopCountDTO> countShopFromClient(
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version) {
        return shopAO.countShopFromClient();
    }

    @UnLoginLimit
    @ApiOperation(value = "商铺详情" , notes = "商铺详情")
    @RequestMapping(value = "/queryShopDetail/{version:.+}",method = RequestMethod.POST)
    public ResultDO<ShopDetailDTO> queryShopDetail(
            @ApiParam(name="shopId",value="商铺id", required=true)@RequestBody JSONObject jsonObject,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version) {
        return shopAO.queryShopDetail(jsonObject.getLong("shopId"));
    }

    @ApiOperation(value = "商铺纠错" , notes = "商铺纠错")
    @RequestMapping(value = "/createCorrect/{version:.+}",method = RequestMethod.POST)
    public ResultDO<Boolean> createCorrect(
            @ApiParam(name="dto",value="请求参数", required=true)@RequestBody CustomerCorrectDTO dto,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version) {
        return shopAO.createCorrect(dto);
    }

    /*@ApiOperation(value = "街铺详情-预约看铺" , notes = "街铺详情-预约看铺")
    @RequestMapping(value = "/createVisit/{version:.+}",method = RequestMethod.POST)
    public ResultDO<Long> createVisit(
            @ApiParam(name="visitInfoReqDTO",value="请求参数", required=true)@RequestBody VisitInfoReqDTO visitInfoReqDTO,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version) {
        return shopAO.createVisit(visitInfoReqDTO);
    }*/

    @ApiOperation(value = "街铺详情-收藏商铺" , notes = "街铺详情-收藏商铺")
    @RequestMapping(value = "/createCollected/{version:.+}",method = RequestMethod.POST)
    public ResultDO<Long> createCollected(
            @ApiParam(name="collectedShop",value="请求参数", required=true)@RequestBody CustomerCollectedShop collectedShop,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version) {
        return shopAO.createCollected(collectedShop);
    }

    @ApiOperation(value = "拨打电话记录" , notes = "拨打电话记录")
    @RequestMapping(value = "/createLiaisonRecord/{version:.+}",method = RequestMethod.POST)
    public ResultDO<Boolean> createLiaisonRecord(
            @ApiParam(name="customerLiaison",value="请求参数", required=true)@RequestBody CustomerLiaison customerLiaison,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version) {
        return shopAO.createLiaisonRecord(customerLiaison);
    }

    @ApiOperation(value = "商铺照片" , notes = "商铺照片")
    @RequestMapping(value = "/queryShopImg/{version:.+}",method = RequestMethod.POST)
    public ResultDO<List<ShopImgDTO>> queryShopImg(
            @ApiParam(name="shopId",value="商铺id", required=true)@RequestBody JSONObject jsonObject,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version) {
        return shopAO.queryShopImg(jsonObject.getLong("shopId"));
    }

    @UnLoginLimit
    @ApiOperation(value = "根据二维码获取商铺id" , notes = "根据二维码获取商铺id")
    @RequestMapping(value = "/getShopId/{version:.+}",method = RequestMethod.POST)
    public ResultDO<Long> getShopId(
            @ApiParam(name="shopCode",value="二维码编号", required=true)@RequestBody JSONObject jsonObject,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version) {
        return shopAO.getShopId(jsonObject.getString("shopCode"));
    }
}
