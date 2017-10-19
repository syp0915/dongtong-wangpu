package com.dongtong.app.controller;

import com.dongtong.app.annotation.UnLoginLimit;
import com.dongtong.app.ao.ClerkHintAO;
import com.dongtong.clerk.dto.ClerkHintDTO;
import com.dongtong.clerk.dto.resp.ReleaseTotalRespDTO;
import com.shfc.common.result.ResultDO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author sunyaping
 * @Package com.dongtong.app.controller
 * @Description :线索
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * @date 2017-05-12 16:48
 * version V1.0.0
 **/
@Api(tags = "ClerkHintController", description = "线索接口")
@RestController
@RequestMapping(value = "/api/clerkHint",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ClerkHintController {

    @Autowired
    private ClerkHintAO clerkHintAO;

    @ApiOperation(value = "金铺寻租", notes = "金铺寻租线索")
    @RequestMapping(value = "/addClerkHint/{version:.+}", method = RequestMethod.POST)
    public ResultDO<Long> addClerkHint(
            @ApiParam(name="dto",value="筛选参数", required=true)@RequestBody ClerkHintDTO clerkHintDTO,
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return clerkHintAO.issueClue(clerkHintDTO);

    }

    @UnLoginLimit
    @ApiOperation(value = "旺铺寻租使用人数", notes = "旺铺寻租使用人数")
    @RequestMapping(value = "/getReleaseTotal/{version:.+}", method = RequestMethod.POST)
    public ResultDO<ReleaseTotalRespDTO> queryReleaseTotal(
            @ApiParam(name="version",value="版本号", required=true, defaultValue ="v1.0.0" )@PathVariable("version") String version){
        return clerkHintAO.getReleaseTotal();

    }
}
