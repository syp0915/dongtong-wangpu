package com.dongtong.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.dongtong.app.ao.ShopFollowAO;
import com.dongtong.shop.dto.ShopFollowDTO;
import com.dongtong.shop.dto.ShopFollowInfoDTO;
import com.shfc.common.result.ResultDO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Package com.dongtong.app.controller.ShopFollowController
 * @Description: 商铺接口
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/5/4 17:00
 * version V1.0.0
 */
@Api(tags = "ShopFollowController", description = "商铺跟进接口")
@RestController
@RequestMapping(value = "/api/follow",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ShopFollowController {

    @Autowired
    private ShopFollowAO shopFollowAO;

    @ApiOperation(value = "添加跟进", notes = "添加跟进")
    @RequestMapping(value = "/add/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Long> add(
            @ApiParam(name="dto",value="请求参数", required=true)@RequestBody ShopFollowInfoDTO dto,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){

        return shopFollowAO.saveFollow(dto);
    }

    @ApiOperation(value = "根据商铺id获取跟进列表", notes = "根据商铺id获取跟进列表")
    @RequestMapping(value = "/getByShopId/{version:.+}", method = RequestMethod.POST)
    public ResultDO<List<ShopFollowDTO>> getByShopId(
            @ApiParam(name="shopId",value="商铺id", required=true)@RequestBody JSONObject jsonObject,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){

        return shopFollowAO.getFollowListByShopId(jsonObject.getLong("shopId"));
    }

    @ApiOperation(value = "删除跟进信息", notes = "删除跟进信息")
    @RequestMapping(value = "/delete/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Boolean> delete(
            @ApiParam(name="id",value="跟进id", required=true)@RequestBody JSONObject jsonObject,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){

        return shopFollowAO.deleteFollowById(jsonObject.getLong("id"));
    }
}
