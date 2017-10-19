package com.dongtong.app.controller;

import com.dongtong.app.annotation.UnLoginLimit;
import com.dongtong.app.ao.FortuneAO;
import com.dongtong.customer.dto.resp.ShopNameIsGoodRespDTO;
import com.dongtong.customer.query.ShopNameIsGoodReqQuery;
import com.shfc.common.result.ResultDO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/10 下午5:42.
 */
@Api(tags = "FortuneController", description = "发送验证接口")
@RestController
@RequestMapping(value = "/api/user/fortune",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class FortuneController {

    @Autowired(required = false)
    private FortuneAO fortuneAO;

    @UnLoginLimit
    @ApiOperation(value = "店名测吉凶", notes = "店名测吉凶")
    @RequestMapping(value = "/shopNameIsGood/{version:.+}", method = RequestMethod.POST)
    public ResultDO<ShopNameIsGoodRespDTO> shopNameIsGood(
            @ApiParam(name="query",value="请求参数", required=true)@RequestBody ShopNameIsGoodReqQuery query,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return fortuneAO.shopNameIsGood(query);
    }
}
