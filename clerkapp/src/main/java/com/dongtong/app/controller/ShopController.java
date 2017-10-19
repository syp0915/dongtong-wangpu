package com.dongtong.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.dongtong.app.annotation.UnLoginLimit;
import com.dongtong.app.ao.ShopAO;
import com.dongtong.shop.dto.*;
import com.dongtong.shop.query.ShopClerkQuery;
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

    @ApiOperation(value = "商铺筛选", notes = "商铺筛选")
    @RequestMapping(value = "/queryClerkShop/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Page<ShopClerkDTO>> queryClerkShop(
            @ApiParam(name="query",value="请求参数", required=true)@RequestBody ShopClerkQuery query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){

        return shopAO.queryShopByClerk(query);
    }

    @ApiOperation(value = "发布商铺", notes = "发布商铺")
    @RequestMapping(value = "/issueShop/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Long> issueShop(
            @ApiParam(name="query",value="目录请求参数", required=true)@RequestBody IssueShopDTO query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return shopAO.issueShop(query);
    }

    @ApiOperation(value = "修改商铺-租售信息", notes = "修改商铺-租售信息")
    @RequestMapping(value = "/updateShopRent/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Boolean> updateShopRent(
            @ApiParam(name="query",value="目录请求参数", required=true)@RequestBody IssueShopDTO query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return shopAO.updateShopRent(query);
    }
    @ApiOperation(value = "修改商铺-客户信息", notes = "修改商铺-客户信息")
    @RequestMapping(value = "/updateShopContacter/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Boolean> updateShopContacter(
            @ApiParam(name="query",value="目录请求参数", required=true)@RequestBody IssueShopDTO query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return shopAO.updateShopContacter(query);
    }

    @ApiOperation(value = "修改商铺-经营状况信息", notes = "修改商铺-经营状况信息")
    @RequestMapping(value = "/updateShopOperateState/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Boolean> updateShopOperateState(
            @ApiParam(name="query",value="目录请求参数", required=true)@RequestBody IssueShopDTO query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return shopAO.updateShopOperateState(query);
    }

    @ApiOperation(value = "修改商铺-租赁相关的信息", notes = "修改商铺-租赁相关的信息")
    @RequestMapping(value = "/updateShopRentParam/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Boolean> updateShopRentParam(
            @ApiParam(name="query",value="目录请求参数", required=true)@RequestBody IssueShopDTO query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return shopAO.updateShopRentParam(query);
    }
    @ApiOperation(value = "修改商铺-位置相关的信息", notes = "修改商铺-位置相关的信息")
    @RequestMapping(value = "/updateShopSite/{version:.+}", method = RequestMethod.POST)
    public ResultDO<DistrictBlockDTO> updateShopSite(
            @ApiParam(name="query",value="目录请求参数", required=true)@RequestBody IssueShopDTO query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return shopAO.updateShopSite(query);
    }
    @ApiOperation(value = "修改商铺-建筑相关的信息", notes = "修改商铺-建筑相关的信息")
    @RequestMapping(value = "/updateShopBuilding/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Boolean> updateShopBuilding(
            @ApiParam(name="query",value="目录请求参数", required=true)@RequestBody IssueShopDTO query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return shopAO.updateShopBuilding(query);
    }

    @ApiOperation(value = "修改商铺-营运相关的信息", notes = "修改商铺-营运相关的信息")
    @RequestMapping(value = "/updateShopService/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Boolean> updateShopService(
            @ApiParam(name="query",value="目录请求参数", required=true)@RequestBody IssueShopDTO query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return shopAO.updateShopService(query);
    }
    @ApiOperation(value = "修改商铺-配套设施相关的信息", notes = "修改商铺-配套设施相关的信息")
    @RequestMapping(value = "/updateShopSupport/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Boolean> updateShopSupport(
            @ApiParam(name="query",value="目录请求参数", required=true)@RequestBody IssueShopDTO query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return shopAO.updateShopSupport(query);
    }

    @ApiOperation(value = "修改商铺-临铺信息", notes = "修改商铺-临铺信息")
    @RequestMapping(value = "/updateShopNear/{version:.+}", method = RequestMethod.POST)
    public ResultDO<List<ShopNearDetailDTO>> updateShopNear(
            @ApiParam(name="query",value="目录请求参数", required=true)@RequestBody IssueShopDTO query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return shopAO.updateShopNear(query);
    }

    @ApiOperation(value = "修改商铺-删除临铺", notes = "修改商铺-删除临铺")
    @RequestMapping(value = "/delShopNear/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Boolean> delShopNear(
            @ApiParam(name="query",value="目录请求参数", required=true)@RequestBody JSONObject jsonObject,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return shopAO.delShopNear(jsonObject.getLong("nearId"));
    }

    @ApiOperation(value = "修改商铺-查看临铺照片", notes = "修改商铺-查看临铺照片")
    @RequestMapping(value = "/shopNearImg/{version:.+}", method = RequestMethod.POST)
    public ResultDO<List<ShopImgDTO>> shopNearImg(
            @ApiParam(name="query",value="目录请求参数", required=true)@RequestBody JSONObject jsonObject,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return shopAO.shopNearImg(jsonObject.getLong("nearId"));
    }

    @ApiOperation(value = "概要详情-上架商铺", notes = "概要详情-上架商铺")
    @RequestMapping(value = "/upShop/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Boolean> upShop(
            @ApiParam(name="shopId",value="目录请求参数", required=true)@RequestBody JSONObject jsonObject,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return shopAO.putAwayShop(jsonObject.getLong("shopId"));
    }

    @ApiOperation(value = "概要详情-下架商铺", notes = "概要详情-下架商铺")
    @RequestMapping(value = "/downShop/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Boolean> downShop(
            @ApiParam(name="shopUndoDTO",value="目录请求参数", required=true)@RequestBody ShopUndoDTO shopUndoDTO,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return shopAO.unDoShop(shopUndoDTO);
    }


    @ApiOperation(value = "商铺数目统计" , notes = "商铺数目统计")
    @RequestMapping(value = "/countShopFromService/{version:.+}",method = RequestMethod.POST)
    public ResultDO<ShopCountDTO> countShopFromService(
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version) {
         return shopAO.countShopFromService();
    }

    @ApiOperation(value = "商铺详情" , notes = "商铺详情")
    @RequestMapping(value = "/queryShopDetail/{version:.+}",method = RequestMethod.POST)
    public ResultDO<ShopDetailDTO> queryShopDetail(
            @ApiParam(name="shopId",value="商铺id", required=true)@RequestBody JSONObject jsonObject,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version) {
        return shopAO.queryShopDetail(jsonObject.getLong("shopId"));
    }

    @ApiOperation(value = "商铺照片" , notes = "商铺照片")
    @RequestMapping(value = "/queryShopImg/{version:.+}",method = RequestMethod.POST)
    public ResultDO<List<ShopImgDTO>> queryShopImg(
            @ApiParam(name="shopId",value="商铺id", required=true)@RequestBody JSONObject jsonObject,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version) {
        return shopAO.queryShopImg(jsonObject.getLong("shopId"));
    }

    @ApiOperation(value = "商铺认领" , notes = "商铺认领")
    @RequestMapping(value = "/claimShop/{version:.+}",method = RequestMethod.POST)
    public ResultDO<String> claimShop(
            @ApiParam(name="shopId",value="商铺id", required=true)@RequestBody JSONObject jsonObject,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version) {
        return shopAO.claimShop(jsonObject.getLong("shopId"));
    }

    @UnLoginLimit
    @ApiOperation(value = "生成商铺二维码" , notes = "生成商铺二维码")
    @RequestMapping(value = "/shopCodeGenerate/{version:.+}",method = RequestMethod.POST)
    public ResultDO<String> shopCodeGenerate(
            @ApiParam(name="query",value="请求参数", required=true)@RequestBody ShopCodeDTO query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version) {
        return shopAO.shopCodeGenerate(query);
    }

    @UnLoginLimit
    @ApiOperation(value = "生成一个未使用的二维码" , notes = "生成一个未使用的二维码")
    @RequestMapping(value = "/createShopNumber/{version:.+}",method = RequestMethod.POST)
    public ResultDO<String> createShopNumber(
            @ApiParam(name="query",value="请求参数", required=true)@RequestBody JSONObject jsonObject,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version) {
        return shopAO.createShopNumber(jsonObject.getString("cityCode"));
    }

}
